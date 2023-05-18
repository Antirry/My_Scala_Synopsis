package Lectures.week2oop

object Классы_образцы_Case_Classes extends App {

  /*

  КЛАССЫ ОБРАЗЦЫ :

    Набор методов, которые всегда придутся кстати для любого класса.
     Именно доступ к таким методам и дают классы образцы,
      избавляя вас от необходимости имплементировать эти методы вручную.

   */
}





// ВЫЗОВ КЛАССА ОБРАЗЦА
object _1_Example_case_class {
  case class Person(name: String, occupation: String)

  def main(args: Array[String]): Unit = {
    val bob = new Person("Bob", "Developer")
    println(bob.name)
    println(bob.occupation)
  }
}

// ОБЫЧНЫЙ КЛАСС
object _2_Example_class {
  class Person(val name: String, occupation: String)

  def main(args: Array[String]): Unit = {
    val bob = new Person("Bob", "Developer")
    println(bob.name)
/*
    ОШИБКА :
      println(bob.occupation)
*/
  }
}

/*

  ВЫВОД _1_Example_case_class - bob.name - автоматический доступ
  ВЫВОД _2_Example_class - bob.name - ДОСТУП ЧЕРЕЗ (val) name: String

 */

// ВЫВОД
object Example_Main1 extends App {
  _1_Example_case_class.main(Array())
  _2_Example_class.main(Array())

}





// ВЫВОД ИНФОРМАЦИИ

object _3_Output_Example_case_class {
  case class Person(name: String, occupation: String)

  def main(args: Array[String]): Unit = {
    val bob = new Person("Bob", "Developer")
    println(bob)
  }
}

object _4_Output_Example_class {
  case class Person(name: String, occupation: String)

  def main(args: Array[String]): Unit = {
    val bob = new Person("Bob", "Developer")
    println(bob)
  }
}

// ВЫВОД
object OUTPUT_Main2 extends App {
  _3_Output_Example_case_class.main(Array())
  _4_Output_Example_class.main(Array())

}





/*
РАБОТАЕТ МЕТОД EQUALS

Одним из свойств case class’ов является обязательное наличие списка параметров (пусть даже пустого),
 что объясняет существование прописанного специально для них метода equals,
  представляющего content level equality и позволяющего производить сравнение по структуре, а не по ссылкам.

 */

object _5_Equals_case_class {
  case class Person(name: String, occupation: String)

  def main(args: Array[String]): Unit = {
    val bob = new Person("Bob", "Developer")
    val bobsDouble = new Person("Bob", "Developer")

    println(bob == bobsDouble) // true
  }
}

object _6_Equals_class {
  class Person(name: String, occupation: String)

  def main(args: Array[String]): Unit = {
    val bob = new Person("Bob", "Developer")
    val bobsDouble = new Person("Bob", "Developer")

    println(bob == bobsDouble) // false
  }
}

object EQUALS_Main3 extends App {
  _5_Equals_case_class.main(Array())
  _6_Equals_class.main(Array())

}





/*

Доступен метод copy

  Метод позволяет как полностью скопировать экземпляр класса,
   так и скопировать с измененными аргументами конструктора.

 */

object _7_Copy_CASE_CLASSES extends App {

  case class Person(name: String, occupation: String)

  val bob = Person("Bob", "Developer")

  val anotherBob = bob.copy()
  println(bob)
  println(anotherBob)

  val bobsTwin = bob.copy(name = "John")
  println(bobsTwin) // Person("John", "Developer")

}




/*

Любой case класс имеет объект-компаньон
 В таком объекте-компаньоне всегда присутствует метод apply.

 Которым можно воспользоваться и сделать так:

 */

object _8_Объект_Компаньон_CASE_CLASS_COMPANION_OBJECT extends App {
  case class Person(name: String, occupation: String)

  // Создаем экземпляр класса без использования ключевого слова new.

  val alice = Person("Alice", "Engineer") // метод apply в действии

}

object Task_14_1 extends App {

  case class Course(title: String, instructor: String)

  object Course {
    def apply(instructor: String): Course = Course("0__AdvancedScala", instructor)
  }

  val scalaCourse = Course("Scala", "Bob") // CASE CLASS

  val course0 = new Course("Scala", "Bob") // CASE CLASS
  val course1 = Course("Scala", "Bob") // CASE CLASS
  val course2 = scalaCourse.copy() // CASE CLASS
  val course3 = Course("Alice") // OBJECT
  val course4 = scalaCourse.copy("AdvancedScala") // CASE CLASS

  println((course0, course1, course2, course3, course4))

/*
ВЫВОД :
  (Course(Scala,Bob),Course(Scala,Bob),Course(Scala,Bob),Course(0__AdvancedScala,Alice),Course(AdvancedScala,Bob))


 ОШИБОЧНЫЙ ВАРИАНТ :
   //  val course = new Course("Scala") // ОБЪЕКТ COURSE (С ОДНИМ ПАРАМЕТРОМ)

    НЕ ИМЕЕТ СПОСОБА СОЗДАНИЯ ЭКЗЕМПЛЯРОМ
 */
}

object Task_14_2 extends App {

/*
Cопоставьте код и результат его работы ->


  case class Course(title: String, instructor: String)
  val course = Course("Scala", "Bob")
  println(course.title)                         // ВЫВОД Scala

  class Course(title: String, instructor: String)
  val course = new Course("Scala", "Bob")
  println(course.title)                         // ВЫВОД error

  case class Course(title: String, instructor: String)
  val course = new Course("Scala", "Bob")
  println(course)                               // ВЫВОД Course(Scala,Bob)

  class Course(title: String, instructor: String)
  val course = new Course("Scala", "Bob")
  println(course)                               // ВЫВОД week2oop.CaseClasses$Course@150c158

 */

}