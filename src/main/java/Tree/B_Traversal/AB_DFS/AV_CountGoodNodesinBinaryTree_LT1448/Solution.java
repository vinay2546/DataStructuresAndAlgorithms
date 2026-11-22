package Tree.B_Traversal.AB_DFS.AV_CountGoodNodesinBinaryTree_1448;

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
    int total = 0;
    public int goodNodes(TreeNode root) {
        dfs(root, root.val);
        return total;
    }

    public void dfs(TreeNode node, int value) {
        if(node == null) {
            return;
        }


        if(node.val >= value) {
            value = node.val;
            total = total + 1;
        }

        dfs(node.left, value);
        dfs(node.right, value);
    }
}
