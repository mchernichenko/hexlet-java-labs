# Свойства и методы (определение)

## main/java/exercise/Converter.java

1. Создайте публичный статический метод `convert()`, который переводит байты в килобайты и наоборот.

Метод принимает два аргумента. Первый аргумент - число, которое нужно перевести. Второй - строка, определяющая направление перевода. Если вторым аргументом передана строка "b", то число переводится в байты, если "Kb"—  в килобайты. Метод должен вернуть число байт или килобайт. Если направление перевода не поддерживается, функция должна вернуть 0.

```java
Converter.convert(4096, "Kb"); // 4
Converter.convert(10, "b"); // 10240
Converter.convert(100, "mb"); // 0
```

2. Создайте публичный статический метод `main()`, в котором выведите на экран количество байт в 10 килобайтах. Вывод на экран должен быть в виде строки "10 Kb = 10240 b". Для перевода килобайт в байты используйте написанный метод `convert()`;

## main/java/exercise/Triangle.java

1. Создайте публичный статический метод `getSquare()`, который принимает на вход две стороны треугольника и угол между ними в градусах, а возвращает площадь этого треугольника. Площадь треугольника вычисляется, как половина произведения этих сторон умноженная на синус угла между ними. Для вычисления синуса угла используйте метод `Math.sin()`. Так как этот метод принимает угол в радианах, вам потребуется перевести градусы в радианы. Чтобы сделать это, нужно количество градусов умножить на число Пи и разделить на 180.

```java
Triangle.getSquare(10, 10, 60); // 43.3
```
2. Создайте публичный статический метод `main()`, в котором выведите на экран площадь треугольника со сторонами 4 и 5 и углом между ними в 45 градусов.

### Подсказки

* [Math](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html)
