class Solution {
    public String minWindow(String s, String t) {
        StringBuilder sb = new StringBuilder();
        if (t == null || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        int m = s.length(), n = t.length();
        // remember check length
        if (m < n) {
            return "";
        }
        for (int i = 0; i < n; ++i) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int size = map.size();
        int res = m + 1;
        int start = 0;
        for (int i = 0; i < m; ++i) {
            Integer count = map.get(s.charAt(i));
            if (count != null) {
                map.put(s.charAt(i), count - 1);
                if (count - 1 == 0) {
                    size--;
                }
            }
            while (size == 0) {
                if (i - left + 1 < res) {
                    res = i - left + 1;
                    start = left;
                } 
                count = map.get(s.charAt(left));
                if (count != null) {
                    if (count == 0) {
                        size++;
                    }
                    map.put(s.charAt(left), count + 1);
                }
                left++;
            }
        }
        // final check !
        if (res == m + 1) {
            return "";
        }
        while (res-- > 0) {
            sb.append(s.charAt(start++));
        }
        return sb.toString();
    }

    // Time = O(m)
    // Space = O(n)
}