
// Intuitively, we can sort the elements in list by their absolute difference values to the target
// then the sublist of the first Kth elements is the result after sorting thr elements by the natural order


class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // convert int[] to List<Integer> for better implementation simplicity
        List<Integer> nums = Arrays.stream(arr)
                                    .boxed()
                                    .collect(Collectors.toList());
        // sort array according to distance to x, if diff same, pick smaller num first
        Collections.sort(nums, (a, b) -> Math.abs(a - x) != Math.abs(b - x) ? Math.abs(a - x) - Math.abs(b - x) : a - b);
        // get first k elem
        nums= nums.subList(0, k);
        // sort elems back to their original order
        Collections.sort(nums);
        return nums;
    }
}

/* 
Time complexity : O(n*log n).
Collections.sort() uses binary sort so it has a  O(n*log n) complexity.

Space complexity : O(k).
The in-place sorting does not consume any extra space. 
However, generating a k length sublist will take some space. 
*/


// another solution
// 

class Solution{    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
            //-------- Main idea behind the binary search algorithm ----------//
            // 1) res will be a consecutive subarray of k size
            // 2) say if we need to pick 4 elems, now we r looking at 5 elem n1, n2, n3, n4, n5
            //    we need to compare two ends: diff(x, n1) and diff(x, n5), the number with bigger diff on the end will be eliminated [ɪˈlɪməˌnet]
            //----------------------------------------------------------------//
            // lo and high: range of all possible start of subarray with size k + 1, so we could compare both ends

            int lo = 0, hi = arr.length - k - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                // for subarray starting at mid with size k+1, we compare element of two ends to eliminate the loser
                if (Math.abs(x - arr[mid]) > Math.abs(x - arr[mid+k])) {
                    lo = mid + 1; // arr[mid] is the one further away from x, eliminate range[lo, mid]
                } else {
                    hi = mid - 1; // arr[mid+k] is the one further away, all [mid, hi] will have similar situation, eliminate them
                }                
            }     
            // return subarray
            List<Integer> res = new ArrayList(k);
            for (int i = 0; i < k; i++) {
                res.add(arr[lo + i]);
            }
            return res;
        }
    }