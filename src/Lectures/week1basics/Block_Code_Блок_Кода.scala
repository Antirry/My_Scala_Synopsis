package Lectures.week1basics

object Block_Code_Блок_Кода extends App {

/*  Блок кода также является выражением,
  результат(и тип) которого равен результату(и типу)
  последнего описанного внутри блока кода выражения.
 */

  val aCodeBlock = {
    val someVal = 1
    val y = 2

    if (someVal + y > 1) true else false
  }
  println(aCodeBlock)

/*  Если, например, допишем строку "String to return"
 в конец нашего предыдущего блока кода,
 то bCodeBlock сменит тип на String,
 что соответствует типу последнего выражения "String to return",
 описанного в блоке
 */

  val bCodeBlock = {
    val someVal = 1
    val y = 2

    if (someVal + y > 1) true else false

    "String to return"
  }
  println(bCodeBlock) // выводит "String to return"
  println(bCodeBlock.getClass) // Класс Строка
}

object Task_4_1 extends App {

  val someValue = {
    1 < 0
    2 == 5
    4 < 9
  }
//  Если хоть одно значение будет True - вывод True
  println(someValue) // Выводит true

  val someCodeBlock = {
    if (someValue) 1 else 0
    0
  }
//  Вывод из-за 0 в конце, мы выбираем, вернуть из ввыражения
  println(someCodeBlock) // Вывод 0
}

object Task_4_2 extends App {

  val someVal1 = 2.045456457454 //  Double
  val someVal2 = println("Hello, world!") //  Unit
  val someVal3 = {1 < 5; "Some String"; 12} //  Int
  val someVal4 = 1 > 0 //  Boolean
  val someVal5 = 2.045456457454f //  Float
}