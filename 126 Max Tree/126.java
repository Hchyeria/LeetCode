/*
    Given [2, 5, 6, 0, 3, 1], the max tree constructed by this array is:
         6
        / \
       5   3
      /   / \
     2   0   1
*/
public class Solution {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
    	if (A == null || A.lengt == 0) {
            return null;
        }

        Deque<TreenNode> stack = new LinkedList<>();
        
        for (int i : A) {
            TreeNode node = new TreeNode(i);
            // [2, 5, 1, 2, 3], think this case
            // need pop all smaller element, so use while
            while (!stack.isEmpty() && i > stack.peekFirst().val) {
                node.left = stack.pollFirst();
            }
            if (!stack.isEmpty()) {
                stack.peekFist().right = node; // also need to offer node after update
            }
            stack.offerFirst(node);
        }
        return stack.peekLast();
    }
    // Time = O(n)
    // Space = O(n)
}