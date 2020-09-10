class Solution {
    // Solution 1: Hash Set
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> a = toList(nums1);
        List<Integer> b = toList(nums2);
        Set<Integer> set = new HashSet<>(a);
        set.retainAll(b);
        int[] res = new int[set.size()];
        int index = 0;
        for (int i : set) {
            res[index++] = i;
        }
        Arrays.sort(res);
        return res;
    }

    private List<Integer> toList(int[] array) {
        List<Integer> res = new ArrayList<>();
        for (int num1 : array) {
            res.add(num1);
        }
        return res;
    }
    // Time Complexity: O(m + n) in average, in worst case O(m * n)
    // Space Complexity: O(m + n)

    // Solution 2: two pointer, when the arrays are sorted
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> res = new ArrayList<>();
        int one = 0, two = 0;
        while (one < nums1.length && two < nums2.length) {
            int a = nums1[one], b = nums2[two];
            if (a < b) {
                one++;
            } else if (a > b) {
                two++;
            } else {
                res.add(a);
                // when have duplicated element
                // careful we should always remember to check the bound!!
                while (one < nums1.length && a == nums1[one]) {
                    one++;
                }
                while (two < nums2.length && b == nums2[two]) {
                    two++;
                }
            }
        }
        return listToArray(res);
    }

    private int[] listToArray(List<Integer> array) {
        int[] res = new int[array.size()];
        int index = 0;
        for (int i : array) {
            res[index++] = i;
        }
        return res;
    }

    // Time Complexity: O(m + n)
    // Space Complexity: O(1)

}