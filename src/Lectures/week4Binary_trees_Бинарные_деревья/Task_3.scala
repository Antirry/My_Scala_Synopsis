package Lectures.week4Binary_trees_Бинарные_деревья

object Task_3 extends App {

  abstract class BinaryTree[+T] {
    def value: T
    def leftChild: BinaryTree[T]
    def rightChild: BinaryTree[T]

    def isEmpty: Boolean
    def nodesAtLevel(level: Int): List[BinaryTree[T]]
  }

  case class Node[+T](
                       override val value: T,
                       override val leftChild: BinaryTree[T],
                       override val rightChild: BinaryTree[T])
    extends BinaryTree[T] {

    override def isEmpty: Boolean = false

    override def nodesAtLevel(level: Int): List[BinaryTree[T]] = {
      if (level == 0) {
        // Базовый случай: мы достигли нужного уровня, поэтому возвращаем список
        // содержащий этот узел
        List(this)
      } else {
        // Рекурсивный случай: вызываем nodesAtLevel для левого и правого дочерних узлов
        // на уровне на один меньше, чем текущий уровень, и объединить результаты
        leftChild.nodesAtLevel(level - 1) ++ rightChild.nodesAtLevel(level - 1)
      }
    }

  }


  case object TreeEnd extends BinaryTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException
    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException
    override def rightChild: BinaryTree[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true

    override def nodesAtLevel(level: Int): List[BinaryTree[Nothing]] = List()
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

  val leaves = tree.nodesAtLevel(2).map(_.value).sortBy(_.intValue()) // List(4,5,6,7)
  println(leaves)
}
