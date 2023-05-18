object Task_6 extends App {
  // Определяем абстрактный класс BinaryTree, который является основой для Node и TreeEnd
  abstract class BinaryTree[+T] {
    // Каждое BinaryTree имеет значение, левый дочерний элемент и правый дочерний элемент
    def value: T
    def leftChild: BinaryTree[T]
    def rightChild: BinaryTree[T]

    // Определяем методы для проверки того, является ли бинарное дерево пустым или листовым узлом
    def isEmpty: Boolean
    def isLeaf: Boolean
  }

  // Определяем класс case с именем Node, который наследуется от BinaryTree
  case class Node[+T](
                       // Класс Node имеет значение, левый дочерний элемент и правый дочерний элемент
                       override val value: T,
                       override val leftChild: BinaryTree[T],
                       override val rightChild: BinaryTree[T]) extends BinaryTree[T] {

    // Определяем методы для проверки того, является ли бинарное дерево пустым или листовым узлом
    override def isEmpty: Boolean = false
    override def isLeaf: Boolean = leftChild.isEmpty && rightChild.isEmpty

  }

  // Определяем объект-синглтон (val `object`: Nothing = null) TreeEnd, который наследуется от BinaryTree
  case object TreeEnd extends BinaryTree[Nothing] {
    // Реализуем методы в BinaryTree с броском нового NoSuchElementException
    override def value: Nothing = throw new NoSuchElementException
    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException
    override def rightChild: BinaryTree[Nothing] = throw new NoSuchElementException

    // Определите методы для проверки того, является ли бинарное дерево пустым или листовым узлом
    override def isEmpty: Boolean = true
    override def isLeaf: Boolean = false
  }

  def parseToInt(s: String): Option[Int] = {
    // Создаем кортеж из булева флага, указывающего, является ли число отрицательным, и списка цифр
    val (isNegative, digits) = s.toList match {
      // Если строка начинается с '-', установите флаг isNegative в true и удалите его из цифр
      case '-' :: rest => (true, rest.map(_ - '0'))
      // Строка не начинается с '-', поэтому установите флаг isNegative в false и преобразуйте все символы в цифры
      case digits => (false, digits.map(_ - '0'))
    }

    // Используйте foldLeft для списка цифр, начиная с опции, содержащей значение 0
    // Для каждой цифры умножьте предыдущее значение на 10 и добавьте к нему новую цифру
    digits.foldLeft(Option(0)) {
      case (acc, digit) =>
        for {
          // Получаем предыдущее значение из опции
          prev <- acc
          // Добавляем цифру к предыдущему значению и сохраняем результат в Option
          result <- Option(prev * 10 + digit)
        } yield result
      // При необходимости сопоставить конечный результат с отрицательным числом или вернуть положительный результат
    }.map(result => if (isNegative) -result else result)
  }

  def convertTree(tree: BinaryTree[String]): BinaryTree[Int] = {
    //соответствие дереву
    tree match {
      // Если дерево пустое, возвращаем пустое дерево
      case TreeEnd => TreeEnd
      // Если узел имеет значение, преобразовать значение в целое число и рекурсивно преобразовать левые и правые дочерние узлы
      case Node(value, left, right) =>
        val intValue = parseToInt(value).getOrElse(0)
        Node(intValue, convertTree(left), convertTree(right))
    }
  }

  def findAllPaths(tree: BinaryTree[String], target: String): List[List[Int]] = {

    // Преобразуем строковое двоичное дерево в целочисленное двоичное дерево и пытаемся разобрать цель как целое число
    val treeInt = convertTree(tree)
    val targetInt = parseToInt(target).getOrElse(0)

    // Рекурсивная вспомогательная функция для поиска всех путей к узлам листьев, значения которых в сумме равны целевому значению
    def helper(node: BinaryTree[Int], target: Int, path: List[Int]): List[List[Int]] = node match {
      // Достигнут листовой узел без значения, возвращаем пустой список
      case TreeEnd => Nil
      // Узел является листовым узлом с целевым значением, возвращаем список, содержащий путь от корня до этого листа
      case Node(value, TreeEnd, TreeEnd) if (value == target) =>
        List(path :+ value)
      // Достигнут листовой узел без значения для добавления, возвращаем пустой список
      case Node(value, TreeEnd, TreeEnd) => Nil
      // Узел не является листовым узлом, выполните перебор левых и правых дочерних узлов с обновленными значениями path и target
      case Node(value, left, right) =>
        val updatedPath = path :+ value
        helper(left, target - value, updatedPath) ++ helper(right, target - value, updatedPath)
      // Узел пуст, возвращаем пустой список
      case _ => Nil
    }

    // Вызов вспомогательной функции с целочисленным деревом, целевым целым числом и пустым путем для начала
    helper(treeInt, targetInt, Nil)
  }

  val tree: Node[String] = Node("1",
    Node("2",
      Node("4",
        TreeEnd,
        TreeEnd),
      Node("5",
        TreeEnd,
        Node("8",
          TreeEnd,
          TreeEnd))),
    Node("3",
      Node("6",
        TreeEnd,
        TreeEnd),
      Node("3",
        TreeEnd,
        Node("1",
          TreeEnd,
          TreeEnd))),
  )

  val paths = findAllPaths(tree, "7")
  println(paths)
}