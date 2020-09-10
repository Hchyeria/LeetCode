class Solution {
    // Solution 1: TreeSet
    public int twoSumLessThanK(int[] A, int K) {
        if (A == null || A.length < 2) {
            return Integer.MIN_VALUE;
        }

        int res = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : A) {
            Integer other = set.lower(K - i);
            if (other != null) {
                res = Math.max(res, i + other);
            }
            set.add(i);
        }
        return res;
    }

    // Time = O(n * log(n))
    // Space = O(n)

    // Solution 2: sort
    public int twoSumLessThanK2(int[] A, int K) {
        if (A == null || A.length < 2) {
            return Integer.MIN_VALUE;
        }

        Arrays.sort(A);;
        int res = Integer.MIN_VALUE;

        int left = 0, right = A.length - 1;
        while (left < right) {
            int sum = A[left] + A[right];
            if (sum < K) {
                res = Math.max(res, sum);
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    // Time = O(n * log(n) + n)
    // Space = O(log(n)) for quick sort
}