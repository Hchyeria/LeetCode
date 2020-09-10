/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (s.val == t.val) {
            return checkSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean checkSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        } else if (s.val != t.val) {
            return false;
        } else {
            return checkSame(s.left, t.left) && checkSame(s.right, t.right);
        }
    }
    // Time = O(n)
    // Space = O(height)


    public boolean isSubtree2(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (checkSame2(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean checkSame2(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        } else if (s.val != t.val) {
            return false;
        } else {
            return checkSame2(s.left, t.left) && checkSame2(s.right, t.right);
        }
    }
}

// Follow up:
// https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/

class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        if (checkSame(A , B)) {
            return true;
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean checkSame(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val != B.val) {
            return false;
        }
        return checkSame(A.left, B.left) && checkSame(A.right, B.right);
    }
}