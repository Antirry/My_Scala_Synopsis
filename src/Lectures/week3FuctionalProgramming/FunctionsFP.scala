package Lectures.week3FuctionalProgramming

object FunctionsFP extends App {

  /*
  val a = f(x)
  val b = y(a)
  val c = z(b)

  Такой стиль дает ряд преимуществ:
   так вы знаете, что а - это всегда f(x),
    поэтому b можно представить как y(f(x)).
     Аналогичные действия можно проделать и с c.

  Так что готовьтесь к тому, что ваш код будет напоминать следующий:

  val c = z(y(f(x)))
 */

  /*

  Итак, пора начинать мыслить функционально и привыкать к тому, что теперь мы будем:

      1. использовать функции в качестве параметров
      2. возвращать функции из функций

 */
}


object Usual_Functions extends App {

/*

  Нам потребовалась функция умножения числа на константу.
   Вспомнив ООП, где мы работаем с классами и их экземплярами, можно сообразить вот такой код:

 */

  class Multiplication {
    def multiply(x: Int): Int = x * 2
  }

/*

Применив обобщенные классы, снять ограничения в использовании типов:

trait - обмен между классами информацией о структуре и полях.

 */

  trait Multiplication1[A, B] {
    def multiply(x: A): B
  }

/*

  Есть еще apply

  Метод apply в Scala - это метод,

   который позволяет вызывать экземпляры класса, как если бы они были функциями.

   Это сокращенное обозначение для вызова метода apply экземпляра вместо явного вызова метода экземпляра.

 */

  trait Multiplicaion2[A, B] {
    def apply(x: A): B
  }

/*

 Сокращение до применения одной переменной:

 слово override используется для указания того,
  что метод в подклассе предназначен для переопределения соответствующего метода в родительском классе или трейте.

 Когда метод переопределяется, он предоставляет новую реализацию метода,
  которая заменяет реализацию, унаследованную от родительского класса.

 */


  val res = new Multiplicaion2[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

  println(res(2))

/*

В нашем распоряжении Function0, Function1, Function2, Function3 и так до Function22 включительно.
 Числа 0, 1, 2, 3, ..., 22 в конце слова Function - указывают на количество задаваемых параметров.

  C одним передаваемым параметром прекрасно подойдет Function1[A, B]

 */

  val res1 = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

/*

  Если два параметра, то тут уже пригодится Function2

  (в качестве напоминания: третий Int в скобках у Function2
  - обозначает тип возвращаемого значения):

*/

  val product = new Function2[Int, Int, Int] {
    override def apply(x: Int, y: Int): Int = x * y
  }

  println(product(3, 4)) // выводит 12

}

object Anonymous_functions_or_lambda_functions extends App {

/*

  Синтаксический сахар -> замена Function2[Int, Int, Int] на (Int, Int) => Int

https://drive.google.com/file/d/1F0jT343RGUwD9JjbWDQZMJ4ji34ZMVA3/view?usp=sharing

 */

  val product = new ((Int, Int) => Int) {
    override def apply(x: Int, y: Int): Int = x * y
  }

/*

  Способы написания анонимных функций ->

 */

  val product1 = (x: Int, y: Int) => x * y
  val product2: (Int, Int) => Int = (x, y) => x * y

/*
  Последний метод позволяет компилятору самому подставить переменные вместо _.
   Удобно, конечно, но тут будьте внимательны - не напутайте с передаваемыми переменными.
 */

  val product3: (Int, Int) => Int = _ * _

  val res = new Function[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

  val res2 = (x: Int) => x * 2
  val res3: Int => Int = x => x * 2
  val res4 = {(x: Int) => x * 2}
  val res5: Int => Int = _ * 2
}

object Task_15_1 extends App {

  val noParams = () => true

  println(noParams) //  -  lectures.week3fp.Functions$$$Lambda$24/0x00000001000c3840@2de8284b
  println(noParams()) //  -  true

/*

ВЫВОД:
  при обращении к лямбда-функциям обязательно
   использовать скобки - иначе получите саму функцию, а не результат ее выполнения
 */

}

object Task_15_2 extends App {

  val sum = new Function2[Int, Int, Int] {
    def apply(a: Int, b: Int): Int = a + b
  }

  val sum0: (Int, Int) => Int = _ + _

/*
    Какой код на самом деле преобразуется компилятором в следующий:
 */

  val sum1 = (a: Int, b: Int) => a+b

}

object Task_15_3 extends App {

/*

Напишите анонимную функцию, вычисляющую длину поданной ей на вход строки.

 */

  val strlen: String => Int = _.length

  val length = strlen("HEYYYYYYYYYYYYYYYYYYYYYYYYYYYYY")
  println(length)
}