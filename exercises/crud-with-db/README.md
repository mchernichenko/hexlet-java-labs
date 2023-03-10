# CRUD with database

В этом задании нужно будет реализовать обработчики для оставшихся трех основных операций с сущностью — создания, обновления и удаления статьи. Вам предстоит поработать с SQL-запросами для обновления и удаления данных в базе.

## Ссылки

* [Вставка и модификация данных](https://ru.hexlet.io/courses/rdb-basics/lessons/sql-dml/theory_unit)
* [Ошибка Internal Server Error](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpServletResponse.html#SC_INTERNAL_SERVER_ERROR)

## src/main/java/exercise/servlet/ArticlesServlet.java

## Задачи

* Изучите содержимое методов `showArticles()` и `showArticle()`, которые выводят список всех статей и страницу просмотра конкретной статьи.

* Обратите внимание на метод `getArticleById()`, который извлекает из базы данных статью по её уникальному идентификатору. Вы можете использовать этот метод в своем решении.

* Допишите метод `createArticle()`, который добавляет новую статью в базу данных.

* Допишите метод `editArticle()`, который выводит форму редактирования данных статьи. Для удобства работы все поля формы должны быть заполнены данными статьи. Для этого данные редактируемой статьи уже передаются в шаблон. Вам нужно только получить их из базы и поместить в переменную `article` с типом `Map<String, String>`.

* Допишите метод `updateArticle()`, который обновляет данные статьи в базе.

* Допишите метод `deleteArticle()`, который выводит страницу с подтверждением удаления статьи. Текст подтверждения должен содержать название статьи, поэтому в шаблон передаются данные удаляемой статьи. Вам понадобится получить эти данные из базы и поместить в переменную `article` с типом `Map<String, String>`.

* Допишите метод  `destroyArticle()`, который удаляет статью из базы данных.

## Подсказки

* Методы для работы с базой данных, а также метод `getArticleById()` могут выбросить исключение `SQLException`. Вам потребуется обработать это исключение. Если в процессе выполнения запросов возникло исключение `SQLException`, нужно установить код ошибки 500 "Internal server error" и сделать возврат.

* При добавлении новой статьи в базу, id статьи генерируется автоматически базой данных. В SQL-запросе нужно указать только значения для полей `title` и `body`.

###Итоговые запросы
* GET host:5000/articles - (showArticles) просмотр списка статей
* GET host:5000/articles/list
* GET host:5000/articles/{id} - (showArticle) просмотр статьи
* GET host:5000/articles/new - (newArticle) добавить новую
* GET host:5000/articles/{id}/edit - (editArticle) запросить данные для формы редактирования статьи
* GET host:5000/articles/{id}/delete - (deleteArticle) запросить данные для удаления

showArticle=editArticle - т.е. по сути одинаковые

* POST host:5000/articles/list - (createArticle) создание статьи
* POST host:5000/articles/{id}/edit - (updateArticle) отредактировать запись
* POST host:5000/articles/{id}/delete - (destroyArticle) отредактировать запись