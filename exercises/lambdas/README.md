# Лямбды

## Ссылки

* [Метод flatMap()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#flatMap-java.util.function.Function-) — преобразует массив стримов в один стрим. В качестве аргумента принимает лямбду, преобразующую текущий элемент в поток.
* [Метод toArray()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#toArray-java.util.function.IntFunction-) — возвращает массив, содержащий элементы потока.

## main/java/exercise/App.java

## Задачи

Создайте класс `App` с публичным статическим методом `duplicateValues()`. Метод принимает в качестве аргумента изображение в виде двумерного массива строк и возвращает двумерный массив, увеличенный в два раза (и по горизонтали и по вертикали).

```java
String[][] image = {
    {"*", "*", "*", "*"},
    {"*", " ", " ", "*"},
    {"*", " ", " ", "*"},
    {"*", "*", "*", "*"},
};
System.out.println(Arrays.deepToString(image)); // =>
// [
//     [*, *, *, *],
//     [*,  ,  , *],
//     [*,  ,  , *],
//     [*, *, *, *],
// ]

String[][] enlargedImage = App.enlargeArrayImage(image);
System.out.println(Arrays.deepToString(enlargedImage)); // =>

// [
//     [*, *, *, *, *, *, *, *],
//     [*, *, *, *, *, *, *, *],
//     [*, *,  ,  ,  ,  , *, *],
//     [*, *,  ,  ,  ,  , *, *],
//     [*, *,  ,  ,  ,  , *, *],
//     [*, *,  ,  ,  ,  , *, *],
//     [*, *, *, *, *, *, *, *],
//     [*, *, *, *, *, *, *, *],
// ]
```

### Подсказки

* Для преобразования потока в массив строк потребуется передать в метод toString() ссылку на конструктор массива: `String[]::new`
