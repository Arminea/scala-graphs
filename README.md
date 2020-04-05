# Graph algorithms in Scala

Associated Udemy course: [Implementing graph algorithms using Scala](https://www.udemy.com/course/implementing-graph-algorithms-using-scala)

### Graph representation

Graph can be represented by:
 1. Adjacency list
   - Fast access by indices
   - Easy to implement
   - Memory consumption (information about every existing or non-existing edge is stored)
   - Ideal for dense small graphs
 
 2. Adjacency matrix
   - Memory efficient
   - Scalable
   - Slower (for finding neighbour whole list of neighbours must be traversed)
   - Ideal for sparse large graphs

Example of directed graph represented by adjacency list:

| ![Graph](imgs/graph.jpg) | ![Graph representation](imgs/graph_representation.jpg) |
| --- | --- |

Represented as `Map[N, List[N]]`, where `N` is generic data type.

Graph API:

```
def nodes: List[N]
def edges: List[(N, N)]
def addEdge(from: N, to: N): Graph[N]
def neighbours(node: N): List[N]
```

