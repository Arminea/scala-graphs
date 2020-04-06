package Traversal

import main.Graphs.Graph

import scala.collection.immutable.Queue

object BFS {

  def iterativeBFS[N](start: N, graph: Graph[N], f: N => Unit): Set[N] = {

    if(!graph.nodes.contains(start))
      Set()
    else {
      // LazyList - an immutable linked list that evaluates elements in order and only when needed
      LazyList.iterate((Queue(start), Set[N](start))) {
        case(queue, visited) => {
          // get the first element of the queue
          val (node, rest) = queue.dequeue
          // new queue will contain non visited neighbours of `node` and the rest of the queue
          val newQueue = rest.enqueueAll(graph.neighbours(node).filterNot(visited.contains))
          // add all neighbours of `node` to `the visited` set
          val newVisited = graph.neighbours(node).toSet ++ visited

          (newQueue, newVisited)
        }
      }.takeWhile(tuple => tuple._1.nonEmpty).foldLeft(Set[N]())( (acc, curr) => {
        val head = curr._1.head
        f(head)
        acc ++ Set(head)
      })
    }

  }

}
