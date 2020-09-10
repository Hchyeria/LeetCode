class Solution {
    // dp
    public int numSquares(int n) {
        int sqrtVal = (int)(Math.sqrt(n));
        if (sqrtVal * sqrtVal == n) {
            return 1;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; i - j*j >= 0; ++j) {
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }
        return dp[n];
    }

    // Time Complexity: O(n * sqrt(n))
    // Space COmplexity: O(n)

    // BFS
    public int numSquares2(int n) {
        int sqrtVal = (int)(Math.sqrt(n));
        if (sqrtVal * sqrtVal == n) {
            return 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(n);
        int depth = 0;
        visited.add(n);
        
        while (!queue.isEmpty()) {
            depth++;
            
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int node = queue.poll();
                for (int j = 1; j * j <= node; ++j) {
                    if (node == j * j) {
                        return depth;
                    }
                    if (visited.add(node - j * j)) {
                        queue.offer(node - j * j);  
                    }
                }
            }
        }
        return depth;
    }
    // Time Complexity: O(V + G) -> O(n ^ sqrt(n))
    // Space COmplexity: O(v)
}