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
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        postTraversal(root, res);
        return res;
    }
    
    private void postTraversal(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        for (Node child : root.children) {
            postTraversal(child, res);
        }
        res.add(root.val);
    }
    
    // Solution 2: iterative
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new LinkedList<>();
        Node pre = null;
        Node cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                while (cur != null) {
                    stack.offerFirst(cur);
                    if (cur.children != null && cur.children.size() != 0) {
                        cur = cur.children.get(0);
                    } else {
                        cur = null;
                        break;
                    }
                    
                }
            } else {
                Node node = stack.peekFirst();
                List<Node> children = node.children;
                int index = findChild(children, pre);
                if (children == null || children.size() == 0
                   || index == -1 || index == children.size() - 1) {
                    res.add(node.val);
                    stack.pollFirst();
                } else {
                    cur = children.get(index + 1);
                }
                pre = node;
            }
            
            
        }
        return res;
    }
    
    private int findChild(List<Node> children, Node target) {
        if (children == null || children.size() == 0 || target == null) {
            return -1;
        } else {
            int index = 0;
            for (Node node : children) {
                if (node == target) {
                    return index;
                }
                index++;
            }
            return -1;
        }
    }
}