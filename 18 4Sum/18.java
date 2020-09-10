import java.util.List;

class Solution {
    private static class Pair {
        int left;
        int lIndex;
        int right;
        int rIndex;
        Pair(int left, int l, int right, int r) {
            this.left = left;
            this.right = right;
            lIndex = l;
            rIndex = r;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Pair) || getClass() != o.getClass()) {
                return false;
            }
            Pair pair = (Pair) o;
            return left == pair.left && right == pair.right;
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }
    }

    // use the set to deduplicate
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> resSet = new HashSet<>();
        Map<Integer, Set<Pair>> pairMap = new HashMap<>();
        int n = nums.length;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < j; ++i) {
                int sum = nums[i] + nums[j];
                Set<Pair> other = pairMap.get(target - sum);

                if (other != null) {
                    for (Pair pair : other) {
                        if (pair.rIndex < i) {
                            resSet.add(Arrays.asList(pair.left, pair.right, nums[i], nums[j]));
                        }
                    }
                }
                pairMap.computeIfAbsent(sum, key -> new HashSet<>())
                    .add(new Pair(nums[i], i, nums[j], j));
            }
        }
        return new ArrayList<>(resSet);
    }

    // Time Complexity: O(n^2*k), the more distinct pair sums we get, the smaller k will be.
    // On average k will be some constant between 1 and n for normal elements distribution in the original 1D array.
    // So on average our algorithm will go in O(n^2) but with worst case of O(n^3).
    // Space Complexity: O(n^2 + n)

    // Solution 2: recursion, reduce to K-1 sum
    // Time Complexity: O(n^3)
    // Space Complexity: O(call-stack)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        kSum(res, new ArrayList<>(), nums, 0, nums.length - 1, 4, target);
        return res;
    }

    private void kSum(List<List<Integer>> res, List<Integer> cur, int[] array, int left, int right, int k, int target) {
        if (right - left + 1 < k || k < 2) {
            return;
        }
        if (k == 2) {
            int l = left;
            int r = right;
            while (l < r) {
                int sum = array[l] + array[r];
                if (sum == target) {

                    cur.add(array[l++]);
                    cur.add(array[r--]);
                    res.add(new ArrayList<>(cur));
                    cur.remove(cur.size() - 1);
                    cur.remove(cur.size() - 1);
                    while (l > left && l < r && array[l - 1] == array[l]) {
                        l++;
                    }
                    while (r < right && r > l && array[r + 1] == array[r]) {
                        r--;
                    }
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
            return;
        }
        int pre = -1;
        for (int i = left; i <= right; ++i) {
            if (pre == -1 || array[i] != array[pre]) {
                cur.add(array[i]);
                kSum(res, cur, array, i + 1, right, k - 1, target - array[i]);
                cur.remove(cur.size() - 1);
                pre = i;
            }
            
        }
    }
}