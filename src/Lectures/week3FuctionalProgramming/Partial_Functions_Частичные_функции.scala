package Lectures.week3FuctionalProgramming

object Partial_Functions_Частичные_функции extends App {
/*
  Часто бывает так, что требуется ограничить значения, которые можно подавать на вход функции.
   Конечно, можно выкрутиться через шаблоны.
    Так, например, мы допускаем только значения mon, fri, sun:

    ПРИМЕР БЕЗ Partial_Functions
 */

  val whatToDo = (d: String) => d match {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  }



//  ПРИМЕР С Partial_Functions

  val aPartialFunction: PartialFunction[String, String] = {
    case "mon" => "Work!"
    case "fri" => "PArty Time"
    case "sun" => "Relax a little"
  }

  println(aPartialFunction("sun")) // Relax a little
  println(aPartialFunction("sat")) // MatchError

/*
  Для недопустимого значения получаем MatchError - намек,
   что частичные функции основаны на совпадении шаблонов.

  Заметьте, что, как и в случае Function,
   последний из указываемых в квадратных скобках типов обозначает тип возвращаемого значения.
 */
}



object Проверка_аргумента_Check_Arguments extends App {

  val aPartialFunction: PartialFunction[String, String] = {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  }

  println(aPartialFunction.isDefinedAt("tue"))  // false

}


object orElse_Partial_Functions_илиЕсли_частичные_функции extends App {

  val aPartialFunction: PartialFunction[String, String] = {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  }

/*
orElse объединяет несколько функций


если aPartialFunction содержит нужный нам case,
 возвращаем его значение,
иначе переключаемся на следующую функцию

Подробнее:
  https://drive.google.com/file/d/12IdW5-b60PhwNbpMPj5uR55lnU6yfdUn/view?usp=sharing

 */

  val pfChain: PartialFunction[String, String] = aPartialFunction.orElse[String, String] {
    case "sat" => "It's just Saturday"
  }

  println(pfChain("mon"))  // Work!
  println(pfChain("sat"))  // It's just Saturday

}


object Лифтинг_Lifting_подъем extends App {

/*
Можно сказать, что лифтинг позволяет поднять частичную функцию на следующий уровень.
  После lift - функция будет возвращать значения типа Option

  (т.е. решается проблема MatchError)
 */

  val aPartialFunction: PartialFunction[String, String] = {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  }

  val lifted = aPartialFunction.lift // теперь на выходе имеем тип Option[String]

  println(lifted("fri"))  // Some(Party Time)
  println(lifted("thu"))  // None
}




object Task_21_1 extends App {

  val aPartialFunction: PartialFunction[String, String] = {
    case "hello" => "Hi, I'm Bot"
    case "bye" => "Bye-bye"
    case "what's up" => "sup-sup"
  }

  val chatbot = aPartialFunction.lift


}

object Task_21_2 extends App {

  val aPartialFunction: PartialFunction[String, String] = {
    case "hello" => "Hi, I'm Bot"
    case "bye" => "Bye-bye"
    case "what's up" => "sup-sup"
  }

  val chatbot = aPartialFunction.lift

  scala.io.Source.stdin.getLines().foreach(line => println(chatbot(line)))
  scala.io.Source.stdin.getLines().map(chatbot(_)).foreach(println)

// Лаконичный код
  scala.io.Source.stdin.getLines().map(chatbot).foreach(println)
}
