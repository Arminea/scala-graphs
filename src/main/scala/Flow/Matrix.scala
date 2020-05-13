package Flow

/**
 * Represents the adjacency matrix.
 *
 * @param data    edges values
 * @param size    number of nodes
 */
case class Matrix[N](data: Vector[N], size: Int) {

  def neighbours(n: Int): Vector[N] = data.slice(n * size, n * size + size)

  def edgeAt(n: Int, m: Int): N = data(n * size + m)

  def update(n: Int, m: Int, w: N): Matrix[N] = Matrix(data.updated(n * size + m, w), size)

}
