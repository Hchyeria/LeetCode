class Solution {
    // Solution 1: HashMap
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, res = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            sum = left + right + 1;
            res = Math.max(res, sum);
            
            if (left > 0) {
                map.put(num - left, sum);
            }
            if (right > 0) {
                map.put(num + right, sum);
            }
            map.put(num, sum);
        }
        return res;
    }
    // Time = O(n)
    // Space = O(n)

    private static class UnionFind {
        int[] parent;
        
        UnionFind(int n) {
            parent = new int[n];
            Arrays.fill(parent, -1);
        }
        
        void union(int i, int j) {
            int m = find(i);
            int n = find(j);
            if (parent[m] <= parent[n]) {
                parent[m] += parent[n];
                parent[n] = m;
            } else {
                parent[n] += parent[m];
                parent[m] = n;
            }
        }
        
        int find(int i) {
            while (parent[i] >= 0) {
                int temp = parent[i];
                if (parent[temp] < 0) {
                    i = temp;
                    break;
                }
                parent[i] = parent[temp];
                i = parent[i];
            }
            return i;
        }
        
        int maxSize() {
            int res = 0;
            for (int i : parent) {
                res = Math.min(res, i);
            }
            return -res;
        }
        
    }
    // Solution 2: UnionFind
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; ++i) {
            if (map.containsKey(nums[i])) {
                continue;
            }
            map.put(nums[i], i);
            Integer left = map.get(nums[i] - 1);
            if (left != null) {
                unionFind.union(i, left);
            }
            Integer right = map.get(nums[i] + 1);
            if (right != null) {
                unionFind.union(i, right);
            }
        }
        return unionFind.maxSize();
    }

    // Time = O(n * log*(n))
    // Space = O(n)
}