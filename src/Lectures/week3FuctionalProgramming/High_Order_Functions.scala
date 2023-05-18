package Lectures.week3FuctionalProgramming

object Функции_высшего_порядка_High_Order_Functions extends App {

/*
  Под функциями высшего порядка понимают такие функции,
   которые на вход получают другую функцию или же, как результат, возвращают функцию.
 */

}

object Example_1 extends App {

/*
  В качестве примера напишем функцию nTimes,
   которая получает на вход три параметра: f, x, n. f - это та функция,
    которая будет применена к параметру x указанное количество раз (в нашем случае это n раз).

 */
/*
   Функцию nTimes можно написать несколькими способами.
     Способ 1, в котором мы передаем все три аргумента сразу - в одних скобках:
 */
  import scala.annotation.tailrec

  @tailrec
  def nTimes(f: Int => Int, x: Int, n: Int): Int = {
    if (n <= 0) x
    else nTimes(f, f(x), n-1)
  }

//  В качестве аргумента, возьмем функцию, увеличивающую число на единицу:
  val increment = (x: Int) => x + 1
  println(nTimes(increment, 0, 3))
}

object Каррированные_функции_Currying_Functions extends App {

  // Второй способ написания функции nTimes

/*
 Под каррированной функцией подразумевают функцию,
  которая на вход принимает несколько аргументов (причем - можно сказать, что аргументы разбиты на группы).

 А в теле этой функции происходит серия вызовов функций, каждая из которых принимает единственный аргумент
 */

/*
  обращаясь к алгебрe, расписать весь процесс можно примерно так:

  f1 = f(x)
  f2 = f1(y)
  result = f2(z)

  Иначе говоря:

  result = f(x)(y)(z)
 */
}

object Example_2 extends App {

// Обычный вариант
  def add(x: Int, y: Int): Int = x + y
  println(add(1,2))

// Второй вариант
  def add2(x: Int): Int => Int = (y: Int) => x + y
  println(add2(1)(2))

// Второй вариант
  def add3(x: Int)(y: Int): Int = x + y
  println(add3(1)(2))
}

object Carried_function_Каррированные_функции extends App {
  // Вариант с КАРРИРОВАННОЙ ФУНКЦИЕЙ

// Способ 2. curryingdNTimes: (f, n) (x)

/*
  Выглядеть это теперь будет так:

    def имяФункции(аргумент1, агрумент2) = (аргумент3) => операция


  Или, может, для кого-то более понятной будет вот такая запись:

    curryingNTimes(f,n) = x => f( f(f...f(x) ) )
 */


  def curryingNTimes(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x // ЛЯМБДА-ФУНКЦИЯ, ЧТО ПРОСТО БЕРЕТ И ВОЗВРАЩАЕТ ЗНАЧЕНИЕ
    else (x: Int) => curryingNTimes(f, n-1) (f(x))
  }

  // ВЫЗОВ КАРРИРОВАННОЙ ФУНКЦИЕЙ

  //  В качестве аргумента, возьмем функцию, увеличивающую число на единицу:
  val increment = (x: Int) => x + 1

  println(curryingNTimes(increment, 3)(0))
}

object Task_16_1 extends App {
/*
  Создание метода - def , Создание переменной функции Function1[Int, Int],
  вызов новой переменной функции
  и применяется метод для функции, который создает функцию с методом который складывает число

  Метод, который складывает число x и y, вызывая много раз функции
 */

  def someFunc: Int => Function1[Int, Int] = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

/*
  Пример, в котором умножаются два числа.
   Для этого на вход подается число Int, а возвращается еще одна функция,
    на вход которой подается число и которая в итоге возвращает целое число(результат умножения):

  Каррированные функции:
    1. https://drive.google.com/file/d/1nGC79e6_vyMNJO0jPfJXFO8qZtjfqgie/view?usp=sharing

    2. https://drive.google.com/file/d/1ppOmMGBKFi9nxT8OfZHB0T_XbLns0BxQ/view?usp=sharing
 */

// Каким образом можно переписать вышеприведенную функцию?

  def someFunc1 = (x: Int) => (y: Int) => x + y
  def someFunc2: Int => ((Int) => Int) = (x: Int) => (y: Int) => x + y
  def someFunc3: Int => (Int => Int) = (x) => (y) => x + y
  def someFunc4: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  def someFunc5(x: Int, y: Int): Int = x + y

  val _1_ = someFunc(5)
  println(_1_(1)) // 6
  println(someFunc(5)) // <function1>


  println(someFunc1(5)(1)) // 6
  println(someFunc2(5)(1))
  println(someFunc3(5)(1))
  println(someFunc4(5)(1))
  println(someFunc5(5,1))

}