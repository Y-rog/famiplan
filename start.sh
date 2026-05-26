#!/bin/bash

(cd src/main/resources/frontend && npm run build)
mvn clean package -DskipTests
docker compose up --build -d


