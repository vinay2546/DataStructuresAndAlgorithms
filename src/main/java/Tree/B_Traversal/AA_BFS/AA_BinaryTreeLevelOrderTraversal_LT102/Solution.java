package Tree.B_Traversal.AA_BFS.AA_BinaryTreeLevelOrderTraversal_102;

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
    public List<List<Integer>> levelOrder(TreeNode root) {

        if(root == null){
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> returnList = new ArrayList<>();

        while(!queue.isEmpty()){

            int totalItems = queue.size();
            List<Integer> nodeItems = new ArrayList<>();

            for (int i=0; i < totalItems; i++){

                TreeNode node = queue.poll();

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }

                nodeItems.add(node.val);
            }
            returnList.add(nodeItems);
        }

        return returnList;

    }
}
