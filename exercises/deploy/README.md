# Деплой

В этом домашнем задании мы научимся обрабатывать строку запроса и выполнять деплой приложения. Деплой — процесс выкладки приложения на сервер (или сервера). В результате к приложению можно будет доступ через браузер. Для выполнения домашнего задания понадобятся утилиты *git* и *heroku* и зарегистрированный аккаунт на [heroku](https://www.heroku.com/).

## Ссылки

* [Урок Передача данных query string](https://ru.hexlet.io/courses/http_protocol/lessons/query_string/theory_unit)
* [Установка утилиты Heroku](https://devcenter.heroku.com/articles/heroku-cli#download-and-install)
* [Сайт heroku](https://www.heroku.com/)
* [Деплой на heroku](https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat#deploy-to-heroku)

## servlet/CompaniesServlet.java

## Задачи

* Реализуйте логику метода `doGet()`, который должен отображать список компаний в зависимости от параметров запроса. Список всех доступных компаний генерируется методом `getCompanies()` (уже импортирован в модуль). При выводе списка компаний, сортировать их дополнительно не требуется.

  * Если строка запроса содержит параметр `search`, то должен выводиться список только тех компаний, которые содержат в имени переданное значение.

    ```text
    http://localhost:5000/companies?search=ov

    Cartwright-Glover and Sons
    Hermann, Macejkovic and Brekke Group
    ```

  * Если значение параметра `search` отсутствует в именах компаний, то должна выводиться строка `Companies not found`.

    ```text
    http://localhost:5000/companies?search=tl

    Companies not found
    ```

  * В случае отсутствия в строке запроса параметра `search` или если значением является пустая строка — должны выводиться все компании. Подробнее смотрите примеры.

    ```text
    http://localhost:5000/companies?search=
    http://localhost:5000/companies

    <список полностью всех компаний>
    ```

## Подсказки

* Для работы со строкой запроса вам понадобится использовать методы [getParameter()](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/ServletRequest.html#getParameter-java.lang.String-) и [getQueryString()](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpServletRequest.html#getQueryString--).

## App.java

## Задачи

* Зарегистрируйте в контейнере tomcat сервлет `CompaniesServlet`, так чтобы он обрабатывал запросы по пути `/companies`. Для проверки работы приложения запустите сервер, используя команду `make start`. Приложение в браузере будет доступно по адресу *http://localhost:5000*

## build.gradle

## Задачи

* В процессе деплоя heroku с помощью gradle будет запускать задачу *stage*, которую необходимо создать. В простейшем случае задача *stage* должна выполнять установку приложения (задачу *install*), но без запуска тестов — это можно сделать вызвав задачу *installDist*. Добавим задачу *stage* с нужной логикой:

  ```java
  task stage(dependsOn: ['clean', 'installDist'])
  installDist.mustRunAfter clean
  ```

## Procfile

## Задачи

* После успешной компиляции и сборки проекта на heroku, приложение нужно запустить. Это можно сделать если указать heroku команду которую нужно выполнить. Добавьте команду запуска исполняемого файла:

  ```sh
  web: sh build/install/deploy/bin/deploy
  ```

## Gradle Wrapper

## Задачи

* В процессе деплоя heroku автоматически определит, что используется проект gradle. Но для корректной работы, heroku необходимо использовать нужную версию gradle, иначе по умолчанию будет использоваться версия 2, которая морально устарела. Сделать это можно с помощью, так называемого Wrapper (локальной для проекта копии Gradle). Для этого выполните в директории домашнего задания команду:

  ```sh
  gradle wrapper --gradle-version 6.8.3
  ```

## Деплой

## Задачи

* Зарегистрируйте бесплатный аккаунт на [heroku](https://www.heroku.com/).
* В директории с домашним заданием выполните последовательно команды, как показано в [руководстве по деплою](https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat#deploy-to-heroku)
* После открытия задеплоенного приложения в браузере убедитесь, что оно работает.
* При сдаче домашнего задания укажите ссылку на задеплоенное приложение.
