package Tree.B_Traversal.BFS.03_NAryTreeLevelOrderTraversal_429;

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

import Tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null){
            return new ArrayList<>();
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> res = new ArrayList<>();

        while(!queue.isEmpty()){
            int queueSize = queue.size();
            List<Integer> values = new ArrayList<>();
            for (int i =0; i < queueSize; i++){
                Node node = queue.poll();

                queue.addAll(node.children);
                values.add(node.val);
            }
            res.add(values);
        }

        return res;
    }
}
