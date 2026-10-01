package Tree.B_Traversal.BFS.AI_KthLargestSumInABinaryTree_2583;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
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
    public long kthLargestLevelSum(TreeNode root, int k) {
        if (root == null){
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Long> levelSumList = new ArrayList<>();

        while(!queue.isEmpty()){
            int size = queue.size();
            long levelSum = 0;
            for(int i =0; i < size; i++){

                TreeNode node = queue.poll();

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }

                levelSum+= node.val;

            }

            levelSumList.add(levelSum);

        }

        Collections.sort(levelSumList);

        if(levelSumList.size() - k >= 0){
            return levelSumList.get(levelSumList.size() - k);
        } else {
            return -1;
        }
    }
}
