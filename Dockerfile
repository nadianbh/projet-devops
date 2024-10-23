# Utiliser une image légère OpenJDK basée sur Alpine Linux
FROM openjdk:17

WORKDIR /app
# Exposer le port sur lequel l'application Spring Boot sera disponible
EXPOSE 8089

# Copier votre fichier JAR généré dans le conteneur
ADD target/*.jar /tp-foyer-5.0.0.jar

# Définir le point d'entrée pour exécuter l'application avec Java
CMD ["java", "-jar", "/tp-foyer-5.0.0.jar"]
