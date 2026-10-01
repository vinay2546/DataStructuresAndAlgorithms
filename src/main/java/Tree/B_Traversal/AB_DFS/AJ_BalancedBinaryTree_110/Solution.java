package Tree.B_Traversal.AB_DFS.AJ_BalancedBinaryTree_110;

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
    boolean isBalanced = true;
    public boolean isBalanced(TreeNode root) {

        dfs(root);

        return isBalanced;

    }

    public int dfs(TreeNode node) {
        if (node == null){
            return 0;
        }



        int leftHeight = dfs(node.left);
        int rightHeight = dfs(node.right);

        int depth = leftHeight - rightHeight;



        if( Math.abs(depth) > 1){
            isBalanced = false;
        }


        return Math.max(leftHeight,rightHeight) + 1;
    }
}
