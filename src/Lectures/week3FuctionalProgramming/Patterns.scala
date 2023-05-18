package Lectures.week3FuctionalProgramming


object Patterns extends App {
/*

  Шаблоны применяются тогда, когда для конкретных значений требуется определить
   свой вариант действия программы (можно провести параллели со switch-case из других языков).

 */

  val someVal = 3

  val description = someVal match {
    case 1 => "action1"
    case 2 => "action2"
    case 3 => "action three"
    case _ => "default action"
  }

  println(description) // action three

/*

  Рядом с case мы задаем шаблоны, которые сравниваем со значением переменной someVal.
   А после => указываем желаемый результат для заданного случая.

  Символ _ обычно описывает ситуацию, когда совпадений среди шаблонов не найдено т.е. результат по умолчанию.

*/
}




object Type_Patterns extends App {

//  КОНСТАНТЫ

  val x: Any = "Scala"
  val constants = x match {
    case 1 => "число"
    case "Scala" => "строка"
    case true => "Булевое значение"
  }
  println(constants) // строка

//  Туплы (tuple)

  val aTuple = (5, 2)
  val matchATuple = aTuple match {
    case (1, 1) => "Полное совпадение"
    case (something, 2) => s"я нашел $something"
  }
  println(matchATuple) // я нашел 5

//  Вложенные туплы (Nested Tuple)

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => s"Тут есть число $v"
  }
  println(matchNestedTuple) // Тут есть число 3

//  List - Списки

  val aStandardList = List(4, 5)
  val listMatching = aStandardList match {
    case List(1, _, _, _) => "Список из 4 элементов, где первый элемент равен 1"
    case List(2, _*) => "Список произвольной длины, где первый элемент равен 2"
    case List(3, 2, 1) :+ 0 => "Список List(3, 2, 1, 0)"
    case 5 :: List(_) => "Список, где идет число 5 и еще один элемент"
    case _ => "default"
  }
  println(listMatching) // default

// Types - Типы

  val unknown: Any = List(1, 2)
  val typeMatch = unknown match {
    case list: List[Int] => "list of INT's"
    case _ => "default"
  }
  println(typeMatch) // list of INT's

//  Классы-образцы - sample-classes

  case class Person(name: String, age: Int)

  val bob = Person("Bob", 30)
  val greeting = bob match {
    case Person(n, a) if a < 18 => s"I'm $n and I'm under 18"
    case Person(n, a) if n != "name" => s"I'm $n and I'm $a years old"
    case _ => "I haven't name"
  }
  println(greeting) // I'm Bob and I'm 30 years old
}




// Привязка имен (Name Binding)

object Name_Binding extends App {

  val nameBindingMatch = List(6,2) match {
    case nonEmptyList@List(1, _, _, _) => s"нашли $nonEmptyList"
    case someList@List(6, _*) => s"нашли список $someList"
  }
  println(nameBindingMatch)  // нашли список List(6, 2)

}

object Example extends App {

  val numbers = List(1, 2, 3)

  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of integers"
    case _ => "default case"
  }
  println(numbersMatch) // a list of strings


/*
Увижу a list of strings. И это несмотря на то, что на вход был подан целочисленный список.


Причина этому - JVM. Компилятор на самом деле видит код без generic-типов. Т.е. для компилятора код выглядит вот так:

//  val numbersMatch = numbers match {
//    case listOfStrings: List => "a list of strings"
//    case listOfNumbers: List => "a list of integers"
//    case _ => "default case"
//  }


 */

}

object Task_20_1 extends App {

/*
1 - нечетное число
2 - четное число
3 - нечетное число
4 - четное число
5 - нечетное число
 */

  for (i <- 1 to 5) {
    i match {
      case i if (i % 2 == 0) => println(s"$i - четное число")
      case i if (i % 2 == 1) => println(s"$i - нечетное число")
    }
  }

}

object Task_20_2 extends App {

/*
  Какой паттерн подойдет для поиска List, состоящего из одного или нескольких элементов?

  List(_*)
 */
}


object Task_20_3 extends App {

  def guard(data: Any, maxLength: Int): String = data match {
    case length_list: List[Any] if (length_list.length <= maxLength) => "Задан список List допустимой длины"
    case maxlengthlist_highest: List[Any] if (maxlengthlist_highest.length > maxLength) => "Длина списка больше максимально допустимого значения"

    case maxlengthstr: String if (maxlengthstr.length <= maxLength) => "Длина строки не превышает максимально допустимого значения"
    case maxlengthstr_highest: String if (maxlengthstr_highest.length >= maxLength) => "Получена строка недопустимой длины"
    case _ => "Что это? Это не строка и не список"
  }

/*

ВЫВОД:
  Можно брать переменные функции и использовать их в case,
    также определять разные переменные для названия этих методов

 */
}


object Task_20_4 extends App {


  object Name_Surname {

    def unapply(name: String): Option[String] = name.split(" ") match {
      case Array(name, surname) => Some(name.head.toUpper + ". " + surname.head.toUpper + ".")
      case _ => None
    }

    /*
      Второй вариант:

        def checkStr(name: String): String = if (name.exists(_.isLetter)) name else ""

        def unapply(name: String): Option[String] = checkStr(name).split(" ") match {
          case Array(name, surname) => Some(name.head.toUpper + ". " + surname.head.toUpper + ".")
        }
    */
  }

  val name_surname = scala.io.StdIn.readLine()

  name_surname match {
    case Name_Surname(name_surname) => println(name_surname)
    case _ => println("Oops, something is wrong")
  }
}

object Task_20_4_2 extends App {

  def main1(): Unit = {
    val input = scala.io.StdIn.readLine()
    val result = if (input != null && input.trim.nonEmpty) {
      val parts = input.split(" ")
      if (parts.length == 2) {
        val firstName = parts(0)
        val lastName = parts(1)
        s"${firstName.head.toUpper}. ${lastName.head.toUpper}."
      } else {
        "Oops, something is wrong"
      }
    } else {
      "Oops, something is wrong"
    }
    println(result)
  }

  main1()
}