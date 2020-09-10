class Solution {
    // Time = O(n), Space = O(1)

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        List<Integer> refine = new ArrayList<>();
        refine.add(nums[0]);
        for (int num : nums) {
            if (num < refine.get(refine.size() - 1)) {
                for (int i = 0; i < refine.size(); ++i) {
                    if (refine.get(i) == num) break;
                    if (refine.get(i) > num) {
                        refine.set(i, num);
                        break;
                    }
                }
            } else if (num > refine.get(refine.size() - 1)) {
                if (refine.size() == 2) {
                    return true;
                } else {
                    refine.add(num);
                }  
            }
        }
        return false;
    }

    // Solution 2:
    public boolean increasingTriplet2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= small) {
                small = num;
            } else if (num <= big) {
                big = num;
            } else {
                return true;
            }
        }
        return  false;
    }
}