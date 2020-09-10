class Solution {
    public String reverseWords(String s) {
        if (s == null) {
            return s;
        } 
        char[] in = s.toCharArray();
        int length = clearSpace(in);
        int i = 0, j = 0;
        while (j < length) {
            if (j > 0 && in[j - 1] == ' ') {
                i = j;
            } else if (j == length - 1 || in[j + 1] == ' ') {
                reverse(in, i, j);
            }
            j++;
        }
        reverse(in, 0, length - 1);
        return new String(in, 0, length);
    }
    
    private int clearSpace(char[] input) {
        int i = 0, j = 0;
        while (j < input.length) {
            if (input[j] != ' ' || (i > 0 && input[i - 1] != ' ')) {
                input[i++] = input[j++];
            } else {
                j++;
            }
        }
        if (i > 0 && input[i - 1] == ' ') {
            i--;
        }
        return i;
    }
    
    private void reverse(char[] input, int left, int right) {
        while (left < right) {
            char temp = input[left];
            input[left++] = input[right];
            input[right--] = temp;
        }
    }

    // Time Complexity: O(3n) -> O(n)
    // Space Complexity: O(n)
}