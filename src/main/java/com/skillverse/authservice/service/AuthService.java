package com.skillverse.authservice.service;

import com.skillverse.authservice.event.EventPublisher;
import com.skillverse.authservice.event.EventType;
import com.skillverse.authservice.model.User;
import com.skillverse.authservice.model.UserSession;
import com.skillverse.authservice.repository.UserSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {

    private final KeycloakService keycloakService;
    private final UserService userService;
    private final UserSessionRepository sessionRepository;
    private final EventPublisher eventPublisher;

    /**
     * Register new user
     */
    public User register(String email, String password, String firstName, String lastName) {
        // Register in Keycloak first
        String keycloakUserId = keycloakService.registerUser(email, password, firstName, lastName);
        
        // Create user in our database
        User user = User.builder()
                .keycloakUserId(keycloakUserId)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .isActive(true)
                .isEmailVerified(false)
                .gdprConsentDate(LocalDateTime.now())
                .build();
        
        return userService.createUser(user);
    }

    /**
     * Login user and create session
     */
    public Map<String, Object> login(String email, String password) {
        // Authenticate with Keycloak
        Map<String, Object> tokens = keycloakService.authenticateUser(email, password);
        
        // Get user from database
        User user = userService.getUserByEmail(email);
        
        // Create session
        UserSession session = UserSession.builder()
                .user(user)
                .token((String) tokens.get("access_token"))
                .refreshToken((String) tokens.get("refresh_token"))
                .isActive(true)
                .expiresAt(LocalDateTime.now().plusDays(1))
                .build();
        sessionRepository.save(session);
        
        // Update last login
        userService.updateLastLogin(user.getId());
        
        // Publish event
        eventPublisher.publishUserEvent(
            EventType.USER_LOGIN, 
            user.getId(), 
            user.getEmail(), 
            Map.of("loginTime", LocalDateTime.now())
        );
        
        log.info("User logged in: {}", email);
        
        return Map.of(
            "accessToken", tokens.get("access_token"),
            "refreshToken", tokens.get("refresh_token"),
            "expiresIn", tokens.get("expires_in"),
            "userId", user.getId()
        );
    }

    /**
     * Refresh access token
     */
    public Map<String, Object> refresh(String refreshToken) {
        // Validate session
        UserSession session = sessionRepository.findByRefreshTokenAndIsActiveTrue(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
        
        // Get new tokens from Keycloak
        Map<String, Object> newTokens = keycloakService.refreshToken(refreshToken);
        
        // Update session
        session.setToken((String) newTokens.get("access_token"));
        session.setRefreshToken((String) newTokens.get("refresh_token"));
        session.setExpiresAt(LocalDateTime.now().plusDays(1));
        sessionRepository.save(session);
        
        return Map.of(
            "accessToken", newTokens.get("access_token"),
            "refreshToken", newTokens.get("refresh_token"),
            "expiresIn", newTokens.get("expires_in")
        );
    }

    /**
     * Logout user and invalidate session
     */
    public void logout(String token) {
        UserSession session = sessionRepository.findByTokenAndIsActiveTrue(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        
        // Invalidate session
        session.setIsActive(false);
        sessionRepository.save(session);
        
        // Logout from Keycloak
        keycloakService.logoutUser(session.getRefreshToken());
        
        // Publish event
        eventPublisher.publishUserEvent(
            EventType.USER_LOGOUT, 
            session.getUser().getId(), 
            session.getUser().getEmail(), 
            Map.of("logoutTime", LocalDateTime.now())
        );
        
        log.info("User logged out: {}", session.getUser().getEmail());
    }

    /**
     * Validate token for other microservices
     */
    public boolean validateToken(String token) {
        return sessionRepository.findByTokenAndIsActiveTrue(token).isPresent();
    }
}