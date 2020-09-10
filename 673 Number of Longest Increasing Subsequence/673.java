class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] len = new int[n];
        int[] count = new int[n];
        int res = 0;
        int maxLen = 0;
        for (int i = 0; i < n; ++i) {
            len[i] = count[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    if (len[i] == len[j] + 1) {
                        count[i] += count[j];    
                    } else if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    }
                }
                
            }
            if (len[i] == maxLen) {
                res += count[i];
            } else if (len[i] > maxLen) {
                maxLen = len[i];
                res = count[i];
            }
        }
        return res;
    }

    // Time = O(n ^ 2)
    // Space = O(n)
}