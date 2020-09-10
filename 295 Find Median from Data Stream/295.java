class MedianFinder {

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(12, Collections.reverseOrder());
    }
    
    // keep the maxHeap.size() == minHeap.size() or maxHeap.size() == minHeap.size() + 1
    public void addNum(int num) {
        if (maxHeap.size() == 0 || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
        } else {
            minHeap.offer(num);
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    public Double findMedian() {
        int n = minHeap.size() + maxHeap.size();
        if (n == 0) {
            return null;
        }
        if (n % 2 == 0)  {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return (double)(maxHeap.peek());
        }
    }

    // Time complexity: insert O(log(n)), findMedian O(1)
    // Space complexity: O(n)
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */