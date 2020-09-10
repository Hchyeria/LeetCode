class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        } 
        int n = ratings.length;
        int[] candy = new int[n];
        Arrays.fill(candy, 1);
        for (int i = 1; i < n; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        
        for (int i = n - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i  + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }
        
        int count = 0;
        for (int i : candy) {
            count += i;
        }
        
        return count;
    }

    // Time = O(n)
    // Space = O(n)

    // Solution 2: one pass O(1) space
    // https://leetcode.com/problems/candy/discuss/135698/Simple-solution-with-one-pass-using-O(1)-space
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        } 
        int n = ratings.length;
        int up = 0, down = 0, peek = 0;
        int res = 1;
        for (int i = 1; i < n; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                up++;
                down = 0;
                peek = up;
                res += 1 + up;
            } else if (ratings[i] == ratings[i - 1]) {
                up = down = peek = 0;
                res += 1;
            } else {
                down++;
                up = 0;
                res += down;
                // lazy update peek
                if (peek < down) {
                    res += 1;
                }
            }
        }
        return res;
    }
    // Time = O(n)
    // Space = O(1)
}