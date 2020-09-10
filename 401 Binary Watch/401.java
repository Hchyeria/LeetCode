class Solution {
    
    private int hour = 4;
    private int minute = 6;
    
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        if (num <= 0) {
            res.add("0:00");
            return res;
        }
        for (int i = 0; i <= num; ++i) {
            List<Integer> h = new ArrayList<>();
            List<Integer> m = new ArrayList<>();
            dfs(h, 0, i, hour, 0);
            dfs(m, 0, num - i, minute, 0);
            
            for (int hv : h) {
                // need to check valid
                if (hv > 11) continue;
                for (int mv : m) {
                    if (mv > 59) continue;
                    res.add(convert(hv, mv));
                }
            }
        }
        return res;
    }
    
    private void dfs(List<Integer> res, int sum, int num, int len, int index) {
        if (num == 0) {
            res.add(sum);
            return;
        }
        if (index == len) return;
        
        dfs(res, sum + (1 << index), num - 1, len, index + 1);
        dfs(res, sum, num, len, index + 1);
    }
    
    private String convert(int h, int m) {
        StringBuilder sb = new StringBuilder();
        sb.append(h).append(":");
        if (m < 10) {
            sb.append("0");
        }
        sb.append(m);
        return sb.toString();
    }

    // Time = O(num * 2^num)
    // Space = O(num) call-stack, List<Integer> h, m
}