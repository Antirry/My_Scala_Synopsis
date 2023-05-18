package Exercises.week2oop

object _2_ extends App {

  class Logger3(val msg: Int = 0) {

    def info1(): Logger3 = {
      println("INFO New Message")
      new Logger3(msg + 1)
    }

    def info1(msgCount: Int = msg): Logger3 = {
      def loop(n: Int, logger: Logger3): Logger3 = {
        if (n <= 0) logger
        else loop(n - 1, logger.info1())
      }
      loop(msgCount, this)
    }

    def print(): Unit = println(msg)
  }


  val logger = new Logger3
  logger.info1().print()
  logger.info1(3).print()
  logger.info1().info1().info1().print()
}
