import java.util.Map;
import java.util.PriorityQueue;

class AutocompleteSystem {
    private static class TrieNode {
        TrieNode[] children;
        Map<String, Integer> prefix;
        boolean isWord;

        TrieNode() {
            children = new TrieNode[27];
            prefix = new HashMap<>();
        }
    }

    private int covertIndex(char c) {
        return c == ' ' ? 26 : c - 'a';
    }

    private void insert(TrieNode root, String s, int time) {
        int n = s.length();
        TrieNode cur = root;
        for (int i = 0; i < n; ++i) {
            int index = covertIndex(s.charAt(i));
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
            cur.prefix.put(s, cur.getOrDefault(s, 0) + time);
        }
        cur.isWord = true;
    }

    private TrieNode root;
    private TrieNode cur;
    StringBuilder prefix;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        cur = root;
        prefix = new StringBuilder();
        int n = sentences.length;
        for (int i = 0; i < n; ++i) {
            insert(root, sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new LinkedList<>();
        if (c == '#') {
            cur = root;
            insert(root, prefix.toString(), 1);
            prefix.setLength(0);
            return res;
        }
        prefix.append(c);
        int index = covertIndex(c);
        if (cur.children[index] == null) {
            return res;
        }
        
        cur = cur.children[index];

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(3, new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                if (e1.getValue() == e2.getValue()) {
                    return e1.getKey().compareTo(e2.getKey());
                }
                return e1.getValue().compareTo(e2.getValue());
            }
        });

        for (Map.Entry<String, Integer> entry : cur.prefix.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > 3) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            res.add(0, minHeap.poll().getKey());
        }


        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */