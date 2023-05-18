package Lectures.week2oop

object Variance_problem_Вариативность extends App {

  // Краткая шпаргалка по Вариантности

  class Fruit
  class Apple extends Fruit
  class Banana extends Fruit

/*
    Инвариантность диктует, что List[Apple] и List[Fruit] -
      это совершенно разные вещи, не связанные никакими родственными связями.

    Тип, указанный в скобках без каких-либо дополнительных знаков, свидетельствует об инвариантности
    ( class InvariantList[A] )

//  class InvariantList[A] // Инвариантность
    ТАК СДЕЛАТЬ НЕ ПОЛУЧИТСЯ ->
//  val invariantFruitList: InvariantList[Fruit] = new InvariantList[Apple]


ВЫВОД : Тип, указанный слева, должен совпадать с типом в правой части.
  -> val invariantFruitList: InvariantList[Fruit] = new InvariantList[Fruit]

 */

  class InvariantList[A] // Инвариантность
  val invariantFruitList: InvariantList[Fruit] = new InvariantList[Fruit]

/*
    Ковариантность подразумевает, что раз Apple наследуется от Fruit,
     то и List[Apple] можно рассматривать как потомка List[Fruit].

      Для обозначения ковариантного списка - не забудьте добавить плюс перед типом:
  class CovariantList[+A]

МОЖНО ЕЩЕ НАПИСАТЬ ТАК:
  class Fruit
  class Apple extends Fruit
  class Banana extends Fruit

  val fruit: Fruit = new Apple

НЕЛЬЗЯ НАПИСАТЬ ТАК :

// fruitList.add(new Banana)

  Ковариантность применяется только к типам, которые используются в качестве выходных параметров методов.

  Просто из списка яблок - список превратится в то, чем и должен был быть изначально, в список фруктов.

 */

  class CovariantList[+A] // Ковариантность
  val fruitList: CovariantList[Fruit] = new CovariantList[Apple]

/*
    Контравариантность предполагает знак минус рядом с типом:
    class ContravariantList[-A]

    И тогда создаем вот такую переменную:
    val contravariantList: ContravariantList[Apple] = new ContravariantList[Fruit]

ДРУГОЙ ПРИМЕР:
*/

//  class Person[-A]
//  val person: Person[ScalaDeveloper] = new Person[Developer]

/*
Представьте ситуацию: вы искали Scala-разработчика к себе в команду,
 а наняли отличного разработчика, знания которого не ограничены одним лишь языком Scala.

Вы ведь от этого только выиграете: у работника больше знаний, больше умений,
 а значит - больше задач, которые он сможет успешно выполнить.

 */

  class ContravariantList[-A] // Контравариантность
  val ContravariantList: ContravariantList[Apple] = new ContravariantList[Fruit]

/*
  Этим кодом мы говорим, что если в список типа A будет добавлен элемент типа B,
   то список типа A превратится в список типа B, причем B является супер типом для A.

  [B >: A] (из лекций) означает: для любого типа B находящегося выше по иерархии (для Apple - это Apple или Fruit).
 */

//  class List[+A] {
//    def add[B >: A](element: B): List[B] = ???
//  }
}



object Example extends App {

/*
  Верно я понимаю, что Ковариантность и Контравариантность взаимозаменяемы с верхним и нижним ограничением?

  Допустим, имеется какое-то приложение, представленное в нескольких странах.
   Перед разработчиками стоит задача организовать географическое разделение клиентов в системе.

 */


  class Customer
  class CustomerNZ extends Customer
  class CustomerKZ extends Customer


  object _1_Example {
    class RegionNZ[T <: Customer](var customer: T)
    // НЕ РАБОЧИЙ ВАРИАНТ class RegionNZ[+T](var customer: T) ТАК КАК ->


/*
Если делать через [+Customer]:

  Сразу ничего не получится и для
  class RegionNZ[+T](var customer: T)

будет выдано предупреждение:
  Covariant type T occurs in contravariant position in type T of value customer

 */

    val nz: RegionNZ[Customer] = new RegionNZ[Customer](new CustomerNZ)
    nz.customer = new CustomerKZ
  }
}

object Task_12_1 extends App {

  abstract class MusicInstrument1[+A] {
    val productionYear: Int
  }

/*

НЕ ПРАВИЛЬНЫЙ КОД ->
Обобщения - это не про объекты, внимание на object MusicInstrument[+A]

//  object MusicInstrument[+A] {
//    def apply(productionYear: Int) = new MusicInstrument[A];
//  }
 */
  class MusicInstrument[+A] {
    def apply(productionYear: Int) = new MusicInstrument[A];
  }

  case class Guitar(productionYear: Int) extends MusicInstrument
  case class Piano(productionYear: Int) extends  MusicInstrument

  val guitar = Guitar.apply(2021)
  val piano = Piano.apply(2022)

  println(guitar)
  println(piano)
}