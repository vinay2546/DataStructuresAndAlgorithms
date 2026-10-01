package Tree.B_Traversal.BFS.AL_CousinsInBinaryTree_993;

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
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null){
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){

            boolean xExists = false;
            boolean yExists = false;
            int levelSize = queue.size();

            for(int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if(node.val == x){
                    xExists = true;
                }

                if(node.val == y){
                    yExists = true;
                }

                if(node.left != null && node.right != null){

                    if((node.left.val == x && node.right.val == y) ||(node.left.val == y && node.right.val == x) ){
                        return false;
                    }
                }

                if (node.left != null){
                    queue.offer(node.left);
                }

                if (node.right != null){
                    queue.offer(node.right);
                }

            }

            if(xExists && yExists){
                return true;
            }

        }

        return false;


    }
}