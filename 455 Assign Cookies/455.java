class Solution {
    public int findContentChildren(int[] g, int[] s) {
        if (g == null || g.length == 0 || s == null || s.length == 0) {
            return 0;
        }
        int m = g.length;
        int n = s.length;
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        for (int i = 0, j = 0; i < m && j < n; ++j) {
            if (s[j] >= g[i]) {
                res++;
                i++;
            }
        }
        return res;
    }
    // max = max(m, n)
    // Time = O(max * log(max))
}