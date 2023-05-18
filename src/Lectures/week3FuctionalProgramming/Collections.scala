package Lectures.week3FuctionalProgramming

import Lectures.week3FuctionalProgramming.Map_1_function.{list1, list2}

object Collections extends App {
}



object Set_0 extends App {
  /*
 Set ( т.е. без дубликатов или повторяющихся элементов)

    https://drive.google.com/file/d/1eGQQR09ZqqfLsmCO8qUJpvGEjAsYIF0M/view?usp=share_link
 */

  val emptySet: Set[Int] = Set()
  val aSet = Set(10, 20, 30, 40)
  val anotherSet = Set(30, 40, 50, 60)

  println(aSet.isEmpty) // false
  println(emptySet.isEmpty) // true

  println(aSet.head) // 10               Set(10, 20, 30, 40)
  println(aSet.tail) // Set(20, 30, 40)

  println(aSet.min) // 10
  println(aSet.max) // 40


  println(aSet.intersect(anotherSet)) // Set(30, 40)  Set(10, 20, 30, 40)
  println(aSet.&(anotherSet)) // Set(30, 40)  Set(30, 40, 50, 60)

  println(aSet.++(anotherSet)) //  HashSet(10, 20, 60, 50, 40, 30)
  println(aSet ++ anotherSet) //  HashSet(10, 20, 60, 50, 40, 30)

}




object Seq_0 extends App {
  /*
  Seq - List
  Seq (т.е. у каждого элемента свой индекс, например - Vector, Range, List, Array)

    https://drive.google.com/file/d/1ftj1VwoFckKk4rak3l4iCdexrOTt5oR0/view?usp=sharing
 */


  val aSequence = Seq(1, 3, 2, 4)

  println(aSequence) // List(1, 3, 2, 4)
  println(aSequence.length) // 4

  // НЕ РАБОЧИЙ ВАРИАНТ ->
  //  println(aSequence.++ Seq(6, 7, 5))
  println(aSequence ++ Seq(6, 7, 5)) // List(1, 3, 2, 4, 6, 7, 5)
  println(aSequence.reverse) // List(4, 2, 3, 1)
  println(aSequence.sorted) //  List(1, 2, 3, 4)

  println(aSequence(1)) // 3
  println(aSequence.search(3)) // Found(1) -  Индекс 1

}




object Map_0 extends App {
/*
  Map (т.е. пары ключ-значение)

     https://drive.google.com/file/d/17nWlUIr2cBSetE9LbJLcn6roUYFUEhNc/view?usp=sharing
 */


  val aMap: Map[String, Int] = Map()
  val colors: Map[String, String] = {

    // Синтаксический сахар - ("black", "#000000") = одно и тоже, что = "blue" -> "#0d1ad1"
    Map(("black", "#000000"), "blue" -> "#0d1ad1", ("Blue", "#161f96")).withDefaultValue("na")
  }

  println(colors)  // Map("black" -> "#000000", "blue" -> "#0d1ad1", "Blue" -> "#161f96")

  //  2 способа проверить, есть ли в Map ключ ->
  println(colors.contains("black"))  // true
  println(colors("black"))  // #000000

  println(colors("red"))  //  Без .withDefaultValue("na") NoSuchElementException
  //  C .withDefaultValue("na") na

  //  Добавление новой пары -> Ключ - Значение
  val color: (String, String) = "green" -> "#339616"  // Ключ - Значение
  val newColors: Map[String, String] = colors + color

  println(newColors)  // Map("black" -> "#000000", "blue" -> "#0d1ad1", "Blue" -> "#161f96", "green" -> "#339616")

  //  Конвертация List - Map , Map - List
  println(colors.toList)  // List(("black", "#000000"), ("blue", "#0d1ad1"), ("Blue", "#161f96"))
  println(List(("White", "#ffffff")).toMap)  //  Map(White -> #ffffff)
}




object Array_0 extends App {

  val anArray: Array[String] = Array("h", "e", "l", "l", "o", ".")

  anArray(5) = "!"  // Замена элемента - Синтаксический сахар
  anArray.update(5, "!")  // <- Синтаксический сахар, полная версия

  println(anArray.mkString("-"))  // h-e-l-l-o-!

  val twoElement: Array[Boolean] = Array.ofDim[Boolean](2)  // Создаем пустой массив от 0 до 2
  twoElement.foreach(println)  // false

  // преобразуем тип
  val numbersSeq: Seq[String] = anArray
  println(numbersSeq)  // ArraySeq(h, e, l, l, o, !)

}





object Tuple_0 extends App {

  // Два способа создания Tuple - Кортежа

  val aTuple: (Int, String) = (100, "Scala")  // Tuple - содержит элементы разных типов
  val sameTuple: (Int, String) = Tuple2(100, "Scala")  // Может содержать до 22 элемент -> (Tuple1, ..., Tuple22)
  // Как (Function1, ..., Function22)

  println(aTuple)  // (100, Scala)

  println(aTuple._1) // 100
  println(aTuple._2) // Scala

  val copy: (Int, String) = aTuple.copy(_2 = "Python")

  println(aTuple.swap) // (Scala, 100)
}





object Range_0 extends App {

//  Меньше 3 и до 1, включая 1
  val aRange: Seq[Int] = 1 until 3
  aRange.foreach(print) // 12
/*
  Второй вариант
    (1 to 3) <- Можно использовать вместо рекурсии, если
    хотим вывести что-то на экран несколько раз
 */
  (1 to 3).foreach(x => print("Hello")) // HelloHelloHello

//  Range помогает создавать коллекции
  val aRangeToVector: Vector[Int] = (1 to 5).toVector
  println(aRangeToVector)  // Vector(1, 2, 3, 4, 5)
}





object Foreach_0 extends App {

  val list = List(1, 2, 3)

  // 2 СПОСОБА ВЫВОДА

/*
  // 1

  Foreach - тот же map, только на вход подаются функции, возвращающие Unit
*/
  list.foreach(print) // 123
/*
  // 2

  Равен foreach только с for
*/
  for {
    n <- list
  } print(n)

}





object Map_0_function extends App {

  /*
  Смысл map заключается в том, что заданная функция применяется к каждому элементу списка.

  flatMap очень похож на map, только он преобразует каждый элемент в целый список элементов
   и выполняет действия уже с ними, а потом результат собирает в одно целое.
 */

  val fruits = List("apple", "banana")
  val mapResult = fruits.map(_.toUpperCase)
  val flatResult = fruits.flatMap(_.toUpperCase)

  println(mapResult) // List(APPLE, BANANA)
  println(flatResult) // List(A, P, P, L, E, B, A, N, A, N, A)

  /*
  Именно из-за того, как работает flatMap, если нам требуется проставить точку после каждого символа строки
   и на выходе получить модифицированную строку, использовать придется именно его.
*/

  val s = "Hello"
  val newStr: String = s.flatMap(c => (c + "."))
  println(newStr)

  // map тоже сработает, только вернет уже не строку
  println(s.map(c => (c + ".")))

}

object Map_1_function extends App {

  val list1 = List(1, 2)
  val list2 = List("a", "b")

//  Каждый элемент списка list1 комбинируем с каждым элементом списка list2
  // 2 Способа

// https://drive.google.com/file/d/12bCMip-hGUa2ReAPVwkf7RztyM07jpvU/view?usp=sharing

  // 1
  val combinations = list1.flatMap(n => list2.map(c => c + n))
  println(combinations) // List(a1, b1, a2, b2)

  // 2
  // Стрелка `<-` используется для извлечения элементов из списков `list1` и `list2`.
  val forCombination = for {
    n <- list1
    c <- list2
  } yield c + n
  println(forCombination) // List(a1, b1, a2, b2)

  // Элементы из list1 должны быть больше 1
  // 2 Способа

  // 1
  list1.filter(_ > 1).flatMap(n => list2.map(c => c + n))

  // 2
  val forFilterCombination = for {
    n <- list1 if n > 1
    c <- list2
  } yield c + n
/*
Символ `yield` используется для возврата некоторого значения,
 которое будет содержать в результирующем списке.
 В данном случае, мы складываем элементы
   `n` из списка `list1`
    и `c` из списка `list2`,
     и добавляем результат в список `forCombination`.
 */

  println(forFilterCombination)

/*
  filter помогает отобрать элементы,
  удовлетворяющие заданным условиям
 */
}

object for_expressions_Example extends App {

  val progLanguages = List("java", "scala", "python")

/*

В результате у нас должен получиться список туплов (tuples):
 List((java,4), (scala,5), (python,6)),

Где каждый тупл (tuple) состоит из двух элементов:
 название языка программирования и вычисленная длина строки.

 */

  val out = for (lng <- progLanguages) yield (lng, lng.length)

//  Альтернатива
  val out1 = progLanguages.map(lng => (lng, lng.length))

  println(out + "\n" + out1)

// названия большими буквами

  val my_out: List[String] = for (lng <- progLanguages) yield lng.toUpperCase
  val my_out1: List[String] = progLanguages.map(_.toUpperCase)

  for {
    lng <- progLanguages
  } yield lng.toUpperCase
}

object Task_17_2 extends App {

  val progLanguages = List("java", "scala", "python")
  val lngAbbrev = List("JA", "SCA", "PY")

  val out = for ((prog, lng) <- progLanguages.zip(lngAbbrev)) yield (prog, lng)
  println(out)

  val out1 = for {
    i <- progLanguages.indices
  } yield (lngAbbrev(i), progLanguages(i))

  println(out1)
  
}

object Task_17_3 extends App {

  println(Seq(3,4,3,3).sorted.search(3)) // Found(0)

}

object Task_17_4 extends App {

  Array.ofDim[String](1).foreach(println)   //  String = null
  Array.ofDim[Boolean](1).foreach(println)  //  Boolean = False
  Array.ofDim[Int](1).foreach(println)      //  Int = 0

}

object Task_17_5 extends App {

/*

copy передается параметр _2="Scala".
 Этот параметр меняет второй элемент кортежа, который теперь содержит строку "Scala".
  Полученный кортеж Tuple2(2, "Scala") автоматически присваивается переменной SampleTuple.

Метод `swap` изменяет порядок элементов кортежа на противоположный,
 то есть новый экземпляр кортежа будет содержать "Scala" как первый элемент и 2 как второй элемент

К кортежу SampleTuple вызывается метод swap, который меняет местами элементы кортежа.
Теперь кортеж содержит ( "Scala", 2).

Из полученного кортежа вызывается метод _1, который возвращает первый элемент кортежа, т.е. строку "Scala".

К строчке "Scala" прибавляется число 5. Scala автоматически преобразуется в число, получается 0 + 5 = 5.

 */

  val SampleTuple = new Tuple2(2, "Hello, World")
  println(SampleTuple.copy(_2="Scala").swap._1 + 5)

}

object Task_17_6 extends App {

  val list = List(1, 2, 3)
  val doubler = (x: Int) => List(x, x * 2)

  println(list.flatMap(doubler))  // List(1, 2, 2, 4, 3, 6)
  println(doubler(5))             // List(5, 10)
  println(list.map(doubler))      // List(List(1, 2), List(2, 4), List(3, 6))

}

object Task_17_7 extends App {
/*

  Какой код позволит получить результат List(80, 800, 8000, 90, 900, 9000, 100, 1000, 10000) для списков

*/

  val nums1 = List(1, 2, 3)
  val nums2 = List(4, 6, 7)
  val nums3 = List(10, 100, 1000)

  val out = nums1.flatMap(a => nums2.filter(_ % 2 == 1).flatMap(b => nums3.map(c => (a + b) * c)))
  val out1 = {
  for {
    a <- nums1
    b <- nums2 if b % 2 == 1
    c <- nums3
  } yield (a + b) * c
  }

  // ПРОВЕРКА, ПРОХОДИТ ИЗ if b % 2 == 1 ТОЛЬКО 7, ПОЭТОМУ ОТВЕТ List(80, 800, 8000, 90, 900, 9000, 100, 1000, 10000)

  for {
    a <- nums1
    b <- nums2 if b % 2 == 1
    c <- nums3
  } print(a + " " + b + " " + c + " " + "\n")

  println(out + "\n" + out1) // List(80, 800, 8000, 90, 900, 9000, 100, 1000, 10000)
}