package Tree.B_Traversal.AA_BFS.AT_AddOneRowToTree_623;

import Tree.TreeNode;

import java.util.LinkedList;
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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if( root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 1;
        if (depth == 1){
            return new TreeNode(val, root, null);
        }

        while(!queue.isEmpty()){
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++){

                TreeNode node = queue.poll();

                if( level == depth - 1){
                    if(node.left != null){
                        TreeNode swap = node.left;
                        node.left = new TreeNode(val,swap, null);
                    } else {
                        node.left = new TreeNode(val,null,null);
                    }
                    if(node.right != null){
                        TreeNode swap = node.right;
                        node.right = new TreeNode(val,null, swap);
                    } else {
                        node.right =  new TreeNode(val,null,null);
                    }
                }

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }


            }
            level = level + 1;
        }
        return root;
    }
}
