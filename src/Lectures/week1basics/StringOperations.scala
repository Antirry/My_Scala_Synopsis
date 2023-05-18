package Lectures.week1basics

object StringOperations extends App {
  val aString: String = "Hello, world!"

//(Длина строки, включая 0)
  println(aString.length) // выводит 13
//  (Символ начинающийся с 0)
  println(aString.charAt(1)) // выводит e
// (Выбрать определенную часть строки - Символы берет с 1, для нужных символов нужен (a-1, a))
  println(aString.substring(0, 2)) // выводит He
//  Перевод в список
  println(aString.split(" ").toList) // выводит List(Hello,, world!)
//  Проверка на начало в тексте
  println(aString.startsWith("He")) // выводит true
//  Замена
  println(aString.replace("!", ".")) // выводит Hello, world.
//  Использование нижнего регистра
  println(aString.toLowerCase) // выводит hello, world!
//  Верхний регистер
  println(aString.toUpperCase) // выводит HELLO, WORLD!
//  Обратный порядок
  println("abcd".reverse) // выводит dcba
//  Взятие двух символов (Строка начинающиеся с 1 - типо один символ)
  println("abcd".take(2)) // выводит ab

  println("\n\n")

// Выводит заглавные буквы и берутся два первых символа
  println("example".toUpperCase.take(2))
//  Альтернативный вариант:
  val upperCaseVal = "example".toUpperCase
  val res = upperCaseVal.take(2)
  println(res)
}

object StringA extends App {

  val aString = "Scala course"
  println(aString.length)

//  Взять последний символ из строки ->

  println(aString.substring(11, 12))
  println(aString.charAt(11))

}

object StringB extends App {
  val aString = "Scala course"
//  Вывесьти только слово с верхним регистром SCALA
  println(aString.substring(0, 5).toUpperCase)
  println(aString.toUpperCase.substring(0, 5))
  println(aString.take(5).toUpperCase)

//  ОШИБКИ:
//  ВКЛЮЧЕН ДОП СИМВОЛ -> " "
  println(aString.substring(0, 6).toUpperCase)
  println(aString.take(6).toUpperCase)

//  НЕЛЬЗЯ НАЗНАЧИТЬ ОДНУ И ТУЖЕ ПЕРЕМЕННУЮ ДВА РАЗА
  /*
  aString = aString.take(5); println(aString.toUpperCase)
   */
}

object StrinbC extends App {
  val aString = "Scala course"
  println(aString.replace("a", "").take(3).reverse)
//  Вывод lcS
}

object ConvertString1 extends App {

  //  Код для пребразования строки в число:
  val aNumber = "42".toInt
  println(aNumber) // выводит 42
  println(aNumber.getClass) // выводит int

  //  Преобразование числа в строку:
  val aString = 42.toString
  println(aString) // выводит 42 в виде строки
  println(aString.getClass) // выводит Str
}

object String_addition extends App {
//  Складывание строк

  /*
  добавление в начало подразумевает наличие двоеточия после  плюса. Соответственно, добавление в конец - перед плюсом
  1) +: или :+ работаем с только типом char
  2) ++ или :++ используем элементы типа String, следовательно здесь уже понадобятся двойные кавычки

  внимание на типы, с которыми работаем: в нашем случае присоединения
  всегда происходит к элементам типа String (в примере это "42" и "bc")
   */

//  1)
  println('1' +: "42" :+ '3') // выводит 1423
  println('a' +: "bc" :+ 'd') // abcd
//  2)
  println("a" ++ "bc" :++ "d") // abcd

//   +: присоединяет только значения типа Char. Кстати, если переписать код в точечной нотации

  //  Так как список переводит в строку с -> ""
  val aStr = "42".+:('1')
  println(aStr.:+('3')) // 1423

//  НЕ ПРАВИЛЬНОЕ ДЕЙСТВИЕ ->
  /*
  val aStr = '1'.:+("42")
  println(aStr)
   */
}

object LIST_String_addition extends App {
//  Добавление Char -> 1 (как один элемент)
  println(1 +: List(2, 3)) // List(1, 2, 3)
  println(List(1, 2) +: List(3, 4)) //List(List(1, 2), 3, 4)

//  Добавление всей строки -> (3,4) (как переменная)
  println(List(1, 2) ++ List(3, 4)) // List(1, 2, 3, 4)
}

object Task_2_1 extends App {

//  Получаем ошибку, тк пытаемся скомбиновать значения типа Char, а данная операция предусматривает присоединение только к Vector или Seq
  /*
  println('3' +: '4')
   */
}

object Str_Interpolation extends App {

//  s-интерполятор

  val name = "John"
  println(s"Hello, $name") // выводит Hello, John

//  ПЛЮС ДОБАВЛЯЕТ ЗАПЯТУЮ
  val name1 = "John"
  val surname = "Smith"
  println(s"Hello, ${name1 + surname}") // выводит Hello, JohnSmith

//  raw-интерполятор

// Может только разделять строку (Не уверен)
  val someString = "This is \n a string"
  println(raw"This is \n a String")
  println(raw"$someString")
}

object Task_2_2 extends App {
  val link = "https://stepik.org"

  println(link :++ "/catalog") //  https://stepik.org/catalog
  println(s"The link is link") //  The link is link
  println(s"The link is $link.toUpperCase") //  The link is https://stepik.org.toUpperCase

// Сравнение интерполяторов -> RAW с S
  println(raw"The link is \t $link") //  The link is \t https://stepik.org
  println(s"The link is \t $link") //  The link is https://stepik.org

  println(s"The link is ${link.toUpperCase}") //  The link is HTTPS://STEPIK.ORG

  // ОШИБКИ
  /*
  println(s"The link is $linc") //  not found: value
  println(link :+ '/ catalog') //  unclosed character literal
   */
}