class Solution {

    // wrong solution
    // [3, 2, 4], 6 will return [0, 0] that is not our expectation
    public int[] twoSumWrong(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            map.put(nums[i], i);
        }
        
        int i = 0;
        for (int num : nums) {
            Integer other = map.get(target - num);
            if (other != null) {
                return new int[] {i, other};
            }
            i++;
        }
        return new int[] {-1, -1};
    }


    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            Integer index = map.get(nums[i]);
            if (index != null) {
                return new int[]{ index, i };
            } else {
                // will can not pick itself
                map.put(target - nums[i], i);
            }
        } 
        return new int[] { -1, -1 };
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)

    // heap sort + two pointer
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // but it will change the indices of elements
    // if we are asked to return indices, it doesn't work
    public int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        heapSort(nums, n);
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[] { nums[left], nums[right] };
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] { -1, -1 };
    }
    
    private void heapSort(int[] array, int len) {
        for (int i = len / 2 - 1; i>= 0; --i) {
            percolateDown(array, len, i);
        }
        while (len > 0) {
            swap(array, 0, len - 1);
            percolateDown(array, --len, 0);
        }
    }
    
    private void percolateDown(int[] array, int size, int index) {
        while (index <= size / 2 - 1) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int swapChild = left;
            if (right < size && array[right] > array[left]) {
                swapChild = right;
            }
            if (array[swapChild] > array[index]) {
                swap(array, swapChild, index);
            } else {
                break;
            }
            index = swapChild;
        }
    }
    
    private void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}