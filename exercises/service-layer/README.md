# Service layer

Service layer – это паттерн проектирования, который помогает инкапсулировать код, отвечающий за бизнес логику вашего приложения, и сделать его доступным из разных мест.
В этом задании мы создадим приложение, которое будет показывать погоду в различных городах мира. Для организации кода будем использовать паттерн Service layer

## Ссылки

* [Урок по архитектуре проекта](https://ru.hexlet.io/courses/js-ddd/lessons/architecture/theory_unit)
* [Получение запроса из имени метода репозитория](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)

## src/main/java/exercise/repository/CityRepository.java

## Задачи

* Добавьте в интерфейс репозитория метод, который позволит извлечь все города, имя которых начинается с указанных символов без учета регистра. Например, при вызове метода с префиксом "б", должны быть найдены такие города, как Берлин, Багдад и так далее.

* Добавьте в интерфейс репозитория метод, который позволит извлечь все города и отсортировать их по имени в прямом порядке.

## src/main/java/exercise/service/WeatherService.java

## Задачи

* Реализуйте логику работы сервиса `WeatherService`. Создайте класс, объект которого умеет запрашивать данные о погоде из интернета. Информацию о погоде по городу можно извлечь, отправив GET запрос на URL *http://weather/api/v2/cities/имя_города"*. Данные с сервера возвращаются в виде JSON:

  ```json
  {
      "name": "Berlin",
      "temperature": "10",
      "cloudy": "clear",
      "wind": "5",
      "humidity": "65"
  }
  ```

  Для получения данных о погоде сервису нужно будет выполнить GET запрос по указанному URL. Для выполнения подобных запросов понадобится http-клиент, библиотека, которая сама формирует правильный http-запрос и возвращает ответ. Для упрощения работы в этом задании мы будем использовать фейковый клиент. Этот клиент имеет такой же интерфейс, как и реальный, и тоже возвращает данные в виде JSON, но не выполняет реальных запросов.

  Пример того, как выполняется GET запрос при помощи клиента:

  ```java
  HttpClient client = new HttpClient();

  // Выполняем GET запрос и получаем ответ
  String responce = client.get("http://weather/api/v2/cities/Berlin")

  // responce содержит данные погоды в виде JSON
  ```

## src/main/java/exercise/controller/CityController.java

## Задачи

* Создайте в контроллере метод, который обрабатывает GET запросы по адресу */cities/{id}* и выводит информацию о конкретном городе и о погоде в нём в виде JSON. Например, запрос на адрес */cities/1* должен вернуть такие данные:

  ```json
  {
      "name":"San Simon",
      "temperature":"25",
      "cloudy":"Partly cloudy",
      "humidity":"90",
      "wind":"13"
  }
  ```

* Создайте в контроллере метод, который обрабатывает GET запросы по адресу */search* и возвращает список городов. Метод должен поддерживать фильтрацию по названию города. Метод должен возвращать список всех городов, имя которых начинается с переданного префикса без учёта регистра. Данные для фильтрации передаются в query string. Например, GET запрос на адрес */search?name=be* должен вернуть все города, название которых начинается со строки "be", регистр при этом не учитывается. Возвращаемые данные должны содержать имя города и температуру в нём:

  ```sh
  GET  /search?name=ca
  ```

  ```json
  [
      {
          "temperature":"1",
          "name":"Capas"
      },
      {
          "temperature":"33",
          "name":"Catia La Mar"
      },
      {
          "temperature":"32",
          "name":"Caramay"
      }
  ]
  ```

  Если условия фильтрации не переданы, должен вернуться полный список, отсортированный по имени города в прямом порядке.

  ```sh
  GET  /search
  ```

  ```json
  [
      {
          "temperature":"1",
          "name":"Agoncillo"
      },
      {
          "temperature":"8",
          "name":"Al Qadarif"
      },
      {
          "temperature":"35",
          "name":"Antas"
      },
       <!-- и так далее -->
  ```

## Деплой

## Задачи

* Выполните деплой получившегося приложения на Heroku.
* Откройте задеплоенное приложение в браузере и убедитесь, что оно работает
