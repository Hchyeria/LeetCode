class Solution {
    public boolean isAnagram(String s, String t) {
        int sL = s.length(), tL = t.length();
        if (sL != tL) return false;
        HashMap<Character, Integer> temp = new HashMap<>();
        for (int i = 0; i < sL; ++i) {
            temp.put(s.charAt(i), temp.getOrDefault(s.charAt(i), 0) + 1);
            temp.put(t.charAt(i), temp.getOrDefault(t.charAt(i), 0) - 1);
        }
        for (Integer val : temp.values()) {
            if (val != 0) return false;
        }
        return true;
    }

    
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

}
