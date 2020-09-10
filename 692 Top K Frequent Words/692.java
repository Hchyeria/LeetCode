class Solution {
    // Solution 1: pq
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> hashMap = buildMap(words);
        // define a minHeap
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, (a, b) -> {
            if (a.getValue().equals(b.getValue())) {
                // use .equals(...), == only works for Integer in [-128, 127]
                return b.getKey().compareTo(a.getKey());
            } else {
                // notice: minHeap -> order of compareTo(...)
                return a.getValue().compareTo(b.getValue());
            }
        });
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {         
                pq.poll();  
            }
        }
        List<String> result = new LinkedList<>();
        for (int i = pq.size() - 1; i >= 0; --i) {
            result.add(0, pq.poll().getKey());
        }
        return result;

    }

    private Map<String, Integer> buildMap(String[] words) {
        Map<String, Integer> res = new HashMap<>();
        for (String word : words) {
            Integer count = res.getOrDefault(word, 0);
            count++;
            res.put(word, count);
        }
        return res;
    }
    // Time Complexity: O(n*log(k) * l), l is average length of word
    // Space Complexity: O(n)
    
    // Solution 2: HashMap + bucketSort + Trie
    public List<String> topKFrequent2(final String[] words, final int k) {
        // build HashMap record the count of each string
        final Map<String, Integer> countMap = new HashMap<>();
        for (final String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        // bucket sort
        int n = words.length;
        List[] bucket = new List[n + 1];
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            final int index = entry.getValue();
            if (bucket[index] == null) {
                bucket[index] = new ArrayList<>();
            }
            bucket[index].add(entry.getKey());
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (bucket[i] != null) {
                getWords(buildTrie(bucket[i]), res, k, new StringBuilder());
            }
        }
        return res;
    }

    private static class TrieNode {
        TrieNode[] children;
        boolean isWord;
        TrieNode() {
            children = new TrieNode[26];
        }

    }

    private TrieNode buildTrie(final List<String> words) {
        final TrieNode root = new TrieNode();
        for (final String s : words) {
            insert(s, root);
        }
        return root;
    }

    private void insert(final String s, final TrieNode root) {
        TrieNode cur = root;
        final int n = s.length();
        for (int i = 0; i < n; ++i) {
            final int index = s.charAt(i) - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
        }
        cur.isWord = true;
    }

    private void getWords(final TrieNode root, final List<String> res, final int k, final StringBuilder sb) {
        if (res.size() == k) {
            return;
        }
        if (root.isWord) {
            res.add(sb.toString());
        }
        for (int i = 0; i < 26; ++i) {
            if (root.children[i] != null) {
                sb.append((char)(i + 'a'));
                getWords(root, res, k, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    // Time Complexity: O(n + n + k*l), l is average length of word
    // Space Complexity: O(n + n + kl)
}