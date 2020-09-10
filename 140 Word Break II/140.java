class Solution {
    // Time Limit Exceeded
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        List<List<String>> res = new ArrayList<List<String>>();
        dp[0] = true;
        res.add(new ArrayList<String>());
        for (int i = 1; i <= n; ++i) {
            boolean flag = false;
            List<String> curRes = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <= i - 1; ++j) {
                String temp = s.substring(j, i);
                if (dp[j] && set.contains(temp)) {
                    flag = true;
                    List<String> getPre = res.get(j);
                    if (getPre == null || getPre.size() == 0) {
                        sb.append(temp);
                        curRes.add(sb.toString());
                        sb.setLength(0);
                    } else {
                        for (String pre :  res.get(j)) {
                            sb.append(pre).append(" ").append(temp);
                            curRes.add(sb.toString());
                            sb.setLength(0);
                        }
                    }
                }
            }
            dp[i] = flag;
            res.add(curRes);
        }
        return res.get(n);
    }
}