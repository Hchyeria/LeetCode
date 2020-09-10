class Solution {
    // Did not pass this case : "WRRBBW"
    //                          "RB"
    private int maxVal = 6;
    public int findMinStep(String board, String hand) {
        if (board == null || board.isEmpty()) {
            return 0;
        }
        if (hand == null || hand.isEmpty()) {
            return -1;
        }
        int[] count = new int[26];
        int n = hand.length();
        for (int i = 0; i < n; ++i) {
            count[hand.charAt(i) - 'A']++;
        }
        
        int res = helper(board , count);
        return res == 6 ? -1 : res;
    }
    
    private int helper(String s, int[] count) {
        s = removeConsecutive(s);
        if (s.isEmpty()) return 0;
        int n =s.length();
        int res = maxVal;
        int i = 0, j = 0;
        while (i < n) {
            while (j < n && s.charAt(i) == s.charAt(j)) j++;
            int need = 3 - (j - i);
            if (need <= count[s.charAt(i) - 'A']) {
                count[s.charAt(i) - 'A'] -= need;
                res = Math.min(res, need + helper(s.substring(0, i) + s.substring(j), count));
                count[s.charAt(i) - 'A'] += need;
            }
            i = j;
        }
        return res;
    }
    
    private String removeConsecutive(String s) {
        if (s.isEmpty()) return s;
        int n = s.length();
        for (int i = 0, j = 0; i < n; ++j) {
            if (j < n && s.charAt(i) == s.charAt(j)) continue;
            if (j - i >= 3) {
                return removeConsecutive(s.substring(0, i)) + removeConsecutive(s.substring(j));
            }
            i = j;
        }
        return s;
    }

    // Time = O(n!), T(n) = n + T(n-c), c is constant
    // Space = O(n)
}