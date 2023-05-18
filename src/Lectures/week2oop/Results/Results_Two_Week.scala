package Lectures.week2oop.Results

class Results_Two_Week {

//  Отметьте верные утверждения.

/*

 + Тип блока кода соответствует типу последнего выражения, описанного в блоке кода

 + При использовании sealed все наследники класса должны находиться в одном файле

 + Класс, заданный ключевым словом class, не может содержать абстрактных методов

 + print имеет тип Unit

 + Сравнение case классов между собой происходит поэлементно

 + Существуют конструкции, описанные внутри тела класса,
  которые выполняются каждый раз при создании экземпляра класса

 */

}

object Task2 extends App {

  class A(val a: String) {
    println("class A")
  }

  val aVal = new A("val")
  val anotherVal = new A("another val")

  println(aVal.a)
  println(anotherVal.a)

/*

ВЫВОД :

Сначала вызывается класс, соответственно,
 вывод class A class A ,
  потом вывод переменных aVal, anotherVal

  class A
  class A
  val
  another val

 */

}

object Task3 extends App {
  object A {
    val a: String = "value a"
    println("object A")
  }

  val aVal = A
  val anotherVAl = A

  println(aVal.a)
  println(anotherVAl.a)

/*

ВЫВОД:

ОБЪЕКТ ВЫЗЫВАЕТСЯ ОДИН РАЗ , ПОЗЖЕ ВЫЗОВ ПЕРЕМЕННЫХ

  object A
  value a
  value a
 */

}