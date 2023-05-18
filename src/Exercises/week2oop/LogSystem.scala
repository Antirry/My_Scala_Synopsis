package Exercises.week2oop

/*
1) Задать значение по умолчанию для msgNum
2) Сделать параметр msgNum полем класса
3) Удалить метод count

Изначально у нас ноль сообщений,
 так что упрощаем инициализацию и добавляем значение по умолчанию.
  Раз можно сразу получить доступ к полю класса,
   предварительно поставив val перед параметром, то незачем прописывать лишние методы.
 */

// class Logger(val msgNum: Int = 0) {
/*
  Мы работаем с неизменяемыми данными,
   так что использования new и создания нового экземпляра класса не избежать
*/

//  def info(): Logger = {
//    println("INFO New Message")
//    new Logger(msgNum + 1)
//  }

  // Сделать перегрузку метода info
/*
Как только мы закончим выводить все сообщения - мы возвращаем текущий объект класса(т.е. this).
 Иначе - вызываем сначала метод info(мы же не хотим повторяться и
  заново описывать те же самые действия - принт
   и создание нового экземпляра - раз на это у нас уже есть код), а затем перегруженный метод info
 */

//  def info(msgCount: Int = msgNum): Unit = {
//
//    def loop(n: Int) {
//      if (n <= 0) this
//      else info.info(n - 1)
//    }
//
//    loop(msgCount)
//  }
//
//  def print(): Unit = println(msgNum)
//}


class Logger(val msgNum: Int = 0) {

  def info(): Logger = {
    println("INFO New Message")
    new Logger(msgNum + 1)
  }

  def info(msgCount: Int = msgNum): Logger = { // изменено: возвращаем экземпляр Logger, а не Unit

    def loop(n: Int, logger: Logger): Logger = { // добавлен параметр logger для накапливания изменений

      if (n <= 0) logger // возвращаем logger, когда достигнуто нужное количество сообщений

      else loop(n - 1, logger.info()) // вызываем рекурсивно loop, передавая увеличенный на 1 экземпляр logger
    }

    loop(msgCount, this) // вызываем loop с начальным значением this
  }

  def print(): Unit = println(msgNum)
}

object Task_1 extends App {

  /*
  ВЫВОД ДОЛЖЕН БЫТЬ:

  INFO New Message
  1
  INFO New Message
  INFO New Message
  INFO New Message
  3
  INFO New Message
  INFO New Message
  INFO New Message
  3
   */

  // ВЫЗОВ КЛАССА, ПОТОМ ВЫЗОВ СООБЩЕНИЙ И
  // НЕСКОЛЬКИХ СООБЩЕНИЙ С ВЫЗОВОМ КОЛИЧЕСТВА СООБЩЕНИЙ

  val logger = new Logger
  logger.info().print()
  logger.info().info().info().print()
  logger.info().info().info().print()

  // ВЫЗЫВ КЛАССА, ПОТОМ ВЫЗОВ СООБЩЕНИЙ И
  // ВЫЗОВ РЕКУРСИИ СООБЩЕНИЙ И ВЫЗОВ КОЛИЧЕСТВА СООБЩЕНИЙ
  // ВЫЗОВ НЕСКОЛЬКИХ СООБЩЕНИЙ С ВЫЗОВОМ КОЛИЧЕСТВА СООБЩЕНИЙ


}



// CHAT GPT

class Logger1 {
  private var messages: List[String] = List()

  def count(): Int = messages.length

  def info(message: String): Unit = {
    messages = message :: messages
    println(s"INFO: $message")
  }

  def info(messages: List[String]): Unit = {
    this.messages = this.messages ::: messages
    messages.foreach(message => println(s"INFO: $message"))
  }
}