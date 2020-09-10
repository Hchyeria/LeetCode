class Solution {
    // Solutions:
    // 1. Triple for loops: Time complexity is O(n^3), space complexity is O(1).
    // 2. For loop + Two Sum (with hash map): Time complexity is O(n^2), space complexity is O(n).
    // 3. For loop + Two Sum (sort + two pointers): Time complexity is O(n^2), space complexity is O(log(n)),
    //    because of quick sort (for primitive types).
    //    if we need to deduplicate, the sort will helpful

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int target = 0;
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            twoSum(res, nums, target - nums[i], i, i + 1, len - 1);

        }
        return res;
    }

    public void twoSum(List<List<Integer>> res, int[] nums, int target,
                       int cur, int left, int right) {
        while (left < right) {
            // the cur should not be the nums[i], it must be the index of the array
            // then left - 1 == cur will work
            // if we change it into nums[left - 1] == cur
            // then will work in deduplicate
            // because if we have {-2,-2,-2},
            //                        left
            // left - 1 != index of the first -2
            // but nums[left - 1] == cur (nums[0] == -2)
            if (nums[left] + nums[right] == target
                    && (left - 1 == cur || nums[left - 1] != nums[left])) {
                // we no need to return
                // let the two pointers keep going to find the next target 
                res.add(Arrays.asList(nums[cur], nums[left++], nums[right--]));
            } else if (nums[left] + nums[right] <= target) {
                left++;
            } else {
                right--;
            }
        }

    }
}