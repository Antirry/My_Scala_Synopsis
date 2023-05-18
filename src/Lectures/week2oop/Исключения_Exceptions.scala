package Lectures.week2oop

/*


Ошибки

Указывают на то, что что-то не так с системой.

  StackOverflowError (с ней мы имели дело в теме рекурсий) ошибка связана со stack памятью,
    которая задействуется при вызове метода (под каждый вызов метода в стеке создается новый блок,
    хранящий информацию о параметрах, переменных метода)

  OutOfMemoryError также намекает, что мы исчерпали доступную нам память.
    Например, если в массиве вдруг стало слишком много элементов.
    Только в этот раз ошибка связана с heap памятью,
    которая используется при выделении памяти под объекты)


Исключения

Дают знать, что что-то не так с вашей программой.

  NullPointerException - возникает, если мы пытаемся получить доступ к чему-то, чего нет

//  val x: String = null
//  println(x.length) // NullPointerException

  RuntimeException - бросается, когда необходимо указать на какие-то логические ошибки программы


Если в коде необходимо бросить исключение,
 достаточно использовать ключевое слово throw (за которым должно следовать new). Например:

  throw new NullPointerException

 */

object Как_поймать_исключения extends App {

  /*

  Тут нам поможет известный try-catch-finally

  В идеале finally не должен содержать в себе ничего, кроме побочных эффектов
    ( информации для логов т.е. принты или запись инфы в файл,
     либо закрытие ресурсов после завершения работы с ними,
    например, закрытие файлов или соединения с базами данных)

    ССЫЛКА -> https://drive.google.com/file/d/1MBHdRWkx9ucdxgVF5OOyj5-mp5rzysWu/view?usp=sharing
   */

  def intOrNothing(hasException: Boolean): Int =
    if (hasException) throw new RuntimeException("Exception is here")
    else 200

  try {
    intOrNothing(true)

  } catch {
    case e: RuntimeException => println("RTE is here")

  } finally {
    println("I will be there no matter what")

  }
}

object Собственное_исключение extends App {

  /*

  Вспоминаем тему наследования классов.

  И создаем свой класс, который extends класс Exception.
  Далее создаем экземпляр класса, который при надобности можно бросать где угодно.

   */

//  class MyException extends Exception
//
//  val exception = new MyException
//  throw exception

  /*

  Естественно, к своему классу с собственным исключением вы можете прикрутить все,
  что только можно прикрутить к классу - методы, поля и т.д.

   */
}

object Тип_Nothing extends App {

  // ВСЕ В SCALA ЯВЛЯЕТСЯ ВЫРАЖЕНИЕМ
/*

  val exceptionVal = throw new NullPointerException

  println(exceptionVal.getClass) <- Должно быть Nothing

  Nothing означает, что ничего нет - пустоту.
  А пустоту, если подумать, можно заполнить чем угодно.
  Поэтому вполне можно в качестве типа переменной указать Int, а можно и String - да что угодно.

 val exceptionVal: Int = throw new NullPointerException
 val exceptionVal: String = throw new NullPointerException

 */

  /*

  Если еще немного пофилософствовать - то все начинается с ничего(Nothing),
  которое затем становится чем-то.

  Мы это к тому, что если увидите схемы с типами в Scala
  (вроде той, что мы приводим ниже) - вам теперь должно быть понятно,

  ССЫЛКА -> https://drive.google.com/file/d/1TkP2X8AF2J6d3EsirknBsBbgVqw-lv4c/view?usp=sharing

  БОЛЕЕ ПОДРОБНАЯ ССЫЛКА -> https://drive.google.com/file/d/1F7f6gndHgGF_OxSUxc3zByCErACa27qc/view?usp=sharing

  почему в самом низу стоит Nothing и именно от него идут стрелки ко всему остальному.

   */

}

object Тип_Any extends App {

  // Если теперь сделаем вот так:
  // То potentialException в этот раз будет иметь тип Any:

  /*

  Это объясняется тем, что необходимо угодить и тому,
  что будет возвращено в блоке try, и тому, что вернется в catch (finally не учитывается).

  Т.е. в нашем примере необходимо использовать универсальный тип,
  который позволил бы переменной хранить в себе как значения типа Int, так и Unit.
  И этим типом как раз будет AnyVal (можно запомнить как "готов к любому значению").



  ССЫЛКА НА ПОЯСНЕНИЕ -> https://drive.google.com/file/d/1NbwOTzURtrYsZQYYeJDCq80LIBmHBvdv/view?usp=share_link

  Ну и второй случай, изображенный на картинке - это когда и try,
  и catch - оба возвращают Int, тем самым диктуя potentialException тип Int.
   */

  def intOrNothing(hasException: Boolean): Int =
    if (hasException) throw new RuntimeException("Exception is here")
    else 200

  val potentialException = try {
    intOrNothing(false)
  } catch {
    case e: RuntimeException => println("RTE is here")
  } finally {
    println("I will be there no matter what")
  }

}

object Task_10_1 extends App {

  /*

  У нас есть код, суммирующий два числа x и y типа Int.
  Необходимо учесть, чтобы сумма этих чисел не превышала максимально допустимое целое число.

   */

  // Какое условие, вызывающее исключение OverflowException, необходимо прописать в if?
//  println(Int.MaxValue) // <- 2147483647
//  println(Int.MaxValue + 1) // <- -2147483648

//  val x = -10
//  val y = -20

/*
(x + y) > Int.MaxValue будет false все время, ведь если максимально допустимое число,
 которое только может быть в программе равно Int.MaxValue, то ничего больше него мы увидеть точно не сможем.
 Поэтому в реальности результат суммы будет со знаком минус при превышении Int.MaxValue

 */



/*
 ОТВЕТ:
  Нелогичное решение, которое сделано ошибочно,
   так как если оба выше нуля их сумма не может быть ниже нуля
 */

  // if ((x > 0) && (y > 0) && (x + y) < 0) throw new OverflowException
}