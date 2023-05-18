package Exercises.week2oop

object _3_ extends App {
/*
  Задача, стоящая перед нами теперь: модифицировать код так,
   чтобы все методы отлично работали.
    Независимо от того, список какого типа мы предпочтем для хранения информации.
 */
}
/*
object параметр_типа_parameter_type_LogList extends App {
/*
  Каким должен быть параметр типа для класса LogList?

  [+A]
 */

  abstract class LogList[+A] {
    def add[B >: A](msg: B): LogList[B]

    def all: String
    def pervious: String
    def last: String
    def isEmpty: Boolean
  }
/*
  Как теперь выглядит объявление класса Log?

class Log[+A](h: A, t: LogList[A]) extends LogList[A]
 */
  class Log[+A](val head: A, val tail: LogList[A]) extends LogList[A] {
    def add[B >: A](msg: B): LogList[B] = new Log(msg, this)

    def all: String = head + " " + tail.all
    def pervious: String = tail.last
    def last: String = head
    def isEmpty: Boolean = if (tail.isEmpty) true else false

  }
  object Empty extends LogList[String] {
    def add[B >: String](msg: B): LogList[B] = new Log(msg, Empty)

    def all: String = ""
    def pervious: String = throw new NoSuchElementException
    def last: String = throw new NoSuchElementException
    def isEmpty: Boolean = true
  }

}
 */

object тип_параметра_parameter_type_LogList extends App {

  class LinkedList1 {
    abstract class LogList[-A] {
      def add[B <: A](msg: B): LogList[B]

      def all: String
      def pervious: String
      def last: String
      def isEmpty: Boolean
    }

    class Log[A](val head: A, val tail: LogList[A]) extends LogList[A] {
      def add[B <: A](msg: B): LogList[B] = new Log(msg, this)

      def all: String = head + " " + tail.all
      def pervious: String = tail.last
      def last: String = head.toString
      def isEmpty: Boolean = if (tail.isEmpty) true else false
    }

    object Empty extends LogList[String] {
      def add[B <: String](msg: B): LogList[B] = new Log(msg, Empty)

      def all: String = ""
      def pervious: String = throw new NoSuchElementException
      def last: String = throw new NoSuchElementException
      def isEmpty: Boolean = true
    }

    val list = new Log("1_INFO", new Log("2_INFO", new Log("3_INFO", Empty)))
  }

  val ll: LinkedList1 = new LinkedList1
  println(ll.list.all)
  println(ll.list.pervious)
  println(ll.list.last)
  println(ll.list.isEmpty)

  val emptyList = ll.Empty
}




// ВТОРОЙ ВАРИАНТ ->




object _2_тип_параметра_parameter_type_LogList extends App {

  class LinkedList2 {
    abstract class LogList[+A] {
      def add[B >: A](msg: B): LogList[B]

      def all: String
      def previous: String
      def last: String
      def isEmpty: Boolean
    }

    class Log[+A](val head: A, val tail: LogList[A]) extends LogList[A] {
      def add[B >: A](msg: B): LogList[B] = new Log(msg, this)

      def all: String = head + " " + tail.all
      def previous: String = tail.last
      def last: String = head.toString
      def isEmpty: Boolean = if (tail.isEmpty) true else false
    }

    object Empty extends LogList[String] {
      def add[B >: String](msg: B): LogList[B] = new Log(msg, Empty)

      def all: String = ""
      def previous: String = throw new NoSuchElementException
      def last: String = throw new NoSuchElementException
      def isEmpty: Boolean = true
    }

    val list = new Log("1_INFO", new Log("2_INFO", new Log("3_INFO", Empty)))
  }

  val ll: LinkedList2 = new LinkedList2
  println(ll.list.all)
  println(ll.list.previous)
  println(ll.list.last)
  println(ll.list.isEmpty)

  val emptyList = ll.Empty
}