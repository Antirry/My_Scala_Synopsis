package Lectures.week4Binary_trees_Бинарные_деревья


object Task_5 extends App {

  abstract class BinaryTree[+T] {
    def value: T
    def leftChild: BinaryTree[T]
    def rightChild: BinaryTree[T]

    def isEmpty: Boolean
    def isLeaf: Boolean
  }

  case class Node[+T](
                       override val value: T,
                       override val leftChild: BinaryTree[T],
                       override val rightChild: BinaryTree[T])
    extends BinaryTree[T] {

    override def isEmpty: Boolean = false
    override def isLeaf: Boolean = leftChild.isEmpty && rightChild.isEmpty
  }

  case object TreeEnd extends BinaryTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException
    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException
    override def rightChild: BinaryTree[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true
    override def isLeaf: Boolean = false
  }

  val tree = Node(1,
    Node(2,
      Node(4,
        TreeEnd,
        TreeEnd
      ),
      Node(5,
        TreeEnd,
        TreeEnd
      )
    ),
    Node(3,
      Node(6,
        TreeEnd,
        TreeEnd
      ),
      Node(7,
        TreeEnd,
        TreeEnd
      )
    )
  )

/*
def hasPath(tree: BinaryTree[Int], target: Int): Boolean = {
    def traverse(node: Node[Int], sum: Int): Boolean = node match {
      case TreeEnd => false // базовый случай: узел не существует
      case Node(value, TreeEnd, TreeEnd) if sum + value == target => true
      case Node(value, left, TreeEnd) => traverse(left.asInstanceOf[Node[Int]]], sum + value)
      case Node(value, TreeEnd, right) => traverse(right.asInstanceOf[Node[Int]]], sum + value)
      case Node(value, left, right) if !node.isLeaf =>
        traverse(left.asInstanceOf[Node[Int]], sum + value) ||
          traverse(right.asInstanceOf[Node[Int]], sum + value) // рекурсивный случай: нелистовой узел с левыми и правыми детьми
      case _ => false // универсальный случай: узел является листом без достижения цели
    }

    traverse(tree.asInstanceOf[Node[Int]], 0) // начинаем рекурсию с корневого узла заданного дерева с начальной суммой 0
  }

  def hasPath(tree: BinaryTree[Int], target: Int): Boolean = {
    def traverse(node: BinaryTree[Int], sum: Int): Boolean = node match {
      case TreeEnd => false // базовый случай: узел не существует
      case Node(value, TreeEnd, TreeEnd) if sum + value == target => true // базовый случай: узел является листом
      case Node(value, left, TreeEnd) => traverse(left, sum + value) // рекурсивный случай: нелистовой узел с единственным левым дочерним узлом
      case Node(value, TreeEnd, right) => traverse(right, sum + value) // рекурсивный случай: нелистовой узел с единственным правым дочерним узлом
      case Node(value, left, right) if !node.isLeaf =>
        traverse(left, sum + value) || traverse(right, sum + value) // рекурсивный случай: нелистовой узел с левым и правым дочерними элементами
      case _ => false // всеобъемлющий случай: узел является листом без достижения цели
    }

    traverse(tree, 0) // начинаем рекурсию с корневого узла заданного дерева с начальной суммой 0
  }
 */

  def hasPath(tree: BinaryTree[Int], target: Int): Boolean = tree match {
    case TreeEnd => false // базовый случай: достиг несуществующего узла
    case Node(value, TreeEnd, TreeEnd) => value == target // базовый случай: достигнут листовой узел
    case Node(value, left, right) =>
      hasPath(left, target - value) || hasPath(right, target - value) // рекурсивный случай: проверка левого и правого поддеревьев
  }

  val leaves = hasPath(tree, 7)
  println(leaves)

}
