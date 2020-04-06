package Traversal

import main.Graphs.Graph
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BFSTest extends AnyFlatSpec with Matchers {

  val graph = Graph[String]()
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

  "Iterative traversal" should "return visited nodes" in {

    val visitedFromAActual = BFS.iterativeBFS("A", graph, print)
    val visitedFromAExpected = List("A", "B", "C", "D", "E", "F")

    visitedFromAActual.toList.sorted shouldBe visitedFromAExpected
  }

  it should "return empty set for non-existing node" in {
    val visitedNodes = BFS.iterativeBFS("V", graph, print)

    visitedNodes shouldBe empty
  }

}
