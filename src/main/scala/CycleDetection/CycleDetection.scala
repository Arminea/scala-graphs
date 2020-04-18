package CycleDetection

import main.Graphs.Graph

object CycleDetection {

  /**
   * Function determines whether the graph contains cycle or not using DFS.
   *
   * @param graph   graph
   * @tparam N
   * @return        true/false
   */
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

  /**
   * Function determines whether the graph contains cycle or not using Floyd's aka. Tortoise and Hare algorithm.
   *
   * @param node      starting node
   * @param graph     graph
   * @tparam N
   * @return          true/false
   */
  def containsCycleFloyd[N](node: N, graph: Graph[N]): Boolean = {

    case class Race(tortoise: List[N], hare: List[N])

    //move one step
    def moveOnce(stack: List[N]): List[N] = stack.headOption.map(n => graph.neighbours(n) ++ stack.tail).getOrElse(Nil)

    // compare heads of tortoise and hare stacks
    def compareHeads(tortoiseHead: Option[N], hareHead: Option[N]): Boolean = (tortoiseHead, hareHead) match {
      case (Some(t), Some(h)) => t == h
      case _ => false
    }

    def containsCycleFloyd0(race: Race): Race = {
      if (race.hare.isEmpty || compareHeads(race.tortoise.headOption, race.hare.headOption))
        race
      else
        containsCycleFloyd0(Race(
          moveOnce(race.tortoise),
          moveOnce(moveOnce(race.hare))
        ))
    }

    val race = containsCycleFloyd0(Race(
      moveOnce(List(node)),
      moveOnce(moveOnce(List(node)))
    ))

    compareHeads(race.tortoise.headOption, race.hare.headOption)

  }

}
