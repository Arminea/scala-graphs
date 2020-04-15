package CycleDetection

import main.Graphs.Graph

object CycleDetection {

  def containsCycleDFS[N](graph: Graph[N]): Boolean = {

    case class DfsCycleResult[N](visited: Set[N], isCyclic: Boolean = false)

    def containsCycleDFS0(node: N, visited: Set[N] = Set[N](), ancestors: Set[N] = Set[N]()): DfsCycleResult[N] = {
      if (ancestors.contains(node))
        DfsCycleResult(visited, isCyclic = true)
      else if (visited.contains(node))
        DfsCycleResult(visited)
      else {
        graph.neighbours(node).foldLeft(DfsCycleResult(visited + node)) {
          case (DfsCycleResult(v, true), _) => DfsCycleResult(v, true)
          case (acc, n) => containsCycleDFS0(n, acc.visited, ancestors + node)
        }
      }
    }

    // nodes that don't have an edge pointed towards them
    val startNodes = graph.nodes.filter(n => graph.edges.forall(e => e._2 != n))

    startNodes.isEmpty || startNodes.exists(n => containsCycleDFS0(n).isCyclic)
  }



}
