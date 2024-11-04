# Utiliser une image légère OpenJDK basée sur Alpine Linux
FROM openjdk:17-jdk-alpine

# Installer curl pour télécharger l'artifact depuis Nexus
RUN apk add --no-cache curl

# Définir l'URL de l'artifact Nexus et les identifiants
ENV NEXUS_URL="http://192.168.50.4:8081/repository/maven-releases/tn/esprit/tp-foyer/5.0.0/tp-foyer-5.0.0.jar"
ENV NEXUS_USERNAME="admin"
ENV NEXUS_PASSWORD="admin"

# Exposer le port sur lequel l'application Spring Boot sera disponible
EXPOSE 8089

# Télécharger le fichier JAR depuis Nexus et le placer dans le conteneur
RUN curl -u $NEXUS_USERNAME:$NEXUS_PASSWORD -o /tp-foyer-5.0.0.jar "$NEXUS_URL"

# Définir le point d'entrée pour exécuter l'application avec Java
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.0.jar"]
