class Solution {
    public char findTheDifference(String s, String t) {
        int x = 0;
        
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            x ^= (s.charAt(i) - 'a') ^ (t.charAt(i) - 'a');
        }
        x ^= t.charAt(n) - 'a';
        
        return (char) (x + 'a');
    }

    // Time = O(n)
    // Space = O(1)
}