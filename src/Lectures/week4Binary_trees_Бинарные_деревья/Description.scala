package Lectures.week4Binary_trees_Бинарные_деревья

import com.sun.source.tree.BinaryTree

object Description extends App {

  /*
  Бинарное дерево состоит из узлов, каждый из которых имеет свое значение.

  В таком дереве каждый узел:

    либо не имеет потомков вообще (такие узлы называются листьями),
    либо имеет только одного потомка (правого или левого),
    либо имеет обоих потомков.

  Самый верхний узел является корнем дерева.

  Картинка:
    https://drive.google.com/file/d/11vFf-Kps0xJ7lnfFZma7RsJNuyt03Cb7/view?usp=share_link

   */

}

object Description1 extends App {

//  Описать структуру данных для дерева можно вот так:

  abstract class BinaryTree[+T] {
    def value: T // значение узла

    def leftChild: BinaryTree[T] // левый потомок

    def rightChild: BinaryTree[T] // правый потомок
  }

//  Дерево состоит из узлов, выразим через класс:

  case class Node[+T](
                     override val value: T,
                     override val leftChild: BinaryTree[T],
                     override val rightChild: BinaryTree[T]
                     ) extends BinaryTree[T]

//  Листья, вообще не имеют потомков. Отсутствие описывается таким образом:

  case object TreeEnd extends BinaryTree[Nothing] {

    override def value: Nothing = throw new NoSuchElementException
    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException
    override def rightChild: BinaryTree[Nothing] = throw new NoSuchElementException
  }

// Дерево, представленное выше, можно задать в коде следующим образом:

  val tree = Node(1,
    Node(2,
      Node(4,
        TreeEnd,
        TreeEnd),
      Node(5,
        TreeEnd,
        Node(8,
          TreeEnd,
          TreeEnd))),
    Node(3,
      Node(6,
        TreeEnd,
        TreeEnd),
      Node(7,
        TreeEnd,
        TreeEnd)))

}




object Description2 extends App {
/*

  В коде задействованы обобщения, так как планируется создание нескольких деревьев,
   одно из которых, например, будет состоять из узлов типа Int,
    другое же будет, например, состоять из узлов типа String.

  Кроме того, мы воспользовались ковариантностью +T.
   Это необходимо для предотвращения нежелательного смешения типов.


Напомним, что ковариантность предполагает работу с подтипами.


  Т.е. если в качестве T использовать Parent, то далее допускается работа с Child,
   причем Child должен быть подтипом Parent,
    т.к. работа с супертипами Parent будет запрещена.


  Проиллюстрировать это можно на следующем примере:

 */

  class GrandParent
  class Parent extends GrandParent
  class Child extends Parent

  class Family[+T]

  val covariantFamily: Family[Parent] = new Family[Child]

/*
  Ошибочный код, потому что мы объявили под типом Family, то есть Family[+T]: (Ковариантность)
  экземпляр класса выше (GrandParent), не может использоваться классом ниже Family[Parent]

    val someFamily: Family[Parent] = new Family[GrandParent]

 */



// Для работы с супертипами и запрета использования подтипов нужна контравариантность:



  class Family1[-T]

  val covariantFamily1: Family1[Parent] = new Family1[GrandParent]

/*
  Ошибочный код, так как Family1[-T] (Контравариантность), то есть
  Экземпляр класса ниже (Child) не может использоваться экземпляром класса выше (Parent)

  val someFamily: Family1[Parent] = new Family1[Child]

 */
}