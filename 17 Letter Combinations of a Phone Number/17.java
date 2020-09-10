class Solution {
    private static final String[] digitString = { "abc", "def", "ghi", "jkl", 
                                                 "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        int n = digits.length();
        for (int i = 0; i < n; ++i) {
            if (digits.charAt(i) < '2' || digits.charAt(i) > '9') {
                return res;
            }
        }
        
        dfs(digits, res, new StringBuilder(), 0);
        return res;
    }
    
    private void dfs(String s, List<String> res, StringBuilder cur, int level) {
        if (level == s.length()) {
            res.add(cur.toString());
            return;
        }
        String digit = digitString[s.charAt(level) - '2'];
        int n = digit.length();
        for (int i = 0; i < n; ++i) {
            cur.append(digit.charAt(i));
            dfs(s, res, cur, level + 1);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    // Time Complexity: O(4 ^ digits.length()), there are at most 4 branched on each node
    // Space Complexity: O(digits.length())
}