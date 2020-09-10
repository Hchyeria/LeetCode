class Solution {

    // Solution 1: recursion way
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return false;
        }
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) return false;
        int target = sum / k;
        boolean[] visited = new boolean[n];
        Arrays.sort(nums);
        if (nums[n - 1] > target) return false;
        return dfs(visited, nums, 0, target, k, n - 1);
    }
    
    private boolean dfs(boolean[] visited, int[] nums, int sum, int target, int k, int index) {
        if (k == 0) return true;
        if (target == sum && dfs(visited, nums, 0, target, k - 1, nums.length - 1)) {
            return true;
        }
        for (int i = index; i >= 0; --i) {
            if (!visited[i] && sum + nums[i] <= target) {
                visited[i] = true;
                if (dfs(visited, nums, sum + nums[i], target, k, index)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    // Time = O(k!)
    // Space = O(N)

    // Solution 2: DP + Bit Mask
    // dp[i]: i is the Bit Mask, which i-th byte represents whether visited the i-th element in nums
    // dp[i] is a length of 2^n array, represents every possible subset, whether it can be partitioned into k parts
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return false;
        }
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) return false;
        int target = sum / k;
        boolean[] visited = new boolean[n];
        Arrays.sort(nums);
        if (nums[n - 1] > target) return false;
        
        boolean[] dp = new boolean[1 << n];
        int[] total = new int[1 << n];
        dp[0] = true;
        for (int i = 0; i < (1 << n); ++i) {
            if (dp[i]) {
                for (int j = 0; j < n; ++j) {
                    int tmp = i | (1 << j);
                    if (tmp != i) {
                        if (nums[j] <= target - (total[i] % target)) {
                            dp[tmp] = true;
                            total[tmp] = total[i] + nums[j];
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        
        
        return dp[(1 << n) - 1];
    }
    // Time = O(n * 2^n)
    // Space = O(2^n)

}