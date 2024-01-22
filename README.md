  BOOTCAMP-PROJECT
  
- В проекте применены технологии: Java 17, Hibernate 6, Spring 6, Spring Boot 3,
  Docker, Docker Compose, Liquibase, MySQL 8, DBUnit, JUnit 5, Mockito, Log4j2, Maven, Lombok.

- Проект содержит 3 модуля: data, service и web. Модуль web выполнен с использованием Spring Boot.


- Для тестов необходимо запустить тестовую БД:

      docker-compose -f test-db/docker-compose.yml up -d

  Таблицы инициализируются автоматически из файла db.init.sql при запуске БД. Для заполнения 
таблиц тестовым набором данных в процессе тестирования используется DBUnit.


- Запуск тестов:

      .\mvnw test


- Сборка и запуск приложения:

      .\mvnw clean package -DskipTests

      docker-compose up -d --build

- Для тестирования эндпоинтов подключен Swagger: http://localhost:8080/swagger-ui/index.html


- API содержит 3 эндпоинта:

      /api/v1/users [GET] - постраничный вывод записей по 10, метод принимает номер страницы

      /api/v1/users [POST] - создание пользователя. Данные валидируются, проверяется уникальность 

      email. Ошибки обрабатываются и логируются
     
      /api/v1/users/all [GET] - вывод всех пользователей