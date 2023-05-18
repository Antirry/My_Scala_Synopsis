package Lectures.week3FuctionalProgramming.Result

object Result extends App {

/*
1. Используйте Try, если                     // ваш код может выдать исключения
2. Используйте Options, если                 // ваш код содержит null значения
3. Лифтинг                                   // решает проблему MatchError
4. Каррирование - это про, что               // методы могут объявляться с несколькими списками параметров
5. Под частичной функцией понимаем функцию,  // допустимую только для определенного подмножества значений
6. Шаблоны                                   // проверяются по порядку


1. ExceptionsFP
2. Options
3. Partial_Functions_Частичные_функции , объект Лифтинг_Lifting_подъем
4. High Order Functions стоит прочитать весь, если забыл
5. Partial_Functions_Частичные_функции , до объекта Лифтинг_Lifting_подъем
6. Patterns , полностью
 */

}

object Task_1 extends App {

  val tel_number: String = scala.io.StdIn.readLine()

  def countNumbers(numbers: String): Map[Char, Int] = {
    val amount: Int = numbers.length
    def loop(i: Int = 0, accumulator: Map[Char, Int] = Map('a' -> 0)): Map[Char, Int] = {
      if (i >= amount) {
        accumulator
      } else {
        val number = numbers(i) match {
          case n if n.isDigit => n.toChar
          case _ => 'a'
        }
        val new_accumulator = accumulator.updated(number, accumulator.getOrElse(number,0)+1)-('a')

        loop(i + 1, new_accumulator)
      }
    }

    loop()
  }

  println(countNumbers(tel_number))
}

object Task_2 extends App {
  val version_numbers: String = scala.io.StdIn.readLine()
  def compare(v1_2: String = version_numbers): Int = {

    def check_number(n: Any): Int = n match {
      case None => 0
      case n => n.toString.toInt
    }
    val v1_2_new: Array[String] = v1_2.replace(".", "").split(" ")
    val amount: Int = if (v1_2_new(0).length > v1_2_new(1).length) v1_2_new(0).length else v1_2_new(1).length
    println(amount)
    def loop(i: Int = 0, accumulator: Int = 0): Int = {
      if (i >= amount) {
        accumulator
      } else {
        val new_accumulator: Int = check_number(v1_2_new(0)(i)) - check_number(v1_2_new(1)(i))
        new_accumulator match {
          case 0 => loop(i + 1, accumulator)
          case _ => accumulator + new_accumulator
        }
      }
    }
    loop()
  }
  println(compare(version_numbers))
}

//object Task_2_1 extends App {
//
//  val version_numbers: String = scala.io.StdIn.readLine()
//  val version_numbers1: String = scala.io.StdIn.readLine()
//
//  def compare(v1: String, v2: String): Int = {
//
//    def check_number(n: Any): String = n match {
//      case None => "0"
//      case n => n.toString
//    }
//    val v1_2_new: Array[String] = (v1.replace(".", "") + " " + v2.replace(".", "")).split(" ")
//    val amount: Int = if (v1_2_new(0).length > v1_2_new(1).length) v1_2_new(0).length else v1_2_new(1).length
//    def loop(i: Int = 0, accumulator: Int = 0): Int = {
//      if (i >= amount) {
//        accumulator
//      } else {
//        val new_accumulator: Int = check_number(v1_2_new(0)(i)) - check_number(v1_2_new(1)(i))
//        new_accumulator match {
//          case 0 => loop(i + 1, accumulator)
//          case _ => accumulator + new_accumulator
//        }
//      }
//    }
//    loop()
//  }
//
//  println(compare(version_numbers, version_numbers1))
//}

object Task_2_2 extends App {
  def compare(v1: String, v2: String): Int = {
    val v1parts = v1.split("\\.")
    val v2parts = v2.split("\\.")
    val maxLength = math.max(v1parts.length, v2parts.length)

    def padToLength(a: Array[String], len: Int): Array[String] = {
      a.padTo(len, "0")
    }

    val v1padded = padToLength(v1parts, maxLength)
    val v2padded = padToLength(v2parts, maxLength)

    val pairs = v1padded.zip(v2padded)

    for {
      (p1, p2) <- pairs
      if p1 != p2
    } {
      return if (p1.toInt > p2.toInt) 1 else if (p1.toInt == p2.toInt) 0 else -1
    }
    0
  }


}