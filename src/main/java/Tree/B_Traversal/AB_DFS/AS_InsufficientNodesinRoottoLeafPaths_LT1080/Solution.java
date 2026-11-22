package Tree.B_Traversal.AB_DFS.AS_InsufficientNodesinRoottoLeafPaths_1080;

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
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return dfs(root, limit, 0);
    }

    public TreeNode dfs(TreeNode node, int limit, int sum) {
        if(node == null) {
            return null;
        }

        sum = sum + node.val;
        if(node.left == null && node.right == null) {
            if(sum < limit) {
                return null;
            } else {
                return node;
            }
        }

        node.left = dfs(node.left, limit, sum);
        node.right = dfs(node.right, limit, sum);


        if(node.left == null && node.right == null) {
            return null;
        }


        return node;
    }
}