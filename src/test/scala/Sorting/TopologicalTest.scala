package Sorting

import main.Graphs.Graph
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TopologicalTest extends AnyFlatSpec with Matchers {

  val graph = Graph[String]()
    .addEdge("Logging", "Game")
    .addEdge("Logging", "Networking")
    .addEdge("Networking", "Game")
    .addEdge("Commons", "Physics")
    .addEdge("Commons", "Math")
    .addEdge("Math", "Physics")
    .addEdge("Math", "Graphics")
    .addEdge("Math", "AI Engine")
    .addEdge("Physics", "Game")
    .addEdge("Graphics", "Game")
    .addEdge("AI Engine", "Game")

  "Kahn's algorithm" should "topologically sort set of tasks for creating a game" in {

    val sortedActual = Topological.sortKahn(graph)
    val sortedExpected = List("Commons", "Math", "Physics", "Graphics", "AI Engine", "Logging", "Networking", "Game")

    sortedActual shouldBe sortedExpected

  }

  "Kahn's algorithm" should "throw exception if the graph contains cycles" in {

    val noStartingNode = Graph[String]()
      .addEdge("Egg", "Hen")
      .addEdge("Hen", "Egg")

    intercept[Exception] {
      Topological.sortKahn(noStartingNode)
    }

  }

}
