# 103. Binary Tree Zigzag Level Order Traversal

## Problem

[LeetCode 103](https://leetcode.com/problems/)

## Status

**Implemented**

## Data Structure

**Tree**

## Pattern

**BFS — Breadth-First Search**

## Approach

BFS by level with alternating direction; reverse levels currently use `add(0, value)`.

## Algorithm

1. Traverse the tree using breadth-first traversal.
2. Apply the problem-specific condition or aggregation.
3. Return the required result.

## Complexity

- **Time:** O(n²) worst case
- **Space:** O(w)

## Submission

[View LeetCode submission](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/submissions/2154969972/)

## Solution

See [Solution.java](./Solution.java).

---

**Problem Order:** AD
