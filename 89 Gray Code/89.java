class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        
        for (int i = 1; i <= n; ++i) {
            int len = res.size();
            for (int j = len - 1; j >= 0; --j) {
                res.add(res.get(j) | (1 << (i - 1)));
            }
        }
        return res;
    }
}
// Time = O(2^n)
// Space = O(1)