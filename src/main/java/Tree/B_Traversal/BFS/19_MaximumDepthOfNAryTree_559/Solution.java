package Tree.B_Traversal.BFS.19_MaximumDepthOfNAryTree_559;

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
    public int maxDepth(Node root) {
        if (root == null){
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        int maxDepth = 0;

        while(!queue.isEmpty()){
            int queueSize = queue.size();

            List<Integer> values = new ArrayList<>();

            for (int i =0; i < queueSize; i++){
                Node node = queue.poll();

                queue.addAll(node.children);
                values.add(node.val);
            }
            maxDepth += 1;
        }

        return maxDepth;
    }
}
