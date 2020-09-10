class Solution {
    // Solution 1: pq 
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        
        int n = nums.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            minHeap.offer(num);
            maxHeap.offer(num);
            if (minHeap.size() > 3) {
                minHeap.poll();
            }
            if (maxHeap.size() > 2) {
                maxHeap.poll();
            }
        }
        int res = 1;
        int max = 1;
        while (!minHeap.isEmpty()) {
            max = minHeap.poll();
            res *= max;
        }
        
        while (!maxHeap.isEmpty()) {
            max *= maxHeap.poll();
        }
        return Math.max(res, max);
    }
    // Time = O(n * log(n))
    // Space = O(n)


    // Solution 2
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        
        int n = nums.length;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    // Time = O(n)
    // Space = O(1)
}