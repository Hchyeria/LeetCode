class Solution {

    // Solution 1: Greedy
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null) return 0;
        if (tasks.length <= 1 || n == 0) {
            return tasks.length;
        }
        
        int[] count = new int[26];
        int max = 0;
        int maxCount = 0;
        for (char t : tasks) {
            int index = t - 'A';
            count[index]++;
            if (count[index] == max) {
                maxCount++;
            } else if (count[index] > max) {
                max = count[index];
                maxCount = 1;
            }
        }
        
        int partCount = max - 1;
        int partLength = n - (maxCount - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - (max * maxCount);
        int idle = Math.max(0, emptySlots - availableTasks);
        
        return tasks.length + idle;
    }

    // Time = O(n)
    // Space = O(26)

    // Solution 2: PQ
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null) return 0;
        if (tasks.length <= 1 || n == 0) {
            return tasks.length;
        }
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> 
                                                                           a.getValue() == b.getValue() ? Integer.compare(a.getKey(), b.getKey()) : Integer.compare(b.getValue(), a.getValue()));
        
        for (char t : tasks) {
            int index = t - 'A';
            map.put(index, map.getOrDefault(index, 0) + 1);
        }
        
        pq.addAll(map.entrySet());
        int count = 0;
        List<Map.Entry<Integer, Integer>> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            int k = n + 1;
            res.clear();
            while (k > 0 && !pq.isEmpty()) {
                Map.Entry<Integer, Integer> entry = pq.poll();
                entry.setValue(entry.getValue() - 1);
                res.add(entry);
                k--;
                count++;
            }
            
            for (Map.Entry<Integer, Integer> entry : res) {
                if (entry.getValue() > 0) pq.offer(entry);
            }
            
            if (pq.isEmpty()) break;
            count += k;
        }
        return count;
    }
    // Time = O(n * log(n))
    // Space = O(n)
}