# Дженерики

## main/java/exercise/App.java

## Задачи

* Создайте класс `App`с публичным статическим методом `findWhere()`, который принимает следующие аргументы:

  * Список `ArrayList` книг. Каждая книга представляет собой словарь `HashMap`, в котором ключи и значения представлены строками
  * Словарь `HashMap` из пар ключей и значений, представленных строками

  Метод должен возвращать список `ArrayList` со всеми книгами, данные которых соответствуют всем переданным парам. Если совпадений нет, метод должен вернуть пустой список.

```java
ArrayList<HashMap> books = new ArrayList<>();

HashMap<String, String> book1 = new HashMap<>(
Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611")
);
HashMap<String, String> book2 = new HashMap<>(
    Map.of("title", "Book of Fooos", "author", "FooBar", "year", "1111")
);
HashMap<String, String> book3 = new HashMap<>(
    Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611")
);
HashMap<String, String> book4 = new HashMap<>(
    Map.of("title", "Book of Foos Barrrs", "author", "FooBar", "year", "2222")
);
HashMap<String, String> book5 = new HashMap<>(
    Map.of("title", "Still foooing", "author", "FooBar", "year", "3333")
);

books.add(book1);
books.add(book2);
books.add(book3);
books.add(book4);
books.add(book5);

HashMap<String, String> where = new HashMap<>(Map.of("author", "Shakespeare", "year", "1611"));

ArrayList<HashMap> result = App.findWhere(books, where);

System.out.println(result); // =>
// [
//     {title=Cymbeline, year=1611, author=Shakespeare},
//     {title=The Tempest, year=1611, author=Shakespeare}
// ]
```
