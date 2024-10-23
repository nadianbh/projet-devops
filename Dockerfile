# Utiliser une image légère OpenJDK basée sur Alpine Linux
FROM openjdk:17-jdk-alpine

# Définir une variable d'environnement pour le port
ENV PORT=8089

# Exposer le port sur lequel l'application Spring Boot sera disponible
EXPOSE $PORT

# Copier votre fichier JAR généré dans le conteneur
COPY target/tp-foyer-5.0.0.jar /tp-foyer-5.0.0.jar

# Définir le point d'entrée pour exécuter l'application avec Java
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/tp-foyer-5.0.0.jar"]
