class Solution {
    // Solution 1: BFS
    public boolean canReach(int[] arr, int start) {
        if (arr.length == 1){
            return true;
        }
        Set<Integer> set = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        set.add(start);
        queue.offerLast(start);
        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            if (arr[cur] == 0) {
                return true;
            }
            int left = cur - arr[cur];
            int right = cur + arr[cur];
            if (left >= 0) {
                if (set.add(left)) {
                    queue.offerLast(left);
                }
            }
            if (right < arr.length) {
                if (set.add(right)) {
                    queue.offerLast(right);
                }
            }

        }
        return false;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)

    // Solution 2: DFS
    public boolean canReach2(int[] arr, int start) {
        if (arr.length == 1){
            return true;
        }
        
        return dfsHelper(arr, start);
    }

    private boolean dfsHelper(int[] array, int index) {
        if (index < 0 || index >= array.length || array[index] >= array.length) {
            return false;
        }
        if (array[index] == 0) {
            return true;
        }
        int jump = array[index];
        array[index] += array.length;
        return dfsHelper(array, index + jump) || dfsHelper(array, index - jump);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
}