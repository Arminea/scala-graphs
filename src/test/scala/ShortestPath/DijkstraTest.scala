package ShortestPath

import ShortestPath.Dijkstra.ShortStep
import main.Graphs.{WeightedEdge, WeightedGraph}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DijkstraTest  extends AnyFlatSpec with Matchers {

  val cities = new WeightedGraph(Map("Mumbai" -> Nil))
    .addEdge("Mumbai", WeightedEdge("Goa", 60))
    .addEdge("Mumbai", WeightedEdge("Colombo", 160))
    .addEdge("Mumbai", WeightedEdge("Dubai", 170))
    .addEdge("Mumbai", WeightedEdge("Jammu", 50))
    .addEdge("Mumbai", WeightedEdge("Bangkok", 250))
    .addEdge("Mumbai", WeightedEdge("Dhaka", 360))
    .addEdge("Goa", WeightedEdge("Chennai", 20))
    .addEdge("Chennai", WeightedEdge("Colombo", 40))
    .addEdge("Colombo", WeightedEdge("Bangkok", 210))
    .addEdge("Dubai", WeightedEdge("Singapore", 320))
    .addEdge("Singapore", WeightedEdge("Bangkok", 210))
    .addEdge("Bangkok", WeightedEdge("Singapore", 260))
    .addEdge("Bangkok", WeightedEdge("Dhaka", 90))
    .addEdge("Dhaka", WeightedEdge("Bangkok", 140))
    .addEdge("Jammu", WeightedEdge("Dubai", 110))
    .addEdge("Jammu", WeightedEdge("Kathmandu", 190))
    .addEdge("Kathmandu", WeightedEdge("Jammu", 160))
    .addEdge("Kathmandu", WeightedEdge("Dhaka", 90))

  it should "return shortest paths" in {

    val resultActual = Dijkstra.findShortestPaths("Mumbai", cities)
    val resultExpected = ShortStep(
      Map("Goa" -> "Mumbai", "Bangkok" -> "Mumbai",
        "Kathmandu" -> "Jammu", "Dubai" -> "Jammu",
        "Singapore" -> "Dubai", "Jammu" -> "Mumbai",
        "Chennai" -> "Goa", "Colombo" -> "Chennai",
        "Dhaka" -> "Kathmandu"),
      Set(),
      Map("Goa" -> 60, "Bangkok" -> 250, "Kathmandu" -> 240,
        "Dubai" -> 160, "Mumbai" -> 0, "Singapore" -> 480,
        "Jammu" -> 50, "Chennai" -> 80, "Colombo" -> 120,
        "Dhaka" -> 330))

    resultActual shouldBe resultExpected
  }

  it should "find shortest path to Dhaka" in {

    val pathExpected = List("Mumbai", "Jammu", "Kathmandu", "Dhaka")

    val result = Dijkstra.findShortestPaths("Mumbai", cities)
    val pathActual = Dijkstra.findPath("Dhaka", result.parents)

    pathActual shouldBe pathExpected
  }

}