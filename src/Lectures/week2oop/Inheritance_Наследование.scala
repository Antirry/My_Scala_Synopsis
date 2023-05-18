package Lectures.week2oop

import Lectures.week2oop.sealed_abstract_class.DayOfTheWeek
import com.sun.jdi.event.Event


// НАСЛЕДОВАНИЕ

/*

Благодаря наследованию - класс может получить доступ к полям
и методам другого класса (с условием, что те не отмечены, как private).

Наследование производится посредством ключевого слова extends.

 */

/*
 Давайте объявим класс Person,
    в котором создадим метод greet.

 И затем создадим новый класс Student,
    расширяющий ранее созданный класс Person.
 */

object Inheritance_Наследование extends App {
  class Person {

    def greet: String = "Hello"

  }

  class Student extends Person

  val student = new Student
  println(student.greet)

  /*
  Как видите, если создать экземпляр класса Student,
  будет возможно обратиться к методу greet класса Person.

  А все благодаря наследованию.

  https://drive.google.com/file/d/17yfNHvHva1lTPm2rqYZs1gyKLWjFlYCe/view?usp=sharing
   */

  class Parent

  class Grandparent

  // ОШИБКА -> нельзя указать несколько классов после extends

//  class child extends Parent, Grandparent {
//    println("I'm a child")
//  }

  // Правильный код
  class child extends Parent {
    println("I'm a child")
  }

  class child1 extends Grandparent {
    println("I'm a child1")
  }
}


// PROTECTED VS PRIVATE

/*

При наследовании у нас появляется доступ ко всем полям и
методам родительского класса, которые не являются private.

Ключевое слово private, написанное перед методом и/или переменной,
делает этот метод и/или переменную доступным только для того класса,
в котором он и/или она были описаны:

 */

// НЕ РАБОЧИЙ ВЫЗОВ ИЗ-ЗА PRIVATE (student.greet)

object private_def extends App {

  class Person {
    private def greet: String = s"Hello"
  }

  // НЕ ПРАВИЛЬНЫЙ КОД ->

//  class Student extends Person {
//    println(greet)
//  }
//
//  val student = new Student
//  println(student.greet)

}
/*

protected работает немного по другому,

делая отмеченные поля и методы доступными для класса и его подкласса,
но недоступными вне их тел

ССЫЛКА -> https://drive.google.com/file/d/1YQ83KrFUJlvqdN3nQ5vKvtfLwCN5lvgu/view?usp=sharing
 */

object def_protected extends App {

class Person {
  protected def greet: String = s"Hello"
}

class Student extends Person {
  println(greet)
}

  // НЕ ПРАВИЛЬНЫЙ КОД ->

//val student = new Student
//println(student.greet)

}


// PUBLIC Открыт во всём коде


object def_PUBLIC extends App {

  class Person {
    def greet: String = s"Hello"
  }

  class Student extends Person {
    println(greet)
  }

  val student = new Student
  println(student.greet)

}


/*

1) посредством private контролируется видимость конструктора класса,
т.е. мы позволяем создание экземпляра класса только через методы объекта.

2) для .toString существует синтаксический сахар,
поэтому println(pizza) на самом деле println(pizza.toString)

 */


// С ИСПОЛЬЗОВАНИЕМ OVERRIDE : для автозапуска через класс

//object Task_9_1_1 extends App {

  class Pizza private {
    override def toString: String = "This is Pizza"
  }

  object Pizza {

    val pizza = new Pizza

    def getInstance: Pizza = {
      pizza
    }
  }

  object TestPizza extends App {

    val pizza = Pizza.getInstance

      println(pizza)
  }
//}


// БЕЗ ИСПОЛЬЗОВАНИЯ OVERRIDE : нужно вызвать метод (функцию)
// для такой же работы, что и с OVERRIDE


//object Task_9_1_2 extends App {

  class Pizza1 private {
    def inString: String = "This is Pizza"
  }

  object Pizza1 {

    val pizza1 = new Pizza1

    def getInstance: Pizza1 = {
      pizza1
    }
  }

  object TestPizza1 extends App {

    val pizza1 = Pizza1.getInstance
    println(pizza1.inString)
  }
//}


// EXTEND ДЛЯ КЛАССА С ПАРАМЕТРАМИ


// Сначала потребуется совершить вызов конструктора из родительского класса.


object extend_class_1 extends App {

  // Пример без this() с обозначением Person(name, age)

  class Person(name: String, age: Int) {

    def greet: String = s"Hello"

  }

  class Student(name: String, age: Int, id: Int) extends Person(name, age) {

    println(greet)

  }
}

// Пример с this()

/*

Либо вспомогательный конструктор def this (вспоминаете начало модуля?).

И теперь компилятор будет находить конструктор класса Person,
который не требует никаких параметров, и все будет работать:

ССЫЛКА -> https://drive.google.com/file/d/1OwWONDrn56t55vuxoSuqb5mBEd8YlW0b/view?usp=sharing
 */

object extend_class_2 extends App {

  // Пример с this() без обозначения Person(name, age)

  class Person(name: String, age: Int) {

    def greet: String = s"Hello"
    def this() = this("UnknownPerson", 0)

  }

  class Student(name: String, age: Int, id: Int) extends Person {

    println(greet)

  }
}


// ПЕРЕОПРЕДЕЛЕНИЕ (OVERRIDE)


/*

Применяется, когда для подкласса хотим применить метод из родительского класса,
но с имплементацией, отличной от начальной.
Либо когда необходимо изменить значение переменной родительского класса.

Для переопределения метода или переменной используем ключевое слово override.
Причем поля, в отличие от методов, могут быть переопределены в конструкторе класса:

ССЫЛКА -> https://drive.google.com/file/d/1uEUZWGG5oDIkfji9pkuqfY3a7Ah2MJyK/view?usp=sharing
 */

object override_val_def_1 extends App {

  class Person(name: String, age: Int) {
    val gender = "n/a"

    def greet: String = s"Hello"
    def this() = this("UnknownPerson", 0)
  }


  // Переопределение val gender в конструкторе избавляет от лишнего кода
  // Переопределение метода (функции) override def greet


  class Student(name: String, age: Int, id: Int, override val gender: String) extends Person {
    override def greet: String = s"Hello, $name"
  }

  val student = new Student(name = "James", age = 24, id = 1, gender = "m")
  println(student.greet)
  println(student.gender)

}

object override_val_def_2 extends App {

  class Person(name: String, age: Int) {
    val gender = "n/a"

    def greet: String = s"Hello"
    def this() = this("UnknownPerson", 0)
  }


  // ВТОРОЙ ВАРИАНТ
  // Переопределение studGender в конструкторе избавляет от лишнего кода


  class Student1(name: String, age: Int, id: Int, studGender: String) extends Person {

    override val gender: String = studGender
    override def greet: String = s"Hello, $name"
  }

  val student1 = new Student1(name = "James", age = 24, id = 1, studGender = "m")
  println(student1.greet)
  println(student1.gender)

}


// SUPER

/*

Полезно, когда хотим воспользоваться имплементацией метода
или значением переменной из родительского класса.
И в то же время прикрутить к этому что-то свое.

Для этого всего-лишь надо применить ключевое слово super.
Смотрите на пример, сразу должно стать понятно:

ССЫЛКА -> https://drive.google.com/file/d/15-7kf24ov1Is6bR3dnS-Vu5p6Mf8mAl1/view?usp=share_link
 */


object super_override_def extends App {

  class Person(name: String, age: Int) {
    val gender = "n/a"

    def greet: String = s"Hello"
    def this() = this("UnknownPerson", 0)
  }

  class Student(name: String, age: Int, id: Int, studGender: String) extends Person {
    override val gender: String = studGender
    override def greet: String = s"${super.greet}, $name"
  }

  val student = new Student(name = "James", age = 24, id = 1, studGender = "m")
  println(student.greet)
  println(student.gender)

}


// ЗАДАНИЕ ПЕРЕОПРЕДЕЛЕНИЯ (OVERRIDE)


object Task_9_1 extends App {

// Запуск первого (основного) класса, обычная кнопка с функцией клика
  class Button(label: String) {
    // Описание основной функции - кнопка нажата
    def click(): String = s"button -$label- has been clicked"

    // Первый случай с тестом, просто запуск (new Button() - пустой случай) - нажата test
    def this() = this("test")
  }

//  Запуск второго класса со своим названием, но с функцией другой кнопки
  class RoundedButton(label: String) extends Button(label) {
    // Запуск второго случая "rounded button - закруглённая кнопка" - нажата
    override def click(): String = s"rounded ${super.click()}"
  }

  class TestButton extends Button {
    val button1 = new Button("1d41s")
    val button2 = new RoundedButton("1sr31a")
    val button3 = new Button()
    println(button1.click())
    println(button2.click())
    println(button3.click())
  }

  new TestButton

  println("Анонимные классы (Anonymous classes)".toUpperCase)
}


// ПОЛИМОРФИЗМ (POLYMORPHISM)


/*

Полиморфизм подразумевает замену типов. Смотрим пример:

ССЫЛКА -> https://drive.google.com/file/d/15ksLS9Ce-ukRQ0LEvCQ9dfEwQTvVuLuX/view?usp=share_link

Хотя и объявили переменную aPerson с типом Person,
но использовать она будет средства Student,
т.к. является экземпляром класса Student - полиморфизм в действии.

 */

object POLYMORPHISM extends App {

  class Person(name: String, age: Int) {
    val gender = "n/a"

    def greet: String = s"Hello"
    def this() = this("UnknownPerson", 0)
  }

  class Student(name: String, age: Int, id: Int, studGender: String) extends Person {
    override val gender: String = studGender
    override def greet: String = s"${super.greet}, $name"
  }

  val student = new Student("Alice", 24, 2, "f")
  println(student.greet)
  println(student.gender)

  val aPerson: Person = new Student(name = "James", age = 24, id = 2, studGender = "m")
  println(aPerson.greet)
  println(student.gender)

}

/*

Хотя и объявили переменную aPerson с типом Person,
но использовать она будет средства Student,
т.к. является экземпляром класса Student - полиморфизм в действии.

 */

/*

Отметьте утверждения, описывающие переопределение и перегрузку ->

    ПЕРЕЗАГРУКА:

ПЕРЕЗАГРУКА -> происходит внутри одного и того же класса

ПЕРЕЗАГРУКА -> влечет за собой изменение сигнатур метода

ПЕРЕЗАГРУКА -> при реализации надо учитывать, что в одном классе не должно существовать двух методов с одинаковой сигнатурой

ПЕРЕЗАГРУКА -> методы носят одинаковое имя, но отличаются параметрами

    ПЕРЕОПРЕДЕЛЕНИЕ:

ПЕРЕОПРЕДЕЛЕНИЕ -> представляет иное поведение, но имеет идентичную с методом родительского класса сигнатуру

ПЕРЕОПРЕДЕЛЕНИЕ -> тип возвращаемого значения обязательно должен быть такой же, как у метода, описанного в родительском классе

 */


// ЗАЩИТА ОТ ПЕРЕОПРЕДЕЛЕНИЯ


/*

Есть несколько способов:

1, использование ключевого слова final перед членами класса,
 для которых нужно запретить override

ССЫЛКА -> https://drive.google.com/file/d/1jtK8iUKsa-PAqQj56_2DA12VQ3U99fZM/view?usp=sharing

2. использование ключевого слова final для целого класса
 (что вообще запретит extends этого класса)

ССЫЛКА -> https://drive.google.com/file/d/1TZVA9rsmLXAuflVt2iHY1XHp07kc_L1h/view?usp=sharing

3. использование ключевого слова sealed для класса
 (это более мягкая версия final,
  поэтому допускается extends в текущем файле,
  но воспрещается вне этого файла)

ССЫЛКА -> https://drive.google.com/file/d/1r7Sg_JaagvmQMmkgdGz9tgqGaJf0YQ6v/view?usp=sharing

 */

object sealed_class extends App {

  // SEALED НУЖЕН ДЛЯ ЗАПУСКА ТОЛЬКО В ТЕКУЩЕМ ФАЙЛЕ

  sealed class Person(name: String, age: Int) {
    val gender = "n/a"

    def greet: String = s"Hello"
    def this() = this("UnknownPerson", 0)
  }

  class Student(name: String, age: Int, id: Int, studGender: String) extends Person {
    override val gender: String = studGender
    override def greet: String = s"${super.greet}, $name"
  }
}

/*

sealed довольно полезная штука,
когда надо гарантировать существование подклассов,
определяемых только в текущем файле.

Если попытаться привести практический пример, представьте, вы описываете дни недели.
Использование sealed гарантирует,

что существовать будут только те дни, что конкретизированы в текущем файле,
и нигде вдруг случайно не всплывет доселе невиданный восьмой день недели.

То, что вы будете знать,
какие дни вам доступны - создает благоприятные условия
для применения шаблонов, если они потребуются.

 */

object sealed_abstract_class extends App {

  sealed abstract class DayOfTheWeek(val name: String, val isWeekend: Boolean)

  case object Monday extends DayOfTheWeek("Monday", false)
  case object Tuesday extends DayOfTheWeek("Tuesday", false)
  case object Wednesday extends DayOfTheWeek("Wednesday", false)
  case object Thursday extends DayOfTheWeek("Thursday", false)
  case object Friday extends DayOfTheWeek("Friday", false)
  case object Saturday extends DayOfTheWeek("Saturday", true)
  case object Sunday extends DayOfTheWeek("Sunday", true)

}


// АБСТРАКТНЫЕ КЛАССЫ (ABSTRACT CLASSES)


/*

Бывают ситуации, когда небходимо в классе задать только поле или метод,
а имплементацию прописывать в подклассах, подстраивая ее под каждый конкретный случай.

В абстрактном классе абстрактные поля мы оставляем пустыми, а абстрактные методы без тела.

Это значит, что мы не можем создать экземпляр абстрактного класса,
пока абстрактные методы и поля не прописаны должным образом.

ССЫЛКА -> https://drive.google.com/file/d/1PKWlAIGkEWHzNWF-a8rKTUvnbhdjXVEv/view?usp=sharing
 */


// Абстрактные классы как раз и используются,
// если нужна совместимость с Java.
// Но там уже надо следить за наличием неабстрактных методов.

object abstract_class extends App {

  abstract class BaseDataSource(dataSourceName: String){

    def save: String = {
      s"Save method implemented"
    }
    def delete: String = {
      s"Delete method implemented"
    }

    val user: String
    def connect: String

  }

  class PublicDataSource(ds: String) extends BaseDataSource(ds) {

    val user = "publicUser"

    override def connect: String = {
      s"Public Data Source, no password needed"
    }

  }
}


// АНОНИМНЫЕ КЛАССЫ (ANONYMOUS CLASSES)


/*

Мы отметили, что мы не можем создать экземпляр абстрактного класса,
пока абстрактные методы и поля не прописаны должным образом.

ССЫЛКА ->

Но тогда что же получается, если написать вот так, избежав создания подкласса:

 */

//  val someSource = new BaseDataSource("someDS") {
//    override val user: String = "someSourceUser"
//
//    override def connect: String = "someSource connection"
//  }


// А получается анонимный класс.


object ANONYMOUS_CLASSES extends App {

  abstract class BaseDataSource(dataSourceName: String){

    def save: String = {
      s"Save method implemented"
    }
    def delete: String = {
      s"Delete method implemented"
    }

    val user: String
    def connect: String

  }

  // ANONYMOUS CLASSES

  val someSource = new BaseDataSource("someDS") {
    override val user: String = "someSourceUser"

    override def connect: String = "someSource connection"
  }

  println(someSource.getClass) // class Lectures.week2oop.ANONYMOUS_CLASSES$$anon$1
}


object TASK_9_3 extends App {

  abstract class Event {
    def trigger(eventName: String): Unit
  }

  class Listener(val eventName: String, var event: Event) {
    def register(evt: Event) {
      event = evt
    }

    def sendNotification() {
      event.trigger(eventName)
    }
  }
/*
  val notification: Listener = new Listener("mousedown", null) {

    override def register(evt: Event): Unit = event

    override def sendNotification(): Unit = eventName

  }
*/
  val notification: Listener = new Listener("mousedown", null)



  // Вызов нового класса Event и в нем,
  // поскольку нет параметров вызов функции и
  // дополнение (дописание) вызовом вывода функции



  println(notification.register(evt = new Event {
    override def trigger(eventName: String): Unit = {
      println(s"trigger $eventName event")
    }
  }))

//  println(notification.register())

}


/*

Представьте, вы подключились к разработке сервиса,
в котором предусматривается создание аккаунтов двух типов:

    бесплатного (FreeAccount) и платного (PaidAccount).

Перед вами код, доставшийся вам от коллеги, успевшего поработать над задачей до вашего прихода.
Ознакомившись с кодом, вы обнаруживаете, что чего-то в коде не хватает.
Видимо, часть изменений, внесенных в код, не сохранилась.


Ваша задача - исправить имеющиеся в коде недочеты:

  правильно организовать наследование
  дописать пропущенные ключевые слова (например, class, trait)


Код должен запускаться, причем для FreeAccount должен работать метод info и subscribe,
 а для PaidAccount - info и unsubscribe.

Вызов этих методов уже прописан в системе, но скрыт от вас. Например,
 работа с PaidAccount организована следующим образом:

 Учитывайте, что:

  Классы FreeAccount и PaidAccount являются наследниками Account;
  Метод subscribe должен быть доступен во FreeAccount благодаря Subscriber;
  Метод unsubscribe должен быть доступен в PaidAccount благодаря Unsubscriber.

 */

object Task_9_4 extends App {

  //  trait - обмен между классами информацией о структуре и полях.
  //  Используется вместе с abstract class, для

  trait Configs {
    val ACCOUNT_TYPE_DEFAULT = "free"
    val ACCOUNT_TYPE_PAID = "paid"
    val SUBSCRIPTION_STATUS = "active"
  }

  // object - для вызова метода без (), например "Settings.AccountSettings"

  object Settings {
    case class AccountSettings(
                                email: String,
                                password: String,
                                picture: String)

    case class SubscriptionSettings(
                                     payment: String,
                                     notifications: String,
                                     expiration: String)
  }

  // object - для вызова метода без (), например "Settings.AccountSettings"

  object Subscriber {
    def subscribe(settings: Settings.SubscriptionSettings): Unit = println("subscribed")
  }

  // object - для вызова метода без (), например "Settings.AccountSettings"

  object Unsubscriber {
    def unsubscribe(): Unit = println("unsubscribed")
  }

  // для изменения информации в классе, в примере о типе аккаунта,
  // используется вместе с trait, в примере для описания аккаунта

  abstract class Account(accountID: String, settings: Settings.AccountSettings) {
    def info(): Unit
  }

  // Используется вызов класса описания (abstract class Account)
  // с переменнами (trait Config)

  // слово extends можно только единожды, но в плане использования

  // with ограничений нет - можно подключить столько трейтов, сколько нужно.

  // Также вызывается функция (метод) с показом типа аккаунта (Подписан)

  class FreeAccount(
                     accountID: String,
                     settings: Settings.AccountSettings)

    extends Account(accountID, settings) with Configs {

    override def info(): Unit = println(s"Account Type: $ACCOUNT_TYPE_DEFAULT")

    def subscribe(settings: Settings.SubscriptionSettings): Unit = {
      Subscriber.subscribe(settings)
    }
  }

  // Используется вызов класса описания (abstract class Account)
  // с переменнами (trait Config)

  // слово extends можно только единожды, но в плане использования

  // with ограничений нет - можно подключить столько трейтов, сколько нужно.

  // Также вызывается функция (метод) с показом типа аккаунта (Не подписан)
  // Метод вызывается в (), так как класс

  class PaidAccount(
                     accountID: String,
                     settings: Settings.AccountSettings)

    extends Account(accountID, settings) with Configs {

    override def info(): Unit = {
      println(s"Account Type: $ACCOUNT_TYPE_PAID")
      println(s"Subscription Status: $SUBSCRIPTION_STATUS")
    }

    def unsubscribe(): Unit = Unsubscriber.unsubscribe()
  }


  val paidAccount = new PaidAccount(
    accountID = "1",
    settings = Settings.AccountSettings(
      email = "test@mail.com",
      password = "abr#45&",
      picture = "link/to/some/pic"))

  // .subscribe(Settings.SubscriptionSettings("payment", "notifications", "expiration"))
  println(paidAccount.info())
  println(paidAccount.unsubscribe())

  val freeAccount = new FreeAccount(
    accountID = "2",
    settings = Settings.AccountSettings(
      email = "test2@mail.com",
      password = "abr#45&",
      picture = "link/to/some/pic"))

  println(freeAccount.info())
  println(freeAccount.subscribe(
    Settings.SubscriptionSettings(
      "payment", "notifications", "expiration"))
  )
}


// АЛМАЗНАЯ ПРОБЛЕМА (DIAMOND PROBLEM)


/*

Разберем множественное наследование на примере алмазной проблемы.
Посмотрите на следующую схему наследования:

ССЫЛКА -> https://drive.google.com/file/d/1IzxEgSlke3J0NRZqpocnIxgA43y91T7b/view?usp=sharing

Этой схеме наследования соответсвует следующий код:
 */

object diamond_problem extends App {

  /*
  Учитывая, что выбирается последний из доступных переопределенных методов, полученный результат scan your txt вполне предсказуем.
  С ОДИНАКОВЫМИ НАЗВАНИЯМИ МЕТОДОВ (функций)
   */

  trait Device {
    def processDoc: Unit
  }

  trait Printer extends Device {
    override def processDoc: Unit = println("print your txt")
  }

  trait Scanner extends Device {
    override def processDoc: Unit = println("scan your txt")
  }

//  class ComboDevice extends Printer with Scanner
//
//  val device = new ComboDevice
//  device.processDoc // ВЫВОД -> scan your txt

//  или

  class ComboDevice extends Scanner with Printer

  val device = new ComboDevice
  device.processDoc // ВЫВОД -> print your txt

  /*
  trait Device {
    def processDoc: Unit
    def print: Unit
  }

  trait Printer extends Device {
    override def print: Unit = println("print your txt")
  }

  trait Scanner extends Device {
    override def processDoc: Unit = println("scan your txt")
  }

  class ComboDevice extends Printer with Scanner

  val device = new ComboDevice
  device.processDoc
  device.print
  */

  /*
  КАК ВИДИТ КОД КОМПИЛЯТОР ->

  ComboDevice extends Printer {
    override def processDoc: Unit = println("print your txt")
  } with Scanner {
    override def processDoc: Unit = println("scan your txt")
  }
   */
}

object Task_9_5 extends App {

  trait Color {
    def color: Unit
  }

  trait Red extends Color {
    override def color: Unit = println("red color")
  }

  trait Blue extends Color {
    override def color: Unit = println("blue color")
  }

  // Изменение в trait используя

  // слово extends можно только единожды, но в плане использования
  // with ограничений нет - можно подключить столько трейтов, сколько нужно.

  // НЕТ РАЗНИЦЫ (в extends или with), ТАК КАК МЫ В ЛЮБОМ СЛУЧАЕ ИЗМЕНЯЕМ trait
  // И ВЫЗЫВАЕМ КЛАСС В ИТОГЕ (без скобок, так как нет параметров в функции)

  class MixedColor extends Red with Blue {
    override def color: Unit = println("mixed color")
  }

  val chosenColor = new MixedColor
  chosenColor.color
}