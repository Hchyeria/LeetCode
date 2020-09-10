
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int zeros = 0;
        int res = 0;
        for (int left = 0, right = 0; right < nums.length; ++right) {
            if (nums[right] == 0) {
                zeros++;
            }
            while (zeros > 1) {
                zeros = nums[left++] == 0 ? zeros - 1 : zeros;
            }
            // length equal right - left + 1, remember plus 1
            // window size [left, right]
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
    // Time complexity: O(n)
	// Space complexity: O(1)

    /* 
        Follow up:
        What if the input numbers come in one by one as an infinite stream?
        In other words, you can't store all numbers coming from the stream as it's too large to hold in memory.
        Could you solve it efficiently? 
    */
    public int findMaxConsecutiveOnes2(int[] nums) {
        int res = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int left = 0, right = 0; right < nums.length; ++right) {
            if (nums[right] == 0) {
                queue.offerFirst(right);
            }
            if (queue.size() > 1) { // // can be generalized to k
                left = queue.pollLast() + 1;
            }
            res = Math.max(res, right - left + 1);
        } 
        return res;
    }

    // Time complexity: O(n)
	// Space complexity: O(n)

}

