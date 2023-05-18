package Lectures.Аспектно_ориентированное_программирование

/*

Немного затронем тему AOP.
 Это очень полезная вещь для создания чистого кода.
  Особенно при внедрении того же логирования на проекте.

Начнем сразу с примеров. Есть какой-то сервис по работе с базами данных:

 */

object Description extends App {

  trait Service {
    def fastRead(): List[String]

    def slowRead(): List[String]
  }

  class DataBaseService extends Service {
    private def read() = List("some", "data", "to", "read")

    override def fastRead(): List[String] = read()

    override def slowRead(): List[String] = {
      ((1 to 100000).foreach(_ => read()))
      read()
    }
  }

  val dbService = new DataBaseService

  println(dbService.fastRead())
  println(dbService.slowRead())

}




/*

  необходимо внедрить логи и отследить, сколько времени занимает выполнение метода

 */

object Example_Standard extends App {




  //  Стандартный вариант:


  trait Service {
    def fastRead(): List[String]

    def slowRead(): List[String]
  }

  class DatabaseService extends Service {
    private def read() = List("some", "data", "to", "read")

    override def fastRead(): List[String] = {
      val start = System.currentTimeMillis()
      val data = read()
      val time = System.currentTimeMillis() - start
      println(s"[fast] read took / чтение заняло $time")
      data
    }

    override def slowRead(): List[String] = {
      val start = System.currentTimeMillis()
      (1 to 100000).foreach(_ => read())
      val data = read()
      val time = System.currentTimeMillis() - start
      println(s"[slow] read took / чтение заняло $time")
      data
    }
  }
}


object EXAMPLE_АСПЕКТНО_ОРИЕНТИРОВАННОЕ_ПРОГРАММИРОВАНИЕ extends App {



  // Пример с ASPECT ORIENTED PROGRAMMING - AOP


  /*

  Сделаем код чище посредством AOP. Для этого

    1. основные методы (fastRead, slowRead) вернем к первоначальному состоянию

    2. функционал, связанный с логированием, вынесем в отдельный трейт (trait LoggingService)

    3. методы из DatabaseService, которые потребуют логирования,
        пропишем в LoggingService под тем же именем, но с ключевым словом abstract

    4. в LoggingService вызов методов из DatabaseService обеспечим через super

    5. при создании экземпляра класса DatabaseService
        не забудем про LoggingService - используем with:  new DatabaseService with LoggingService

   */


  trait Service {
    def fastRead(): List[String]

    def slowRead(): List[String]
  }

  class DatabaseService extends Service {
    private def read(): List[String] = List("some", "data", "to", "read")

    override def fastRead(): List[String] = read()

    override def slowRead(): List[String] = {
      ((1 to 100000).foreach(_ => read()))
      read()
    }
  }

  trait LoggingService extends Service {
    abstract override def fastRead(): List[String] = {
      val start = System.currentTimeMillis()
      val data = super.fastRead()
      val time = System.currentTimeMillis() - start
      println(s"[fast] read took / чтение заняло $time")
      data
    }

    abstract override def slowRead(): List[String] = {
      val start = System.currentTimeMillis()
      val data = super.fastRead()
      val time = System.currentTimeMillis() - start
      println(s"[fast] read took / чтение заняло $time")
      data
    }
  }

  val dbService = new DatabaseService with LoggingService

  println(dbService.fastRead())
  println(dbService.slowRead())

}
