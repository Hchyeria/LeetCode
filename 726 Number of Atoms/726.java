class Solution {
    // Solution 1: recursion way
    public String countOfAtoms(String formula) {
        TreeMap<String, Integer> map = helper(formula);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey());
            int v = entry.getValue();
            if (v > 1) sb.append(v);
        }
        return sb.toString();
    }

    private TreeMap<String, Integer> helper(String s) {
        TreeMap<String, Integer> res = new TreeMap<>();
        int n = s.length();
        char[] input = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < n) {
            char c = input[i];
            if (isUpper(c)) {
                sb.setLength(0);
                sb.append(c);
                i++;
                while (i < n && isLower(input[i])) {
                    sb.append(input[i++]);
                }
                int num = 1;
                // remember to check boundary when you want to input[i]
                if (i < n && isDigit(input[i])) {
                    int[] getSum = computeSum(input, i);
                    num = getSum[1];
                    i = getSum[0];
                }
                res.put(sb.toString(), num);
            } else if (c == '(') {
                int left = 1;
                i++;
                int l = i;
                while (left != 0) {
                    char j = input[i++];
                    if (j == ')') left--;
                    else if (j == '(') left++;
                }
                int r = i - 2;
                int num = 1;
                if (isDigit(input[i])) {
                    int[] getSum = computeSum(input, i);
                    num = getSum[1];
                    i = getSum[0];
                }
                TreeMap<String, Integer> map = helper(new String(input,
                        l, r - l + 1));
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    String k = entry.getKey();
                    int v = entry.getValue();
                    res.put(k, res.getOrDefault(k, 0) + v * num);
                }
            }
        }
        return res;
    }

    // Time = O(n)
    // Space = O(n), worse case

    private boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private int[] computeSum(char[] input, int i) {
        int[] res = new int[2];
        int num = 0;
        int n = input.length;
        while (i < n && isDigit(input[i])) {
            num = num * 10 + (input[i++] - '0');
        }
        res[0] = i;
        res[1] = num;
        return res;
    }

    // Solution 2: iterative way
    public String countOfAtoms2(String formula) {
        if (formula == null || formula.length() <= 0) {
            return "";
        }
        TreeMap<String, Integer> map = new TreeMap<>();
        int n = formula.length();
        char[] input = formula.toCharArray();
        StringBuilder sb = new StringBuilder();
        Deque<TreeMap<String, Integer>> stack = new LinkedList<>();
        int i = 0;
        while (i < n) {
            char c = input[i];
            if (c == '(') {
                stack.offerFirst(map);
                map = new TreeMap<>();
                // remember to increase i!
                i++;
            } else if (c ==')') {
                TreeMap<String, Integer> tmp = map;
                map = stack.pollFirst();
                i++;
                int num = 1;
                if (i < n && isDigit(input[i])) {
                    int[] getSum = computeSum(input, i);
                    num = getSum[1];
                    i = getSum[0];
                }
                for (Map.Entry<String, Integer> entry : tmp.entrySet()) {
                    String k = entry.getKey();
                    int v = entry.getValue();
                    map.put(k, map.getOrDefault(k, 0) + v * num);
                }
            } else if (isUpper(c)) {
                sb.setLength(0);
                sb.append(c);
                i++;
                while (i < n && isLower(input[i])) {
                    sb.append(input[i++]);
                }
                int num = 1;
                if (i < n && isDigit(input[i])) {
                    int[] getSum = computeSum(input, i);
                    num = getSum[1];
                    i = getSum[0];
                }
                String tmp = sb.toString();
                map.put(tmp, map.getOrDefault(tmp, 0) + num);
            }
        }

        sb.setLength(0);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey());
            int v = entry.getValue();
            if (v > 1) sb.append(v);
        }
        return sb.toString();
    }
    
    // Time = O(n)
    // Space = O(n)
}