// LintCode 634. Word Squares

public class Solution {
    /*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    
    private static class TrieNode {
        TrieNode[] children;
        List<String> prefix;
        
        TrieNode() {
            children = new TrieNode[26];
            prefix = new ArrayList<>();
        }
    } 
    
    private void insert(TrieNode node, String s) {
        TrieNode cur = node;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int index = s.charAt(i) - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
            // careful !!
            // add s to prefix of child, not cur
            cur.prefix.add(s);
        }
    }
    
    public List<List<String>> wordSquares(String[] words) {
        // write your code here
        List<List<String>> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        
        int len = words[0].length();
        
        TrieNode root = new TrieNode();
        
        for (String s : words) {
            insert(root, s);
        }
        
        List<String> builder = new ArrayList<>();
        for (String s : words) {
            builder.add(s);
            findSquares(builder, root, res, len);
            builder.remove(builder.size() - 1);
        }
        
        return res;
    }
    
    private void findSquares(List<String> cur, TrieNode node, List<List<String>> res, int len) {
        if (cur.size() == len) {
            res.add(new ArrayList<>(cur));
            return;
        }

        int idx = cur.size();
        TrieNode curNode = node;
        for (String s : cur) {
            int index = s.charAt(idx) - 'a';
            if (curNode.children[index] == null) {
                return;
            }
            curNode = curNode.children[index];
        }
        for (String s : curNode.prefix) {
            cur.add(s);
            findSquares(cur, node, res, len);
            cur.remove(cur.size() - 1);
        }
    }

}