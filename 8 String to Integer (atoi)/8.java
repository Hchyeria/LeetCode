class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        int n = str.length();
        int i = 0;
        while (i < n && str.charAt(i) == ' ') {
            i++;
        }
        
        int sign = 1, base = 0;
       
        if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
            sign = str.charAt(i) == '+' ? 1 : -1;
            i++;
        }
        while (i < n && isDigit(str.charAt(i))) {
            if (base > Integer.MAX_VALUE / 10 
                || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = 10 * base + (str.charAt(i++) - '0');
        }
        
    
        return sign * base;
    }
    
    private boolean isDigit(char i) {
        return i >= '0' && i <= '9';
    }

    // Time = O(n)
    // Space = O(1)
}