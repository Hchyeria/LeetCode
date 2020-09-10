class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int res = 0;
        for (int i = 1; i <= 26; ++i) {
            int count = 0;
            int left = 0;
            map.clear();
            for (int j = 0; j < n; ++j) {
                int c = map.getOrDefault(s.charAt(j), 0);
                map.put(s.charAt(j), c + 1);
                if (c + 1 == k) {
                    count++;
                }
                while (map.size() > i) {
                    c = map.get(s.charAt(left));
                    map.put(s.charAt(left), c - 1);
                    if (c == k) {
                        count--;
                    }
                    if (c - 1 == 0) {
                        map.remove(s.charAt(left));
                    }
                    left++;
                }
                if (count == i) {
                    res = Math.max(res, j - left + 1);
                }
            }
        }
        return res;
    }
    
}