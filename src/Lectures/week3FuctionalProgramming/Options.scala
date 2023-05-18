package Lectures.week3FuctionalProgramming

object Options extends App {

/*
Опции - это всего лишь способ защиты от NullPointerException

Не следует вручную делать проверки на Null - доверьте это дело Option,
 поместив в него переменную, которую подозреваете в отсутствии значения:
 */

  def unsafeMethod(): String = null
  def safeMethod(): String = "There is a String"

  val unsafeRes = Option(unsafeMethod())
  val safeRes = Option(safeMethod())

  println(unsafeRes) // None
  println(safeRes) // Some(There is a String)

//  Т.е. Option вернет None при отсутствии значения и Some со значением, если значение есть.



//  Можно заранее оценить результат, вернув true или false:

  val someOption: Option[String] = Some("Success")
  val noneOption: Option[Int] = None

  println(someOption.isEmpty) // false
  println(noneOption.isEmpty) // true
}

object Option_and_orElse extends App {

/*
  Когда есть необходимость протестировать несколько методов одновременно
   с помощью Option, сделать код лаконичнее поможет конструкция orElse:
*/

  def unsafeMethod(): String = null
  def maybeSageMethod(): String = "There is no harm"

  val chainedResult = Option(unsafeMethod()).orElse(Option(maybeSageMethod()))
  println(chainedResult) // Some(There is no harm)

// В качестве альтернативы - можно заложить проверку непосредственно в описываемый метод:

  def unsafeMethod1(): Option[String] = None
  def maybeSafeMethod1(): Option[String] = Some("There is no harm")

  val chainedRes = unsafeMethod1() orElse maybeSafeMethod1()
  println(chainedRes) // Some(There is no harm)
}

object Task_18_1 extends App {

//   Использование в коде уже готового API

  def unsafeMethod(): String = null
  def maybeSafeMethod(): String = "There is no harm"

  val chainedResult = Option(unsafeMethod()).orElse(Option(maybeSafeMethod()))
  println(chainedResult)

//   Разработка собственного API

  def unsafeMethod1(): Option[String] = None
  def maybeSafeMethod1(): Option[String] = Some("Everything is Ok")
  val chainedRes = unsafeMethod1() orElse maybeSafeMethod1()
  println(chainedRes)


/*

ВЫВОД:
  Первый код предусматривает применение Option к вызываемым методам.
   Если бы это был разработанный нами API, то применения Option при вызове методов не потребовалось,
    т.к. метод unsafeMethod и maybeSafeMethod уже были бы спроектированы с Option

 */
}

object Task_18_2 extends App {

  val someOption: Option[String] = Some("Success")
  val noneOption: Option[Int] = None

  println(noneOption.get)      // NoSuchElementException
  println(someOption.isEmpty)  // false
  println(noneOption.isEmpty)  // true
  println(someOption.get)      // Success

/*

ВЫВОД:
  Из-за того, что get бросает исключения там,
   где isEmpty выдает true, на практике использование get в подобных случаях не приветствуется

*/

}

object Task_18_3 extends App {

//  Какой вывод:

  val someVal = Some(2)
  println(someVal.map(_ * 2))  // Some(4)

}

object Task_18_4 extends App {

  val someVal = Some(2)
  println(someVal.filter(x => x > 0))            // Some(2)
  println(someVal.flatMap(x => Option(x * 2)))   // Some(4)
  println(someVal.filter(x => x > 10))           // None

/*
ВЫВОД:
  В этом примере вы можете увидеть, как filter может из Some сделать None

 */

}