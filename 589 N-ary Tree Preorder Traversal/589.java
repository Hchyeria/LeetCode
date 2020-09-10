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
class Solution {
    // Solution 1: recursion
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        preTraversal(res, root);
        return res;
    }
    
    private void preTraversal(List<Integer> res, Node root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (Node child : root.children) {
            preTraversal(res, child);
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(height)
    
    // Solution 2: iterative
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollFirst();
            if (node.children != null) {
                int size = node.children.size();
                for (int i = size - 1; i >= 0; --i) {
                    Node child = node.children.get(i);
                    stack.offerFirst(child);
                }
            }
            res.add(node.val);
            
        }
        return res;
    }
    
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    
}