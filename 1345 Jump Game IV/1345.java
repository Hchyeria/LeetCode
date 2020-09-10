class Solution {
    
    // BFS
    public int minJumps(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int n = arr.length;
        if (n == 2) return 1;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        // should loop from the end to start
        // because we need to try the farthest position firstly
        // otherwise case [1,1,1,1,1,1,1,1,1 ......] will get TLE
        for (int i = n - 1; i >= 0; --i) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(0);
        visited.add(0);
        int res = 0;
        while (!q.isEmpty()) {
            res++;
            int size = q.size();
            while (size -- > 0) {
                int cur = q.poll();
                for (int i = cur - 1; i <= cur + 1; ++i) {
                    if (i < 0 || i == cur || i >= n) {
                        continue;
                    }
                    if (i == n - 1) return res;
                    if (visited.add(i)) {
                        q.offer(i);
                    }
                }
                
                for (int i : map.get(arr[cur])) {
                    if (i == cur) {
                        continue;
                    }
                    if (i == n - 1) return res;
                    if (visited.add(i)) {
                        q.offer(i);
                    }
                }
            }
        }
        
        return res;
    }

    // Time = O(E)
    // Space = O(N)
}