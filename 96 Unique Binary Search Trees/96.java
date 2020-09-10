class Solution {
    // Solution 1: recursion + memorize 
    public int numTrees(int n) {
        if (n <= 2) {
            return n;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(1, 1);
        
        return helper(map, n);
    }
    
    private int helper(Map<Integer, Integer> map, int num) {
        Integer c = map.get(num);
        if (c != null) {
            return c;
        }
        
        int sum = 0;
        for (int i = 1; i <= num; ++i) {
            sum += helper(map, i - 1) * helper(map, num - i);
        }
        map.put(num, sum);
        return sum;
    }
    // Time = O(n ^ 2)
    // Space = O(n)

    // Solution 2: dp
    public int numTrees2(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
    // Time = O(n ^ 2)
    // Space = O(n)

    // Solution 3: Mathematical Deduction
    // Cn+1 = Cn * 2 * (2 * i + 1) / (i + 2)
    public int numTrees3(int n) {
        if (n <= 2) {
            return n;
        }
        long c = 1L;
        for (int i = 0; i < n; ++i) { // i from 0 to n - 1
            c = c * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) c;
    }
}