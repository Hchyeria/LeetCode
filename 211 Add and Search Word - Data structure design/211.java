class WordDictionary {
    public static class TrieNode {
        TrieNode[] children;
        boolean isWord;
        TrieNode() {
            children = new TrieNode[26];
        }
    }
    
    private TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            int index = word.charAt(i) - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        return match(root, word, 0);
    }
    
    private boolean match(TrieNode node, String word, int index) {
        if (index == word.length()) {
            if (node.isWord) {
                return true;
            }
            return false;
        }
        char c = word.charAt(index);
        if (c == '.') {
            for (TrieNode child : node.children) {
                if (child != null) {
                    if (match(child, word, index + 1)) {
                        return true;
                    }
                }
            }
        } else if (node.children[c - 'a'] != null) {
            return match(node.children[c - 'a'], word, index + 1);
        }
        return false;
    }
    // Time = O(26^l)
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */