package Flow

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MatrixTest extends AnyFlatSpec with Matchers {

  val g = Vector(
    /*    S  A  B  C  D  T */
    /*S*/ 0, 4, 8, 0, 0, 0,
    /*A*/ 0, 0, 0, 6, 0, 0,
    /*B*/ 0, 3, 0, 6, 2, 0,
    /*C*/ 0, 0, 0, 0, 3, 11,
    /*D*/ 0, 0, 0, 0, 0, 2,
    /*T*/ 0, 0, 0, 0, 0, 0
  )

  val matrix = Matrix(g, 6)

  it should "contain data" in {
    matrix.data shouldBe Vector(0, 4, 8, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 3, 0, 6, 2, 0, 0, 0, 0, 0, 3, 11, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0)
  }

  it should "return neighbours of B" in {

    val neighboursOfBExpected = Vector(0, 3, 0, 6, 2, 0)

    matrix.neighbours(2) shouldBe neighboursOfBExpected

  }

  it should "update the edge" in {

    val n = 2 // node B
    val m = 3 // node C

    val updatedMatrix = matrix.update(n, m, 10)

    matrix.edgeAt(n, m) shouldBe 6
    updatedMatrix.edgeAt(n, m) shouldBe 10

  }

}
