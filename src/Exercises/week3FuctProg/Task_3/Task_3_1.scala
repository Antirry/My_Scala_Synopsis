package Exercises.week3FuctProg.Task_3


object Task_3_1 extends App {

  def add(network: Map[String, Set[String]], location: String): Map[String, Set[String]] = network + (location -> Set())

  def remove(network: Map[String, Set[String]], location: String): Map[String, Set[String]] = {
    def loop(destinations: Set[String], acc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (destinations.isEmpty) acc
      else loop(destinations.tail, disconnect(acc, location, destinations.head))
    }

    val disconnected = loop(network(location), network)
    disconnected - location
  }

  def disconnect(network: Map[String, Set[String]], pointA: String, pointB: String): Map[String, Set[String]] = {
    network + (pointA -> (network(pointA) - pointB)) + (pointB -> (network(pointB) - pointA))
  }

  def connect(network: Map[String, Set[String]], pointA: String, pointB: String): Map[String, Set[String]] = {
    network + (pointA -> (network(pointA) + pointB)) + (pointB -> (network(pointB) + pointA))
  }

  def nFlights(network: Map[String, Set[String]], location: String): Int = network.count(_._2.contains(location))

  def mostFlights(network: Map[String, Set[String]]): String = network.maxBy(_._2.size)._1
  def nLocationsWithNoFlights(network: Map[String, Set[String]]): Int = network.count(_._2.isEmpty)

  // Функция проверяет, соединены ли точки pointA и pointB в network
  def isConnected(network: Map[String, Set[String]], pointA: String, pointB: String): Boolean = {
    // Внутренняя функция, проходящая граф в ширину
    @annotation.tailrec
    def bfs(queue: List[String], visited: Set[String]): Boolean = {
      // Если не осталось ни одной не посещённой вершины, то точки не соединены
      if (queue.isEmpty) false
      else {
        val curr = queue.head // берем первую вершину из очереди
        val neighbors = network(curr).toList.filter(!visited.contains(_)) // выбираем её непосещенных соседей
        if (neighbors.contains(pointB)) true // если соседи содержат pointB, то точки связаны
        else bfs(queue.tail ++ neighbors, visited + curr) // добавляем найденных соседей в конец очереди, идем дальше
      }
    }

    bfs(List(pointA), Set.empty) // вызываем bfs со списком из pointA и пустым множеством посещенных вершин
  }
}


// _._2 == location <- Не правильно, правильный вариант: _._2.contains(location)