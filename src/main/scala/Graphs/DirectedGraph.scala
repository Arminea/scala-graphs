package main.Graphs

class DirectedGraph[N](adjList: Map[N, List[N]]) extends Graph[N] {

  override def nodes: List[N] = adjList.keys.toList

  override def edges: List[(N, N)] = adjList.map {
      case (node, neighbours) => {
        neighbours.map(neighbour => (node, neighbour))
      }
    }.flatten.toList

  override def addEdge(from: N, to: N): Graph[N] = {
    val fromNeighbours = to +: neighbours(from)
    new DirectedGraph(adjList + (from -> fromNeighbours))
  }

  override def neighbours(node: N): List[N] = {
    adjList.getOrElse(node, Nil)
  }
}
