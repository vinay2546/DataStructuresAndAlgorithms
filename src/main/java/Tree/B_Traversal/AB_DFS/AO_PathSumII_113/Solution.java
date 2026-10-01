package Tree.B_Traversal.AB_DFS.AO_PathSumII_113;

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
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, new ArrayList<>(), 0, targetSum);
        return res;
    }

    public void dfs(TreeNode node, List<Integer> itrPath, int itrSum, int targetSum) {
        if(node == null){
            return;
        }

        itrSum = itrSum + node.val;

        itrPath.add(node.val);

        if(node.left == null && node.right == null && itrSum == targetSum) {
            res.add(itrPath);
        }


        if(node.left != null) {
            dfs(node.left, new ArrayList<>(itrPath), itrSum, targetSum);
        }

        if(node.right != null) {
            dfs(node.right, new ArrayList<>(itrPath), itrSum, targetSum);
        }
    }
}
