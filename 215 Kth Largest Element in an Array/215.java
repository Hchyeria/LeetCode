// You may assume k is always valid, 1 ≤ k ≤ array's length.
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (k == nums.length) {
            // descend order
            return Arrays.stream(nums).min().getAsInt();
        }
        return helper(nums, 0, nums.length - 1, k - 1);
    }

    private int helper(int[] array, int start, int end, int k) {
        if (start >= end) {
            return array[k];
        }
        int left = partition(array, start, end);
        if (left < k) {
            return helper(array, left + 1, end, k);
        } else if (left > k) {
            return helper(array, start, left - 1, k);
        } else {
            return array[left];
        }
    }

    private int partition(int[] array, int start, int end) {
        int p = start + (int)(Math.random() * (end - start + 1));
        int pivot = array[p];
        swap(array, p, end);
        int left = start, right = end - 1;
        // the terminate condition is <= not <
        // think about the case the all rest elements of the array are larger than pivot
        while (left <= right) {
            if (array[left] > pivot) {
                left++;
            } else if (array[right] <= pivot) {
                right--;
            } else {
                swap(array, left++, right--);
            }
        }
        swap(array, left, end);
        return left;
    }

    private void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Time complexity is O(n), average time, if array is randomly shuffled
    // O(n^2) worse case
	// Space complexity O(log(n)), if array is randomly shuffled
}