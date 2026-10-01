package Tree.B_Traversal.AB_DFS.AN_BinaryTreePaths_257;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

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
    List<String> res = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root, "");
        return res;
    }

    public void dfs(TreeNode node, String itrPath) {
        if (node == null) {
            return;
        }

        if(node.left == null && node.right == null){
            itrPath = itrPath + Integer.toString(node.val);
            res.add(itrPath);
        } else {
            itrPath = itrPath + Integer.toString(node.val) + "->";
        }

        dfs(node.left, itrPath);
        dfs(node.right, itrPath);
    }
}
