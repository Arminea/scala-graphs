package Traversal

import main.Graphs.Graph

object DFS {

  /**
   * Recursive DFS approach.
   *
   * @param start     start node
   * @param graph     graph
   * @param f         higher order function
   * @param visited   set of visited nodes
   * @tparam N
   * @return          updated set of visited nodes
   */
  def recursiveDFS[N](start: N, graph: Graph[N], f: N => Unit, visited: Set[N] = Set[N]()): Set[N] = {

    def recursiveDFS0(node: N, visited0: Set[N]): Set[N] = {
      if (visited0.contains(node))
        visited0
      else {
        f(node)
        graph.neighbours(node).foldLeft(visited0 + node)((allVisited, neighbour) =>
          recursiveDFS0(neighbour, allVisited)
        )
      }
    }

    if(!graph.nodes.contains(start))
      Set()
    else {
      recursiveDFS0(start, visited)
    }

  }

  /**
   * Iterative DFS approach.
   *
   * @param start   start node
   * @param graph   graph
   * @param f       higher order function
   * @tparam N
   * @return        set of visited nodes
   */
  def iterativeDFS[N](start: N, graph: Graph[N], f: N => Unit): Set[N] = {
    if(!graph.nodes.contains(start))
      Set()
    else {
      // LazyList - an immutable linked list that evaluates elements in order and only when needed
      LazyList.iterate((List(start), Set[N](start))) {
        case (stack, visited) => {
          // get head of the stack
          val node = stack.head
          // new stack will contain non visited neighbours of `node` and the rest of the stack
          val newStack = graph.neighbours(node).filterNot(visited.contains) ++ stack.tail
          // add all neighbours of `node` to `the visited` set
          val newVisited = graph.neighbours(node).toSet ++ visited

          (newStack, newVisited)
        }
      }.takeWhile(tuple => tuple._1.nonEmpty).foldLeft(Set[N]())((acc, curr) => {
        val head = curr._1.head
        f(head)
        acc ++ Set(head)
      })
    }
  }

}
