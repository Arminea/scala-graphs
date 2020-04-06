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

### Traversing graphs

#### Depth first search

Algorithm starts at the root node and explores as far as possible along each 
along each branch before backtracking. It can be implemented recursively or 
iteratively. DFS takes time `Θ(|N| + |E|)` where `N` is the number of nodes 
and `E` is the number of edges.

To avoid infinite run for cyclic graph we have to keep track of explored nodes.

Pseudocode of recursive approach: 

```
visited_nodes = {}

DFS(node):
    if visited_nodes not contain node:
        process node
        add node to visited_nodes
        foreach neighbour in node.neighbours
            DFS(neighbour)
```

Pseudocode of iterative approach:

```
DFS(node):
    visited_nodes = {}
    stack = {node}

    while stack not empty:
        head = stack.pop
        if visited_nodes not contain head:
            process node
            add node to visited_nodes
            add node.neighbours to stack
```

#### Breadth first search