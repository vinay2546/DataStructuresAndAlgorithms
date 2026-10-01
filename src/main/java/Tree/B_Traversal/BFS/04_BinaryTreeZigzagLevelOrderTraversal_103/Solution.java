package Tree.B_Traversal.BFS.D_BinaryTreeZigzagLevelOrderTraversal_103;

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean direction = false;

        List<List<Integer>> levelValuesList = new ArrayList<>();

        while(!queue.isEmpty()){
            int queueSize = queue.size();

            List<Integer> levelValues = new ArrayList<>();

            for( int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();

                if(!direction){
                    levelValues.add(node.val);
                } else {
                    levelValues.add(0, node.val);
                }


                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            direction = !direction;
            levelValuesList.add(levelValues);

        }
        return levelValuesList;
    }
}
