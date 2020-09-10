class Solution {
    public String reverseWords(char[] s) {
        if (s == null) {
            return s;
        } 
        int i = 0, j = 0;
        int length = s.length;
        while (j < length) {
            if (j > 0 && s[j - 1] == ' ') {
                i = j;
            } else if (j == length - 1 || s[j + 1] == ' ') {
                reverse(s, i, j);
            }
            j++;
        }
        reverse(s, 0, length - 1);
        return new String(s, 0, length);
    }
    
    private void reverse(char[] input, int left, int right) {
        while (left < right) {
            char temp = input[left];
            input[left++] = input[right];
            input[right--] = temp;
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}