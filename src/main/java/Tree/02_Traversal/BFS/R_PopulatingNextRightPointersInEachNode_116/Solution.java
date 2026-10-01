package Tree.BFS.AM_PopulatingNextRightPointersInEachNode_116;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

import Tree.Node;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public Node connect(Node root) {
        if (root == null){
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            Node previous = null;
            // queue.offer(new Node(Integer.MIN_VALUE,null,null,null));
            for (int i = 0; i < size; i++) {

                Node node = queue.poll();

                if (previous != null) {
                    previous.next = node;
                }

                previous = node;

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }


            }
        }

        return root;
    }
}
