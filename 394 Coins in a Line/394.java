// 394. Coins in a Line
// LintCode

public class Solution {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        // write your code here
        if (n == 0) {
            return false;
        }
        
        if (n == 1 || n == 2) {
            return true;
        }
        
        boolean pre = true, old = true, now = true;
        
        for (int i = 3; i <= n; ++i) {
            now = !old || !pre;
            pre = old;
            old = now;
        } 
        
        return now;
    }

    // Time = O(n)
    // Space = O(1)
}