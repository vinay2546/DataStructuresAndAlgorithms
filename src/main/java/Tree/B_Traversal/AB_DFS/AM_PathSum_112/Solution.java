package Tree.B_Traversal.AB_DFS.AM_PathSum_112;

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
    boolean isZero = false;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return isZero;
    }

    public void dfs(TreeNode node, int targetSum){
        if (node == null) return;

        targetSum = targetSum - node.val;

        if(targetSum == 0 && node.left == null && node.right == null) {
            isZero = true;
        }

        dfs(node.left, targetSum);
        dfs(node.right, targetSum);



    }
}
