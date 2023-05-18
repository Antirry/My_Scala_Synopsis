package Lectures.week1basics

import scala.reflect.internal.util.TriState.True
import scala.runtime.BoxesRunTime
import scala.runtime.BoxesRunTime.multiply

object Functions extends App {
  /*
  Функция создается следующим образом:

  1) Начинается все с ключевого слова def
  2) За которым следует имя функции
  3) В круглых скобках (при необходимости) указываются параметры и их типы
  4) После скобок ставится двоеточие и конкретизируется тип возвращаемого значения
  5) Наконец - ставится знак равенства и прописывается тело функции
   */

  def aPerson(name: String, surname: String): String = {
    s"$name $surname"
  }

  println(aPerson("John", "Smith"))

/*
  возможно прописать print в теле самой функции,
  просто для этого необходимо изменить тип возвращаемого значения на Unit:
 */

  def bPerson(name: String, surname: String): Unit = println(s"$name $surname")
  bPerson("John" , "Smith")
}

object ParameterlessFunction extends App {
  /*
  Здесь вам стоит запомнить, что если создается функция без параметров,
  то ее вызов возможен двумя способами:
   */

//  с круглыми скобками на конце
//  без круглых скобок на конце.

  /*
  В этом случае компилятор просто уведомит вас,
  что вызываемый метод - parameterless т.е.без параметров
   */

  def aParameterlessFunction(): Unit = println("Function witch no parameters")

  aParameterlessFunction()
  aParameterlessFunction

}

object FunctionDefaultParameter extends App {
  /*
  Можем задавать дефолтные значения для параметров,
    что позволит нам лишний раз не указывать параметры при вызове функции.
    Только следите за порядком аргументов.
   */

  def aFunctionWithDefaultParameter(x: Int, y: String = "Default Parameter"): String = {
    s"x = $x and y = $y"
  }

  println(aFunctionWithDefaultParameter(1)) // выводит x = 1 and y = Default Parameter

}

object FunctionCall extends App {

/*
 рядом с параметром, вызываемым по имени, печатается знак =>)
 */

/*
  Если для callByValue на экран выводятся два одинаковых значения, то для callByName значения будут разными.
    Это объясняется разницей в подходах к вычислению значений параметров.

*/

/*
  Вызов по значению подразумевает вычисление значения переданного выражения перед вызовом функции.
    Преимущество: значение вычисляется только один раз.

  Вызов по имени подразумевает вычисление значения выражения в момент его вызова в функции
    Преимущество: значение не вычисляется, если не используется в теле функции.
*/

//  Вызов по значению(call-by-value)
  def callByValue(x: Long): Unit = {
    println(s"call by value: x1 = $x")
    println(s"call by value: x2 = $x")
  }

  callByValue(System.nanoTime())

//  Вызов по имени(call-by-name)
  def callByName(x: => Long): Unit = {
    println(s"call by name: x1 = $x")
    println(s"call by name: x2 = $x")
  }

  callByName(System.nanoTime())

/*
  для callByValue мы вычислили значение System.nanoTime()
  и подставили это значение в функцию.
  Однако для callByName предварительных вычислений не делалось,
  и значение высчитывалось уже непосредственно в функции.
*/
}

object BossFunction extends App {

//  Возможность определить функцию или даже несколько
  //  функций внутри одной функции и там же ее(или их) вызвать.

  def aHelperFunction(): String = "I'm here to help"
  aHelperFunction()

}

object Task_5_1 extends App {

//  Вывод самой функции без переменной,

  def someFunc(): Int = 2 * someFunc() + 1

  def callSomeFunc(x: Int, y: => Int) = println(x)
  callSomeFunc(someFunc(), 1) // Ошибка, так как "someFunc()" она пытается вычисляться

}

object Task_5_2 extends App {

  def someFunc(): Int = 2 * someFunc() + 1
  def callSomeFunc(x: Int, y: => Int) = println(x)

  callSomeFunc(1, someFunc()) // Ошибки нет, "someFunc()" Не вычисляется из-за "=>" - вызова по имени

}

/*
Видите, не вычислять значение, если оно не используется в теле функции, бывает полезно
 */

object Task_5_3 extends App {

  // исправьте код
  def aCondition(): Boolean = if (1 < 2) true else false


/*

Правильное решение задачи с сайта, было не видно полностью код,
там был странный вызов println(someFunnc(Args(1), multiply(Args(2))))
->
    def someFunc(x: Int, y: => Int): Int = {
    if (aCondition) x * 2
    else multiply(y)
  }

  println(someFunnc(3, multiply(4,2)))

*/


  /*
  Мое решение исходя из кода, что мне было видно
   */

  def someFunnc(x: Int, y: => Int): Any = {
    if (aCondition()) y * 2
    else multiply(x, 2)
  }

  println(someFunnc(3, 4))

}

object Functions1 extends App {

  def aZero(y: Int): Int = 1 / 0;

  def test(x: Int, y: Int): Int = {
    if (true) x + y
    else aZero(y)
  }

  println(test(1, 2))
}

