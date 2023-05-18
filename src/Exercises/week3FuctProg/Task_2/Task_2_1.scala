package Exercises.week3FuctProg.Task_2

import scala.util.Try

object Task_2_1 extends App {

  object HttpService {
    def apply(host: String, port: String): Connection = new Connection()
  }

  class Connection {
    def get(url: String) = "..."

    def safeConnection(host: String, port: String): Try[Connection] = {
      Try(HttpService.apply(host, port))
    }
    safeConnection(host, port).foreach(_ => Try(render(_)).getOrElse("Your Connection was Interrupted"))
  }

  def render(s: String) = print(s)

  val host = ""
  val port = ""

}