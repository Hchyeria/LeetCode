class Solution {
    public String reverseStr(String s, int k) {
        if (s == null || s.length() <= 1 || k <= 1) {
            return s;
        }
        char[] in = s.toCharArray();
        if (k >= s.length()) {
            reverse(in, 0, in.length - 1);
        } else {
            int i = 0;
            for ( ; i + k - 1 < in.length; i += 2 * k) {
                reverse(in, i , i + k - 1);
            }
            if (k < in.length) {
                reverse(in, i , in.length - 1);
            }
        }
        return new String(in, 0, in.length);
    }
    
    private void reverse(char[] input, int left, int right) {
        while (left < right) {
            char temp = input[left];
            input[left++] = input[right];
            input[right--] = temp;
        }
    }
    // Time Complexity: O(n/2k * k)
    // Space Complexity: O(n)
}
