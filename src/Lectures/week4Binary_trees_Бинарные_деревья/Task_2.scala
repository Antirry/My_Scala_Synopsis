package Lectures.week4Binary_trees_Бинарные_деревья

object Task_2 extends App {

  abstract class BinaryTree[+T] {
    def value: T
    def leftChild: BinaryTree[T]
    def rightChild: BinaryTree[T]

    def isEmpty:  Boolean

    def isLeaf: Boolean
    def collectLeaves: List[BinaryTree[T]]
    def countLeaves: Int
  }

  case class Node[+T](
                       override val value: T,
                       override val leftChild: BinaryTree[T],
                       override val rightChild: BinaryTree[T])
    extends BinaryTree[T] {

    override def isEmpty: Boolean = false
    override def isLeaf: Boolean = leftChild.isEmpty && rightChild.isEmpty

    override def collectLeaves: List[BinaryTree[T]] = {
      // Определяем внутреннюю функцию для сбора листьев дерева
      @annotation.tailrec
      def collectTail(toInspect: List[BinaryTree[T]], leaves: List[BinaryTree[T]]): List[BinaryTree[T]] = {
        // Обрабатываем список узлов для обхода
        toInspect match {
          case Nil => leaves // Если список узлов пуст, возвращаем список листьев
          case node :: rest =>
            // Добавляем дочерние узлы к текущему списку узлов, исключая пустые узлы
            val nextToInspect = rest ++ List(node.leftChild, node.rightChild).filterNot(_.isEmpty)
            if (nextToInspect.isEmpty) {
              // Если список узлов пуст, возвращаем список листьев с добавлением текущей ноды
              collectTail(Nil, leaves :+ node)
            } else {
              // Иначе рекурсивно вызываем функцию collectTail с новым списком узлов
              // и обновленным списком листьев, в зависимости от того, является ли текущая нода листом.
              collectTail(nextToInspect, if (node.isLeaf) leaves :+ node else leaves)
            }
        }
      }
      // Вызываем внутреннюю функцию collectTail с корнем дерева и пустым списком листьев
      collectTail(List(this), List.empty)
    }

    override def countLeaves: Int = collectLeaves.map(_.value).size

  }

  case object TreeEnd extends BinaryTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException
    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException
    override def rightChild: BinaryTree[Nothing] = throw  new NoSuchElementException

    override def isEmpty: Boolean = true

    override def isLeaf: Boolean = false
    override def collectLeaves: List[BinaryTree[Nothing]] = List()
    override def countLeaves: Int = 0
  }

}
