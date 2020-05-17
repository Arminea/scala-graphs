package Flow

import scala.collection.immutable

sealed trait Matrix[N] {
  def getIndex(n: N): Int

  def neighbours(n: N): Vector[N]

  def neighboursWeights(n: N): Vector[Int]

  def edgeAt(n: N, m: N): Int

  def update(n: N, m: N, w: Int): Matrix[N]
}

/**
 * Represents the adjacency matrix.
 *
 * @param nodes     nodes mapped to index
 * @param weights   edges weights
 * @param size      number of nodes
 */
case class AdjMatrix[N](nodes: List[N], weights: Vector[Int], size: Int) extends Matrix[N] {

  def getIndex(n: N): Int = nodes.indexOf(n)

  def neighbours(n: N): Vector[N] = {
    nodes.zip(neighboursWeights(n)).flatMap {
      case (neighbour, weight) if (weight > 0) => Some(neighbour)
      case _ => None
    }.toVector
  }

  def neighboursWeights(n: N): Vector[Int] = {
    val i = getIndex(n)
    weights.slice(i * size, i * size + size)
  }

  def edgeAt(n: N, m: N): Int = {
    val i = getIndex(n)
    val j = getIndex(m)
    weights(i * size + j)
  }

  def update(n: N, m: N, w: Int): Matrix[N] = {
    val i = getIndex(n)
    val j = getIndex(m)
    AdjMatrix(nodes, weights.updated(i * size + j, w), size)
  }

}
