package Lectures.week4Binary_trees_Бинарные_деревья

// Сделай обход дерева по уровням с использованием хвостовой рекурсии чтобы возвращал List[Int] (List[T])

//Do a tree traversal using tail recursion to return List[Int] (List[T])

object Task_4 extends App {

  abstract class BinaryTree[+T] {
    def value: T
    def leftChild: BinaryTree[T]
    def rightChild: BinaryTree[T]

    def isEmpty: Boolean
    def isLeaf: Boolean
    def collectNodes(): List[T]
  }

  case class Node[+T](
                       override val value: T,
                       override val leftChild: BinaryTree[T],
                       override val rightChild: BinaryTree[T])
    extends BinaryTree[T] {

    override def isEmpty: Boolean = false
    override def isLeaf: Boolean = leftChild.isEmpty && rightChild.isEmpty

    override def collectNodes(): List[T] = {
      def loop_nodes(toInspect: List[BinaryTree[T]], acc: List[T]): List[T] = {
        // Пустой список сигнализирует об окончании обхода, и возвращается накопленный список 'acc'
        toInspect match {
          case Nil => acc
          // Когда найден листовой узел, отбрасываем его и переходим к следующему узлу в списке
          case TreeEnd :: tail => loop_nodes(tail, acc)
          // Когда найден нелистовой узел, разверните его, добавьте его "значение" и перейдите к его дочерним узлам
          case Node(value, left, right) :: tail =>
            loop_nodes(left :: right :: tail, acc :+ value)
        }
      }

      // Начинаем осмотр узлов с корневого узла
      loop_nodes(List(this), List.empty).reverse
    }


  }

  case object TreeEnd extends BinaryTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException
    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException
    override def rightChild: BinaryTree[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true
    override def isLeaf: Boolean = false
    override def collectNodes(): List[Nothing] = List()
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

  val leaves = tree.collectNodes() // List(4,5,6,7)
  println(leaves)
}
