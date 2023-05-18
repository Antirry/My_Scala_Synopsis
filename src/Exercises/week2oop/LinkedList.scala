package Exercises.week2oop

/*

1. last - возвращает последнее поступившее сообщение (иначе говоря - head списка)
2. previous - возвращает все ранее поступившие собщения(это будет tail или хвост списка),
 за исключением самого последнего (т.е. за исключением head)
3. isEmpty - проверяет, если ли в нашем списке сообщения
4. all - предоставляет нам содержимое нашего списка в виде строки
5. add(msg) - добавляет сообщение в список

LogList(тут указаны требуемые нам методы)
Log (хранит методы для непустого списка)
Empty (описывает методы для пустого списка)
*/

object Определение_классов_Definition_classes extends App {
/*

  LogList  -  abstract class
  Log  -  class
  Empty  -  object

Log и Empty содержат в себе одинаковые методы, которые просто применяются по-разному в зависимости от ситуации.
  Логично, что Log и Empty имеют один и тот же супер класс
  (Суперкласс: класс, функции которого наследуются, известен как суперкласс (или базовый класс, или родительский класс),
  например class _1_ , class _2_ extends _1_
  class child_class_name extends parent_class_name {
    // Methods and fields
  }).
  LogList является абстрактным,
т.к. нам нужен класс, который бы содержал в себе список методов, которые необходимо реализовать по-разному в соответсвующих подклассах
*/
}

object Описание_для_классов_Description_for_classes extends App {
/*

abstract class LogList  -  ничего не требуется, можно открывать скобки {}
object Empty  -  extends LogList
class Log  -  (head: String, tail: LogList) extends LogList

*/
}

object Реализация_методов_Implementation_methods extends App {
/*

def add(msg: String): LogList  -  LogList
def add(msg: String): LogList = new Log(msg, this)  -  Log
def add(msg: String): LogList = new Log(msg, Empty)  -  Empty

*/
}

object Пример_односвязного_списка_Example_single_link_list extends App {
/*
Как работает односвязный список. Какой код сконструирует список a, b, c ?

  val list = new Log("b", new Log("c", Empty))
  val newList = list.add("a")

  val list = new Log("a", new Log("b", new Log("c", Empty)))

ВЫВОД :
  1)  Лист всегда должен заканчиваться на Empty, это как маркер для останова рекурсии
  2) Метод add добавляет элемент в голову, а не в хвост, как может показаться на первый взгляд
*/
}

object Односвязный_список_В_Задании_Single_Linked_List_In_Task extends App {
/*

    val list = new Log("INFO_1", new Log("INFO_2", new Log("INFO_3", Empty)))

Какой элемент выведется на  экран в результате исполнения кода:

    println(list.previous.last)

ОТВЕТ: INFO_2
 */

}



object ИТОГОВЫЙ_КОД_TOTAL_CODE extends App {

  class LinkedList {

    abstract class LogList {
      def add(msg: String): LogList
      def all: String
      def pervious: String
      def last: String
      def isEmpty: Boolean
    }

    class Log(val head: String, val tail: LogList) extends LogList {
      def add(msg: String): LogList = new Log(msg, this)

      def all: String = head + " " + tail.all
      def pervious: String = tail.last
      def last: String = head
      def isEmpty: Boolean = if(tail.isEmpty) true else false

    }

    object Empty extends LogList {
      def add(msg: String): LogList = new Log(msg, Empty)

      def all: String = ""
      def pervious: String = throw new NoSuchElementException
      def last: String = throw new NoSuchElementException
      def isEmpty: Boolean = true
    }

    val list = new Log("1_INFO", new Log("2_INFO", new Log("3_INFO", Empty)))
  }

  val ll: LinkedList = new LinkedList
  println(ll.list.all)
  println(ll.list.pervious)
  println(ll.list.last)
  println(ll.list.isEmpty)

  val emptyList = ll.Empty
}
