package main.Graphs

case class WeightedEdge[N](destination: N, weight: Int)

class WeightedGraph[N](adjList: Map[N, List[WeightedEdge[N]]]) extends Graph[N] {

  override def nodes: List[N] = adjList.keys.toList

  override def edges: List[(N, N)] = adjList.flatMap {
    case (node, edges) => edges.map(edge => (node, edge.destination))
  }.toList

  def addEdge(from: N, weightedEdge: WeightedEdge[N]): WeightedGraph[N] = {
    val fromNeighbours = weightedEdge +: adjList.getOrElse(from, Nil)
    new WeightedGraph(adjList + (from -> fromNeighbours))
  }

  override def addEdge(from: N, to: N): WeightedGraph[N] = addEdge(from, WeightedEdge(to, 0))

  override def neighbours(node: N): List[N] = adjList.getOrElse(node, Nil).map(_.destination)

  def neighboursWithWeights(node: N): List[WeightedEdge[N]] = adjList.getOrElse(node, Nil).toList

}
