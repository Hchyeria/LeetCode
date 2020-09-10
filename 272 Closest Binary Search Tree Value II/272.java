// LintCode 901. Closest Binary Search Tree Value II

class Solution {
    // in-order traversal
    // slide window
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root, pre, node;
        while (cur != null) {
            if (cur.left == null) {
                if (!add(res, cur.val, k, target)) {
                    break;
                }
                cur = cur.right;
            } else {
                node = cur.left;
                while (node.right != null) {
                    node = node.right;
                }
                node.right = cur;
                pre = cur;
                cur = cur.left;
                pre.left = null; // avoid circle
            }
        }

        return res;
    }

    private boolean add(LinkedList<Integer> res, int val, int k, double target) {
        if (res.size() < k) {
            res.offerLast(val);
            return true;
        } else if (Math.abs(res.peekFirst() - target) > Math.abs(val - target)) {
            res.pollFirst();
            res.offerLast(val);
            return true;
        }
        return false;
    }
    
    // Time Complexity: O(n)
    // Space Complexity: O(1)
}