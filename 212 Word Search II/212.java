class Trie {
    public static class TrieNode {
        TrieNode[] children;
        boolean isWord;
        TrieNode() {
            children = new TrieNode[26];
        }
    }

    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
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

    public boolean search(String word) {
        TrieNode cur = root;
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            int index = word.charAt(i) - 'a';
            if (cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
        }
        boolean res = cur.isWord;
        cur.isWord = false;
        return res;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        int n = prefix.length();
        for (int i = 0; i < n; ++i) {
            int index = prefix.charAt(i) - 'a';
            if (cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
        }
        return true;
    }

}


class Solution {  
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m, n;
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || words.length == 0) {
            return res;
        }
        Trie trie = buildTrie(words);
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(res, board, i, j, trie, new StringBuilder());
            }
        }
        return res;
    }
    
    private void dfs(List<String> res, char[][] board, int i, int j, Trie trie, StringBuilder sb) {

        char c = board[i][j];

        sb.append(c);
        String s = sb.toString();
        if (!trie.startsWith(s)) {
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        if (trie.search(s)) {
            res.add(s);
        }
        board[i][j] = '#';
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (isArea(x, y) && board[x][y] != '#') {
                dfs(res, board, x, y, trie, sb);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        board[i][j] = c;

    }
    
    private boolean isArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }
    
    private Trie buildTrie(String[] words) {
        Trie trie = new Trie();
        for (String s : words) {
            trie.insert(s);
        }
        return trie;
    }
}


// 59ms: Use search and startsWith in Trie class like this popular solution.
// 33ms: Remove Trie class which unnecessarily starts from root in every dfs call.
// 30ms: Use w.toCharArray() instead of w.charAt(i).
// 22ms: Use StringBuilder instead of c1 + c2 + c3.
// 20ms: Remove StringBuilder completely by storing word instead of boolean in TrieNode.
// 20ms: Remove visited[m][n] completely by modifying board[i][j] = '#' directly.
// 18ms: check validity, e.g., if(i > 0) dfs(...), before going to the next dfs.
// 17ms: De-duplicate c - a with one variable i.
// 15ms: Remove HashSet completely. 

class Solution2 {
    public static class TrieNode {
        TrieNode[] children;
        String word;
        TrieNode() {
            children = new TrieNode[26];
        }
    }

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m, n;
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || words.length == 0) {
            return res;
        }
        TrieNode root = buildTrie(words);
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(res, board, i, j, root);
            }
        }
        return res;
    }
    
    private void dfs(List<String> res, char[][] board, int i, int j, TrieNode trie) {
        char c = board[i][j];
        if (trie.children[c - 'a'] == null) {
            return;
        }
        trie = trie.children[c - 'a'];
        if (trie.word != null) {
            res.add(trie.word);
            trie.word = null; //  avoid repeat
        }
        board[i][j] = '#';
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (isArea(x, y) && board[x][y] != '#') {
                dfs(res, board, x, y, trie);
            }
        }
        board[i][j] = c;

    }
    
    private boolean isArea(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String s : words) {
            TrieNode cur = root;
            for (char c : s.toCharArray()) {
                int index = c - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new TrieNode();
                }
                cur = cur.children[index];
            }
            cur.word = s;
        }
        return root;
    }
}