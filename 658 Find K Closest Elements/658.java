// Binary search and two pointers
// the original array has been sorted, so we can take this advantage by following steps
// Step1: if the target is less or equal than the first element in array
//      so we return the first k elements 
// Step2: Similarly[ˈsɪmələ(r)li], if the target is more or equal than the last element in array
//      we return the last k elements
// Step3: Otherwise, we can do the binary search to find the index of the element, which is equal or little bit larger than target
//      then set low to its left k-1 position, and high to the right k-1 position
//      of this index as a start
//      the desired[dɪ'zaɪrd] k numbers must in this rang [index-k+1, index+k-1]
//      so we can shrink[ʃrɪŋk] this range to get the result using following rules
//          1. if low reaches the lowest index 0 or the low element is closer to target than the high element
//              decrease the high index
//          2. if the high reaches the highest index array,size()-1, or it is nearer to the target than low element
//              increase the low index
//          3. the looping end when there are k elements in [low, high]
//      the k elements are in rang of [low, high]

public class Solution {
	public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
		int n = arr.size();
		if (x <= arr.get(0)) {
			return arr.subList(0, k);
		} else if (arr.get(n - 1) <= x) {
			return arr.subList(n - k, n);
		} else {

			int index = Collections.binarySearch(arr, x);
			if (index < 0) {
                index = -index - 1;
            }

			int low = Math.max(0, index - k + 1), high = Math.min(arr.size() - 1, index + k - 1);

			while (high - low > k - 1) {
				if (low < 0 || (x - arr.get(low)) <= (arr.get(high) - x))
					high--;
				else if (high > arr.size() - 1 || (x - arr.get(low)) > (arr.get(high) - x))
					low++;
				else
					System.out.println("unhandled case: " + low + " " + high);
			}
			return arr.subList(low, high + 1);
		}
	}
}
/* 
Time complexity : O(log(n)+k). 
O(log(n)) is for the time of binary search, while O(k) is for shrinking the index range to k elements.

Space complexity : O(k). 
It is to generate the required sublist. 
*/

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<Integer>();
        
        if (arr == null || arr.length == 0) {
            return res;
        }
        if (k == 0) {
            return res;
        }

        List<Integer> newArr = Arrays.stream(arr)
                                    .boxed()
                                    .collect(Collectors.toList());
        int n = newArr.size();
        if (x <= newArr.get(0)) {
            return newArr.subList(0, k);
        } else if (x >= newArr.get(n - 1)) {
            return newArr.subList(n - k, n);
        }

        int left = largestSmaller(x, newArr);
        int right = left + 1;
        int low = Math.max(0, left - k + 1);
        int high = Math.min(left + k, n - 1);

        while (high - low + 1 > k) {
            if (newArr.get(high) - x > x - newArr.get(low)) {
                high--;
            } else if (newArr.get(high) - x < x - newArr.get(low)) {
                low++;
            }
        }

        return newArr.subList(low, high + 1);
    }
    
    private int largestSmaller(int target, List<Integer> array) {
        int left = 0;
        int right = array.size() - 1;
        while (left < right - 1) {
            int mid = left + (right-left) / 2;
            if (array.get(mid) <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (array.get(right) <= target) {
            return right;
        }
        if (array.get(left) <= target) {
            return left;
        }

        return -1;
    }

}