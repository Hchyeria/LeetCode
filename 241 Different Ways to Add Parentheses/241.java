class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if (input == null || input.isEmpty()) {
            return res;
        }
        
        return helper(input, new HashMap<>());
    }
    
    private List<Integer> helper(String s, Map<String, List<Integer>> map) {
        List<Integer> res = map.get(s);
        if (res != null) {
            return res;
        }
        res = new ArrayList<>();
        
        char[] input = s.toCharArray();
        int n = input.length;
        for (int i = 0; i < n; ++i) {
            char c = input[i];
            if (c == '+' 
                || c == '-' 
                || c == '*') {
                List<Integer> left = helper(new String(input, 0, i), map);
                List<Integer> right = helper(new String(input, i + 1, n - 1 - i), map);
                for (int l : left) {
                    for (int r : right) {
                        res.add(calc(c, l, r));
                    }
                }
            }
        }
        
        if (res.isEmpty()) {
            try {
                res.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                res.add(0);
            }
        }
        map.put(s, res);
        return res;
    }
    
    private int calc(char c, int l, int r) {
        switch (c) {
            case '+': 
                return l + r;
            case '-':
                return l - r;
            case '*':
                return l * r;
            default:
                return 0;
                
        }
    }
    // Time = O(n^2)
    // Space = O(n)
}