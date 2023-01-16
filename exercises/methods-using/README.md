# Свойства и методы (использование)

## main/java/exercise/Card.java

В методе `printHiddenCard()` в переменной `cardNumber` в виде строки находится номер кредитной карты, состоящий из 16 цифр.
Выведите на экран скрытую версию номера, которая может использоваться на сайте для отображения. Если исходная карта имела номер _2034399002125581_, то скрытая версия выглядит так _\*\*\*\*5581_. Другими словами, нужно заменить первые 12 символов на звездочки. Количество звездочек указано в переменной `starsCount`.

```java
Card.printHiddenCard("4567214587350971", 4) // => "****0971"
Card.printHiddenCard("4567214587350971", 3) // => "***0971"
```

## main/java/exercise/Sentence.java

* В методе `printSentence()` выведите на экран предложение, которое находится в переменной `sentence`. Если предложение восклицательное, выведите его в верхнем регистре, если нет — в нижнем. Предложение считается восклицательным, если у него в конце стоит восклицательный знак.


```java
Sentence.printSentence("HellO, World!"); // => "HELLO, WORLD!"
Sentence.printSentence("HellO, World"); // => "hello, world"
```

### Подсказки

* [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)
* [Метод format](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#format-java.lang.String-java.lang.Object...-)