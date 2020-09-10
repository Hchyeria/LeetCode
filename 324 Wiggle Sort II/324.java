class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int n = nums.length;
        int[] temp = Arrays.copyOfRange(nums, 0, n);
        // find n / 2, not (n - 1) / 2
        int midVal = findMid(temp, 0, n - 1, n / 2);
        int[] ans = new int[n];
        Arrays.fill(ans, midVal);

        int left = 1, right = (n % 2 == 0) ? n - 2 : n - 1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < midVal) {
                ans[right] = nums[i];
                right -= 2;
            } else if (nums[i] > midVal) {
                ans[left] = nums[i];
                left += 2;
            }
        }

        System.arraycopy(ans, 0, nums, 0, n);
    }

    private int findMid(int[] nums, int left, int right, int k) {
        if (left >= right) {
            return nums[left];
        }
        int index = partition(nums, left, right);

        if (index < k) {
            return findMid(nums, index + 1, right, k);
        } else if (index > k) {
            return findMid(nums, left, index - 1, k);
        }
        return nums[index];
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = left + (int) (Math.random() * (right - left + 1));
        swap(nums, pivot, right);

        int l = left, r = right - 1;
        while (l <= r) {
            if (nums[l] < nums[right]) {
                l++;
            } else if (nums[r] >= nums[right]) {
                r--;
            } else {
                swap(nums, l++, r--);
            }
        }
        swap(nums, l, right);
        return l;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    // Time = O(n)
    // Space = O(n + log(n))
}