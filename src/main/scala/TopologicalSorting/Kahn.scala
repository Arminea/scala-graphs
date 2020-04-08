package TopologicalSorting

import Utils.Utils
import main.Graphs.Graph

import scala.annotation.tailrec

object Kahn {

  def sort[N](graph: Graph[N]): List[N] = {

    val inDegree: Map[N, Int] = buildInDegreeMap(graph)
    val startNodes = inDegree.filter(_._2 == 0)

    @tailrec
    def sort0(startNodes: Map[N, Int], result: List[N], inDegree: Map[N, Int]): List[N] = {

      if(startNodes.isEmpty) {
        result
      } else {
        val firstNode = startNodes.head._1
        val neighbours = graph.neighbours(firstNode)

        // decrease value of incoming edges for first node's neighbours
        val newInDegree = neighbours.foldLeft(inDegree)( (acc, curr) =>  Utils.adjust(acc, curr)(_ - 1))

        // neighbours which have now no incoming edge
        val zeroNeighbours = newInDegree.filter({case (k, v) => neighbours.contains(k) && v == 0})
        val newStartNodes = startNodes.removed(firstNode) ++ zeroNeighbours

        sort0(newStartNodes , result :+ firstNode, newInDegree)
      }
    }

    sort0(startNodes, List[N](), inDegree)

  }

  private def buildInDegreeMap[N](graph: Graph[N]): Map[N, Int] = {
    val zeroMap = graph.nodes.map(n => n -> 0).toMap
    val edgesCnt = graph.edges.groupBy(e => e._2)
      .map{
        case (node, edges) => node -> edges.size
      }

    zeroMap ++ edgesCnt
  }

}
