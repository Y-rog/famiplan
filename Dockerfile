# Étape 1 — Générer le CSS Tailwind
FROM node:18 AS frontend
WORKDIR /app
COPY src/main/resources/frontend/package*.json ./
RUN npm ci --no-cache
COPY src/main/resources/frontend/ .
RUN npm run build

# Étape 2 — Compiler le jar avec Maven
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
COPY --from=frontend /static/css/main.css src/main/resources/static/css/main.css
RUN mvn clean package -DskipTests

# Étape 3 — Lancer l'app
FROM eclipse-temurin:17-jre
RUN groupadd -g 10001 appgroup && useradd -u 10000 -g appgroup -s /bin/bash -M appuser
WORKDIR /app
COPY --from=builder /app/target/famiplan-0.0.1-SNAPSHOT.jar app.jar
RUN chown appuser:appgroup /app/app.jar
USER appuser:appgroup
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]

