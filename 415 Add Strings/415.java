class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int m = num1.length() - 1;
        int n = num2.length() - 1;
        int add = 0;
        while (m >= 0 || n >= 0) {
            int one = 0;
            int two = 0;
            if (m >= 0) {
                one = num1.charAt(m) - '0';
            }
            if (n >= 0) {
                two = num2.charAt(n) - '0';
            }
            int sum = one + two + add;
            sb.insert(0, sum % 10);
            add = sum / 10;
            m--;
            n--;
        }
        if (add == 1) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }
    // Time = O(n)
    // Space = O(1)
}