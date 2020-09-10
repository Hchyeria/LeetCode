class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        int m = s.length(), n = t.length();
        if (m != n) {
            return false;
        }
        int[] mapS = new int[256];
        int[] mapT = new int[256];
        
        for (int i = 0; i < m; ++i) {
            if (mapS[s.charAt(i)] != mapT[t.charAt(i)]) {
                return false;
            }
            mapS[s.charAt(i)] = i + 1;
            mapT[t.charAt(i)] = i + 1;
        }
        return true;
        
    }
    // Time = O(n)
    // Space = O(2 * n)
}