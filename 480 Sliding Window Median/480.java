class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        TreeSet<Integer> maxSet = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> minSet = new TreeSet<>(comparator);
        
        Supplier<Double> median = (k % 2 == 0) 
            ? () -> ((double) nums[maxSet.first()] + nums[minSet.first()]) / 2.0
            : () -> (double) nums[maxSet.first()];
        // pay attention if return double then you'd better convert the num to double at first
        // (double) (nums[maxSet.first()] + nums[minSet.first()]) / 2.0, it will failed when [2147483647,2147483647]
        // sum will cause overflow

        Runnable balance = () -> {
            while (minSet.size() > maxSet.size()) {
                maxSet.add(minSet.pollFirst());
            }
        };
        
        for (int i = 0; i < k; ++i) {
            minSet.add(i);
        }
        balance.run();
        res[0] = median.get();
        int n = nums.length;
        for (int i = k, r = 1; i < n; ++i, ++r) {
            if (!minSet.remove(i - k)) {
                maxSet.remove(i - k);
            }
            // must maxSet.add(i) then minSet.add(maxSet.pollFirst())
            // if we just write like this minSet.add(i)
            // when maxSet.size() == minSet.size() + 1
            // the result will be inaccurate
            // maybe the new element i belongs to maxSet, but we insert it to minSet
            // and the balance function will not update the result
            maxSet.add(i);
            minSet.add(maxSet.pollFirst());
            balance.run();
            res[r] = median.get();
        }
        return res;
    }

    // However instead of using two priority queue's we use two Tree Sets as we want O(log(k)) for remove(element). 
    // Priority Queue would have been O(k) for remove(element) giving us an overall time complexity of O(nk) instead of O(n*log(k)).
    // However there is an issue when it comes to duplicate values so to get around this we store the index into nums in our Tree Set. 
    // To break ties in our Tree Set comparator we compare the index.
}