public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        // write your code here
        if (L  == null || L.length == 0) {
            return 0;
        }
        int maxVal = 0;
        // remember to use long !!!!!
        long sum = 0;
        for (int i : L) {
            maxVal = Math.max(maxVal, i);
            sum += i;
        }
        if (sum < k) {
            return 0;
        }
        if (sum == k) {
            return 1;
        }
        
        int left = 1;
        int right = maxVal;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (isCut(L, mid, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        
        if (isCut(L, right, k)) {
            return right;
        } else {
            return left;
        }
    }
    
    private boolean isCut(int[] len, int val, int k) {
        int count = 0;
        for (int l : len) {
            count += l / val;
        }
        return count >= k;
    } 

    // Time = O(n log (Len)), where Len is the longest length of the wood
    // Space = O(1)
}