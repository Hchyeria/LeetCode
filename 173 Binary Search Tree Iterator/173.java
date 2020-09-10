/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {
    Deque<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new LinkedList<>();
        addNode(root);
    }
    
    private void addNode(TreeNode node) {
        while (node != null) {
            stack.offerFirst(node);
            node = node.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode cur = stack.pollFirst();
        addNode(cur.right);
        return cur.val; 
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

 // Solution 2: Mirror Traversal
 class BSTIterator {
    TreeNode cur;
    public BSTIterator(TreeNode root) {
        cur = root;
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode node = cur, pre = cur;
        while (node != null) {
            if (node.left == null) {
                pre = node;
                node = node.right;
                break;
            } else {
                TreeNode left = node.left;
                while (left.right != null && left.right != node) {
                    left = left.right;
                }
                if (left.right == null) {
                    left.right = node;
                    node = node.left;
                } else {
                    pre = node;
                    left.right = null;
                    node = node.right;
                    break;
                }
            }
        }
        cur = node;
        return pre.val; 
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return cur != null;
    }
}