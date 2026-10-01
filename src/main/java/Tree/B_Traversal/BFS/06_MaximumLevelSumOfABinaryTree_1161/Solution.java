package Tree.B_Traversal.BFS.06_MaximumLevelSumOfABinaryTree_1161;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    public int maxLevelSum(TreeNode root) {
        if(root == null) {
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Integer> levelSumList = new ArrayList<>();

        int maxSum = Integer.MIN_VALUE;
        int maxLevel = 1;
        int currentLevel = 1;

        while(!queue.isEmpty()){
            int queueSize = queue.size();
            int levelSum = 0;

            for( int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();

                levelSum += node.val;

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }
            }

            if (maxSum<levelSum){
                maxSum = levelSum;
                maxLevel = currentLevel;
            }
            currentLevel++;
        }
        return maxLevel;
    }
}