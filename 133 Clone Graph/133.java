/*
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
*/
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node newHead = new Node(node.val);
        if (node.neighbors == null || node.neighbors.size() == 0) {
            return newHead;
        }
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        Node newNode = map.get(node);
        if (newNode != null) {
            return newNode;
        }
        
        newNode = new Node(node.val);
        map.put(node, newNode);
        

        for (Node n : node.neighbors) {
            newNode.neighbors.add(dfs(n, map));
        }
        return newNode;
    }
    // Time = O(V + E)
    // Space = O(v)
}