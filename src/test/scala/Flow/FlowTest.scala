package Flow

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FlowTest extends AnyFlatSpec with Matchers {

  val nodes = List("S" ,"A" ,"B" ,"C" ,"D", "T")
  val g = Vector(
    /*    S  A  B  C  D  T */
    /*S*/ 0, 4, 8, 0, 0, 0,
    /*A*/ 0, 0, 0, 6, 0, 0,
    /*B*/ 0, 3, 0, 6, 2, 0,
    /*C*/ 0, 0, 0, 0, 3, 11,
    /*D*/ 0, 0, 0, 0, 0, 2,
    /*T*/ 0, 0, 0, 0, 0, 0
  )

  val graph = AdjMatrix(nodes, g, 6)

  it should "find path from source node to target node" in {
    val pathActual = Flow.findPathBFS("S", "T", graph)
    val pathExpected = List(("S", "A"), ("A", "C"), ("C", "T"))

    pathExpected shouldBe pathActual
  }

  it should "find maximum flow" in {
    val flowActual = Flow.maxFlow(graph, "S", "T")

    flowActual shouldBe 12
  }

  it should "return 0 for maximum flow to non-existing node" in {
    val flowActual = Flow.maxFlow(graph, "S", "X")

    flowActual shouldBe 0
  }

}
