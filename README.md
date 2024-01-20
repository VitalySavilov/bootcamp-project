  BOOTCAMP-PROJECT
  
- Сборка и запуск приложения:

      docker-compose -f test-db/docker-compose.yml up -d

      .\mvnw clean package

      docker-compose up -d --build

- Для тестирования эндпоинтов подключен Swagger: http://localhost:8080/swagger-ui/index.html