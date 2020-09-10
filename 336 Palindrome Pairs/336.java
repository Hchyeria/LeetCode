class Solution {
    // Trie
    private static class TrieNode {
        TrieNode[] children;
        int index; // record if this trieNode is a word, means from root to this trienode can form a word in words array and the index in words array. if no word ends on this node, index is -1.
        List<Integer> palindromBelow;  // if from this letter in a word to beginning of this word can form a palindrome, add the word index in words array.
        
        TrieNode () {
            children = new TrieNode[26];
            index = -1;
            palindromBelow = new ArrayList<>();
        }
    }
    
    
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        // snaity check
        if (words == null || words.length <= 1) {
            return res;
        }
        
        int n = words.length;
        TrieNode root = new TrieNode();
        for (int i = 0; i < n; ++i) {
            String w = words[i];
            insert(w, i, root);
        }
        
        for (int i = 0; i < n; ++i) {
            findPair(root, words[i], i, res);
        }
        return res;
        
    }
    
    private void insert(String s, int index, TrieNode root) {
        TrieNode cur = root;
        int n = s.length();
        for (int i = n - 1; i >= 0; --i) {
            int branch = s.charAt(i) - 'a';
            if (cur.children[branch] == null) {
                cur.children[branch] = new TrieNode();
            }
            if (isPalindrom(s, 0, i)) {
                cur.palindromBelow.add(index);
            }
            cur = cur.children[branch];
        }
        cur.index = index;
    }
    
    private void findPair(TrieNode root, String s, int index, List<List<Integer>> res) {
        TrieNode cur = root;
        int n = s.length();
        // search part 1: compare the word to trie (the word may longer than the counterparty in trie)
        for (int i = 0; i < n; ++i) {
            if (cur.index != -1 && cur.index != index && isPalindrom(s, i, n - 1)) {
                res.add(Arrays.asList(index, cur.index));
            } 
            int branch = s.charAt(i) - 'a';
            if (cur.children[branch] == null) {
                return;
            }
            cur = cur.children[branch];
        }
        
        // search part 2: the word is end, only check the rest in trie. (the counterparty in trie may longer than the word)
        // if it is last trie node of a word, add to result.
        if (cur.index != -1 && cur.index != index) {
            res.add(Arrays.asList(index, cur.index));
        }
        // if from this trie node to beginning of a word can form a palindrome, add it to result.
        for (int i : cur.palindromBelow) {
            if (i != index) {
                res.add(Arrays.asList(index, i));
            }
        }
    }
    
    private boolean isPalindrom(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    // Time Complexity: O(k^2 * n), k is the average length of string
    // Space Complexity: use Trie will decrease the space splendid, share common suffix
    // worse case the trie is too sparse, O(n * k)
}