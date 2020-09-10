
// Given a non-empty integer array of size n
// find the minimum number of moves required to make all array elements equal
// where a move is incrementing n - 1 elements by 1

class Solution {
    // increase n - 1 element by 1, which means decrease 1 element by 1
    public int minMoves(int[] nums) {
        int sum = 0;
        int minValue = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            minValue = Math.min(minValue, num);
        }
        return sum - minValue * nums.length;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}