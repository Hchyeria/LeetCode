class Solution {
    private int[] array;
    public Solution(int[] nums) {
        array = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return array;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] temp = array.clone();
        for (int i = temp.length - 1; i >= 0; --i) {
            // Java Random.nextInt(n) return integer in [0, n) - uniformly distributed [0, n-1]
            int index = (int)(Math.random() * (i + 1));
            swap(temp, i, index);
        }
        return temp;
    }
    
    private void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */