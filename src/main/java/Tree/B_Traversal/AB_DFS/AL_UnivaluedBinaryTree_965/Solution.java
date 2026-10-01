package Tree.B_Traversal.AB_DFS.AL_UnivaluedBinaryTree_965;

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
    boolean isUni = true;
    public boolean isUnivalTree(TreeNode root) {
        dfs(root, root.val);
        return isUni;
    }

    public void dfs(TreeNode node, int value){
        if(node == null) {
            return;
        }

        if (node.val != value){
            isUni = false;
            return ;
        }

        dfs(node.left, value);
        dfs(node.right, value);
    }
}
