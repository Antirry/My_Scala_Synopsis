package Lectures._1Лучшие_практики

object _0_Description extends App {


// При проверке числа на четность / нечетность, используете n % 2 == 0 / n % 2 != 0

/*
Не используйте == для сравнения Array,
 используйте sameElements

Причина: происходит сравнение не значений, а ссылок:
 */

  println(Array(1) == Array(1)) // false
  println(Array(1).sameElements(Array(1))) // true





/*
Не используйте == NaN для проверки числа на NaN,
 используйте .isNan

Причина: NaN вообще ничему не равняется, даже себе:
*/

  println(Double.NaN == Double.NaN) // false
  println(Double.NaN.isNaN) // true





/*
Не используйте .size для проверки коллекции на наличие в ней элементов,
 используйте .isEmpty

Причина: .isEmpty дает большую эффективность,
 так как позволяет избежать прохождения по всем элементам коллекции
 */

  println(Set(1,1,1,1,11,1,1,1,1).size) // 2
  println(Set(None, null).size) // 2
  println(Set(1,1,1,1,11,1,1,1,1).isEmpty) // false





/*
Не используйте .last при работе с коллекциями, предпочитайте .lastOption
Не используйте .head при работе с коллекциями, предпочитайте .headOption

Причина: если коллекция пуста, вы получите исключение
 */

  println(List.empty[Int].head) // NoSuchElementException

  println(List(1, 2, 3).headOption) // Some(1)
  println(List.empty[Int].headOption) // None





/*
Не используйте init для получения всех элементов коллекции, кроме последнего, предпочитайте .dropRight(1)
Не используйте tail для получения всех элементов коллекции, кроме первого, предпочитайте .drop(1)
Не используйте reduce, предпочитайте .reduceOption

Причина: если коллекция пуста, вы получите исключение
 */

  println(List.empty[Int].init) // UnsupportedOperationException
  println(List(1, 2, 3).dropRight(1)) // List(1, 2)
  println(List.empty[Int].dropRight(1)) // List()

  println(List.empty[Int].tail) // UnsupportedOperationException : tail of empty list
  println(List(1, 2, 3).drop(1)) // List(2, 3)
  println(List.empty[Int].drop(1)) // List()

  println(List.empty[Int].reduce(_ + _)) // UnsupportedOperationException
  println(List(1, 2, 3).reduceOption(_ + _)) // Some(6)
  println(List.empty[Int].reduceOption(_ + _)) // None





/*
Не используйте return
с return у вас нет гарантий, что программа отработает так, как было запланировано:
 */

// код для получения суммы всех элементов списка

  def add: Int = List(1, 2, 3).foldRight(0)((a, b) => a + b)

  def addSum: Int = List(1, 2, 3).sum
  def addR: Int = List(1, 2, 3).foldRight(0)((a, b) => return a + b)

  println(add) // 6
  println(addR) // 3

/*
Разница в результатах объясняется тем,
 что return происходит не для функции в целом,
  а для метода, в котором return прописан, следовательно,
   текущие вычисления просто не учитываются.

В примере c addR вы получаете результат сложения числа три с нулем.
*/






// Кроме того, на самом деле return возвращает тип Nothing. Это легко доказать:

  def x: Int = {
    val someVal: String = return 2
    1
  }

  println(x) // 2

/*
Вывод:
  Полученный результат свидетельствует о том,
   что на самом деле тип someVal скорее всего не String,
   а Nothing. Иначе бы мы получали уведомление о несоответствии типов.
 */





/*
При объявлении case class - делайте его final

Причина: помогает избежать непредвиденных результатов:
 */

  case class Customer(id: Int)
  class VipCustomer(id: Int, name: String) extends Customer(id)

  println(new VipCustomer(1, "Bob") == new VipCustomer(1, "Alice")) // true

/*
В этом примере equals или ==, доступный для case-класса,
 производит поэлементное сравнение,
  но только equals ничего не знает о новых аргументах,
   которые добавились в VipCustomer при наследовании.
 */

/*

Это мой первый курс по языку, поэтому опыта написания программ на этом языке вообще не было,
но по  моему мнению курс учит начальным знаниям языка, пусть и в практике приходилось больше додумывать самому,
как решать и учиться комбинировать то, чему научили в теории ооп и функционального программирования,
именно последние задания практики не получилось решить, остается ждать ответа чтобы получить баллы

Итог: я бы поставил 4.5, так как до сих пор не могу решить последние задания в практики обоих частей

 */
}
