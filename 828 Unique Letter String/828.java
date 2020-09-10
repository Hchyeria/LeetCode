import java.lang.reflect.Array;

class Solution {
    // brute force not passed
    // Time Limit Exceeded 423ms
    // Time complexity: O(n^2)
    // Space complexity: O(n)
    public int uniqueLetterStringNo(String S) {
        if (S == null) {
            return 0;
        }
        if (S.length() <= 1) {
            return S.length();
        }
        int count = 0;
        char[] input = S.toCharArray();
        for (int i = 1; i <= input.length; ++i) {
            count += countBySize(input, i);
        }

        return count;
    }

    private int countBySize(char[] input, int size) {
        int count = 0;
        for (int i = 0; i <= input.length - size; ++i) {
            count += CountAllUnique(input, i, size);
        }
        return count;

    }

    private int CountAllUnique(char[] words, int index, int size) {
        int count = 0;
        int occurChars = 0;
        int[] vec = new int[26];
        for (int i = index; i < index + size; ++i) {
            int k = words[i] - 'A';
            int bitFlag = 1 << k;
            if ((occurChars & bitFlag) != 0) {
                vec[k] = 0;
            } else {
                occurChars |= bitFlag;
                vec[k] = 1;
            }
        }

        for (int v : vec) {
                count += v;
        }
        return count;
    }
    // Solution 1: using cash position of previously visited char
    // https://leetcode.com/problems/unique-letter-string/discuss/128952/One-pass-O(N)-Straight-Forward
    public int uniqueLetterString(String S) {
        if (S == null) {
            return 0;
        }
        if (S.length() <= 1) {
            return S.length();
        }
        int[][] cash = new int[26][2];
        for (int i = 0; i < 26; ++i) {
            Arrays.fill(cash[i], -1);
        }
        int n = S.length(), res = 0;
        int mod = (int)Math.pow(10, 9) + 7;
        for (int i = 0; i < n; ++i) {
            int c = S.charAt(i) - 'A';
            res += (cash[c][1] - cash[c][0]) * (i - cash[c][1]) % mod;
            cash[c] = new int[] {cash[c][1], i};
        }

        for (int[] item : cash) {
            res += (item[1] - item[0]) * (n - item[1]) % mod;
        }
        return res % mod;
    }

    // Time complexity: O(n)
    // Space complexity: O(26*2) -> O(1)
    // if not only 25 characters, we can use HashMap to store
    // JDK 8 - computeIfAbsent
    // Map<String, List<String>> map = new HashMap<>();
    // map.computeIfAbsent(c, x-> new ArrayList<Integer>()).add(i);

    // Solution 2: two pointer, no need to cash
    public int uniqueLetterString2(String S) {
        if (S == null) {
            return 0;
        }
        if (S.length() <= 1) {
            return S.length();
        }

        long res = 0;
        int mod = (int)Math.pow(10, 9) + 7;
        for (int n = S.length(), i = 0, l = 0, r = 0; i < n; ++i) {
            char c = S.charAt(i);
            for (l = i - 1; l >= 0 && S.charAt(l) != c; --l);
            for (r = i + 1; r < n && S.charAt(r) != c; ++r);
            res += (i - l) * (r - i) % mod;
        }

        return (int)res % mod;
    }

    // This is because for each distinct character in S, the left and right search can be done in O(2n) time 
    // (i.e., all other characters will be visited at most twice). If only uppercase letters are allowed, 
    // the total number of distinct characters in S will be no more than 26, hence the algorithm will run at linear time

    // In the more general case, the time complexity of this algorithm will be O(n * k)
    // while solution 1 will run at O(n + k), where k is the size of the allowed character set
    // So for small k, naive search in both directions will actually outperform those caching the positions of previously visited characters
    // due to the fact that no additional space for the caching are needed
}