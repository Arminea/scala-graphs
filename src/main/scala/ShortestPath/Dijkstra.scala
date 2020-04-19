package ShortestPath

import main.Graphs.{WeightedEdge, WeightedGraph}
import scala.util.Try

object Dijkstra {

  case class ShortStep[N](parents: Map[N,N],
                       unprocessed: Set[N],
                       distances: Map[N, Int]) {

    /**
     * Function extracts minimal value from unprocessed nodes.
     *
     * @return
     */
    def extractMin(): Option[(N, Int)] = Try(unprocessed.minBy(n => distances(n))).toOption.map(n => (n, distances(n)))
  }

  /**
   * Function finds the shortest paths from the source node to every other nodes in the graph.
   *
   * @param source    source node
   * @param graph     graph
   * @tparam N
   * @return
   */
  def findShortestPaths[N](source: N, graph: WeightedGraph[N]): ShortStep[N] = {

    // set distances to all nodes to infinity, distance to source node is zero
    val sDistances: Map[N, Int] = graph.nodes.map(_ -> Int.MaxValue).toMap + (source -> 0)

    def shortestPath(step: ShortStep[N]): ShortStep[N] = {
      // get nodes with minimal value
      step.extractMin().map {
        case (node, currentDistance) =>
          val newDist = graph.neighboursWithWeights(node).collect {
            case WeightedEdge(neighbour, neighbourDistance) if step.distances.get(neighbour).exists(_ > currentDistance + neighbourDistance) =>
              neighbour -> (currentDistance + neighbourDistance)
          }

          val newParents = newDist.map { case (neighbour, _) => neighbour -> node }

          shortestPath(ShortStep(
            step.parents ++ newParents,
            step.unprocessed - node, // current node is processed
            step.distances ++ newDist))
      }.getOrElse(step)
    }

    shortestPath(ShortStep(Map(), graph.nodes.toSet, sDistances))
  }

  /**
   * Function return recursively parents of given node.
   *
   * @param node            node
   * @param parents         parents
   * @tparam N
   * @return                path
   */
  private def findPathRec[N](node:N, parents: Map[N, N]): List[N] =
    parents.get(node).map(parent => node +: findPathRec(parent, parents)).getOrElse(List(node))

  /**
   * Function return the way from source node to given destination node.
   *
   * @param destination     destination node
   * @param parents         parent nodes
   * @tparam N
   * @return                path from source to destination
   */
  def findPath[N](destination: N, parents: Map[N, N]): List[N] = {
    findPathRec(destination, parents).reverse
  }

}
