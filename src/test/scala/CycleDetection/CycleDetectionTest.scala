package CycleDetection

import main.Graphs.Graph
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CycleDetectionTest extends AnyFlatSpec with Matchers {

  val bigCycle = Graph[String]
    .addEdge("A", "B")
    .addEdge("B", "C")
    .addEdge("C", "D")
    .addEdge("D", "A")

  val withCycle = Graph[String]
    .addEdge("A", "B")
    .addEdge("B", "C")
    .addEdge("C", "D")
    .addEdge("D", "B")

  val withoutCycle = Graph[String]
    .addEdge("A", "B")
    .addEdge("B", "C")
    .addEdge("B", "D")

  "DFS - Graph" should "contain cycle - empty start nodes" in {
    CycleDetection.containsCycleDFS(bigCycle) shouldBe true
  }

  "DFS - Graph" should "contain cycle" in {
    CycleDetection.containsCycleDFS(withCycle) shouldBe true
  }

  "DFS - Graph" should "not contain cycle" in {
    CycleDetection.containsCycleDFS(withoutCycle) shouldBe false
  }

}
