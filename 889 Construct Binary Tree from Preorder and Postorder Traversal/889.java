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
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        int n = pre.length;
        for (int i = 0; i < n; ++i) {
            indexMap.put(post[i], i);
        }
        
        return helper(pre, 0, n - 1, post, 0, n - 1, indexMap);
    }
    
    private TreeNode helper(int[] pre, int preLeft, int preRight, 
                            int[] post, int postLeft, int postRight,
                           Map<Integer, Integer> indexMap) {
        if (preLeft > preRight) {
            return null;
        }
        
        TreeNode root = new TreeNode(pre[preLeft]);
        int leftSize = preLeft == preRight
                        ? 0 
                        : indexMap.get(pre[preLeft + 1]) - postLeft + 1;
        root.left = helper(pre, preLeft + 1, preLeft + leftSize,
                          post, postLeft, postLeft + leftSize - 1, indexMap);
        root.right = helper(pre, preLeft + leftSize + 1, preRight,
                            post, postLeft + leftSize, postRight, indexMap);
        return root;
    }

    // Time complexity: O(n)
    // Space complexity: O(height)
    // Solution 2: O(n)
    private int preIndex = 0;
    private int postIndex = 0;
    public TreeNode constructFromPrePost2(int[] pre, int[] post) {
        int val = pre[preIndex++];
        TreeNode root = new TreeNode(val);
        if (val != post[postIndex]) {
            root.left = constructFromPrePost(pre, post);
        }
        if (val != post[postIndex]) {
            root.right = constructFromPrePost(pre, post);
        }
        postIndex++;
        return root;
    }
}