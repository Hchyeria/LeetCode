class Solution {
    // Solution 1: pq 
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k, (a, b) -> {
            return a.getValue().compareTo(b.getValue());
        });
        
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll().getKey());
        }
        return res;
    }
    // Time Complexity: O(n * log(k))
    // Space Complexity: O(k)

    // Solution 2: bucket sort
    public List<Integer> topKFrequent2(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        List<Integer>[] buckets = new List[nums.length + 1];
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int count = entry.getValue();
            if (buckets[count] == null) {
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(entry.getKey());
            
        }
        
        for (int i = nums.length; i >= 0 && res.size() < k; --i) {
            if (buckets[i] != null) {
			    res.addAll(buckets[i]);   
            }
        }
        
        return res;
    }
    // Time Complexity: O(n + n + k)
    // Space Complexity: O(n)
}