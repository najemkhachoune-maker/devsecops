package com.skillverse.authservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "keycloak_user_id", unique = true)
    private String keycloakUserId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "profile_picture_url", columnDefinition = "TEXT")
    private String profilePictureUrl;

    // Flags
    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "is_email_verified")
    @Builder.Default
    private Boolean isEmailVerified = false;

    @Column(name = "has_learner_profile")
    @Builder.Default
    private Boolean hasLearnerProfile = false;

    @Column(name = "has_teacher_profile")
    @Builder.Default
    private Boolean hasTeacherProfile = false;

    // Gamification
    @Column(name = "learner_level")
    @Builder.Default
    private Integer learnerLevel = 1;

    @Column(name = "teacher_level")
    @Builder.Default
    private Integer teacherLevel = 1;

    @Column(name = "available_tokens")
    @Builder.Default
    private Integer availableTokens = 0;

    @Column(name = "total_tokens_earned")
    @Builder.Default
    private Integer totalTokensEarned = 0;

    // Statistics
    @Column(name = "quests_completed")
    @Builder.Default
    private Integer questsCompleted = 0;

    @Column(name = "students_taught")
    @Builder.Default
    private Integer studentsTaught = 0;

    @Column(name = "average_rating_as_learner", precision = 3, scale = 2)
    @Builder.Default
    private BigDecimal averageRatingAsLearner = BigDecimal.ZERO;

    @Column(name = "average_rating_as_teacher", precision = 3, scale = 2)
    @Builder.Default
    private BigDecimal averageRatingAsTeacher = BigDecimal.ZERO;

    // RGPD
    @Column(name = "gdpr_consent_date")
    private LocalDateTime gdprConsentDate;

    @Column(name = "data_anonymized")
    @Builder.Default
    private Boolean dataAnonymized = false;

    @Column(name = "encrypted_personal_data", columnDefinition = "TEXT")
    private String encryptedPersonalData;

    // Timestamps
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKeycloakUserId() {
        return keycloakUserId;
    }

    public void setKeycloakUserId(String keycloakUserId) {
        this.keycloakUserId = keycloakUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public Boolean getHasLearnerProfile() {
        return hasLearnerProfile;
    }

    public void setHasLearnerProfile(Boolean hasLearnerProfile) {
        this.hasLearnerProfile = hasLearnerProfile;
    }

    public Boolean getHasTeacherProfile() {
        return hasTeacherProfile;
    }

    public void setHasTeacherProfile(Boolean hasTeacherProfile) {
        this.hasTeacherProfile = hasTeacherProfile;
    }

    public Integer getLearnerLevel() {
        return learnerLevel;
    }

    public void setLearnerLevel(Integer learnerLevel) {
        this.learnerLevel = learnerLevel;
    }

    public Integer getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(Integer teacherLevel) {
        this.teacherLevel = teacherLevel;
    }

    public Integer getAvailableTokens() {
        return availableTokens;
    }

    public void setAvailableTokens(Integer availableTokens) {
        this.availableTokens = availableTokens;
    }

    public Integer getTotalTokensEarned() {
        return totalTokensEarned;
    }

    public void setTotalTokensEarned(Integer totalTokensEarned) {
        this.totalTokensEarned = totalTokensEarned;
    }

    public Integer getQuestsCompleted() {
        return questsCompleted;
    }

    public void setQuestsCompleted(Integer questsCompleted) {
        this.questsCompleted = questsCompleted;
    }

    public Integer getStudentsTaught() {
        return studentsTaught;
    }

    public void setStudentsTaught(Integer studentsTaught) {
        this.studentsTaught = studentsTaught;
    }

    public BigDecimal getAverageRatingAsLearner() {
        return averageRatingAsLearner;
    }

    public void setAverageRatingAsLearner(BigDecimal averageRatingAsLearner) {
        this.averageRatingAsLearner = averageRatingAsLearner;
    }

    public BigDecimal getAverageRatingAsTeacher() {
        return averageRatingAsTeacher;
    }

    public void setAverageRatingAsTeacher(BigDecimal averageRatingAsTeacher) {
        this.averageRatingAsTeacher = averageRatingAsTeacher;
    }

    public LocalDateTime getGdprConsentDate() {
        return gdprConsentDate;
    }

    public void setGdprConsentDate(LocalDateTime gdprConsentDate) {
        this.gdprConsentDate = gdprConsentDate;
    }

    public Boolean getDataAnonymized() {
        return dataAnonymized;
    }

    public void setDataAnonymized(Boolean dataAnonymized) {
        this.dataAnonymized = dataAnonymized;
    }

    public String getEncryptedPersonalData() {
        return encryptedPersonalData;
    }

    public void setEncryptedPersonalData(String encryptedPersonalData) {
        this.encryptedPersonalData = encryptedPersonalData;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
