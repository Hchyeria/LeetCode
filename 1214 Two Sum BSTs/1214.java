// Given two binary search trees
// return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target

import java.util.Deque;
import java.util.LinkedList;

public class TwoSumBSTs {

    // Solution 1: recursion
    // Time = O(2 ^ (h1 + h2))
    // Space = O(h1 + h2)
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) {
            return false;
        }
        int sum = root1.val + root2.val;
        if (sum == target) {
            return true;
        } else if (sum < target) {
            return twoSumBSTs(root1.right, root2, target) || twoSumBSTs(root1, root2.right, target);
        } else {
            return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1, root2.left, target);
        }
    }

    // Solution 2: iterative
    // Time = O(n1 + n2)
    // Space = O(h1 + h2)
    Deque<TreeNode> stack1 = new LinkedList<>();
    Deque<TreeNode> stack2 = new LinkedList<>();
    TreeNode s1;
    TreeNode s2;

    public boolean twoSumBSTs2(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) {
            return false;
        }
        s1 = root1;
        s2 = root2;

        TreeNode node1 = getNext();
        TreeNode node2 = getPrev();

        while (node1 != null && node2 != null) {
            int sum = node1.val + node2.val;
            if (sum == target) {
                return true;
            } else if (sum < target) {
                node1 = getNext();
            } else {
                node2 = getPrev();
            }
        }
        return false;
    }

    private boolean hasNext() {
        return !stack1.isEmpty() || s1 != null;
    }

    // in-order traversal
    private TreeNode getNext() {
        if (!hasNext()) return null;

        while (s1 != null) {
            stack1.offerFirst(s1);
            s1 = s1.left;
        }

        TreeNode ans = stack1.pollFirst();
        assert ans != null;
        s1 = ans.right;
        return ans;
    }

    private boolean hasPrev() {
        return !stack2.isEmpty() || s2 != null;
    }

    // reverse in-order traversal
    private TreeNode getPrev() {
        if (!hasPrev()) return null;

        while (s2 != null) {
            stack2.offerFirst(s2);
            s2 = s2.right;
        }

        TreeNode ans = stack2.pollFirst();
        assert ans != null;
        s2 = ans.left;
        return ans;
    }
}
