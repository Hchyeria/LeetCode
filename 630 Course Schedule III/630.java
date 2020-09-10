class Solution {
    // memorialize
    // MLE
    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0) {
            return 0;
        }
        Arrays.sort(courses, Comparator.comparing(c -> c[1]));
        int n = courses.length;
        int[][] cache = new int[n][courses[n - 1][1] + 1];
        return helper(cache, courses, 0, 0);
    }
    
    private int helper(int[][] cache, int[][] courses, int index, int day) {
        if (index == courses.length) {
            return 0;
        }
        if (cache[index][day] != 0) {
            return cache[index][day];
        }
        int taken = 0;
        if (day + courses[index][0] <= courses[index][1]) {
             taken = 1 + helper(cache, courses, index + 1, day + courses[index][0]);
        }
        int noTaken = helper(cache, courses, index + 1, day);
        cache[index][day] = Math.max(taken, noTaken);
        return cache[index][day];
    }

    // Greedy
    public int scheduleCourse2(int[][] courses) {
        if (courses == null || courses.length == 0) {
            return 0;
        }
        Arrays.sort(courses, Comparator.comparing(c -> c[1]));
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int time = 0;
        for (int[] c : courses) {
            time += c[0];
            maxHeap.offer(c[0]);
            if (time > c[1]) {
                time -= maxHeap.poll();
            }
        }
        return maxHeap.size();
    }

    // Time = O(n * log(n))
    // Space = O(n)
}