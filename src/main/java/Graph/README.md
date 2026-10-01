# Graph

Graph problems organized into a learning-oriented progression from fundamentals to advanced patterns.

## Learning Progression

**Fundamentals → Traversal → Structure → Connectivity → Optimization → Specialized Models → Design/Advanced**

| Package | Focus | Problems |
|---|---|---:|
| 01_Fundamentals | Graph representation, vertices, edges, degree reasoning, direct connectivity, and basic graph modeling. | 14 |
| 02_BFS | Breadth-first search for unweighted shortest paths, level exploration, and implicit state-space graphs. | 19 |
| 03_DFS | Depth-first search for recursive exploration, components, reachability, and graph traversal. | 13 |
| 04_CycleDetection | Cycle detection in directed and undirected graphs, back edges, and structural cycle reasoning. | 6 |
| 05_TopologicalSort | DAG ordering, prerequisites, dependency resolution, Kahn's algorithm, and DFS ordering. | 18 |
| 06_Bipartite | Two-coloring, bipartite validation, and graph partitioning. | 4 |
| 07_UnionFind | Disjoint Set Union for connectivity, component merging, equivalence classes, and redundant edges. | 25 |
| 08_ShortestPath | Shortest-path algorithms including Dijkstra-style methods, 0-1 BFS, constrained routes, and dynamic shortest paths. | 25 |
| 09_MST | Minimum spanning trees using Kruskal/Prim and advanced spanning-tree constraints. | 7 |
| 10_DirectedGraphs | Directed reachability, functional graphs, indegree/outdegree reasoning, and directed state transitions. | 9 |
| 11_GridGraphs | Grids as implicit graphs: islands, flood fill, multi-source BFS, grid shortest paths, and obstacles. | 13 |
| 12_AdvancedTraversal | Advanced BFS/DFS state modeling, implicit graphs, and traversal with additional state dimensions. | 3 |
| 13_PathProblems | Path construction, path counting, path scoring, path reconstruction, and constrained routes. | 13 |
| 14_WeightedGraphs | Weighted-edge modeling, graph costs, probability/ratio edges, and specialized weighted transformations. | 7 |
| 15_GraphDesign | Graph data-structure and graph API design, including shortest-path query abstractions. | 0 |
| 16_AdvancedGraphPatterns | Graph combined with DP, bitmasking, greedy methods, string/state transformations, and other advanced techniques. | 57 |

**Unique problem entries:** 233

## Rules

- Every problem has one **primary learning package**; physical duplicate folders are avoided.
- Cross-topic techniques belong in the problem README rather than duplicated folders.
- Existing `README.md` and `Solution.java` placeholders are moved without changing their implementations.
- This reorganization does not implement any solutions.

## Edge Cases

- Empty and single-vertex graphs
- Disconnected components
- Self-loops and parallel edges
- Directed vs. undirected graphs
- Cycles and DAGs
- Weighted vs. unweighted edges
- Multiple valid paths or components
- Grid boundaries and blocked cells
- Large state spaces and visited-state representation
