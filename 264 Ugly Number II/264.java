class Solution {
    // Solution 1: BFS + PriorityQueue
    public int nthUglyNumber(int n) {
        int[] factors = {2 , 3, 5};
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        pq.offer(1L);
        visited.add(1L);
        long result = 0L;
        for (int i = 0; i < n - 1; ++i) {
            result = pq.poll();

            for (int d : factors) {
                long next = result * d;
                if (visited.add(next)) {
                    pq.offer(next);
                }
            }
        }
        // Long -> long -> int
        // Long can't convert to int
        result = pq.poll();
        return (int) result;
    }
    // Time Complexity: O(k * log(k))
    // Space complexity: O(k)
    // Notice that all 3 factors are co-prime with each other, therefore every
    // product is unique, only under this condition can we use hash set.

    // What if the given factors are not co-prime with each other?
    // Use Set<List<Integer>> to keep the number of each factor
    // hashCode() of List has already been implemented.

    // Solution 2: dp
    public int nthUglyNumber2(int n) {
        int x = 0, y = 0, z = 0;
        long[] dp = new long[n];
        dp[0] = 1;
        for (int i = 1; i < n; ++i) {
            dp[i] = Math.min(dp[x] * 2, Math.min(dp[y] * 3, dp[z] * 5));
            if (dp[i] == dp[x] * 2) {
                x++;
            }
            if (dp[i] == dp[y] * 3) {
                y++;
            }
            if (dp[i] == dp[z] * 5) {
                z++;
            }
        }
        return (int)dp[n-1];
    }
    // Time Complexity: O(k)
    // Space complexity: O(k)
}