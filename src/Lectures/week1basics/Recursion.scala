package Lectures.week1basics

import scala.annotation.tailrec
import scala.jdk.Accumulator
import scala.runtime.BoxesRunTime

object Recursion extends App {
  /*
  в функциональном программировании в этом не заинтересованы,
   т.к. работаем с неизменяемыми переменными - т.е. с val.
   */

  //  разберем рекурсию на одном из самых известных ее примеров  - примере вычислении факториала.

  /*
  Факториал, это когда мы на вход передаем какое-то число и
  на выходе получаем произведение всех чисел от 1 до заданного числа включительно.
  Например, факториал 3 равен 1*2*3 = 6
   */

  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial(n - 1)
  }

  println(factorial(3)) // выводит 6

  /*
  Промежуточные вычисления хранятся в стеке,
  а это значит,
  что каждый вызов рекурсивной функции сопряжен с использованием стека.
 */

  //  Подробнее ->

  def factorial1(n: Int): Int = {
    if (n <= 0) 1
    else {
      println(s"Имеем число $n, для которого считаем факториал ${n - 1}")
      var res = n * factorial1(n - 1)
      println(s"Вычислили факториал $n")

      res
    }
  }

  factorial1(3)
}






/*
ХВОСТОВАЯ РЕКУРСИЯ - ЭТО ИМЕННО ТО, ЧТО ПРИМЕНЯЕТСЯ ПРИ РАБОТЕ С БОЛЬШИМИ ДАННЫМИ.
ЭДАКАЯ ФУНКЦИОНАЛЬНАЯ ФОРМА ЦИКЛОВ. ПОЭТОМУ НАСТОЯТЕЛЬНО РЕКОМЕНДУЕМ КАК СЛЕДУЕТ В НЕЙ РАЗОБРАТЬСЯ.
 */

object TailRecursion extends App {

  /*
  Не случайно назвали рекурсивную функцию loop.
  По уже сложившейся традиции - такие функции принято называть loop или go.
  Однако вы вполне можете использовать любое другое подходящее имя.

  Отличительной особенностью функции с хвостовой рекурсией является то,
  что последней операцией идет вызов этой самой функции.
  И теперь промежуточные вычисления накапливаются в аккумуляторе.

  Такое применение вспомогательной функции, написанной в стиле хвостовой рекурссии,
  позволяет обойтись без использования дополнительного стека для хранения промежуточных результатов.
   */




  //  КАК ПИСАТЬ ФУНКЦИИ В СТИЛЕ ХВОСТОВОЙ РЕКУРСИИ

  /*
  1. Определяете функцию. Назовем ее основной.

  2. Внутри основной функции прописываете еще одну функцию
  .Назовем ее вспомогательной.

  3. В качестве аргументов вспомогательной функции указываете число вызовов
  (этот аргумент совпадает с одним из аргументов основной функции) - плюс аккумулятор,
   который будет содержать конечный результат.

  4. Прописываете код с рекурсией в теле вспомогательной функции
  с использованием аккумулятора(так, чтобы значение постепенно накапливалось)

  5. Вызываете вспомогательную функцию из основной функции.
  Не забудьте указать начальное значение аккумулятора,
  задающее отправную точку всех вычислений.
  Используйте аргумент по умолчанию для оптимизации кода.

   */

  def factorialWithTailRecursion(n: Int): Int = {
    def loop(x: Int, accumulator: Int): Int ={
      if (x <= 1) accumulator
      else loop(x-1, x*accumulator)
    }
    loop(n, 1)
  }
}

object Check_Function_on_Tail_Recursion extends App {
  import scala.annotation.tailrec

  def Check_Function_on_Tail_Recursion(n: Int): Int = {
    @tailrec
    def loop(x: Int, accumulator: Int = 1): Int = {
      if (x <= 1) accumulator
      else loop(x - 1, x * accumulator)
    }
    loop(n)
  }

  println(Check_Function_on_Tail_Recursion(3))
}




// Пример работы хвостовой функции

// https://drive.google.com/file/d/1joHbiIowTB4jmug9F4O1kd9NErJnK1pM/view?usp=sharing





object repeatWordObject extends App {

  //  Пример без хвостовой функции

  /*
    var i = 0
    while (i < 3) {
      println("hello")
      i += 1
    }
  */

  // Пример с хвостовой функции ниже
  // https://drive.google.com/file/d/107C3CfOQzZBdA3e7D7TDA6C2K8J7ONAo/view?usp=sharing

  def repeatWord(word: String, n: Int): String = {
    def loop(i: Int, acc: String = word): String = {
      if (i <= 1) acc
      else loop(i-1, acc = s"$word $acc")
    }
    loop(n)
  }

  println(repeatWord("hello", 3))
}


object Task_6_1 extends App {

//  def powerOfTwo(n: Int): Unit = {
//    def loop(x: Int, accumulator: Int = 1): Unit = {
//      if (x <= 1) false
//      else println(x, accumulator)
//    }
//
//    loop(n)
//  }




/*

Каждый раз прочитывается если (постоянно будет false) и
он каждый раз вызывает функцию уменьшая количество попыток
аккамулятор - accumulator используется для сохранения значений
каждый раз он умножает на два и сохраняет значение

В ЗАДАНИИ ТРЕБОВАЛОСЬ BIGINT,
ТАК КАК БЫЛИ ОГРОМНЫЕ ЗНАЧЕНИЯ accumulator ИХ СОХРАНЯЛ

 */

  import scala.annotation.tailrec

  def powerOfTwo(n: Int): BigInt = {
    @tailrec
    def loop(x: Int, accumulator: BigInt = 2): BigInt = {
      if (x <= 1) accumulator
      else loop(x - 1, accumulator = accumulator * 2)
    }

    loop(n)
  }


  println(powerOfTwo(32))
}



object Task_6_2 extends App {


  /*
      Задание посчитать количетсво чисел и
      столько раз вывесьти его ответ и вконце приписать "is the result"

      Вывод без +1 не подходит для задания (15 is the result),
      так как в цикле i должно быть от 1, а length читает от 0
      15 15 is the result
   */


  val fArgs = Array(10, 1, 5)
  val value = fArgs(0) + (fArgs(1) * fArgs(2))

  def printNumbersResult(n: Int): String = {

    def loop(i: Int, accumulator: String = "is the result"): String = {

      if (i <= 1) accumulator
      else (loop(i-1, accumulator = s"$value $accumulator"))
    }

    loop(fArgs(0).toString.length+1)
  }

  println(printNumbersResult(fArgs(2)))

}