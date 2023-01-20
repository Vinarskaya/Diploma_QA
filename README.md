# Дипломный проект по профессии «Тестировщик»

## Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

### Описание приложения
*Приложение* — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:
1. Обычная оплата по дебетовой карте.
1. Уникальная технология: выдача кредита по данным банковской карты.

<img width="1055" alt="Main Page" src="https://user-images.githubusercontent.com/107788282/213795192-76ab0290-8713-404e-aa3b-4385b7326dbd.png">

Заявлена поддержка двух **СУБД**: 
* MySQL;
* PostgreSQL.

Приложение в собственной СУБД сохраняет информацию о том, успешно ли был совершён платёж и каким способом.

Само приложение не обрабатывает данные по картам, а пересылает их **банковским сервисам**:
* сервису платежей - Payment Gate;
*  кредитному сервису - Credit Gate.

Доступ к реальным банковским сервисам не даётся. В рамках дипломной работы разработан симулятор банковских сервисов, который может принимать запросы в нужном формате и генерировать ответы. Симулятор расположен в каталоге [gate-simulator](https://github.com/Vinarskaya/Diploma_QA/tree/master/gate-simulator).

### Запуск приложения 
Для запуска приложения понадобится установить следующее ПО: 
* Docker Desktop. Скачать установочный файл можно по [ссылке](https://docs.docker.com/desktop/). Далее следуя инструкции установить и запустить приложение;
* IntelliJ IDEA. Установить бесплатную версию идеи (Community version) можно с [официальной страницы](https://www.jetbrains.com/idea/download). Далее следуя инструкции установить и запустить приложение.

Шаги запуска:
1. Cклонировать репозиторий или скачать архив по [ссылке](https://github.com/Vinarskaya/Diploma_QA). Открыть проект в IntelliJ IDEA
2. Запустить необходимые базы данных (MySQL и PostgreSQL), а также NodeJS. Параметры для запуска хранятся в файле [docker-compose.yml](https://github.com/Vinarskaya/Diploma_QA/blob/master/docker-compose.yml). Для запуска необходимо ввести в терминале команду:
  `docker-compose up`

3. Само приложение веб-сервиса расположено в файле [aqa-shop.jar](https://github.com/Vinarskaya/Diploma_QA/blob/master/artifacts/aqa-shop.jar) и запускается на порту 8080 одним из следующих способов:
* в новой вкладке терминала ввести `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar` - для MySQL;
* в новой вкладке терминала ввести `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar` - для PostgreSQL.

### Запуск тестов
В новой вкладке терминала ввести команду в зависимости от запущенной БД:
* `./gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app` - для MySQL
* `./gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app` - для PostgreSQL

### Отчёт по итогам тестирования - Allure
В новой вкладке терминала ввести команду: `gradlew allureServe`

Сгенерированный отчет откроется в браузере автоматически.

<img width="728" alt="Снимок экрана Allure" src="https://user-images.githubusercontent.com/107788282/213795302-baf262ee-4577-4b93-8f03-6600d25e31e2.png">

*При необходимости можно перезагрузить приложение/тесты. Для этого необходимо закрыть ранее открытые вкладки терминала или нажать в них Ctrl+С*

## Документация
* План автоматизации тестирования - [Plan.md](https://github.com/Vinarskaya/Diploma_QA/blob/master/documents/Plan.md)
* Отчётные документы по итогам тестирования - [Issue](https://github.com/Vinarskaya/Diploma_QA/issues), [Report.md](https://github.com/Vinarskaya/Diploma_QA/blob/master/documents/Report.md)
* Отчётный документ по итогам автоматизации - [Summary.md](https://github.com/Vinarskaya/Diploma_QA/blob/master/documents/Summary.md)

