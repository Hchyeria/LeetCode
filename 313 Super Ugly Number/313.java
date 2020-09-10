class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        minHeap.offer(1L);
        visited.add(1L);
        long p = 0L;
        for (int i = 0; i < n - 1; ++i) {
            p = minHeap.poll();
            
            for (int prime : primes) {
                if (visited.add(p * prime)) {
                    minHeap.offer(p * prime);
                }
            }
        }
        p = minHeap.poll();
        return (int) p;
        
    }
    // Time = O(k * log(k))
    // Space = O(k)

    public int nthSuperUglyNumber2(int n, int[] primes) {
        int len = primes.length;
        int[] ugly = new int[n];
        int[] idx = new int[len];
        ugly[0] = 1;
        for (int i = 1; i < n; ++i) {
            ugly[i] = Integer.MAX_VALUE;
            for (int j = 0; j < len; ++j) {
                ugly[i] = Math.min(ugly[i], ugly[idx[j]] * primes[j]);
            }
            
            for (int j = 0; j < len; ++j) {
                if (ugly[i] == ugly[idx[j]] * primes[j]) {
                    idx[j]++;
                }
            }
        }
        return ugly[n - 1];   
    }
    // Time = O(k)
    // Space = O(n + len)
}