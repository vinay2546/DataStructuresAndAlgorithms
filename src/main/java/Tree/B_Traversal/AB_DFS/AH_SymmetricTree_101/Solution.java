package Tree.B_Traversal.AB_DFS.AH_SymmetricTree_101;

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
    boolean isSame = true;

    public boolean isSymmetric(TreeNode root) {
        dfs(root.left, root.right);
        return isSame;
    }

    public void dfs(TreeNode p, TreeNode q) {
        if (!isSame) return;
        if(p == null && q == null) {
            return ;
        }

        if(p == null || q == null) {
            isSame = false;
            return ;
        }

        if(p.val != q.val) {
            isSame = false;
            return;
        }

        dfs(p.left, q.right);
        dfs(p.right, q.left);

    }
}
