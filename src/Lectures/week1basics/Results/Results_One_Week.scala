package Lectures.week1basics.Results

import scala.jdk.Accumulator

object Results_One_Week {

//  САМОЕ ГЛАВНОЕ ИЗ НЕДЕЛИ ->
  /*
  If является выражением и возвращает значение

  Результат последнего выражения в блоке будет результатом всего блока

  Определенные внутри блока кода переменные не доступны вне его

  Не используйте циклы while в коде

  Если требуется написать цикл - используйте рекурсию

   */
}

object Task_7_1 extends App {

//  Ввод данных в программу ->

  import scala.io.StdIn.readLine

//  I like     Scala

  val str = readLine()

  println(str.split(" ").toList.filter(_.nonEmpty).reverse.mkString(" "))

  /*
  input , потому что в задании использовалось такое название переменной

  println(input.split(" ").toList.filter(_.nonEmpty).reverse.mkString(" "))
   */



}
