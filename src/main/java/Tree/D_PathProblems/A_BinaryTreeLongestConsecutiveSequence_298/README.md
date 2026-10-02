# 298. Binary Tree Longest Consecutive Sequence

- LeetCode: https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
- Difficulty: Medium
- Topics: Tree, Depth-First Search, Binary Tree, DP on Trees

## Status
- [ ] Solution

## Prerequisites

### 1. Compare adjacent values in one pass
You should be able to iterate through a sequence and compare the current value with the previous value.

```java
int[] a = {3, 4, 5, 2};

for (int i = 1; i < a.length; i++) {
    if (a[i] == a[i - 1] + 1) {
        System.out.println("consecutive");
    }
}
```

### 2. Carry state through recursion
Know how to pass the current streak length into a recursive call.

```java
void dfs(TreeNode node, TreeNode parent, int length) {
    if (node == null) return;

    int nextLength = (parent != null && node.val == parent.val + 1)
            ? length + 1
            : 1;

    dfs(node.left, node, nextLength);
    dfs(node.right, node, nextLength);
}
```

### 3. Know when a path state resets
A child that does not continue the consecutive rule starts a new streak rather than extending the old one.

**Tree pattern to learn:** DFS + parent/current comparison + path-state propagation.
