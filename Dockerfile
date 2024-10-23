# Utiliser une image de base JDK 17
FROM openjdk:17

# Exposer le port 8089 pour accéder à l'application
EXPOSE 8089

# Ajouter le fichier JAR à l'image
ADD target/tp-foyer-5.0.0.jar app.jar

# Utiliser "ENTRYPOINT" avec UTF-8 pour démarrer l'application avec Spring Boot
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/app.jar"]
