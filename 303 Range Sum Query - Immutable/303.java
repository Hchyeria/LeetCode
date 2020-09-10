class NumArray {
    
    private long[] sum;
    public NumArray(int[] nums) {
        int n = nums.length;
        sum = new long[n];
        long total = 0;
        for (int i = 0; i < n; ++i) {
            total += nums[i];
            sum[i] = total;
        }
    }
    // Time = O(n)
    // Space = O(n)
    
    public int sumRange(int i, int j) {
        return (int) (i == 0 ? sum[j] : sum[j] - sum[i - 1]);
    }
    // Time = O(1)
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */