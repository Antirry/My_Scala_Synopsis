package Exercises.week3FuctProg.Task_1

object API_Task extends App {
  val config = Map("host" -> "127.0.0.1", "port" -> "8080")

  class ConnectionImpl {

    def connect(host: String, port: String): Boolean = {
      true
    }
    def connect_check(config: Map[String, String]): Option[ConnectionImpl] = {
      (for {
        host <- config.get("host")
        port <- config.get("port")
        if (host.nonEmpty && port.nonEmpty)
      } yield {
        connect(host, port); new ConnectionImpl
      }).orElse(None)
    }
  }

  object ConnectionImplObject extends ConnectionImpl {

    def apply(config: Map[String, String]): Unit = {
      connect_check(config) match {
        case Some(_) => println("Connected")
        case None => println("")
      }
    }
  }

  ConnectionImplObject(config)

}
