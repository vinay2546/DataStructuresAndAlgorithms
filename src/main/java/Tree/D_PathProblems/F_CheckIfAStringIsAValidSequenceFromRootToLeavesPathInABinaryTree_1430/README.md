# 1430. Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree

- LeetCode: https://leetcode.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree/
- Difficulty: Medium
- Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree

## Status
- [ ] Solution

## Prerequisites

### 1. Compare a sequence using one index
The index represents how much of the target sequence has already been matched.

```java
int[] target = {0, 1, 0, 1};
for (int i = 0; i < target.length; i++) {
    System.out.println(target[i]);
}
```

### 2. Guard array bounds
Before reading `target[index]`, ensure the index is valid.

```java
if (index >= target.length) return false;
```

### 3. Recognize a leaf condition
For a root-to-leaf sequence, the final matched node must also be a leaf.

```java
boolean isLeaf = node.left == null && node.right == null;
```

**Tree pattern to learn:** DFS + sequence index + value matching + exact leaf termination.
