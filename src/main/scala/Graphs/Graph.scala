package main.Graphs

trait Graph[N] {

  /**
   * Function returns all nodes in the graph.
   *
   * @return    list of nodes
   */
  def nodes: List[N]

  /**
   * Function returns all edges in the graph.
   *
   * @return    list of tuples of nodes
   */
  def edges: List[(N, N)]

  /**
   * Function add an edge between start node and
   * end node and returns a new graph which includes
   * this edge.
   *
   * main.Graph is immutable.
   *
   * @param from   node
   * @param to     node
   * @return        new graph
   */
  def addEdge(from: N, to: N): Graph[N]

  /**
   * Function returns all neighbours of an input node.
   *
   * @param node
   * @return      list of nodes
   */
  def neighbours(node: N): List[N]

}

object Graph {

  def apply[N](adjList: Map[N, List[N]]): Graph[N] = new DirectedGraph(adjList)

  def apply[N](): Graph[N] = new DirectedGraph(Map[N, List[N]]())

}
