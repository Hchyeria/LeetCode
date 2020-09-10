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
    public TreeNode recoverFromPreorder(String S) {
        int level, nodeVal;
        Deque<TreeNode> stack = new LinkedList<>();
        // do not have i++
        for (int i = 0; i < S.length(); ) {
            for (level = 0; i < S.length() && S.charAt(i) == '-'; ++i) {
                level++;
            }

            for (nodeVal = 0; i < S.length() && S.charAt(i) != '-'; ++i) {
                nodeVal = nodeVal * 10 + (S.charAt(i) - '0');
            }
            while (level < stack.size()) {
                stack.pollFirst();
            }
            TreeNode newNode = new TreeNode(nodeVal);
            if (!stack.isEmpty()) {
                TreeNode tempNode = stack.peekFirst();
                if (tempNode.left == null) {
                    tempNode.left = newNode;
                } else if (tempNode.right == null) {
                    tempNode.right = newNode;
                }
            }
            stack.offerFirst(newNode);
        }
        while (stack.size() > 1) {
            stack.pollFirst();
        }
        return stack.pollFirst();
        
        
    }
    // Time Complexity: O(S)
    // Space Complexity: O(N)
}

