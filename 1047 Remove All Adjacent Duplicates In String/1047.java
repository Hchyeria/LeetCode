class Solution {
    public String removeDuplicates(String S) {
        if (S == null || S.length() <= 1) {
            return null;
        }
        char[] array = S.toCharArray();
        int slow = 1;
        int fast = 1;
        for ( ; fast < array.length; ++fast) {
            if (slow == 0 || (slow >= 1 && array[fast] != array[slow - 1])) {
                array[slow++] = array[fast];
            } else {
                slow--;
            }
        }
        return new String(array, 0, slow);
    }

    // Time complexity: O(n)
    // Space complexity: O(n), toCharArray()
}