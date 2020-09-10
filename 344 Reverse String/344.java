class Solution {
    // Solution 1: iterative solution
    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        int left = 0, right = s.length - 1;
        while (left < right) {
            swap(s, left, right);
            left++;
            right--;
        }
    }
    
    private void swap(char[] array, int i, int j) {
        if (i != j) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)

    // Solution 2: recursive way
    public void reverseString2(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        helper(s, 0, s.length - 1);
    }

    private void helper(char[] in, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(in, left++, right--);
        helper(in, left, right);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
}