package Tree.B_Traversal.AB_DFS.AZ_BinaryTreeMaximumPathSum_124;

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
    int maxsum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxsum;
    }

    public int dfs(TreeNode node) {
        if(node == null ){
            return 0;
        }


        int leftval = Math.max(0, dfs(node.left));
        int rightval = Math.max(0, dfs(node.right));



        maxsum = Math.max(leftval + node.val + rightval, maxsum);


        return Math.max(leftval, rightval) + node.val;
    }
}

