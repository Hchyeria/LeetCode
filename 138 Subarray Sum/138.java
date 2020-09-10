// LintCode
// 138. Subarray Sum
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        Map<Long, Integer> sumMap = new HashMap<>();
        sumMap.put(0L, -1);
        long sum = 0L;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            Integer pre = sumMap.get(sum);
            if (pre != null) {
                res.add(pre + 1);
                res.add(i);
                return res;
            }
            sumMap.put(sum, i);
        }
        return res;
    }
}