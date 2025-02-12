# Makefile

APP_NAME=oneshot:latest

build:
	./gradlew clean build -x test

docker-build:
	docker build -t $(APP_NAME) .

up:
	docker-compose up -d

start: stop build docker-build up

logs:
	docker-compose logs -f

stop:
	docker-compose down
