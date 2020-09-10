class Solution {
    // Solution 1: use array
    public List<Integer> findAnagrams(String s, String p) {
        int[] counter = new int[26];
        for (int i = 0; i < p.length(); ++i) {
            counter[p.charAt(i) - 'a']++;
        }
        int left = 0, right = 0;
        int count = p.length();
        List<Integer> result = new ArrayList<>();
        while (right < p.length()) {
            if (right - left == p.length()) {
                count++;
                counter[p.charAt(left++) - 'a']++;
            }
            if (counter[p.charAt(right++) - 'a']-- > 0) {
                count--;
            }
            if (count == 0) {
                result.add(left);
            }

        }
        return result;
    }
    // Time complexity: O(m + n)
    // Space complexity: O(26)

    // Solution 2: hash table
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        HashMap<Character, Integer> set = new HashMap<>();
        for (int i = 0; i < p.length(); ++i) {
            int k = set.getOrDefault(p.charAt(i), 0);
            set.put(p.charAt(i), k + 1);
        }
        int left = 0, right = 0;
        int count = set.size();
        Integer k;
        while (right < s.length()) {
            if (right - left == p.length()) {
                k = set.get(s.charAt(left));
                if (k != null) {
                    set.put(s.charAt(left), k + 1);
                    if (k + 1 == 1) {
                        count++;
                    }
                }
                left++;
            }
         
            k = set.get(s.charAt(right));
         
            if (k != null) {
                set.put(s.charAt(right), k - 1);
                if (k - 1 == 0) {
                    count--;
                }
            }
         
            if (count == 0) {
                result.add(left);
            }
         
            right++;
        }
        return result;
    }

    // Time complexity: O(m + n)
    // Space complexity: O(smals.length)
}