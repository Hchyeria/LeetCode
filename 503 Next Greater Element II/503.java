class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0]; 
        }
        
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < 2 * n; ++i) {
            int num = nums[i % n];
            while (!stack.isEmpty() && num > nums[stack.peekFirst()]) {
                res[stack.pollFirst()] = num;
            }
            if (i < n) {
                stack.offerFirst(i);
            }
            // the stack can not be empty, because the max num can't pop out
            // if (stack.isEmpty()) {
            //     break;
            // }
        }
        return res;
    } 

    // Time = O()
}
