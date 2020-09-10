class Solution {

    // Solution 1: DP
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[days[n - 1] + 1];
        dp[days[0]] = Math.min(Math.min(costs[0], costs[1]), costs[2]);
        Set<Integer> set = new HashSet<>();
        for (int d : days) {
            set.add(d);
        }
        for (int i = days[0] + 1; i <= days[n - 1]; ++i) {
            if (!set.contains(i)) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = dp[i - 1] + costs[0];
            dp[i] = Math.min(dp[i], dp[Math.max(i - 7, days[0] - 1)] + costs[1]);
            dp[i] = Math.min(dp[i], dp[Math.max(i - 30, days[0] - 1)] + costs[2]);
        }
        return dp[days[n - 1]];
    }
    // Time = O(days[n - 1]) -> O(365)
    // Space = O(days[n - 1]) -> O(365) -> O(30)

    // optimise space
    public int mincostTickets2(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[31];
        Set<Integer> set = new HashSet<>();
        for (int d : days) {
            set.add(d);
        }
        for (int i = days[0]; i <= days[n - 1]; ++i) {
            if (!set.contains(i)) {
                dp[i % 31] = dp[(i - 1) % 31];
                continue;
            }
            dp[i % 31] = dp[(i - 1) % 31] + costs[0];
            dp[i % 31] = Math.min(dp[i % 31], dp[Math.max(i - 7, 0)  % 31] + costs[1]);
            dp[i % 31] = Math.min(dp[i % 31], dp[Math.max(i - 30, 0) % 31] + costs[2]);
        }
        return dp[days[n - 1] % 31];
    }

    // Solution 2: Memorialize
    public int mincostTickets3(int[] days, int[] costs) {
        return helper(days, costs, new int[days.length], 0, 0);
    }
    
    private int helper(int[] days, int[] costs, int[] cache, int index, int day) {
        if (index == days.length) {
            return 0;
        }
        if (days[index] < day) {
            return helper(days, costs, cache, index + 1, day);
        }
        if (cache[index] != 0) return cache[index];
        
        int one = helper(days, costs, cache, index + 1, days[index] + 1) + costs[0];
        int seven = helper(days, costs, cache, index + 1, days[index] + 7) + costs[1];
        int thirsty = helper(days, costs, cache, index + 1, days[index] + 30) + costs[2];
        cache[index] = Math.min(Math.min(one, seven), thirsty);
        
        return cache[index];
    }

    // Solution 3: Queue
    private static class Node {
        int d;
        int c;
        
        Node(int d, int c) {
            this.d = d;
            this.c = c;
        }
    }
    
    public int mincostTickets4(int[] days, int[] costs) {
        Queue<Node> q7 = new LinkedList<>(), q30 = new LinkedList<>();
        int cost = 0;
        for (int d : days) {
            while (!q7.isEmpty() && q7.peek().d + 7 <= d) {
                q7.poll();
            }
            
            while (!q30.isEmpty() && q30.peek().d + 30 <= d) {
                q30.poll();
            }
            
            q7.offer(new Node(d, cost + costs[1]));
            q30.offer(new Node(d, cost + costs[2]));
            cost = Math.min(Math.min(cost + costs[0], q7.peek().c), q30.peek().c);
        }
        return cost;
    }
}