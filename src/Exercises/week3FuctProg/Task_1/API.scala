package Exercises.week3FuctProg.Task_1

/*

Последовательность работы программы:

  получение значения хоста
  получение значения порта
  отправка запроса на установление соединения
  получение статуса соединения
  вывод статуса соединения на экран



отправка запроса на соединение: взяв значение хоста,
 которого может и не быть,
  взяв значение порта, которого может и не быть,
   вызываем метод, ответственный за попытку установить соединение

получение статуса соединения: если запрос был успешен,
 получить статус соединения, иначе - значения у статуса не будет

вывод статуса соединения на экран: только для успешного соединения выводим сообщение на экран

flatMap =>

//  if (host != null)
//    if (port != null)
//      return Connection.apply(host, port)
//  return null

map

//  if (conn != null)
//    return conn.connect
//  return null

foreach

//  if (connStatus != null)
//    println(connStatus)
 */

object API extends App {

  import scala.util.Try

  val config: Map[String, String] = Map("host" -> "localhost", "port" -> "8080")

  class Connection(host: String, port: String) {
    def connect(): Unit = {
      // Perform connection logic here
      if (Try(new java.net.Socket(host, port.toInt)).isSuccess) {
        println("Connected")
      }
    }
  }

  object Connection {
    def apply(config: Map[String, String]): Option[Connection] = {
      val host = config.get("host")
      val port = config.get("port")
      (host, port) match {
        case (Some(h), Some(p)) => Some(new Connection(h, p))
        case _ => None
      }
    }
  }

  // Create a new Connection instance if the host and port values are present in the config Map
  // Then, attempt to connect to the server using the connect() method
  Connection(config).foreach(_.connect())
}

/*
object API_1_in_one_line extends App {

  val config: Map[String, String] = Map("host" -> "port")

  config.get("host").flatMap(host => config.get("port").flatMap(port => Connection(host, port)).map(connection => connection.connect)).foreach(println)

  val forConnectionStatus = for {
    host <- config.get("host")
      port <- config.get("port")
    connection <-  Connection(host, port)
  } yield  connection.connect

    forConnectionStatus.foreach(println)
}
 */