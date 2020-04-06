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

    if(visited.contains(start))
      visited
    else {
      f(start)
      graph.neighbours(start).foldLeft(visited + start)((allVisited, neighbour) =>
        recursiveDFS(neighbour, graph, f, allVisited)
      )
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

    val visitedNodes = Set[N](start)
    // LazyList - an immutable linked list that evaluates elements in order and only when needed
    LazyList.iterate((List(start), visitedNodes)) {
      case(stack, visited) => {
        // get head of the stack
        val node = stack.head
        // new stack will contain non visited neighbours of `node` and the rest of the stack
        val newStack = graph.neighbours(node).filterNot(visited.contains) ++ stack.tail
        // add all neighbours of `node` to `the visited` set
        val newVisited = graph.neighbours(node).toSet ++ visited

        (newStack, newVisited)
      }
    }.takeWhile(tuple => tuple._1.nonEmpty).foldLeft(Set[N]())( (acc, curr) => {
      val head = curr._1.head
      f(head)
      acc ++ Set(head)
    })

  }

}
