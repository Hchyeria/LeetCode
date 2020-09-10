class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        if (nums1 == null || nums2 == null 
            || nums1.length == 0 || nums2.length == 0) {
            return res;
        }
        Map<Integer, Integer> cache = new HashMap<>();
        Deque<Integer> stack = new LinkedList<>();
        int n = nums2.length;
        cache.put(nums2[n - 1], -1);
        stack.offerFirst(nums2[n - 1]);
        for (int i = n - 2; i >= 0; --i) {
            if (nums2[i] < stack.peekFirst()) {
                cache.put(nums2[i], stack.peekFirst());
                stack.offerFirst(nums2[i]);
            } else {
                while (!stack.isEmpty() && nums2[i] >= stack.peekFirst()) {
                    stack.pollFirst();
                }
                if (!stack.isEmpty()) {
                    cache.put(nums2[i], stack.peekFirst());
                } else {
                    cache.put(nums2[i], -1);
                }
                stack.offerFirst(nums2[i]);
            }
        }
        
        n = nums1.length;
        for (int i = 0; i < n; ++i) {
            res[i] = cache.get(nums1[i]);
        }
        return res;
    }

    // Time = O(m + n)
    // Space = O(n)

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        if (nums1 == null || nums2 == null 
            || nums1.length == 0 || nums2.length == 0) {
            return res;
        }
        Map<Integer, Integer> cache = new HashMap<>();
        Deque<Integer> stack = new LinkedList<>();
        int n = nums2.length;
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peekFirst() < num) {
                cache.put(stack.pollFirst(), num);
            }
            stack.offerFirst(num);
        }
        
        n = nums1.length;
        for (int i = 0; i < n; ++i) {
            res[i] = cache.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}