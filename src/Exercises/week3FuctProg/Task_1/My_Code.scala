package Exercises.week3FuctProg.Task_1

object Test1 extends App {

  import scala.util.Try

  case class ConnectionImpl1(status: String) {
    def connect: String = {
      if (status == "Connected") status else ""
    }
  }

  object ConnectionObjectImpl {
    def apply(host: Option[String], port: Option[String]): Option[ConnectionImpl1] = {
      val check_host_port = host.getOrElse("", "").toString.split("\\.").forall(_.toInt < 256) && port.getOrElse("", "").toString.split("\\.").length < 5
      if (check_host_port) {
        None
      } else {
        val maybeConn = for {
          h <- host
          p <- port
        } yield ConnectionImpl1("Connected")
        maybeConn
      }
    }
  }

  object ConnectionImpl {
    def connect(config: Map[String, String]): Unit = {
      val maybeConn = for {
        host <- config.get("host")
        port <- config.get("port")
        conn <- ConnectionObjectImpl(Some(host), Some(port))
        connStatus <- Try(conn.connect).toOption
      } yield connStatus

      maybeConn.foreach(println)
    }
  }

  // Example configuration
  val config = Map(
    "host" -> "1.1.1.1",
    "port" -> "8080"
  )

  // Call Connector.connect() with the configuration
  ConnectionImpl.connect(config)


}

