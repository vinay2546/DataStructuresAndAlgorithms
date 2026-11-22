package Tree.B_Traversal.AB_DFS.AT_PseudoPalindromicPathsinaBinaryTree_1457;

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
    int count = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        dfs(root, 0);
        return count;
    }

    public void dfs(TreeNode node, int palindromic) {
        if( node == null ) {
            return;
        }
        //0010  0100
        palindromic = (palindromic ^ (1 << node.val));

        if(node.left == null && node.right == null) {
            if((palindromic & (palindromic - 1)) == 0 ){
                count += 1;
            }
        }

        dfs(node.left, palindromic);
        dfs(node.right, palindromic);
    }
}
