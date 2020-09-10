class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        // What should we return when needle is an empty string?
        // This is a great question to ask during an interview
        // For the purpose of this problem, we will return 0 when needle is an empty string
        // This is consistent to C's strstr() and Java's indexOf()
        if (needle.equals("")) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); ++i) {
            if (equal(haystack, needle, i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean equal(String haystack, String needle, int index) {
        for (int i = index; i < index + needle.length(); ++i) {
            if (haystack.charAt(i) != needle.charAt(i - index)) {
                return false;
            }
        }
        return true;
    }

    // Time Complexity: O(m * n)
    // Space Complexity: O(1)

    // Solution 2: Rabin-Karp algorithm
    public int strstr2(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        // String compare can't use ==
        // should use .equals()
        // == compare their reference or address location
        if (needle.equals("")) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        
        int largePrime = 101;
        int prime = 31;
        int seed = 1;
        int targetHash = needle.charAt(0) % largePrime;
        for (int i = 1; i < needle.length(); ++i) {
            seed = moduleHash(seed, 0, prime, largePrime);
            targetHash = moduleHash(targetHash, needle.charAt(i), prime, largePrime);
        }
        int hash = 0;
        for (int i = 0; i < needle.length(); ++i) {
            hash = moduleHash(hash, haystack.charAt(i), prime, largePrime);
        }
        if (hash == targetHash && equal(haystack, needle, 0)) {
            return 0;
        }
        for (int i = 1; i <= haystack.length() - needle.length(); ++i) {
            hash = nonNegative(hash - seed * haystack.charAt(i - 1) % largePrime, largePrime);
            hash = moduleHash(hash, haystack.charAt(i + needle.length() - 1), prime, largePrime);
            if (hash == targetHash && equal(haystack, needle, i)) {
                return i;
            }
        }
        return -1;
    }
    private int moduleHash(int hash, int addition, int prime, int largePrime) {
        return (hash * prime % largePrime + addition) % largePrime;
    }

    private int nonNegative(int hash, int largePrime) {
        if (hash < 0) {
            hash += largePrime;
        }
        return hash;
    }

    // Time Complexity: O(m + n)
    // Space complexity: O(1)

    // Solution 3: KMP algorithm
    public int strStr3(String large, String small) {
        if (large == null || small == null) {
            return -1;
        }
        if (small.length() > large.length()) {
            return -1;
        }
        if (small.equals("")) {
            return 0;
        }
        char[] in = small.toCharArray();
        int[] kmp = buildKmp(in);
        int i = 0, j = 0;
        while (i < large.length() && j < in.length) {
            if (large.charAt(i) == in[j]) {
                j++;
                i++;
            } else if (j == 0){
                i++;
            } else {
                // kmp[k -1] not the kmp[k] !!!
                j = kmp[j - 1];
            }
        }
        if (j == in.length) {
            return i - in.length;
        }
        return -1;
    }

    private int[] buildKmp(char[] small) {
        int[] temp = new int[small.length];
        if (small.length <= 1) {
            return temp;
        }
        int i = 0, j = 1;
        while (j < small.length) {
            if (small[i] == small[j]) {
                temp[j++] = ++i;
            } else {
                // when you want to write a nested while loop
                // you should consider avoid to to this
                // because it may cause the repeated code logic
                if (i == 0) {
                    temp[j] = 0;
                    j++;
                } else {
                    i = temp[i - 1];
                }
            }
        }

        return temp;
    }
    // Time Complexity: O(large.length)
    // Space complexity: O(small.length) build the kmp search array


}