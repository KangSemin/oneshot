# Makefile

ifeq ($(OS), Windows_NT)
  GRADLE_CMD = gradlew.bat
else
  GRADLE_CMD = ./gradlew
endif

.PHONY: build docker-build up start logs stop fast-build fast

APP_NAME=oneshot:latest

build:
	$(GRADLE_CMD) clean build -x test

docker-build:
	docker build -t $(APP_NAME) .

up:
	docker-compose up -d

start: stop build docker-build up

logs:
	docker-compose logs -f

stop:
	docker-compose down

fast-build:
	$(GRADLE_CMD) build -x test

fast: fast-build docker-build up
