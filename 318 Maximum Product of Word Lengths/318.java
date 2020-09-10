class Solution {
    public int maxProduct(String[] words) {
        if (words == null || words.length < 2) {
            return 0;
        }
        int largest = 0;
        Arrays.sort(words, (a, b) -> {
            if (a.length() == b.length()) {
                return 0;
            }
            return a.length() - b.length() < 0 ? 1 : -1;
        });
        int n = words.length;
        Map<String, Integer> stringMap = new HashMap<>();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int aMask = stringMap.computeIfAbsent(words[i], this::getBitMask);
                int bMask = stringMap.computeIfAbsent(words[j], this::getBitMask);
                int product = words[i].length() * words[j].length();
                if (product < largest) {
                    break;
                }
                if ((aMask & bMask) == 0) {
                    largest = words[i].length() * words[j].length();
                }

            }
        }
        return largest;
    }

    private int getBitMask(String k) {
        int mask = 0;
        int n = k.length();
        for (int i = 0; i < n; ++i) {
            mask |=  (1 << (k.charAt(i) - 'a'));
        }
        return mask;
    }

    // Time complexity: O(n*k + n*log(n) + n^2)
    // Space complexity: O(n)

    // Take-away: When there is only a limited number of possible chars in each
    // word, bit mask can save time on checking whether two words have the same
    // chars in common.
}