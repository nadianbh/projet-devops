# Utiliser une image de base JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Exposer le port 8082 pour accéder à l'application
EXPOSE 8082

# Ajouter le fichier JAR à l'image
ADD target/tp-foyer-5.0.0.jar app.jar

# Utiliser "entrypoint" pour démarrer l'application avec Spring Boot
ENTRYPOINT ["java","-jar","/app.jar"]
