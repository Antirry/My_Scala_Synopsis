package Lectures.week2oop

import scala.util.chaining.scalaUtilChainingOps


/*
Закономерный вопрос - а зачем это надо?

Так вот, может оказаться, что потребуется создать переменные или методы,
доступные в любое время,
причем без дополнительного объявления экземпляров класса (что-то такое универсальное).

ССЫЛКА по поводу объектов -> https://drive.google.com/file/d/1FvENp55faS34JvdIyikGSn6d7k1uwupl/view?usp=sharing
 */

object ObjectsOOP_Объекты extends App {

  object ObjectsOOP {
    val Pi = 3.14
  }

  println(ObjectsOOP.Pi)

}
/*

Объекты Scala могут содержать в себе:
  методы и переменные

 */

/*

Объекты vs Классы

  Отличительно чертой объектов является то, что они:

    не имеют параметров
    являются одиночками (Singleton Object)

Когда мы говорим "одиночки" мы подразумеваем, что объекты существуют в единственном экземпляре.
  Можно, кстати, еще добавить,
    что у них свой тип (например, наш объект Number,
    взятый в качестве примера, типа Number)
 */

// https://drive.google.com/file/d/11RQtEQadqXTxWESaktz2nQ8DXufYLFfl/view?usp=sharing

object Check_Object_vs_Classes extends App {

  // Утверждение про одиночек может быть легко проверено:

  object Number {
    val Pi = 3.14
  }

  val numA = Number.Pi // 3.14
  val numB = Number.Pi // 3.14

  println(numA == numB) // выведет true

  // Результат будет отрицательным,
  // т.к. теперь дело будем иметь с двумя разными экземплярами класса.

  class Number

  val numC = new Number
  val numD = new Number

  println(numC == numD) // выведет false

}

// КОМПАНЬОНЫ


/*

  Если в одном и том же файле и под одним и тем же именем объявить объект и класс,
  то в таком случае их можно смело назвать компаньонами.
  Объект имеет доступ ко всем полям и методам своего класса-компаньона (в том числе и private).


  РАЗБОР КОДА -> https://drive.google.com/file/d/1tWYy6zZZSTjlhTB2kKsmHm6HqwRp4k4x/view?usp=sharing

  Дополнительная инфа (код тот же, но цель - подробнее показать работу вызываемых методов):
  ССЫЛКА -> https://drive.google.com/file/d/1Z42bDa-5FVSVvqHgQT8_q4XWmkAqwiOc/view?usp=sharing
 */


object Companions extends App {
  class MyString(val str: String) {

    private var extra = "extraData"
    def getString: String = str + extra

  }

  object MyString {

    def apply(base: String, extras: String): MyString = {
      val s = new MyString(base)
      s.extra = extras
      s
    }

    def apply(base: String) = new MyString(base)

  }

  println(MyString.apply("hello", "world").getString)
  println(MyString.apply("welcome").getString)
}

// ОПТИМИЗАЦИЯ КОДА

/*

Смотрите, тут два ключевых момента,
оба связаны с синтаксическим сахаром
(подробно о нем поговорим чуть позже в этом модуле):

1. apply можно не прописывать, а сразу после имени объекта
    в скобках указывать параметры apply метода (позже apply будет разбираться более подробно)

2. переименовав getString в toString,
    мы избавили себя от необходимости вообще прописывать
    имя метода для его вызова

// опускаем наименование метода apply и сразу пишем необходимые для этого метода параметры

 */

object Reformat_Code extends App {
  class MyString(val str: String) {

    private var extra = "extraData"
    override def toString: String = str + extra

  }

  object MyString {

    def apply(base: String, extras: String): MyString = {
      val s = new MyString(base)
      s.extra = extras
      s
    }

    def apply(base: String) = new MyString(base)
  }

  println(MyString.apply("hello", "world"))
  println(MyString.apply("welcome"))
}


// ФАБРИЧНЫЙ МЕТОД (Factory method)


/*

У объекта, как мы выяснили ранее, отсутствуют передаваемые параметры.

Но если все же надо как-то имплементировать
 усложненный интерфейс для создания объекта,
 а конструктор класса менять не айс,
 то выкрутиться поможет фабричный метод.

ССЫЛКА НА ОПИСАНИЕ КОДА -> https://drive.google.com/file/d/1qmUR7BupbIJCgEvN87b6OilBlM_p3x5l/view?usp=sharing
 */


object Factory_Method extends App {
  class Number(val num: Int)

  object Number {

    val Pi = 3.14

    def apply(x: Number, y: Number): Number = new Number(x.num + y.num)

  }

  val numA = new Number(1)
  val numB = new Number(2)

  val numC = Number(numA, numB)

  println(numA.num)
  println(numB.num)
  println(numC.num)
}

object Task_8_1 extends App {

// ОШИБОЧНЫЙ КОД ->

  //  ОШИБКА - вместо object Person(name: String) должно быть object Person

//  class Person(name: String)
//
//  object Person(name: String) {
//    val age = 30
//  }
//
//  val bob = Person("Bob")
//  println(Person.age)

}


/*

  Итак, мы занимаемся разработкой приложения для банка.
  Наше приложение помогает регистрировать нового пользователя в системе,
  что происходит при открытии клиентом нового счета в банке.

  Приложение поддерживает создание аккаунтов двух типов: персонального(Personal) и бизнес(Business).
  Причем функционал прописывается как для головного офиса(HeadOffice), так и для филиалов банка(Branch).

 */


// Неплохо, но этот путь просто добавит
// разработчику головной боли в будущем
// ->


object Task_8_2_0 extends App {
  class PersonalAccount

  class BusinessAccount

  class Branch {

    def openAccount(accountType: String) = {
      if (accountType == "business") new BusinessAccount
      else new PersonalAccount
      }

    }


  val branch = new Branch()
  val account = branch.openAccount("business")
}


/*

Представьте, что банк решил внедрить еще один тип аккаунта.

Это приведет к необходимости менять код openAccount, расширяя логику if-else. Все бы ничего,
если бы Branch было единственным местом, где такой метод существует.

А ведь где-то там есть еще и HeadOffice (а может, и еще что-то).
Зная масштаб проекта, представьте, сколько кода придется перелопачивать.


ПРИМЕЧАНИЕ: обычно открытие счета сопровождается всякими
дополнительными проверками вроде установления личности и т.д.
Все это разбивается на отдельные методы,
вызов которых происходит в openAccount
это мы к вопросу о том, а зачем нужно вызывать Account именно внутри openAccount в Branch)

 */

// РЕШЕНИЕ: выносим логику по созданию аккаунта в object Account.

object Task_8_2_1 extends App {

  class PersonalAccount {
    println(PersonalAccount.this)
  }
  class BusinessAccount {
    println(BusinessAccount.this)
  }

  class Branch {

    def openAccount(accountType: String): Unit = {
      Account(accountType)
    }

  }

  object Account {
    def apply(accountType: String): Object = {

      if (accountType == "business") new BusinessAccount
      else new PersonalAccount

    }
  }

  val branch = new Branch()
  val account = branch.openAccount("business")

}
