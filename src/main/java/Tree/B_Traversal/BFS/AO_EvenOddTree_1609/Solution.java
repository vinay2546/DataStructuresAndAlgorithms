package Tree.B_Traversal.BFS.AO_EvenOddTree_1609;

import Tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isEvenOddTree(TreeNode root) {
        boolean even = false;
        TreeNode[] nodes = new TreeNode[] { root };
        int n = 1;

        while (n != 0) {
            TreeNode[] newNodes = new TreeNode[2 * n];
            int newN = 0;
            int prevVal = even ? 10000000 : 0;
            for (int i = 0; i < n; i++) {
                if (even) {
                    if (nodes[i].val % 2 != 0 || nodes[i].val >= prevVal) {
                        return false;
                    }

                } else {
                    if (nodes[i].val % 2 != 1 || nodes[i].val <= prevVal) {
                        return false;
                    }
                }
                prevVal = nodes[i].val;

                if (nodes[i].left != null) {
                    newNodes[newN++] = nodes[i].left;
                }

                if (nodes[i].right != null) {
                    newNodes[newN++] = nodes[i].right;
                }
            }

            n = newN;
            nodes = newNodes;
            even = !even;
        }

        return true;
    }
}
