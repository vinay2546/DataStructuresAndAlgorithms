# 1104. Path In Zigzag Labelled Binary Tree

- LeetCode: https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/
- Difficulty: Medium
- Topics: Math, Tree, Binary Tree

## Status
- [ ] Solution

## Prerequisites

### 1. Know perfect-binary-tree level boundaries
For level `d`, labels in normal order run from `2^d` through `2^(d+1)-1`.

```java
int start = 1 << d;
int end = (1 << (d + 1)) - 1;
```

### 2. Reflect a value inside an interval
A reversed level can be converted into normal order with:

```java
int reflected = start + end - value;
```

For labels `4..7`, reflecting `6` gives `5`.

### 3. Compute a parent from the label
In normal labeling, `parent(x) = x / 2`. With zigzag labeling, first reflect the value into normal coordinates, then move to the parent level.

**Tree pattern to learn:** level modeling + coordinate reflection + parent computation.
