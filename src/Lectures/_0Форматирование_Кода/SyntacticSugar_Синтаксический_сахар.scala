package Lectures._0Форматирование_Кода

object SyntacticSugar_Синтаксический_сахар extends App {
  println("a")
  /*
  Синтаксический сахар - это альтернативный способ написания кода,
   который больше напоминает естественный язык.
    Поэтому может быть более приятен человеческому глазу.
 */
}

object ИНФИКСНАЯ_НОТАЦИЯ_INFIX_NOTATION extends App {

  //  ИНФИКСНАЯ НОТАЦИЯ (INFIX NOTATION)

  println("Постфиксная нотация (Postfix notation)".toUpperCase())

  class Person(val name: String, occupation: String) {
    def worksAs(jobName: String): Boolean = jobName == occupation
  }

  val bob = new Person("Bob", "Developer")

  //  Привычный способ - через точечную нотацию
  println(bob.worksAs("Developer")) // true

  //  Можно переписать в инфиксной нотации
  println(bob worksAs "Developer") // true

/*

  На практике инфиксная и постфиксная нотации применяются довольно редко.
   И предпочтение отдается точечной нотации, чтобы не вводить в заблуждение коллег.

    Кроме того, в версиях Scala, начиная с 2.13, постепенно уходят от постфиксных нотаций,
     что требует дополнительного импортирования import scala.language.postfixOps

    ВЫВОД:
      Результат вызова функции не изменился, но код стал напоминать обычное предложение.

      Избавились от точки и скобок, обратившись к пробелам.

*/

/*

ИНФИКСНАЯ НОТАЦИЯ - название функций

  Если указывать имена функций в обратных кавычках,
   то в именах этих функций вполне спокойно можно задействовать пробелы:

Дан код:
 */

  def `short name`(name: String) = name.take(2).toUpperCase + "."
  val names = List("Alex", "Sam", "Peter", "andrew")

// Какой код позволит получить List(AL., AN.)?

  names map `short name` filter (_.startsWith("A"))
}

object ПОСТФИКСНАЯ_НОТАЦИЯ_POSTFIX_NOTATION {

  // Импорт постфиксов
  import scala.language.postfixOps

  // ПОСТФИКСНАЯ НОТАЦИЯ (POSTFIX NOTATION)

  class Person(val name: String, occupation: String) {
    def workAs(jobName: String): Boolean = jobName == occupation
    def speaksEnglish: Boolean = true
  }
  val bob = new Person("BoB", "Developer")

  println(bob.speaksEnglish) // точечная нотация
  println(bob speaksEnglish) // постфиксная нотация

/*

На практике инфиксная и постфиксная нотации применяются довольно редко.
 И предпочтение отдается точечной нотации, чтобы не вводить в заблуждение коллег.

  Кроме того, в версиях Scala, начиная с 2.13, постепенно уходят от постфиксных нотаций,
   что требует дополнительного импортирования import scala.language.postfixOps

 */
}

object Operators_Операторы extends App {
/*
  Операторы в Scala на самом деле являются методами.
   А это значит, что к ним тоже вполне можно обратиться через точку:

   ПРИМЕР НИЖЕ:
*/
  println(1 + 2)  // обычное обращение
  println(1.+(2))  // обращение через точку



  // ПРИМЕР НОТАЦИИ С КЛАССАМИ:

  class Person(val name: String, occupation: String) {
    def workAs(jobName: String): Boolean = jobName == occupation
    def speaksEnglish: Boolean = true

    def &(person: Person): String = s"${this.name} and ${person.name} are colleagues / являются коллегами"
  }

  val bob = new Person("Bob", "Developer")
  val alice = new Person("Alice", "Data Engineer")

  println(bob.&(alice)) // точечная нотация
  println(bob & alice) // инфиксная нотация
}


object ПРЕФИКСНАЯ_НОТАЦИЯ_PREFIX_NOTATION extends App {
  import scala.language.postfixOps

// ПРЕФИКС UNARY_ ПОДХОДИТ ТОЛЬКО ДЛЯ + - ~ !
  val x = -1
  val x1 = 1.unary_-
  println(s"${x} == $x1" + s" = ${x == x1}") // true

  class Person(val name: String, occupation: String) {
    def workAs(jobName: String): Boolean = jobName == occupation
    def speaksEnglish: Boolean = true
    def &(person: Person): String = s"${this.name} and ${person.name} are colleagues / являются коллегами"

    // ПРЕФИКС UNARY_ ПОДХОДИТ ТОЛЬКО ДЛЯ + - ~ !
    def unary_! : String = s"$name is not real / не настоящий"

  }

  val bob = new Person("Bob", "Developer")

//  Вызвать unary_! мы можем тремя способами:

  println(!bob) // префиксная нотация
  println(bob.unary_!) // точечная нотация
  println(bob unary_!) // постфиксная нотация

// ВЫВОД: Bob is not real / не настоящий
}

object Method_Apply_Метод_Применить extends App {

  class Person(val name: String, occupation: String) {
    def worksAs(jobName: String): Boolean = jobName == occupation
    def speaksEnglish: Boolean = true
    def &(person: Person): String = s"${this.name} and ${person.name} are colleagues / являются коллегами"
    def unary_! : String = s"$name is not real"


    def apply(): String = s"$name works as a $occupation"
  }

  val bob = new Person("Bob", "Developer")

  println(bob.apply())
  println(bob()) // используется наиболее часто

/*

 Как только вы видите какой-то объект класса,
  который подозрительно похож на функцию, знайте - все дело в apply.

 */
}

object Task_13_1 extends App {

//    ОТМЕТЬТЕ ВСЕ ВОЗМОЖНЫЕ СПОСОБЫ ВЫЗОВА APPLY:

  class Person(val name: String, occupation: String) {
    def apply(company: String): String = s"$name works as a $occupation in $company"
  }

  val bob = new Person("Bob", "Developer")

  // ВСЕ СПОСОБЫ РАБОЧИЕ

  println(bob("Samsung"))
  println(bob("Samsung"))
  println(bob.apply("Samsung"))

/*
  в случае с apply мы может опустить название этого метода,
   и тогда у нас останутся только скобки с аргументами этого метода,
    т.е. получаем что-то вроде краткой версии println(bob apply("Samsung"))

    ПРИМЕР НИЖЕ:
 */

  println(bob apply "Samsung")

}

object Task_13_2 extends App {

  import scala.language.postfixOps

  class Person(val name: String, occupation: String) {
    def worksAs(jobName: String): String = s"$name is a $jobName"

    // ВОЗМОЖНОСТЬ РЕАЛИЗОВЫВАТЬ ФУНКЦИИ С POSTFIX
    def isDeveloper: String = this worksAs "Scala Developer"
  }

  val bob = new Person("Bob", "Developer")
  println(bob isDeveloper) // ВЫВОД Bob is a Scala Developer

}

object Task_13_3 extends App {

  import scala.language.postfixOps

  class Person(val name: String, occupation: String) {
    def worksAs(jobName: String): String = s"$name is a $jobName"

// ВАРИАНТЫ НАПИСАНИЯ ЭТОЙ ФУНКЦИИ С POSTFIX'сом

    def isDeveloper1: String = this worksAs ("Scala Developer")
    def isDeveloper2: String = this.worksAs("Scala Developer")
    def isDeveloper3: String = worksAs("Scala Developer")
  }

  val bob = new Person("Bob", "Developer")
  println(bob isDeveloper1)
  println(bob isDeveloper2)
  println(bob isDeveloper3)

}

/*

  Напишите функцию для класса Person, которая будет срабатывать на вызов:

  val person = new Person("Bob")
  println((+person).name)

 */

object Task_13_4 extends App {

  import scala.language.postfixOps

  class Person(val name: String) {
    def unary_+ = new Person(s"${this.name} NoSurname")

  }

  val person = new Person("Bob")
  println((+person).name)
}