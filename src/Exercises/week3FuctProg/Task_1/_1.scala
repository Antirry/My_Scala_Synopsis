package Exercises.week3FuctProg.Task_1

object _1 extends App {
  import scala.util.{Failure, Success, Try}

  class Connection {
    def connect: Try[String] = {
      // Логика установки соединения
      // ...
      Success("Connected")
    }
  }

  object Connection {
    def apply(config: Map[String, String]): Option[Connection] = {
      val array_host: Array[String] = config.getOrElse("host", "").split("\\.")
      val check_host = array_host.forall(_.toInt < 256) && array_host.length < 5
      val check_port = if (config.getOrElse("port", "").length < 6) true else false
      if (check_port && check_host) {
        Some(new Connection)
      } else {
        None
      }
    }
  }

  // Используем API
  val config = Map("host" -> "127.0.255.1.1", "port" -> "80804") // Здесь используйте фактические значения из API

  Connection(config) match {
    case Some(connection) =>
      connection.connect match {
        case Success(status) => println(status)
        case Failure(exception) => println(exception)
      }
    case None => println("")
  }

}


object _3 extends App {

  val config = Map("host" -> "127.0.1111.1", "port" -> "8080")

  val array_host: Array[String] = config.getOrElse("host", "").split("\\.")
  val check_host = array_host.forall(_.toInt < 256)

  println(check_host)
}