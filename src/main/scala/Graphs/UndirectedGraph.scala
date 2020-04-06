package main.Graphs

class UndirectedGraph[N](adjList: Map[N, List[N]]) extends DirectedGraph[N](adjList) {

  override def addEdge(from: N, to: N): UndirectedGraph[N] = {
    val fromNeighbours = to +: neighbours(from)
    val toNeighbours = from +: neighbours(to)
    new UndirectedGraph(adjList ++ Map(from -> fromNeighbours, to -> toNeighbours))
  }

}
