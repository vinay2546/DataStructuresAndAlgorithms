package Tree.B_Traversal.AB_DFS.AF_MaximumDepthofBinaryTree_104;

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
    public int maxDepth(TreeNode root) {
        int maxHeight = dfs(root);
        return maxHeight;
    }

    public int dfs(TreeNode node){
        if (node == null ) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);


        return Math.max(left,right) + 1;
    }
}
