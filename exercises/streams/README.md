# Стримы

## Ссылки

* [Класс Collectors](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Collectors.html#counting())

## src/main/java/exercise/App.java

## Задачи

Создайте класс `App` c публичным статическим методом `getCountOfFreeEmails()`. Метод принимает в качестве аргумента список `ArrayList` емейлов, а возвращает общее количество емейлов, расположенных на бесплатных доменах. Бесплатные домены: gmail.com, yandex.ru и hotmail.com.

```java
String[] emails = {
  "info@gmail.com",
  "info@yandex.ru",
  "mk@host.com",
  "support@hexlet.io",
  "info@hotmail.com",
  "support.yandex.ru@host.com"
};

ArrayList<String> emailsList = new ArrayList<>(Arrays.asList(emails));
App.getCountOfFreeEmails(emailsList); // 3
```

## Подсказки

* Для подсчета количества элементов в стриме можно воспользоваться методом `collect()`, передав туда `Collectors.counting()` в качестве коллектора.
