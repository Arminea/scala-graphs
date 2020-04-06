package Traversal

import main.Graphs.Graph
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DFSTest extends AnyFlatSpec with Matchers {

  val graph = Graph[String]()
    // first component
    .addEdge("A", "B")
    .addEdge("A", "C")
    .addEdge("B", "C")
    .addEdge("B", "D")
    .addEdge("C", "D")
    .addEdge("D", "B")
    .addEdge("D", "E")
    .addEdge("D", "F")
    .addEdge("E","B")
    .addEdge("E", "F")
    // second component
    .addEdge("G", "H")
    .addEdge("G", "I")
    .addEdge("H", "I")

  "Recursive traversal" should "return visited nodes" in {

    val visitedFromAActual = DFS.recursiveDFS("A", graph, print)
    val visitedFromAExpected = List("A", "B", "C", "D", "E", "F")

    print("\n-----------\n")

    val visitedFromGActual = DFS.recursiveDFS("G",graph, print)
    val visitedFromGExpected = List("G", "H", "I")

    visitedFromAActual.toList.sorted shouldBe visitedFromAExpected
    visitedFromGActual.toList.sorted shouldBe visitedFromGExpected
  }

  "Iterative traversal" should "return visited nodes" in {

    val visitedFromAActual = DFS.iterativeDFS("A", graph, print)
    val visitedFromAExpected = List("A", "B", "C", "D", "E", "F")

    print("\n-----------\n")

    val visitedFromGActual = DFS.iterativeDFS("G",graph, print)
    val visitedFromGExpected = List("G", "H", "I")

    visitedFromAActual.toList.sorted shouldBe visitedFromAExpected
    visitedFromGActual.toList.sorted shouldBe visitedFromGExpected
  }

}
