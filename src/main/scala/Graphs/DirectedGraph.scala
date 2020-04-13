package main.Graphs

class DirectedGraph[N](adjList: Map[N, List[N]]) extends Graph[N] {

  override def nodes: List[N] = adjList.keys.toList

  override def edges: List[(N, N)] = adjList.map {
      case (node, neighbours) => {
        neighbours.map(neighbour => (node, neighbour))
      }
    }.flatten.toList

  override def addNode(n: N): Graph[N] = new DirectedGraph(adjList + (n -> List()))

  override def addEdge(from: N, to: N): Graph[N] = {
    val fromNeighbours = to +: neighbours(from)
    val g = new DirectedGraph(adjList + (from -> fromNeighbours))

    if(g.nodes.contains(to)) g else g.addNode(to)

  }

  override def neighbours(node: N): List[N] = {
    adjList.getOrElse(node, Nil)
  }
}
