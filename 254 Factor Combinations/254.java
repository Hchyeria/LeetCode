// LintCode
// 1308. Factor Combinations


class solution {
    public List<List<Integer>> factors(int target) {
        int[] factors = findFactors(target);
        List<List<Integer>> res = new ArrayList<>();
        dfs(target, factors, res, new ArrayList<>(), 0);
        return res;
   }

   private int[] findFactors(int target) {
       Set<Integer> factors = new TreeSet<>();
       int n = (int)(Math.sqrt(target));
       for (int i = 2; i <= n; ++i) {
           if (target % i == 0) {
               factors.add(i);
               factors.add(target / i);
           }
       }

       return convert(factors);
   }

   private int[] convert(Set<Integer> list) {
       int[] res = new int[list.size()];
       int index = 0;
       for (int i : list) {
           res[index++] = i;
       }
       return res;
   }

   private void dfs(int target, int[] factors, List<List<Integer>> res, List<Integer> cur, int level) {
       if (target == 1) {
           res.add(convertRes(cur, factors));
           return;
       }
       if (level == factors.length) {
           return;
       }
       int count = (int)(Math.log(target) / Math.log(factors[level]));
       for (int i = 0; i <= count; ++i) {
           if (target % Math.pow(factors[level], i) == 0) {
               cur.add(i);
               dfs((int)(target / Math.pow(factors[level], i)), factors, res, cur, level + 1);
               cur.remove(cur.size() - 1);
           }
       }
   }

   private List<Integer> convertRes(List<Integer> cur, int[] factors) {
       List<Integer> res = new ArrayList<>();
       int index = 0;
       for (int i : cur) {
           int j = i;
           while (j-- > 0) {
               res.add(factors[index]);
           }
           index++;
       }
       return res;
   }

   // Time Complexity: 
   // O(target) to find all factors, 
   // suppose target have k factors, O(k*log(k)) to sort factors, and there are at most k layers,
   // and each node has at most log(target) branch, so O(log(target) * k)
   // Space Complexity: O(k)
}