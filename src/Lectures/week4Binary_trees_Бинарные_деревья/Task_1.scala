package Lectures.week4Binary_trees_Бинарные_деревья

object Task_1 extends App {

  abstract class BinaryTree[+T] {
    def value: T
    def leftChild: BinaryTree[T]
    def rightChild: BinaryTree[T]

    def isEmpty: Boolean
    def isLeaf: Boolean
    def collectLeaves: List[BinaryTree[T]]
  }

  case class Node[+T](
                       override val value: T,
                       override val leftChild: BinaryTree[T],
                       override val rightChild: BinaryTree[T])
    extends BinaryTree[T] {
    override def isEmpty: Boolean = false
    override def isLeaf: Boolean = leftChild.isEmpty && rightChild.isEmpty

    override def collectLeaves: List[BinaryTree[T]] = {
      @annotation.tailrec
      def collectTail(toInspect: List[BinaryTree[T]], leaves: List[BinaryTree[T]]): List[BinaryTree[T]] = {
        toInspect match {
          case Nil => leaves
          case node :: rest =>
            val nextToInspect = rest ++ List(node.leftChild, node.rightChild).filterNot(_.isEmpty)
            if (nextToInspect.isEmpty) {
              collectTail(Nil, leaves :+ node)
            } else {
              collectTail(nextToInspect, if (node.isLeaf) leaves :+ node else leaves)
            }
        }
      }

      collectTail(List(this), List.empty)
    }
  }

  case object TreeEnd extends BinaryTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException
    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException
    override def rightChild: BinaryTree[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true
    override def isLeaf: Boolean = false
    override def collectLeaves: List[Nothing] = List()
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

  // Collect and print the leaves
  val leaves = tree.collectLeaves.map(_.value).sortBy(_.intValue())
  println(leaves)

}
