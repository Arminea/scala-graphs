package Flow

import scala.collection.immutable.Queue

sealed trait MaxFlow {
  def buildPath[N](child: N, parents: Map[N, N]): List[(N, N)]

  def findPathBFS[N](source: N, target: N, graph: Matrix[N]): List[(N, N)]

  def maxFlow[N](residualGraph: Matrix[N], source: N, target: N, flow: Int): Int
}

object Flow extends MaxFlow {

  def buildPath[N](child: N, parents: Map[N, N]): List[(N, N)] = {
    parents.get(child).map(p => (p, child) +: buildPath(p, parents)).getOrElse(Nil)
  }

  def findPathBFS[N](source: N, target: N, graph: Matrix[N]): List[(N, N)] = {
    val sq = LazyList.iterate((Queue(source), Set(source), Map[N, N]())) {
      case (queue, visitedNodes, parents) =>
        val (node, tail) = queue.dequeue
        val neighbours: Set[N] = graph.neighbours(node).toSet -- visitedNodes
        val newQueue = tail ++ neighbours
        val newVisited = neighbours ++ visitedNodes
        val newParents = parents ++ neighbours.map(_ -> node)

        (newQueue, newVisited, newParents)
    }
    val parentsMap: Map[N, N] = sq.takeWhile(q => q._1.nonEmpty).last._3
    buildPath(target, parentsMap).reverse
  }

  def maxFlow[N](residualGraph: Matrix[N], source: N, target: N, flow: Int = 0): Int = {
    val path = findPathBFS(source, target, residualGraph)

    if (path.nonEmpty) {
      val minPathFlow = path.map {
        case (u, v) => residualGraph.edgeAt(u, v)
      }.min

      val newResidualGraph = path.foldLeft(residualGraph) {
        case (resGraph, (eA, eB)) =>
          val flowForward = resGraph.edgeAt(eA, eB)
          val flowBackward = resGraph.edgeAt(eB, eA)

          resGraph.update(eA, eB, flowForward - minPathFlow)
                  .update(eB, eA, flowBackward + minPathFlow)
      }
      val newFlow = flow + minPathFlow
      maxFlow(newResidualGraph, source, target, newFlow)
    }
    else {
      flow
    }
  }

}
