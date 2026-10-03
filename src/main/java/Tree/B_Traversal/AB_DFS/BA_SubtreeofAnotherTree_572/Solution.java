package Tree.B_Traversal.AB_DFS.BA_SubtreeofAnotherTree_572;

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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root, subRoot);
    }

    public boolean dfs(TreeNode p, TreeNode q) {
        if(p == null) {
            return false;
        }

        if(isSame(p , q)) {
            return true;
        }

        return dfs(p.left, q) || dfs(p.right, q);
    }

    public boolean isSame(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }

        if(p == null || q == null || p.val != q.val) {
            return false;
        }


        return isSame(p.left, q.left) && isSame(p.right, q.right);
    }
}

