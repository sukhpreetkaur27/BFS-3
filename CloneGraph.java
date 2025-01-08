// Problem Type: BFS - 3
// LeetCode - 133

// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : 
/**
 * node == null case not handled
 * <p>
 * Intuition: Graph and traversal problem
 * Hence, apply BFS or DFS to traverse all the nodes and their adj. list.
 * <p>
 * NOTE: Maintain a mapping of the original node and the deep copy node.
 */

// Your code here along with comments explaining your approach

import java.util.*;

public class CloneGraph {

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private Map<Node, Node> nodes = new HashMap<>();

    // Time Complexity : O(V + E)
    // Space Complexity : O(V) + O(V)
    public Node cloneGraph_bfs(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> oldNewMap = new HashMap<>();
        Deque<Node> q = new ArrayDeque<>();
        q.offer(node);
        // add to Q and mark as visited
        oldNewMap.put(node, new Node(node.val, new ArrayList<>()));
        while (!q.isEmpty()) {
            Node curr = q.poll();
            Node clone = oldNewMap.get(curr);

            for (Node neigh : curr.neighbors) {
                Node neighClone = oldNewMap.getOrDefault(neigh, new Node(neigh.val, new ArrayList<>()));
                if (!oldNewMap.containsKey(neigh)) {
                    // add to Q and mark as visited
                    oldNewMap.put(neigh, neighClone);
                    q.offer(neigh);
                }
                clone.neighbors.add(neighClone);
            }
        }
        return oldNewMap.get(node);
    }

    private Map<Integer, Node> nodes_dfs = new HashMap<>();

    // Time Complexity : O(V + E)
    // Space Complexity : O(V) + O(V)
    public Node cloneGraph_dfs(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> oldNewMap = new HashMap<>();
        dfs(node, oldNewMap);
        return oldNewMap.get(node);
    }

    private void dfs(Node node, Map<Node, Node> oldNewMap) {
        oldNewMap.put(node, new Node(node.val, new ArrayList<>()));
        Node clone = oldNewMap.get(node);

        for (Node neigh : node.neighbors) {
            // if unvisited
            if (!oldNewMap.containsKey(neigh)) {
                dfs(neigh, oldNewMap);
            }
            Node neighClone = oldNewMap.get(neigh);
            clone.neighbors.add(neighClone);
        }
    }

}