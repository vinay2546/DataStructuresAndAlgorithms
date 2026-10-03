package Tree.B_Traversal.AB_DFS.AX_DiameterofBinaryTree_543;

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
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    public int dfs(TreeNode node) {
        if(node == null ){
            return 0;
        }


        int leftHeight = dfs(node.left);
        int rightHeight = dfs(node.right);

        int diameter = leftHeight + rightHeight;

        max = Math.max(max, diameter);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
