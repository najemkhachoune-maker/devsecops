# Étape 1 : utiliser une image Java officielle
FROM eclipse-temurin:17-jdk-alpine

# Étape 2 : définir le dossier de travail dans le container
WORKDIR /app

# Étape 3 : copier le fichier JAR dans le container
COPY target/notification-service-0.0.1-SNAPSHOT.jar app.jar

# Étape 4 : exposer le port 8080 (Spring Boot)
EXPOSE 8080

# Étape 5 : lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
