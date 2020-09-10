// Solution 1: use two hashMap
// Time = O(k * n), WordFilter, O(n), f
// Space = O(k * n)


// Solution 2: Trie
class WordFilter {
    private static class TrieNode {
        TrieNode[] children;
        int weight;
        
        TrieNode () {
            children = new TrieNode[27]; // a-z + '{'
        }
    }
    private TrieNode root;
    public WordFilter(String[] words) {
        root = new TrieNode();
        int n = words.length;
        for (int weight = 0; weight < n; ++weight) {
            StringBuilder word = new StringBuilder(words[weight]).append('{');
            int len = word.length();
            for (int i = 0; i < len; ++i) {
                TrieNode cur = root;
                for (int j = i; j < 2 * len - 1; ++j) {
                    int index = word.charAt(j % len) - 'a';
                    if (cur.children[index] == null) {
                        cur.children[index] = new TrieNode();
                    }
                    cur = cur.children[index];
                    cur.weight = weight;
                }
            }
            
        }
    }
    // Time Complexity: O(k^2 * n)
    
    public int f(String prefix, String suffix) {
        StringBuilder sb = new StringBuilder(suffix).append('{').append(prefix);
        int len = sb.length();
        TrieNode cur = root;
        for (int i = 0; i < len; ++i) {
            int index = sb.charAt(i) - 'a';
            if (cur.children[index] == null) {
                return -1;
            }
            cur = cur.children[index];
        }
        return cur.weight;
    }
    // Time Complexity: O(k)

    // Space Complexity: use Trie will decrease the space splendid, share common suffix
    // worse case O(k * n)
}


/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */