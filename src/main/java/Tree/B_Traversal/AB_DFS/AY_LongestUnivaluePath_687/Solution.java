package Tree.B_Traversal.AB_DFS.AY_LongestUnivaluePath_687;

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
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        dfs(root, root.val);
        return max;
    }

    public int dfs(TreeNode node, int value) {
        if(node == null){
            return 0;
        }

        int leftmax = dfs(node.left, node.val);
        int rightmax = dfs(node.right, node.val);


        max = Math.max(max, leftmax + rightmax);

        if(node.val == value) {
            return  Math.max(leftmax, rightmax) + 1;
        } else {
            return 0;
        }

    }
}
