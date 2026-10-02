# 1372. Longest ZigZag Path in a Binary Tree

- LeetCode: https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
- Difficulty: Medium
- Topics: Dynamic Programming, Tree, Depth-First Search, Binary Tree, DP on Trees

## Status
- [ ] Solution

## Prerequisites

### 1. Understand state as what is expected next
After moving left, the next move must be right, and vice versa.

### 2. Carry direction and length
A recursive call can carry both pieces of state.

```java
void dfs(TreeNode node, int lastDirection, int length) {
    if (node == null) return;
    // change direction for the child
}
```

### 3. Reset when the pattern is broken
If the same direction is taken twice, the ZigZag streak is broken; start a new streak from the new edge.

**Tree pattern to learn:** DFS + direction state + reset/extend path length + global maximum.
