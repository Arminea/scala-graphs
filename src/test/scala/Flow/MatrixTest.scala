package Flow

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MatrixTest extends AnyFlatSpec with Matchers {

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

  val matrix = AdjMatrix(nodes, g, 6)

  it should "contain data" in {
    matrix.weights shouldBe Vector(0, 4, 8, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 3, 0, 6, 2, 0, 0, 0, 0, 0, 3, 11, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0)
  }

  it should "return neighbours of B" in {
    val neighboursExpected = Vector("A", "C", "D")

    matrix.neighbours("B") shouldBe neighboursExpected
  }

  it should "return weights of neighbours of B" in {
    val neighboursWeightsExpected = Vector(0, 3, 0, 6, 2, 0)

    matrix.neighboursWeights("B") shouldBe neighboursWeightsExpected
  }

  it should "update the edge" in {
    val updatedMatrix = matrix.update("B", "C", 10)

    matrix.edgeAt("B", "C") shouldBe 6
    updatedMatrix.edgeAt("B", "C") shouldBe 10
  }

}
