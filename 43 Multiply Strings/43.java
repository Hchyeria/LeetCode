class Solution {
    public String multiply(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int m = num1.length();
        int n = num2.length();
        int[] res = new int[m + n];
        
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                int p = i + j;
                int q = i + j + 1;
                long mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                long sum = mul + res[q];
                res[p] += (int) sum / 10;
                res[q] = (int) sum % 10;
            }
        }
        
        for (int i : res) {
            if (sb.length() == 0 && i == 0) {
                continue;
            }
            sb.append(i);
        }
        // remember to check
        return sb.length() == 0 ? "0" : sb.toString();
    }
    // Time = O(m * n)
    // Space = O(1)
}