# 666. Path Sum IV

- LeetCode: https://leetcode.com/problems/path-sum-iv/
- Difficulty: Medium
- Topics: Array, Hash Table, Tree, Depth-First Search, Binary Tree

## Status
- [ ] Solution

## Prerequisites

### 1. Decode a value into independent fields
Practice extracting digits from a number using division and modulo.

```java
int x = 325;
int value = x % 10;
int position = (x / 10) % 10;
int depth = x / 100;
```

### 2. Store state by a computed key
Know how to use a map when a tree is represented indirectly.

```java
Map<Integer, Integer> valueByPosition = new HashMap<>();
valueByPosition.put(12, 5);
```

### 3. Accumulate a path value during DFS
Know the standard path-state pattern.

```java
void dfs(TreeNode node, int sum) {
    if (node == null) return;

    int nextSum = sum + node.val;
    dfs(node.left, nextSum);
    dfs(node.right, nextSum);
}
```

**Tree pattern to learn:** interpret implicit tree positions + path-state accumulation + map lookup.
