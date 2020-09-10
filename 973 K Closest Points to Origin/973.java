class Solution {
    // Solution 1: sort
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (a, b) -> {
            int aDis = a[0] * a[0] + a[1] * a[1];
            int bDis = b[0] * b[0] + b[1] * b[1];
            if (aDis == bDis) {
                return 0;
            }
            return aDis < bDis ? -1 : 1;
        });
        return Arrays.copyOfRange(points, 0, K);
    }
    // Time complexity: O(n*log(n))
    // Space Complexity: O(n)

    // Solution 2: quick select
    public int[][] kClosest2(int[][] points, int K) {
        quickSelect(points,0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private void quickSelect(int[][] points, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int index = partition(points, left, right);
        if (index == k) {
            return;
        }
        if (index < k) {
            quickSelect(points, index + 1, right, k);
        } else {
            quickSelect(points, left, index - 1, k);
        }
    }

    private int partition(int[][] points, int left, int right) {
        int pivot = left + (int)(Math.random() * (right - left + 1));
        swap(points, pivot, right);
        int i = left, j = right - 1;
        while (i <= j) {
            if (compare(points[j], points[right]) >= 0) {
                j--;
            } else if (compare(points[i], points[right]) < 0) {
                i++;
            } else {
                swap(points, i++, j--);
            }
        }
        swap(points, i, right);
        return i;
    }

    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    private int compare(int[] a, int[] b) {
        int aDis = a[0] * a[0] + a[1] * a[1];
        int bDis = b[0] * b[0] + b[1] * b[1];
        if (aDis == bDis) {
            return 0;
        }
        return aDis < bDis ? -1 : 1;
    }

    // Time Complexity: O(n^2) worse case
    // Space Complexity: O(n), because of call-stack
    // Time = O(n), space = O(log(n)), if array is randomly shuffled
}