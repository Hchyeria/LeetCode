class Solution {
    // Solution 1: bucket sort
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int n = citations.length;
        int[] bucket = new int[n + 1];
        
        for (int c : citations) {
            if (c >= n) {
                bucket[n]++;
            } else {
                bucket[c]++;
            }
        }
        
        int count = 0;
        for (int i = n; i >= 0; --i) {
            count += bucket[i];
            if (count >= i) {
                return i;
            }
        }
        return 0;
    }

    // Time = O(n)
    // Space = O(n)

    // Solution 2: sort + binary search, O(n*log(n) + log(n))
}