// LintCode 637. Valid Word Abbreviation

class Solution {
    // Solution 1: recursion way
    boolean validWordAbbreviation(String word, String abbr) {
        if (word == null && abbr == null) {
            return true;
        } else if (word == null || abbr == null) {
            return false;
        }
        return helper(word, 0, abbr, 0);
    }

    private boolean helper(String word, int wordStart, String abbr, int abbrStart) {
        if (wordStart == word.length() && abbrStart == abbr.length()) {
            return true;
        } else if (wordStart == word.length() || abbrStart == abbr.length()) {
            return false;
        }
        // Character.isDigit()
        if (isDigital(abbr.charAt(abbrStart))) {
            if (abbr.charAt(abbrStart) == '0' ) {
                return false;
            }
            int num = 0;
            while (abbrStart < abbr.length() && isDigital(abbr.charAt(abbrStart))) {
                // Character.getNumericValue()
                num = num * 10 + (abbr.charAt(abbrStart++) - '0');
            }
            return wordStart + num <= word.length() && helper(word, wordStart + num, abbr, abbrStart);
        } else {
            if (word.charAt(wordStart) != abbr.charAt(abbrStart)) {
                return false;
            } else {
                return helper(word, wordStart + 1, abbr, abbrStart + 1);
            }
        }
    }

    private boolean isDigital(char c) {
        return c >= '0' && c <= '9';
    }
    // Time Complexity: O(min(m, n))
    // Space Complexity: O(min(m, n)), because of call-stack

    // Solution 2: iterative way
    boolean validWordAbbreviation2(String word, String abbr) {
        if (word == null && abbr == null) {
            return true;
        } else if (word == null || abbr == null) {
            return false;
        }
        int i = 0, j = 0;
        int m = word.length(), n = abbr.length();
        while (i < m && j < n) {
            if (isDigital(abbr.charAt(j))) {
                // check if abbr equal 0
                if (abbr.charAt(j) == '0') return false;
                int num = 0;
                while (j < n && isDigital(abbr.charAt(j))) {
                    num = num * 10 + (abbr.charAt(j++) - '0');
                }
                i += num;
            } else {
                if (word.charAt(i++) != abbr.charAt(j++)) {
                    return false;
                }
            }
        }
        return i == m && j == n;
    }
    // Time Complexity: O(min(m, n))
    // Space Complexity: O(1)
}