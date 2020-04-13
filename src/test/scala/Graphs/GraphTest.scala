package test.Graphs

import main.Graphs.Graph
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GraphTest extends AnyFlatSpec with Matchers {

  val graph = Graph[String]()
      .addEdge("London", "Lisbon")
      .addEdge("London", "Bucharest")
      .addEdge("Lisbon", "Madrid")
      .addEdge("Madrid", "London")
      .addEdge("Madrid", "Rome")
      .addEdge("Rome", "London")
      .addEdge("Paris", "Rome")

  it should "contain all cities" in {
    val citiesExpected = List("London", "Lisbon", "Madrid", "Rome", "Paris", "Bucharest")
    val citiesActual = graph.nodes
    citiesExpected should contain theSameElementsAs citiesActual

  }

  it should "contain all edges" in {
    val edgesExpected = List(("London","Lisbon"), ("London", "Bucharest"), ("Lisbon", "Madrid"), ("Madrid", "London"),
                            ("Madrid", "Rome"), ("Rome", "London"), ("Paris", "Rome"))
    val edgesActual = graph.edges

    edgesExpected should contain theSameElementsAs edgesActual
  }

  it should "contain new edge from Paris to London" in {
    val graphWithNewEdge = graph.addEdge("Paris", "London")

    graphWithNewEdge.neighbours("Paris") should contain ("London")
  }

  it should "return neighbours of Madrid" in {
    val neighboursExpected = List("London", "Rome")
    val neighboursActual = graph.neighbours("Madrid")

    neighboursExpected should contain theSameElementsAs neighboursActual
  }

}
