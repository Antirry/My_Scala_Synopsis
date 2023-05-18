package Exercises.week3FuctProg.Task_1

object _2 extends App {
  import scala.util.Try

  case class Connection(status: String) {
    def connect: String = {
      if (status == "Connected") status else ""
    }
  }

  object Connection {
    def apply(host: Option[String], port: Option[String]): Option[Connection] = {
      val maybeConn = for {
        h <- host
        p <- port
      } yield Connection("Connected")

      maybeConn
    }
  }

  object Connector {
    def connect(config: Map[String, String]): Unit = {
      val maybeConn = for {
        host <- config.get("host")
        port <- config.get("port")
        conn <- Connection(Some(host), Some(port))
        connStatus <- Try(conn.connect).toOption
      } yield connStatus

      maybeConn.foreach(println)
    }
  }
}

object _4 extends App {

  import scala.collection.mutable.Map

  object Connection {
    def apply(host: String, port: String): Option[Connection] = {
      if (host.nonEmpty && port.nonEmpty) {
        Some(new Connection(host, port))
      } else {
        None
      }
    }
  }

  class Connection(host: String, port: String) {
    def connect(): Unit = {
      // logic for connecting to the server
      println("Connected")
    }
  }

  val config: Map[String, String] = Map("host" -> "localhost", "port" -> "8080")

  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect()))
    .foreach(println)

}
