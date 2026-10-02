# 3327. Check if DFS Strings Are Palindromes

- LeetCode: https://leetcode.com/problems/check-if-dfs-strings-are-palindromes/
- Difficulty: Hard
- Topics: Array, Hash Table, String, Tree, Depth-First Search, Hash Function

## Status
- [ ] Solution

## Prerequisites

### 1. Palindrome check in one pass with two pointers
You only need to compare mirrored positions.

```java
boolean isPalindrome(char[] a) {
    int left = 0, right = a.length - 1;
    while (left < right) {
        if (a[left] != a[right]) return false;
        left++;
        right--;
    }
    return true;
}
```

### 2. Build a sequence during DFS
Understand append, recurse, then undo when backtracking.

```java
path.add(node.val);
dfs(node.left, path);
dfs(node.right, path);
path.remove(path.size() - 1);
```

### 3. Understand DFS order as a generated string
The order in which nodes are visited can define a string. Generate the sequence correctly before optimizing how it is represented.

### 4. Understand why repeated string construction can be expensive
Repeatedly constructing large strings for many subtrees can cause unnecessary work, motivating hashing or compact signatures.

**Tree pattern to learn:** DFS-order sequence generation + palindrome checking + subtree representation/hashing.
