class Solution {
    // Solution 1: sort + dp
    // Time = O(n * log(n) + n*d)
    // Space = O(n
    public int maxJumps(int[] arr, int d) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < n; ++i) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        int res = 1;
        int[] dp = new int[n];

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            for (int i : list) {
                dp[i] = 1;
                helper(arr, dp, i, d, true);
                helper(arr, dp, i, d, false);
                res = Math.max(res, dp[i]);
            }
        }

        return res;
    }

    private void helper(int[] arr, int[] dp, int i, int d, boolean isLeft) {
        int n = arr.length;
        int h = arr[i];
        for (int j = 1; j <= d; ++j) {
            int next = isLeft ? i - j : i + j;
            if (next >= 0 && next < n && arr[next] > h) {
                dp[i] = Math.max(dp[i], dp[next] + 1);
                break;
            }
        }
    }

    // Solution 2: Memoization dp
    // Time = O(n * d)
    // Space = O(n
    public int maxJumps2(int[] arr, int d) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;

        int[] res = {1};
        int[] dp = new int[n];
        for (int i = 0; i < n; ++i) {
            helper2(dp, res, arr, d, i);
        }
        return res[0];
    }

    private int helper2(int[] dp, int[] res, int[] arr, int d, int i) {
        if (dp[i] != 0) {
            return dp[i];
        }
        int n = arr.length;
        dp[i] = 1;
        int[] dir = {-1, 1};
        for (int isLeft : dir) {
            for (int j = 1; j <= d; ++j) {
                int next = isLeft == -1 ? i - j : i + j;
                if (next >= 0 && next < n && arr[next] < arr[i]) {
                    dp[i] = Math.max(dp[i], helper2(dp, res, arr, d, next) + 1);
                } else {
                    break;
                }
            }
        }
        res[0] = Math.max(res[0], dp[i]);
        return dp[i];
    }

    // Solution 3: Decrease Stack
    // Time = O(n)
    // Space = O(n)
    public int maxJumps3(int[] arr, int d) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int res = 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        Deque<Integer> stack = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            while (!stack.isEmpty() && (i == n || arr[stack.peekFirst()] < arr[i])) {
                int j = stack.pollFirst();
                list.clear();
                list.add(j);
                while (!stack.isEmpty() && arr[stack.peekFirst()] == arr[j]) {
                    list.add(stack.pollFirst());
                }
                for (int pre : list) {
                    if (i - pre <= d) {
                        dp[i] = Math.max(dp[i], dp[pre] + 1);
                    }
                    if (!stack.isEmpty()) {
                        int p = stack.peekFirst();
                        if (pre - p <= d) {
                            dp[p] = Math.max(dp[p], dp[pre] + 1);
                        }
                    }
                }
            }
            stack.offerFirst(i);
        }

        for (int i = 0; i < n; ++i) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}