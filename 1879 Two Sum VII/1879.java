// LintCode 1879 Two Sum VII

public class Solution {
    /**
     * @param nums: the input array
     * @param target: the target number
     * @return: return the target pair
     */
    public List<List<Integer>> twoSumVII(int[] nums, int target) {
        // write your code here
        
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return res;
        }
        
        int n = nums.length;
        int left = -(n - 1), right = n - 1;
        while (left < right) {
            int l = Math.abs(left);
            int r = Math.abs(right);
            if (l + Math.abs(nums[l]) > Math.abs(left + nums[l])) {
                left++;
            } else if (r + Math.abs(nums[r]) > Math.abs(right + nums[r])) {
                right--;
            } else {
                int sum = nums[l] + nums[r];
                if (sum == target) {
                    if (l < r) {
                        res.add(Arrays.asList(l, r));
                    } else {
                        res.add(Arrays.asList(r, l));
                    }
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    // Time = O(n)
    // Space = O(1)
}