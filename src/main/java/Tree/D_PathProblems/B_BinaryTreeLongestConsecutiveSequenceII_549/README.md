# 549. Binary Tree Longest Consecutive Sequence II

- LeetCode: https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/
- Difficulty: Medium
- Topics: Tree, Depth-First Search, Binary Tree, DP on Trees

## Status
- [ ] Solution

## Prerequisites

### 1. Track two directional states
Practice returning multiple values from a recursive subtree call.

```java
int[] dfs(TreeNode node) {
    if (node == null) return new int[]{0, 0};

    int[] left = dfs(node.left);
    int[] right = dfs(node.right);

    return new int[]{1, 1};
}
```

### 2. Combine left and right child information
Some tree problems cannot be solved by carrying only root-to-current state. You must combine information returned from both children.

```java
int bestThroughNode = leftIncreasing + 1 + rightDecreasing;
```

### 3. Separate returned state from the global answer
The value needed by the parent may be one directional chain, while the global answer may combine both sides.

**Tree pattern to learn:** postorder DFS + multi-state return value + global answer through a node.
