package Exercises.week2oop

/*

ЛОГИКА ПРОГРАММЫ ->

Программа определяет класс "Logger2", который содержит несколько методов. 

Метод "info" создает новый объект класса "Logger" с увеличенным номером сообщения. 

Метод "info(msgCount)" вызывает метод "info()" заданное количество раз,
 используя рекурсию и передавая в качестве аргумента текущий объект класса "Logger2".
  Таким образом, эта функция создает цепочку объектов класса "Logger", связанных между собой. 

Метод "print" выводит на экран номер последнего сообщения. 

В основной программе создается объект класса "Logger2"
 и вызываются несколько методов "info()" с различными аргументами.
  Затем номер последнего сообщения выводится на экран.

 */

class Logger2(val msgNum: Int = 0) {

  def info(): Logger2 = {
    println("INFO New Message")
    new Logger2(msgNum + 1)
  }

  def info(msgCount: Int = msgNum): Logger2 = {
    def loop(n: Int, logger: Logger2): Logger2 = {
      if (n <= 0) logger
      else loop(n - 1, logger.info())
    }
    loop(msgCount, this)
  }

  def print(): Unit = println(msgNum)
}

object _01_ extends App {

  val logger2 = new Logger2
  logger2.info().print()
  logger2.info(3).print()
  logger2.info().info().info().print()
}
