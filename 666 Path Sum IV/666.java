// LintCode
// 1098. Path Sum IV

public class Solution {
    /**
     * @param nums: the list
     * @return: the sum of all paths from the root towards the leaves
     */
    public int pathSumIV(int[] nums) {
        // Write your code here.\
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            int a = n / 100;
            n %= 100;
            int b = n / 10;
            int c = n % 10;
            
            map.put((1 << (a - 1)) + b - 1, c);
        }
        return helper(map, 1, 0);
    }
    
    private int helper(Map<Integer, Integer> map, int index, int sum) {
        if (!map.containsKey(index)) {
            return 0;
        }
        sum += map.get(index);
        if (!map.containsKey(index * 2) && !map.containsKey(index * 2 + 1)) {
            return sum;
        }
        return helper(map, index * 2, sum) + helper(map, index * 2 + 1, sum);
    }
}