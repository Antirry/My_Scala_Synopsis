package Lectures.week2oop

object Task_9_4_Inheritance extends App {

  //  trait - обмен между классами информацией о структуре и полях.
  //  Используется вместе с abstract class, для

  trait Configs {
    val ACCOUNT_TYPE_DEFAULT = "free"
    val ACCOUNT_TYPE_PAID = "paid"
    val SUBSCRIPTION_STATUS = "active"
  }

  // object - для вызова метода без (), например "Settings.AccountSettings"

  object Settings {
    case class AccountSettings(
                                email: String,
                                password: String,
                                picture: String)

    case class SubscriptionSettings(
                                     payment: String,
                                     notifications: String,
                                     expiration: String)
  }

  // object - для вызова метода без (), например "Settings.AccountSettings"

  object Subscriber {
    def subscribe(settings: Settings.SubscriptionSettings): Unit = println("subscribed")
  }

  // object - для вызова метода без (), например "Settings.AccountSettings"

  object Unsubscriber {
    def unsubscribe(): Unit = println("unsubscribed")
  }

  // для изменения информации в классе, в примере о типе аккаунта,
  // используется вместе с trait, в примере для описания аккаунта

  abstract class Account(accountID: String, settings: Settings.AccountSettings) {
    def info(): Unit
  }

  // Используется вызов класса описания (abstract class Account)
  // с переменнами (trait Config)

  // слово extends можно только единожды, но в плане использования

  // with ограничений нет - можно подключить столько трейтов, сколько нужно.

  // Также вызывается функция (метод) с показом типа аккаунта (Подписан)

  // создаем класс FreeAccount, который наследуется от класса Account и реализует трейт Configs

  /*
  Трейт - это некий шаблон, который содержит набор методов и полей,
  которые могут быть использованы в классах.
  Трейты могут быть реализованы в классах с помощью ключевого слова with.
   */
  class FreeAccount(
                     accountID: String,
                     settings: Settings.AccountSettings)

    extends Account(accountID, settings) with Configs {

    // переопределяем метод info, который выводит информацию о типе аккаун
    override def info(): Unit = println(s"Account Type: $ACCOUNT_TYPE_DEFAULT")

    // создаем метод subscribe, который подписывает пользователя на уведомления
    def subscribe(settings: Settings.SubscriptionSettings): Unit = {
      Subscriber.subscribe(settings)
    }
  }

  // Используется вызов класса описания (abstract class Account)
  // с переменнами (trait Config)

  // слово extends можно только единожды, но в плане использования

  // with ограничений нет - можно подключить столько трейтов, сколько нужно.

  // Также вызывается функция (метод) с показом типа аккаунта (Не подписан)
  // Метод вызывается в (), так как класс


  // создаем класс PaidAccount, который наследуется от класса Account и реализует трейт Configs
  class PaidAccount(
                     accountID: String,
                     settings: Settings.AccountSettings)

    extends Account(accountID, settings) with Configs {

    // переопределяем метод info, который выводит информацию о типе аккаунта и статусе подписки
    override def info(): Unit = {
      println(s"Account Type: $ACCOUNT_TYPE_PAID")
      println(s"Subscription Status: $SUBSCRIPTION_STATUS")
    }

    // создаем метод unsubscribe, который отписывает пользователя от уведомлений
    def unsubscribe(): Unit = Unsubscriber.unsubscribe()
  }

  // создаем объект paidAccount класса PaidAccount с заданными параметрами
  val paidAccount = new PaidAccount(
    accountID = "1",
    settings = Settings.AccountSettings(
      email = "test@mail.com",
      password = "abr#45&",
      picture = "link/to/some/pic"))


  // выводим информацию о типе аккаунта и отписываем пользователя от уведомлений
  // .subscribe(Settings.SubscriptionSettings("payment", "notifications", "expiration"))
  println(paidAccount.info())
  println(paidAccount.unsubscribe())

  // создаем объект freeAccount класса FreeAccount с заданными параметрами
  val freeAccount = new FreeAccount(
    accountID = "2",
    settings = Settings.AccountSettings(
      email = "test2@mail.com",
      password = "abr#45&",
      picture = "link/to/some/pic"))

  // выводим информацию о типе аккаунта и подписываем пользователя на уведомления
  println(freeAccount.info())
  println(freeAccount.subscribe(
    Settings.SubscriptionSettings(
      "payment", "notifications", "expiration"))
  )
}