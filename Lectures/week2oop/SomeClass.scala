package Lectures.week2oop


/*

Для просмотра как код воспринимает компилятор, можно воспользоваться ->

scalac -Xprint:constructor src/Lectures/week2oop/ClassesOOP_классы.scala

Для этого нужно скачать по этой ссылке ->
https://scala-lang.org/download/
библиотеки для работы со скалой на Windows

Засунуть в комментарий -> println(student.name)
 */


/*

параметр класса еще не член класса,
доступ к которому можно получить через точку.

Чтобы этот доступ сделать возможным -
необходимо добавить ключевое слово val или var,
прописав его перед параметром.
Тогда мы получим поле класса, к которому можно при необходимости обратиться.

 */

//class Student(id: Int, val name: String)

//val student = new Student(id = 0, name = "Bob")
//println(student.name)

/*

Дополнение по поводу доступности параметров конструктора класса

Параметры класса, объявленные без val/var,
доступны только внутри класса, снаружи к ним доступа нет.

Для понимания, почему так происходит, рассмотрим пример:

 */

class SomeClass(a: Int, b: Int, val c: Int) {
  def someFunc(): Int = b
}

/*

a - существует только в качестве локальной переменной конструктора.
Тогда доступность / недоступность объясняется областью видимости

b -  использовано в функции (ключевое слово - функция) внутри класса,
поэтому доступность / недоступность объясняется тем, что b является private полем

c - наличие val делаетс полем класса,
для которого существует геттер, что и объясняет доступность вне класса

 */

//object ClassesOOP_классы extends App {
//
//}



/*

ОТВЕТЫ НА ВОПРОСЫ:

Число аргументов конструктора класса всегда совпадает с числом полей класса?
ДА,
Параметр конструктора становится полем класса только после добавления в объявление val или var

Является ли color полем класса?
class Car(model: String) {
  val color = "blue"
}
Да, является



 */


object OOPBasics extends App {

  val student = new Student(0, "Bob")
  println(student.name)

  //    student.uni <- Вызов переменной из класса
}

class Student(id: Int, val name: String) {

  //    val uni = "University" <- СОздание переменной в классе

  println("Student Class")

}


/*

 Как видите, значение name печатается последним.
 Это связано с тем, СНАЧАЛА КЛАСС - ПОТОМ ЕГО ЭКЗЕМПЛЯР
 что при создании экземпляра класса автоматически исполняются все конструкции,
 описанные внутри класса.

 */


/*
 Ключевой момент, который вам стоит запомнить - это то,
 как использование ключевого слова this влияет на результат программы.
 Рассмотрим действие this на примере getId - внимание на результат:
 */


/*

  Задание ->

  Расположите значения, выводимые принтом, в порядке их появления на экране.

  class Student(val name: String)

  class Course(val title: String, val instructor: String) {
    val id = "cs_101"
    println(id)
  }

  val course = new Course("Scala", "Bob")
  val student = new Student("Sam")

  println(student.name)
  println(course.title)


  Ответ:
    cs_101
    Sam
    Scala

 */


// МЕТОДЫ КЛАССА

/*
Ссылка на работу ->

https://drive.google.com/file/d/1yi_goQaCSPfHPwgZvexIgXo9muWsMfLi/view?usp=sharing

 */

class Student2(id: Int, val name: String) {

  def getId(name: String, id:Int): String = s"${this.name} has ID ${this.id} and ${name} has ID ${id}"

}





// this позволяет компилятору различать
// параметры класса и параметры метода класса.

// То есть он не создает новый Id и name, а проверяет существующие





object OOPBasics2 extends App {

  val student2 = new Student2(id = 1, name = "Sam")
  println(student2.getId(name = "Pam", id = 2))

  //    Вывод -> Sam has ID 1 and Pam has ID 2

}


// ПЕРЕЗАГРУЗКА МЕТОДА(Overloading)

/*
Позволяет иметь функции с одинаковым названием.

ЕДИНСТВЕННОЕ УСЛОВИЕ - чтобы набор аргументов и(или) их тип был разный,

чтобы компилятор мог понять, вызов какой именнно функции вам требуется.
 */


class Student3(id: Int, val name: String) {

  def getId(name: String, id: Int): String =
    s"${this.name} has ID ${this.id} and $name has ID $id"


  //   Получение последнего ID
  def getId: String = s"here is $name's ID $id"

}



object OOPBasics3 extends App {

  val student3 = new Student3(id = 1, name = "Sam")
  student3.getId(name = "Pam", 2)
  student3.getId

}


// ПЕРЕГРУЖЕННЫЕ КОНСТРУКТОРЫ

// https://drive.google.com/file/d/1bi1ujJ97sAQdmlNYZFmidfmFY_BQbJIs/view?usp=share_link


/*
Класс может иметь несколько конструкторов.
Это достигается за счет использования def this.

Допустим, нам нужен конструктор,
который бы по умолчанию использовал 0 в качестве значения id:
 */

class Student4(id: Int, val name: String) {

  def this(name: String) = this(0, name)
  def this() = this(0, "NoName")

  def getId: String = s"here is $name's ID $id"

}

object OOPBasics4 extends App {

  val noStudent = new Student4()
  val newStudent = new Student4(name = "Will")
  val student = new Student4(id = 1, name = "Sam")

  println(noStudent.getId)
  println(newStudent.getId)
  println(student.getId)

}


// Более элегантной альтернативой будет
// задание значения по умолчанию в конструкторе класса.


class Student5(id: Int = 0, val name: String)

object OOPBasics5 extends App {

  val student =  new Student5(id = 1, name = "Sam")
  val newStudent = new Student5(name = "Will")

  println(student)
  println(newStudent)
}


// Задание

/*
    class Employee(val name: String, var salary: Double) {
        // здесь пропущена строка кода
    }

    val employee = new Employee()
    println(s"${employee.name}'s salary is ${employee.salary}")
 */



//class Employee(val name: String, var salary: Double) {
//    def this(name: String) = this(name, 0.0)
//}
//
//val employee = new Employee()
//println(s"${employee.name}'s salary is ${employee.salary}")

object Employee1 extends App {
  class Employee(val name: String, val salary: Double) {
    def this() = this("John", 0.0)
  }

  var employee = new Employee()
  println(s"${employee.name}'s salary is ${employee.salary}")
}


/*

Instructor: id, name, surname

Методы класса:

- fullName: возвращает полное имя в виде Имя Фамилия.

 первые буквы имени и фамилии обязательно должны быть заглавными,
 а на вход вполне могут передаваться данные,
 состоящие полностью как из больших букв, так и из маленьких.



Course: courseID, title, releaseYear, instructor

Методы класса:
- getID: возвращает id в формате courseID + instructor.id (например, если courseId = 1,
 а instructor.id = 2, то метод вернет 12)
- isTaughtBy(instructor): проверяет, является ли указанный человек инструктором курса
- copyCourse(newReleaseYear): возвращает новый экземпляр класса Course с обновленным годом релиза



Перед тем, как вы приступите к программированию, давайте удостоверимся, что вы понимаете, какие типы должны быть использованы

name, surname, fullName, title, releaseYear, getID - String
courseId, id - Int
instructor (в конструкторе класса Course), instructor (в методе isTaughtBy) - Instructor
isTaughtBy - Boolean
copyCourse - Course

 */


object OOP_task extends App {

  val instructor = new Instructor(0, "aaa", "bbb")
  println(instructor.fullName)
  val course: Course = new Course(0, "Title1", releaseYear = "1999", instructor = instructor)
  println(course.getID)
  println(course.isTaughtBy(instructor))
  println(course.isTaughtBy(new Instructor(1, "ofAr", "GuardPen")))

  //    Для изменения переменной используя функцию с переменной из другого класса

  println(course.copyCourse("4321").releaseYear)

}


class Instructor(val id: Int = 1, name: String, surname: String) {
  /*
  Альтернативный вариант ->
  1.
      val name1 = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()
      val surname1 = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase()

  2.
      private val name1 = name.capitalize
      private val surname1 = surname.capitalize
      def fullName: String = s"$name1 $surname1"
   */

  def fullName: String = s"${name.capitalize} ${surname.capitalize}"

}

class Course(courseID: Int = 1, title: String, val releaseYear: String, instructor: Instructor) {

  private val ID: Int = (courseID.toString + instructor.id.toString).toInt
  def getID: String = ID.toString
  def isTaughtBy(instructor1: Instructor): Boolean = this.instructor.id == instructor1.id

  /*

  ДЛЯ ТОГО, ЧТОБЫ ЗАДАТЬ В РУЧНУЮ В КЛАССЕ ->

  def copyCourse(newReleaseYear: String): String = releaseYear.replaceAll(releaseYear, newReleaseYear)
   */

  def copyCourse(newReleaseYear: String): Course = new Course(courseID, title, newReleaseYear, instructor)
}

/*

ВЫВОД ИЗ ЗАДАНИЯ :

переменные сделанные в качестве аргумента в классе (val, var),
например Instructor(val id: Int = 1) , Course(val releaseYear: String)
могут вызываться в самой программе и изменять значения, например:

println(course.copyCourse("4321").releaseYear)

Из-за создания нового элемента ->
def copyCourse(newReleaseYear: String): Course = new Course(courseID, title, newReleaseYear, instructor)

 */