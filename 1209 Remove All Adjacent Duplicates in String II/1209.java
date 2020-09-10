class Solution {
    // Solution 1: bad idea
    public String removeDuplicates(String s, int k) {
        if (s == null || s.length() <= 1) {
            return null;
        }
        char[] array = s.toCharArray();
        int slow = 0;
        int fast = 0;
        int count = 0;
        while (fast < array.length) {
            int begin = fast;
            while (fast < array.length && array[begin] == array[fast] 
                   && fast - begin < k) {
                fast++;
            }
            int range = fast - begin;
            if (range == k) {
                continue;
            }
            if (slow >= 1 && array[slow - 1] == array[begin]) {
                if (range + count < k) {
                    Arrays.fill(array, slow, slow + range, array[begin]);
                    slow += range;
                    count += range;
                } else if (range + count >= k) {
                    int up = range + count - k;
                    slow -= count - up;
                    count = updateCount(array, slow - 1);
                }
            } else {
                Arrays.fill(array, slow, slow + range, array[begin]);
                slow += range;
                count = range;
                
            }
            
        }
        return new String(array, 0, slow);
    }
    
    private int updateCount(char[] array, int index) {
        int count = 0;
        int right = index;
        while (right >= 0 && array[index] == array[right]) {
            right--;
            count++;
        }
        return count;
    }
    
    // Time Complexity: O(n * k) worse case, O(n) best case
    // Space Complexity: O(n), toCharArray()

    // Solution 2: use a array to store count
    public String removeDuplicates2(String s, int k) {
        if (s == null || s.length() <= 1) {
            return null;
        }
        char[] array = s.toCharArray();
        int slow = 0;
        int fast = 0;
        int n = array.length;
        int[] count = new int[n];
        while (fast < n) {
            count[slow] = slow > 0 && array[fast] == array[slow - 1] ? count[slow - 1] + 1 : 1;
            if (count[slow] == k) {
                slow -= k - 1;
                fast++;
                continue;
            }
            array[slow++] = array[fast++];
            
        }
        return new String(array, 0, slow);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n + n), toCharArray() + count
}
