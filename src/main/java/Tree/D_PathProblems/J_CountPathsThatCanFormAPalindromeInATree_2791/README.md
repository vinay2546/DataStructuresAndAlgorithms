# 2791. Count Paths That Can Form a Palindrome in a Tree

- LeetCode: https://leetcode.com/problems/count-paths-that-can-form-a-palindrome-in-a-tree/
- Difficulty: Hard
- Topics: Hash Table, Bit Manipulation, Tree, Depth-First Search

## Status
- [ ] Solution

## Prerequisites

### 1. Palindrome check by iterating once from both ends
You should know how to test a palindrome with two pointers in one pass.

```java
boolean isPalindrome(int[] a) {
    int left = 0, right = a.length - 1;

    while (left < right) {
        if (a[left] != a[right]) return false;
        left++;
        right--;
    }
    return true;
}
```

### 2. Understand the at-most-one-odd-frequency rule
A sequence can be rearranged into a palindrome when no more than one value occurs an odd number of times.

### 3. Represent parity with bits
Toggle one bit whenever a value is seen.

```java
int mask = 0;
mask ^= 1 << value;
```

### 4. Compare parity states with XOR
For two path states, XOR reveals which values have different parity. A palindrome-permutable path needs zero or one differing bits.

**Tree pattern to learn:** DFS path parity state + bitmask + frequency-state comparison.
