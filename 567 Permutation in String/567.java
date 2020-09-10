class Solution {
    // HashMap + slideWindow
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.length() == 0) {
            return true;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            char c = s1.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int count = map.size();
        for (int i = 0, j = 0; i < s2.length() && j < s2.length(); ++j) {
            char current = s2.charAt(j);
            Integer frequency = map.get(current);
            if (frequency != null) { 
                map.put(current, frequency - 1);
                // careful when we update count
                if (frequency - 1 == 0) {
                    count--;
                }
            }
            if (j - i + 1 > s1.length()) {
                char current2 = s2.charAt(i);
                Integer frequency2 = map.get(current2);
                if (frequency2 != null) {
                    // careful when we update count
                    if (frequency2 == 0) {
                        count++;
                    }
                    map.put(current2, frequency2 + 1);
                }
                i++;
            }
            if (j - i + 1 == s1.length() && count == 0) {
                return true;
            }
        }
        return false;
    }

    // Time Complexity: O(m + n), s1.length() == m and s2.length() == n
    // Space Complexity: O(m)
}