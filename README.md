# FamiPlan

Un tableau de bord familial pour gérer les tâches, l'agenda et la météo.

## Fonctionnalités
- Todo list partagée
- Agenda familial
- Affichage de la météo en temps réel

## Technologies
- Java 17
- Spring Boot 4.0.0
- PostgreSQL 18
- Tailwind CSS 4.3.0
- Docker Compose 5.1.3

## Démarrage

### Prérequis
- Docker
- Docker Compose
- Node.js (pour Tailwind)

### Lancer l'application
1. Créez un fichier `.env` à partir de `.env.example` et configurez vos variables.
2. Générez le CSS Tailwind : `cd src/main/resources/frontend && npm run build`.
3. Lancez les conteneurs : `docker-compose up -d --build`.

L'application est accessible à `http://localhost:8081`.

## Structure
- `src/main/java` : Code Java
- `src/main/resources/templates` : Templates Thymeleaf
- `src/main/resources/frontend` : Fichiers Tailwind
- `src/main/resources/static` : Ressources statiques   