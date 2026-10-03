package Tree.B_Traversal.AB_DFS.AW_MaximumDifferenceBetweenNodeandAncestor_1026;

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
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    public int dfs(TreeNode node, int min, int max) {
        if(node == null) {
            return max - min;
        }

        min = Math.min(min,node.val);
        max = Math.max(max, node.val);

        int left = dfs(node.left, min, max);
        int right = dfs(node.right, min, max);

        return Math.max(left, right);
    }
}