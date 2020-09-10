// LintCode
// 586 Sqrt(x) II
public class Solution {
    /**
     * @param x a double
     * @return the square root of x
     */
    public double sqrt(double x) {
        double start = 0.0;
        double end = x;
        double exp = 1e-12;

        if (x < 0.1) {
            end = 0.1;
        }

        while (end - start > exp) {
            double mid = start + (end  - start) / 2.0;
            if (mid * mid < x) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return start;

    }
}