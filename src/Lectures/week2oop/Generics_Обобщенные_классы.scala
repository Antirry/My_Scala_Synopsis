package Lectures.week2oop

import Lectures.week2oop.Generics_Обобщенные_классы.Vehicle
import com.sun.tools.javac.jvm.Items

object Generics_Обобщенные_классы extends App {

  //  ОБОБЩЕНИЯ (GENERICS)

  // Пример без обобщения

  def countSumOfInt(nums: List[Int]): Int = nums.sum

  println(countSumOfInt(List(1,2,3)))

  /*
  Вдруг потребуется посчитать сумму для чисел типа Float или Long - опять копировать?
  Это будет не самое удачное решение.
  В этом случае полезно будет воспользоваться обобщениями:
   */

  // Пример с обобщением

  def countSum[A: Numeric](nums: List[A]): A = nums.sum

  println(countSum[Int](List(1,2,3)))
  println(countSum[Double](List(1.3, 2.2, 5.5)))


  // Как принимать значения абсолютно любого типа


  def createElement[A](el: A): A = {
    el
  }

  val anInt = createElement(2)
  val aString = createElement("aRolf")
  println(s"anInt = $anInt, aString = $aString") // anInt = 2, aString = aRolf

  case class Vehicle(name: String)
  val aVehicle = createElement(Vehicle("car"))

  println(s"aVehicle = $aVehicle, Vehicle.name = ${aVehicle.name}, aVehicle.getClass = ${aVehicle.getClass}")
  // aVehicle = Vehicle(car), Vehicle.name = car,
  // aVehicle.getClass = class Lectures.week2oop.Generics_Обобщенные_классы$Vehicle

}

object Task_11_1 extends App {

  // создайте обобщенный метод
  /*
  def getElementType(el: Int) = {
    println(s"argument of ${el.getClass.getSimpleName} type")
  }
  */

  def getElementType[A](el: A): Unit = {
    println(s"argument of ${el.getClass.getSimpleName} type")
  }
}

object list_generics_обобщения_списка extends App {

  // Аналогичным образом происходит работа со списками:
  // при внедрении обобщений конкретный тип списка заменяется на обобщенный.

  // Метод выбирающий случайный элемент из списка, Без обобщения ->

  def randomInt(items: List[Int]): Int = {
    val randomIndex = util.Random.nextInt(items.length)
    items(randomIndex)
  }

  println(randomInt(List(1,2,3,4,5)))

  // С обобщением

  def randomItem[A](items: List[A]): A = {
    val randomIndex = util.Random.nextInt(items.length)
    items(randomIndex)
  }

  println(randomItem(List("a", "bc", "def")))
  println(randomItem(List(1.5, 7.3, 8.89)))
}


object class_generics_Обобщение_класса extends App {


  /*
  Примечание:

      обобщения применяются к классам(class), трейтам(trait),  но никак не к объектам (object)
   */


  class SomeClass {
    def someFunc(aVal: Int): Unit = println(s"integer value $aVal")
  }

  val anInstance = new SomeClass
  anInstance.someFunc(1)


  // Два вызова 1) через класс и 2) через функцию
  // Отличие первого в том, где вызывается тип

  // Вызов через класс

  class SomeClass2[T: Numeric] {
    def someFunc2(aVal: T): Unit = println("generic type is used")
  }

  val print = new SomeClass2[Double]
  print.someFunc2(1.5)

  // Вызов через функцию

  class SomeClass3 {
    def someFunc3[T: Numeric](aVal: T): Unit = println(s"generic type is used")
  }

  val print1 = new SomeClass3
  print1.someFunc3[Double](1.5)

}



object Example_generic_class extends App {


  // ВЕСЬ ОБЪЕКТ ОШИБОЧЕН, ТАК КАК МОЖНО НАЗНАЧИТЬ ЛЮБОЙ КЛАСС К ОБОБЩЕНИЮ
    // ПРАВИЛЬНЫЙ КОД ->
  // Для назначения только велосипедов, например
  // class Parking1_>[T >: Bicycle <: Vehicle](val vehicle: T)


  // Схема классов ->
//  https://drive.google.com/file/d/1kbhAFKWRs0OSYPWObp1RuWe7a2x2ai7U/view?usp=sharing

  trait Thing

  class Vehicle extends Thing

  class Car extends Vehicle
  class Ambulance extends Car
  class Taxi extends Car
  class Jeep extends Car

  class Bicycle extends Vehicle
  class Tricycle extends Vehicle

  /*
  класс Parking, который бы позволил вести учет припаркованного транспорта,

  причем класс должен быть универсальным и
  должен уметь работать с транспортом всех указанных типов - а это означает,

  что при написании класса придется задействовать обобщения:
   */

  // Parking[T] говорит о том,

  // что переменные класса будут иметь обобщенный тип T,
  // который станет известен только в момент создания экземпляра класса Parking.

  class Parking[T](val vehicle: T)

  // Например, если работаем с Parking[Bicycle]

  // то в качестве переменной vehicle мы должны
  // будем использовать переменную типа Bicycle,
  //
  // иными словами - инстанс класса Bicycle

  // РАБОЧИЙ КОД ->

  new Parking[Bicycle](new Bicycle)

  // ИЛИ

  new Parking[Car](new Jeep)

  // НЕ РАБОЧИЙ КОД ->
  // Почему не рабочий -> ССЫЛКА - https://drive.google.com/file/d/1dV8dc7x2vcbx7S57VAD-YepXCIWcye1d/view?usp=share_link

//  new Parking[Bicycle](new Car)

}


object TYPE_LIMITS extends App {


// ОГРАНИЧЕНИЯ ТИПОВ (ВЕРХНЕЕ <: И НИЖНЕЕ >:)


  // В примере с парковкой мы не особо заботились о том,
  // какие типы можно использовать в качестве Т
  // могли быть подходящие нам Vehicle вроде Car, Ambulance, Bicycle;

  // в качестве T могли быть использованы совершенно неподходящие типы - вроде Cat:

  class Cat

  class Parking[T](val vehicle: T)
  new Parking[Cat](new Cat)

//  Чтобы это исправить, необходимо ограничить типы.
  //  В нашем распоряжении верхнее <: и нижнее >: ограничение.

// Начнем с разбора верхнего ограничения:
  // ССЫЛКА НА СХЕМУ -> https://drive.google.com/file/d/1n-Lcz1d2Ssz-e9eGn9QjcZHTf3I9M4Co/view?usp=sharing

  class Parking_<[T <: Vehicle](val vehicle: T)

  // Parking[T <: Vehicle] означает,
  // что в качестве типа T допускается использовать либо непосредственно сам тип Vehicle,
  // либо его подтипы, те Car, Ambulance, Taxi, Jeep, Bicycle, Tricycle

  // new Parking_<[Cat](new Cat)

  // ССЫЛКА -> https://drive.google.com/file/d/1dHMjvwwhJouG4_38wR9FgT6RbuU8Sm_Y/view?usp=sharing


// Если бы вместо верхнего ограничения мы использовали нижнее:
  // ССЫЛКА -> https://drive.google.com/file/d/1juGOkjdBpDYLT2v4ZIW8SO1NPtI8rWwk/view?usp=sharing

  class Parking_>[T >: Vehicle](val vehicle: T)


// Если наша парковка - только для велосипедов ->


  // НЕ ПРАВИЛЬНЫЙ КОД, потому что ->

  // качестве Т мы можем бы использовать любой из супертипов Bicycle, включая AnyRef - получается перебор.
  // Поэтому в идеале следует одновременно наложить на тип и верхнее, и нижнее ограничение
  // СХЕМА КОДА ->

//  trait Thing
//  class Vehicle extends Thing
//  class Bicycle extends Vehicle
//
//  class Parking1_>[T >: Bicycle](val vehicle: T)


  // ПРАВИЛЬННЫЙ КОД ->

// Если наша парковка - только для велосипедов ->
  // Верхнее, и нижнее ограничение:

  trait Thing
  class Vehicle extends Thing
  class Bicycle extends Vehicle

  class Parking1_>[T >: Bicycle <: Vehicle](val vehicle: T)

}


object Task_11_2 extends App {

  // Используя полученные знания по обобщениям, дополните код, сделав его рабочим.

  /*

  Классы, никак не связанные с Event, не должны работать с Registrable.  Т.е. вот такой код не должен срабатывать:

//  case class Unknown(id: String, operation: String)
//
//  val unknownEvent = new Registrable[Unknown] {
//    def update(event: Unknown, status: String): Unknown = event.copy(operation = status)
//  }

   */

  // Создаем трайт с общими значениями, которые будем использовать дальше в классах

  trait Event {
    def id: String
    def evType: String
    def operation: String
  }

  // Создаем классы, которые будут использоваться в дальнейшем коде
  // Как выбор из ивентов

  case class UserEvent(id: String, evType: String, operation: String) extends Event
  case class SystemEvent(id: String, evType: String, operation: String) extends Event

  // Создаем общую функцию (Класс трайт),
  // Который наследуют Все кто ниже Event, то есть UserEvent, SystemEvent

  trait Registrable[T <: Event] {
    def update(event: T, status: String) : Event
  }

  // Объект, который имеет в себе все методы из case классов выше ->

  object EventOps {

    // Вызов трайта используя схему case класса,
    // в котором описывается запись в обновление ивента (операцию) статус

    val userEvent = new Registrable[UserEvent] {
      def update(event: UserEvent, status: String): UserEvent = event.copy(operation = status)
    }

    val systemEvent = new Registrable[SystemEvent] {
      def update(event: SystemEvent, status: String): SystemEvent = event.copy(operation = status)
    }
  }

  // Обобщенный класс,
  // который включает только операции ивента [T <: Event] и выносит переменную T как Ивент
  // Также он включает прошлый Трайт и заменяет операцию на последнюю функцией update
  // чтобы было видно изменение операции

  class EventOps[T <: Event](event: T)(ops: Registrable[T]) {
    def update(status: String): Event = ops.update(event, status)
  }

  // Проверка написанного выше

  val user1Event = UserEvent("1", "user_event", "account_create")
  val user1Ops = new EventOps(user1Event)(EventOps.userEvent)
  val user1EventUpd = user1Ops.update("permission_add")

  println(s"User1 | Operation: ${user1EventUpd.operation}")

}