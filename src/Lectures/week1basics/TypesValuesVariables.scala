package Lectures.week1basics

/*
Byte [ 8 битовое знаковое значение. Диапазон от -128 до 127 ]
Short [ 16 битовое знаковое значение. Диапазон от -32768 to 32767 ]
Int [ 32 битовое знаковое значение. Диапазон от -2147483648 to 2147483647 ]
Long [ 64 битовое знаковое значение. Диапазон от -9223372036854775808 to 9223372036854775807 ]
Float [ 32 битовое IEEE 754 число с плавающей точкой одинарной точности ]
Double [ 64 битовое IEEE 754 число с плавающей точкой двойной точности ]
Char [ 16 битовое беззнаковый символ Unicode. Диапазон от U+0000 to U+FFFF ]
String [ Последовательность экземпляров Char ]
Boolean [ Логическое буквенное значение true или false ]
Unit [ Соответствует отсутствию значения ]
Null [ Null или пустая ссылка ]
Nothing [ Подтип любого другого типа, включает в себя отсутствие значения. ]
AnyRef [ Супертип любого ссылочного типа. ]
*/

object TypesValuesVariables extends App {

  private val aString: String = "Hello"
  println(aString)
  val bString = "Hello"
  println(bString)

//  val aChar: Char = "C" сам пишет -> : String

  val aChar: Char = 'C'
  val aInt: Int = 11
  val aLong: Long = 11L

//  val aFloat: Float = 2.0 : Double

  /*
  2.0f - float
  11L - long
   */

  val aFloat: Float = 2.0f
  val aDouble: Double = 2.0

  val aBoolean: Boolean = true

  val (name1: Int, name2: String) = (2, "geekforgeeks")
  println(name1)
  println(name2)
}

object Long_and_Float extends App {

  val aLong = 11
//  Вывод int числа (Из-за не правильной типизации)
  println(aLong.getClass)

  val bLong = 11L
//  Вывод long числа
  println(bLong.getClass)

  val aFloat = 2.0f
}

object String_change extends App {
//  val aString: String = "Hello"

//  aString = "Hello, world" <- ошибка

/*
val (в отличие от var) создает переменную только для чтения (неизменяемую переменную).
И получается, что нельзя переназначить переменные, созданные с ключевым словом val.
*/

  var bString: String = "Hello"
  bString = "Hello, world"

  var cString = "abc"
  cString += "defg"
  var bnInt = 1
  bnInt -= 1
  var anInt = 1
  anInt += 1

  println(cString, bnInt, anInt)

  // НЕ РАБОЧИЕ ВАРИАНТЫ

// Нельзя вычитать из строки
  /*
  var dString = "abc"
  dString -= "bc"
   */

//  Правильный вариант
  /*
  val dString = "abc"
  val eString = dString.replace("bc", "")
  println(eString)
   */

//  Статичная переменная
  /*
  val cnInt = 1
  cnInt += 1
  println(cnInt)
   */



  val Char1 = 'c'; val Int1 = 1

  println(Char1, Int1)
}
