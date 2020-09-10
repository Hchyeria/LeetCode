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

class Solution {

    // Solution 1: BFS
    // Time = O(n)
    // Space = O(n)
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        Node pre = null;
        while (!q.isEmpty()) {
            int size = q.size();
            pre = null;
            while (size -- > 0) {
                Node node = q.poll();
                node.next = pre;
                if (node.right != null) {
                    q.offer(node.right);
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                pre = node;
            }
        }
        return root;
    }

    // Follow up: only use constant extra space, recursive approach is fine
    // you may assume implicit stack space does not count as extra space for this problem
    
    // Solution 2: recursion
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        helper(root.left, root.right);
        return root;
    }
    
    private void helper(Node left, Node right) {
        if(left == null || right == null) {
            return;
        }
        left.next = right;
        helper(left.left, left.right);
        helper(left.right, right.left);
        helper(right.left, right.right);
    }

    // Solution: iterative without Queue
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        while (cur != null && cur.left != null) {
            Node next = cur.left;
            while (cur != null) {
                cur.left.next = cur.right;
                cur.right.next = cur.next == null ? null : cur.next.left;
                cur = cur.next;
            }
            cur = next;
        }
        
        return root;
    }
    // Time = O(n)
    // Space = O(1)
}