// LintCode 595. Binary Tree Longest Consecutive Sequence

class Solution {
    // Solution 1: recursive
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = new int[1];
        dfs(root, res, 1);
        return res[0];
    }

    private void dfs(TreeNode node, int[] res, int len) {
        res[0] = Math.max(res[0], len);
        if (node.left != null) {
            if (node.left.val == node.val + 1) {
                dfs(node.left, res, len + 1);
            } else {
                dfs(node.left, res, 1);
            }
        }
        if (node.right != null) {
            if (node.right.val == node.val + 1) {
                dfs(node.right, res, len + 1);
            } else {
                dfs(node.right, res, 1);
            }
        }
    }

    // Time = O(N)
    // Space = O(height)

    // Solution 2: iterative
    public int longestConsecutive2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 1));
        int res = 1;
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> cur = queue.poll();
            TreeNode curNode = cur.getKey();
            int curLen = cur.getValue();
            res = Math.max(res, curLen);
            if (curNode.left != null) {
                if (curNode.left.val == curNode.val + 1) {
                    queue.offer(new Pair<>(curNode.left, curLen + 1));
                } else {
                    queue.offer(new Pair<>(curNode.left, 1));
                }
            }
            if (curNode.right != null) {
                if (curNode.right.val == curNode.val + 1) {
                    queue.offer(new Pair<>(curNode.right, curLen + 1));
                } else {
                    queue.offer(new Pair<>(curNode.right, 1));
                }
            }
        }
        return res;
    }
}