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

<!-- PROBLEM_TRACKER:START -->
## Progress Overview — Graph

**0 / 233 problem placements solved (0.0%)**

🟩 Solved: 0 · 🟨 In Progress: 0 · ⬜ Not Started: 233

This tracker is generated recursively. Update the **Status** field in an individual problem README.

### Subdirectories

| Directory | Total | Solved | In Progress | Not Started | Completion |
|---|---:|---:|---:|---:|---:|
| [A_Fundamentals](A_Fundamentals/README.md) | 14 | 0 | 0 | 14 | 0.0% |
| [B_BFS](B_BFS/README.md) | 19 | 0 | 0 | 19 | 0.0% |
| [C_DFS](C_DFS/README.md) | 13 | 0 | 0 | 13 | 0.0% |
| [D_CycleDetection](D_CycleDetection/README.md) | 6 | 0 | 0 | 6 | 0.0% |
| [E_TopologicalSort](E_TopologicalSort/README.md) | 18 | 0 | 0 | 18 | 0.0% |
| [F_Bipartite](F_Bipartite/README.md) | 4 | 0 | 0 | 4 | 0.0% |
| [G_UnionFind](G_UnionFind/README.md) | 25 | 0 | 0 | 25 | 0.0% |
| [H_ShortestPath](H_ShortestPath/README.md) | 24 | 0 | 0 | 24 | 0.0% |
| [I_MST](I_MST/README.md) | 7 | 0 | 0 | 7 | 0.0% |
| [J_DirectedGraphs](J_DirectedGraphs/README.md) | 9 | 0 | 0 | 9 | 0.0% |
| [K_GridGraphs](K_GridGraphs/README.md) | 13 | 0 | 0 | 13 | 0.0% |
| [L_AdvancedTraversal](L_AdvancedTraversal/README.md) | 3 | 0 | 0 | 3 | 0.0% |
| [M_PathProblems](M_PathProblems/README.md) | 13 | 0 | 0 | 13 | 0.0% |
| [N_WeightedGraphs](N_WeightedGraphs/README.md) | 7 | 0 | 0 | 7 | 0.0% |
| [O_GraphDesign](O_GraphDesign/README.md) | 1 | 0 | 0 | 1 | 0.0% |
| [P_AdvancedGraphPatterns](P_AdvancedGraphPatterns/README.md) | 57 | 0 | 0 | 57 | 0.0% |


<sub>Counts are problem-folder placements, not unique LeetCode IDs. Intentional duplicates are counted wherever they appear.</sub>
<!-- PROBLEM_TRACKER:END -->
