class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] in = s.toCharArray();
        int slow = 0, fast = 0;
        for ( ; fast < in.length; ++fast) {
            if (in[fast] != ' ' && (fast == 0 || in[fast - 1] == ' ')) {
                slow = fast;
            }
            if (in[fast] != ' ' && (fast == in.length - 1 || in[fast + 1] == ' ')) {
                reverse(in, slow, fast);
            }
        }
        return new String(in);
    }
    
    private void reverse(char[] in, int left, int right) {
        while (left < right) {
            char temp = in[left];
            in[left] = in[right];
            in[right] = temp;
            left++;
            right--;
        } 
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
}