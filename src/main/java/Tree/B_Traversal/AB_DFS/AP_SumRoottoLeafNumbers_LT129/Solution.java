package Tree.B_Traversal.AB_DFS.AP_SumRoottoLeafNumbers_129;

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
    public int sumNumbers(TreeNode root) {

        dfs(root, "");

        int sum = 0;
        for(String s : res) {
            sum = sum + Integer.parseInt(s);
        }
        return sum;

    }

    public void dfs(TreeNode node, String num){
        if(node == null) {
            return;
        }

        num = num + Integer.toString(node.val);

        if(node.left == null && node.right == null){
            res.add(num);
        }

        dfs(node.left, num);
        dfs(node.right, num);
    }
}
