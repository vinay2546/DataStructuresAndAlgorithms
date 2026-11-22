package Tree.B_Traversal.AB_DFS.AI_InvertBinaryTree_226;

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
    public TreeNode invertTree(TreeNode root) {
        return dfs(root);
    }

    public TreeNode dfs(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode left = dfs(node.right);
        TreeNode right = dfs(node.left);

        node.left = left;
        node.right = right;
        return node;
    }
}
