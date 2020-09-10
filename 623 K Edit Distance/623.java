// 623. K Edit Distance
// LintCode

public class Solution {
    /**
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */
    
    private static class TrieNode {
        TrieNode[] children;
        boolean isWord;
        int[] distance;

        TrieNode (int n) {
            children = new TrieNode[26];
            distance = new int[n + 1];
        }
    }

    private TrieNode root;
    private int n;
    // dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
    public List<String> kDistance(String[] words, String target, int k) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (words == null || target == null
                || words.length == 0) {
            return res;
        }
        n = target.length();
        root = new TrieNode(n);
        for (int i = 1; i <= n; ++i) {
            root.distance[i] = i;
        }

        for (String word : words) {
            insert(word, res, k, target);
        }

        return res;
    }

    private void insert(String s, List<String> res, int k, String target) {
        TrieNode cur = root;
        int len = s.length();

        for (int i = 1; i <= len; ++i) {
            int index = s.charAt(i - 1) - 'a';
            if (cur.children[index] == null) {
                TrieNode next = new TrieNode(n);
                for (int j = 0; j <= n; ++j) {
                    if (j == 0) {
                        next.distance[j] = i;
                        continue;
                    }
                    if (s.charAt(i - 1) == target.charAt(j - 1)) {
                        next.distance[j] = cur.distance[j - 1];
                    } else {
                        next.distance[j] = Math.min(next.distance[j - 1],
                                Math.min(cur.distance[j - 1], cur.distance[j])) + 1;
                    }
                }
                cur.children[index] = next;
            }
            cur = cur.children[index];
            // can't return first
            // the distance of prefix is large than k
            // but with the postfix the distance maybe decrease
            // think this test case ["ab"], target = "eefab"
//            if (cur.distance[n] > k) {
//                return;
//            }
        }
        if (cur.distance[n] <= k) {
            res.add(s);
        }
        cur.isWord = true;
    }
}


// dfs
public class Solution {
    /**
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */
    
    private static class TrieNode {
        TrieNode[] children;
        String hasWord;

        TrieNode () {
            children = new TrieNode[26];
        }
    }

    private TrieNode root;
    private int n;
    // dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
    public List<String> kDistance(String[] words, String target, int k) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (words == null || target == null
                || words.length == 0) {
            return res;
        }
        n = target.length();
        int[] helper = new int[n + 1];
        root = new TrieNode();
        for (int i = 1; i <= n; ++i) {
            helper[i] = i;
        }

        for (String word : words) {
            insert(word);
        }

        dfs(res, root, helper, target, k);

        return res;
    }

    private void dfs(List<String> res, TrieNode cur, int[] helper, String target, int k) {
        if (cur.hasWord != null && helper[n] <= k) {
            res.add(cur.hasWord);
        }
        
        for (int i = 0; i < 26; ++i) {
            if (cur.children[i] == null) {
                continue;
            }
            
            int[] fn = new int[n + 1];
            for (int j = 0; j <= n; ++j) {
                
                fn[j] = helper[j] + 1;
                if (j == 0) {
                    continue;
                }
                
                fn[j] = Math.min(fn[j], helper[j - 1] + 1);
                fn[j] = Math.min(fn[j], fn[j - 1] + 1);
                if (target.charAt(j - 1) - 'a' == i) {
                    fn[j] = Math.min(fn[j], helper[j - 1]);
                }
            }
            // don't change the cur reference
            // cur = cur.children[i];
            // dfs(res, cur, fn, target, k);
            dfs(res, cur.children[i], fn, target, k);
        }
        
        
    }

    private void insert(String s) {
        TrieNode cur = root;
        int len = s.length();

        for (int i = 1; i <= len; ++i) {
            int index = s.charAt(i - 1) - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
        }
        cur.hasWord = s;
    }
}