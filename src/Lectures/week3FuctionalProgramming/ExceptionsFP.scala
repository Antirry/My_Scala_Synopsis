package Lectures.week3FuctionalProgramming

import scala.util.{Failure, Success}


object ExceptionsFP extends App {

/*
Из ООП мы знаем о существовании try-catch-finally, что неплохо работает в маленьких программках,
 но в реальности код, с которым придется иметь дело, будет гораздо сложнее.
  И если переусердствовать с try-catch-finally, то получится код,
   читать который со временем будет все сложнее  и сложнее.

Поэтому на помощь в функциональном программировании нам приходит Try
 */

}

object Exceptions_Example extends App {

// для TRY необходимо предварительно импортировать:

  import scala.util.Try

  def unsafeMethod(): String = throw new RuntimeException("Sorry, not your day")

// Просто пишем Try, а затем в круглых скобках указываем метод, который было бы желательно проверить:

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)  // Failure(java.lang.RuntimeException: Sorry, not your day)

// можно писать Try, затем через пробел открывать фигурные скобки, в которых прописывать код для тестирования.

  val anotherPotentialFailure = Try {
    throw new RuntimeException("Sorry, not your day")
  }

  println(anotherPotentialFailure)  // Failure(java.lang.RuntimeException: Sorry, not your day)

// Мы можем заранее проверить, содержит ли метод исключения, получив в ответ true или false

  println(potentialFailure.isSuccess)  // false
}

object Task_19_1 extends App {
  import scala.util.Try

  def unsafeMethod(): String = throw new RuntimeException("Sorry, not your day")

  val anotherPotentialFailure = Try {
    Try(unsafeMethod())
    "another failure / очередная неудача"
  }

  println(anotherPotentialFailure)  // Success(another failure)

/*

Try наложен на Try, в отличии от try - throw new,

 Try(unsafeMethod()). Дополнительно обернув код в Try,
  мы тем самым провернули трюк, сделав код рабочим,
   так что увидим мы сообщение об успехе, внутри которого - значение блока кода, а не Failure

 */
}

object Try_and_orElse extends App {
  import scala.util.Try

  def unsafemethod(): String = throw new RuntimeException("Sorry not your day")
  def myMethod(): String = "My method is valid"

//  Каждый try раздельно
  val executeWithTry = Try(unsafemethod()).orElse(Try(myMethod()))
  println(executeWithTry)  // Success(My method is valid)


/*
   Еще одним вариантом будет обернуть в Try результат работы вашего метода.

   СКРИНШОТ НА РАБОТУ КОДА НИЖЕ ->
     https://drive.google.com/file/d/1X_HchaShKpqbn4W0atLjQNBd7o42CFqi/view?usp=sharing
 */

  def methodWhichFails(): Try[String] = Failure(new RuntimeException("Ooops..."))
  def methodWhichSucceeds(): Try[String] = Success("Everything is Okay")

  val tryMethods = methodWhichFails() orElse methodWhichSucceeds()
  println(tryMethods)  // Success(Everything is OK)
}

object Task_19_2 extends App {

/*

  Failure(new RuntimeException("Ooops..."))  //  Try[RuntimeException]
  Try("Success")                             //  Try[String]
  "Success"                                  //  String
  Success("Success")                         //  Success[String]

*/

}

object Task_19_3 extends App {

  import scala.util.{Try, Failure, Success}

// Выражение Try(new RuntimeException("Ooops...")) соответствует типу Try[RuntimeException], а у нас Try[String]

  def firstMethod(): Try[RuntimeException] = Try(new RuntimeException("Oooops..."))
  def secondMethod(): Try[String] = Try("Everything is OK")

  val res = firstMethod() orElse secondMethod()
  println(res)
}

object Task_19_4 extends App {

  val someVal = Success(3)

  println(someVal.filter(_ > 5))  // Failure(java.util.NoSuchElementException: Predicate does not hold for 3)

/*

Вариант с Failure в качестве аргумента можно скармливать исключения (Throwable), но никак не Success[Int]

  println(Failure(someVal))

 */
}