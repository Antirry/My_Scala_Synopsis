package Lectures.week1basics

object Expressions_Выражения extends App {
  /*
  Два основных свойства выражений:

  1) выражения возвращают какое-то значение
  2) имеют тип
   */

  val aVal = 1 + 2 * 3
  println(aVal) // Отличия от обычного print добавление /n в конце
  print(1 + 2 * 3)

/*
  Если выражение - это когда мы возвращаем какое-то, как правило,
  вычисленное значение, то инструкция - это когда мы выполняем какое-то действие,
  говорим компьютеру сделать что-то, но выражение не возвращаем.

  Мораль такова, что все в Scala является выражением
 */

/*
  что применяется в Scala-выражениях:
  1)  + - / *
  2)  & | ^ << >> >>>
  3)  == != > >= <= < ! && ||
 */
}

object If_condition extends App {

//  Все в Scala является выражением - это очень важно понять и запомнить. И if не является исключением.

//  Сразу пишем значение, которое хотим присвоить в первом и во втором случае

  val aCondition = true
  val ifExpressionValue = if (aCondition) "True Condition" else "False Condition"
  println(ifExpressionValue) // выведет True Condition

//  ПРОВЕРКА НА ТО, ЧТО ЭТО ВЫРАЖЕНИЕ
  println(if (aCondition) "True Condition" else "False Condition") // выведет True Condition



//  Ожидается, что выражение возвращает значение.
//  Но бывают случаи, когда никакого значения не возвращается.
//  ~За примером далеко ходить не надо - тот же print.

  //  Unit особенно полезен в функциях, когда нам ничего,
  //  кроме как вывести что-то на экран, не требуется.

  val someVal1 = print("I just want to print")

//  Т.е.код можно переписать так:
  val someVal2: Unit = print("I just want to print")

//  Если проводить параллели с другими языками программирования
  //  - Unit можно ассоциировать с Void.

}

object Task_3_1 extends App {

  val aNumber = if(('1' +: "23").toInt == 24) 24 else 123
//  '1' +: "23" = "123".toInt == 123 = 123

  val bNumber = if (("string".length == 6 & 1 < 2) & ('1' +: "23" :+ '4').toInt == 1234) 24 else 123
  println(bNumber)
//  ("string".length == 6 & 1 < 2) - true
//  ('1' +: "23" :+ '4').toInt == 1234) - true
//  = 24

/*  Код с сайта ->
val aNumber = if ("string".length == 6 & 1 < 2) & ('1' +: "23" :+ '4').toInt == 1234  24 else 123

Нет скобок, поэтому не работает
 */

//  Вывод принта и вывод переменной
  val someVal = print("It is just a value")
  println(someVal)
}