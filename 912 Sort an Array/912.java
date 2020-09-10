class Solution {
    // heap sort
    // Time = O( n(heapify) + log(n)*n )
    // Space = O(1)
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        
        // heapify
        for (int i = n / 2 - 1; i >= 0; --i) {
            percolateDown(nums, i, n);
        }
        // sort
        while (n > 1) {
            swap(nums, 0, n - 1);
            percolateDown(nums, 0, --n);
        }
        return nums;
    }
    
    // max heap
    private void percolateDown(int[] nums, int index, int size) {
        while (index <= size / 2 - 1) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int swapIndex = left;
            if (right < size && nums[right] > nums[left]) {
                swapIndex = right;
            }
            if (nums[swapIndex] > nums[index]) {
                swap(nums, swapIndex, index);
                index = swapIndex;
            } else {
                break;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}