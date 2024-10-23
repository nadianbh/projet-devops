# Utiliser une image de base JDK 17
FROM openjdk:17-alpine

# Exposer le port 8082 pour accéder à l'application
EXPOSE 8089

# Ajouter le fichier JAR à l'image
ADD target/tp-foyer-5.0.0.jar app.jar

# Utiliser "entrypoint" pour démarrer l'application avec Spring Boot
ENTRYPOINT ["java","-jar","/app.jar"]
