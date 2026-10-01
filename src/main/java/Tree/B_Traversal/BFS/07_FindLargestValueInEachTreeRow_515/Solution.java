package Tree.B_Traversal.BFS.G_FindLargestValueInEachTreeRow_515;

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
    public List<Integer> largestValues(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Integer> res = new ArrayList<>();

        while(!queue.isEmpty()){
            int maxVal = Integer.MIN_VALUE;
            int size = queue.size();

            for( int i =0; i< size; i++){
                TreeNode node = queue.poll();


                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }

                if (node.val > maxVal){
                    maxVal = node.val;
                }

            }
            res.add(maxVal);

        }

        return res;

    }
}
