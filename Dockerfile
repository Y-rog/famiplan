FROM eclipse-temurin:17-jre

# Crée un utilisateur et un groupe non-root avec un UID/GID fixe
RUN groupadd -g 10001 appgroup && useradd -u 10000 -g appgroup -s /bin/bash -M appuser

# Définit le répertoire de travail
WORKDIR /app

# On copie le fichier jar et on le renomme et on change son propriétaire
COPY target/famiplan-0.0.1-SNAPSHOT.jar app.jar
RUN chown appuser:appgroup /app/app.jar

USER appuser:appgroup

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]

