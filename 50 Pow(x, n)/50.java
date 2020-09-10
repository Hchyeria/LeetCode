class Solution {
    public double myPow(double x, int n) {
        if (n == 1) {
            return x;
        } else if (n == 0) {
            return 1;
        } else if (n == -1){
            return 1.0 / x;
        } else {
            // when n < 0 can't write myPow(1.0 / x, -n);
            // this case will not pass
            // 1.00000
            // -2147483648
            // If n is Integer.MIN_VALUE, the code will have overflow runtime error
            // because INT_MIN is -2147483648 but INT_MAX is 2147483647 ,so n = -n is failed!!

            // if you not store the halfResult
            // return power(a, b / 2) * power(a, b / 2)
            // there will still cause more time to do redundant recursion
            double res = myPow(x, n / 2);
            if (n % 2 == 0) {
                return res * res;
            } else{
                if (n > 0) {
                    return res * res * x;
                } else {
                    return res * res * (1.0 / x);
                }
                
            }
        }
        
    }

    // Time Complexity: O(log(n))
    // Space Complexity: O(1)
}
