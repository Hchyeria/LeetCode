import java.util.List;

class Solution {
    private int largestSmaller(int target, int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right-left) / 2;
            if (array[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (array[right] <= target) {
            return right;
        }
        if (array[left] <= target) {
            return left;
        }

        return -1;
    }
    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new LinkedList<>(k);
        
        if (arr == null || arr.length == 0) {
            return res;
        }
        if (k == 0) {
            return res;
        }
  
        int left = largestSmaller(x, arr);
        int right = left + 1;


        int index = 0;
        while (index++  < k) {
            if (right >= arr.length ||
                    left >= 0 && (x - arr[left]) <= (arr[right] - x)) {
                
                    res.add(0, arr[left--]);
                
            } else {
                res.add(arr[right++]);
            }
        }
        return res;
    }

}