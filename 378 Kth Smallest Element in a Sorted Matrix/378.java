class Solution {
    class Cell {
        int row;
        int col;
        int val;

        Cell(int row, int col, int val) {
            this.col = col;
            this.row = row;
            this.val = val;
        }
    }

    // Solution 1: BFS + Heap
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(k, (c1, c2) -> {
            if (c1.val == c2.val) {
                return 0;
            }
            return c1.val < c2.val ? -1 : 1;
        });
        // if we write like this: Boolean[][] visited = new Boolean[m][n];
        // then will get a NPE, because visited[0] default value is null
        boolean[][] visited = new boolean[m][n];
        minHeap.offer(new Cell(0, 0, matrix[0][0]));
        visited[0][0] = true;
        for (int i = 0; i < k - 1; ++i) {
            Cell cell = minHeap.poll();
            if (cell != null) {
                if (cell.row < m - 1 && !visited[cell.row + 1][cell.col]) {
                    minHeap.offer(new Cell(cell.row + 1, cell.col, matrix[cell.row + 1][cell.col]));
                    visited[cell.row + 1][cell.col] = true;
                }
                if (cell.col < n - 1 && !visited[cell.row][cell.col + 1]) {
                    minHeap.offer(new Cell(cell.row, cell.col + 1, matrix[cell.row][cell.col + 1]));
                    visited[cell.row][cell.col + 1] = true;
                }
            }
        }
        return minHeap.peek().val;
    }

    // Time complexity: O(k*log(k))
    // Space Complexity: O(k + M*N) (can be optimized to O(k) using hash table)
    // How about the k is on the same magnitude as M*N ?

    // Solution 2: Binary Search
    public int kthSmallest2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int low = matrix[0][0], high = matrix[m - 1][n - 1];
        // Notice that using (low < high) in while loop rather than using (low <= high) to avoid stay in the loop
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            for (int i = 0; i < m; ++i) {
                // only copy a row of matrix if faster than copy all matrix
                count +=  countSmallAndEqual(matrix[i], mid);
            }
            if (count < k) {
                low = mid + 1;
            } else {
                // when count >= k, if count == k, we can't return right now
                high = mid;
            }
        }
        // why? return low ?? How can guarantee the low is actually in the matrix?
        // the answer is at the bottom
        return low;
    }

    // Actually, the goal is to find the index of the smallest of the element larger than target
    // also equal to the size of elements smaller or equal to target
    private int countSmallAndEqual(int[] array, int target) {
        int length = array.length;
        if (target >= array[length - 1]) {
            return length;
        }
        if (target < array[0]) {
            return 0;
        }
        // high != n - 1, why?
        // think about the case, when the target is larger than all elements in array
        int low = 0, high = length;
        // think about the terminate condition
        while (low < high) {
            int mid = low + (high - low) / 2;
            // don't emit the =
            if (array[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // Time Complexity: O(m*log(n)*log(Integer.MAX_VALUE))
    // Space Complexity: O(1)

    /* 
        The correctness of this algorithm is to ensure that the target value is within the range of [low, high] for each loop step.
        For example, there is a matrix and the k = 14,
        [11, 15, 20, 21]
        [12, 16, 25, 26]
        [16, 20, 30, 31]
        [30, 31, 35, 38]
        It is easy to find the target value is 31, and the algorithm will make sure the target value is within the range of [low, high].
        First step, let low = 11, high = 38, and target value must in the range of [11, 38], so mid is 24, count = 8, count < k, let low = 24 + 1 = 25, now target value 31 is within the range of [25, 38].
        Second step, low = 25, high = 38, mid = 31, count = 14, count = k, let high = mid = 31, now target value 31 is also within the range of [25, 31].
        Third step, low = 25, high = 31, mid = 28, count = 10, count < k, let low = mid + 1 = 29, now target value 31 is also within the range of [29, 31].
        Next step, low = 29, high = 31, mid = 30, count = 12, count < k, let low = mid + 1 = 31, now target value 31 is also within the range of [31, 31]. Now low == high, we find the target value.
        Notice that every time the algorithm narrows the range, the target value must in the new range. When count >= k
        the mid is big enough, the target value must in the range of [low, mid], so let high = mid. When count < k,
        the mid is small, the target value must in the range of [mid+1, high], so let low = mid + 1. 
        This loop invariants will remain until the algorithm gets end (low == high)

    */
}