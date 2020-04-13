package main.Graphs

class UndirectedGraph[N](adjList: Map[N, List[N]]) extends DirectedGraph[N](adjList) {

  override def addNode(n: N): UndirectedGraph[N] = new UndirectedGraph(adjList + (n -> List()))

  override def addEdge(from: N, to: N): UndirectedGraph[N] = {
    val fromNeighbours = to +: neighbours(from)
    val toNeighbours = from +: neighbours(to)
    val g = new UndirectedGraph(adjList ++ Map(from -> fromNeighbours, to -> toNeighbours))

    if(g.nodes.contains(to)) g else g.addNode(to)
  }

}
