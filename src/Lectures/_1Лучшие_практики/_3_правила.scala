package Lectures._1Лучшие_практики

object _3_правила extends App {
/*

  Если что-то может выдать исключения - используем Try
  Если что-то может не сработать - используем Either
  Если чего-то может не быть - используем Option

Помните, что в случае исключений не работает referential transparency

это когда результат программы не меняется
 при замене выражения переменной, содержащей это выражение
 */

  private def foo(): Unit = if (1 > 2) throw new RuntimeException else println(2)
  foo() // 2 - все отлично отработало

  private def anotherFoo(): Unit = {
    val exception = throw new RuntimeException("Oops")
    if (1 > 2) exception else println(2) // заменили new RuntimeException на переменную exception
  }
  anotherFoo() // RuntimeException

// Кстати, даже сам код anotherFoo будет помечен, как Unreachable Code
}
