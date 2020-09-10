class Solution {

    // Solution 1: PQ
    public String reorganizeString(String S) {
        if (S == null || S.isEmpty()) {
            return "";
        }
        
        Map<Integer, Integer> map = new HashMap<>();        
        for (char c : S.toCharArray()) {
            int index = c - 'a';
            map.put(index, map.getOrDefault(index, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
            (a, b) -> a.getValue().equals(b.getValue()) 
            ? Integer.compare(a.getKey(), b.getKey()) 
            : Integer.compare(b.getValue(), a.getValue())
        );
        pq.addAll(map.entrySet());
        
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<Integer, Integer>> tmp = new ArrayList<>();
        while (!pq.isEmpty()) {
            int n = 2;
            tmp.clear();
            while (n > 0 && !pq.isEmpty()) {
                Map.Entry<Integer, Integer> entry = pq.poll();
                char c = (char) (entry.getKey() + 'a');
                int count = entry.getValue();
                entry.setValue(count - 1);
                tmp.add(entry);
                sb.append(c);
                n--;
            }
            
            for (Map.Entry<Integer, Integer> e : tmp) {
                if (e.getValue() > 0) pq.offer(e);
            }
            if (pq.isEmpty()) break;
            if (n > 0) return "";
        }
        return sb.toString();
    }

    // Time = O(n * log(n))
    // Space = O(n)

    // Solution 2: sort
    // Time = O(n * log(n) + n)
    // Space = O(n)
    private static class Pair<K, V> {
        K key;
        V value;
        
        Pair(K k, V v) {
            key = k;
            value = v;
        }
        
        K getKey() {
            return key;
        }
        V getValue() {
            return value;
        }
    }
    
    public String reorganizeString(String S) {
        if (S == null || S.isEmpty()) {
            return "";
        }
        
        int[] count = new int[26];
        for (char c : S.toCharArray()) {
            int index = c - 'a';
            count[index]++;
        }
        int len = S.length();
        List<Pair<Character, Integer>> list = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            if (count[i] > (len + 1 ) / 2) {
                return "";
            }
            list.add(new Pair<>((char) (i + 'a'), count[i]));
        }
        Collections.sort(list, (a, b) -> Integer.compare(b.getValue(), a.getValue()));
        StringBuilder tmp = new StringBuilder();
        for (Pair<Character, Integer> p : list) {
            int c = p.getValue();
            while (c -- > 0) {
                tmp.append(p.getKey());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = (len + 1) / 2; i < (len + 1) / 2; ++i, ++j) {
            sb.append(tmp.charAt(i));
            if (j < len) {
                sb.append(tmp.charAt(j));
            }
        }
        return sb.toString();
    }

    // Solution 3: no sort, the best solution
    // Time = O(n)
    // Space = O(n)
    public String reorganizeString3(String S) {
        if (S == null || S.isEmpty()) {
            return "";
        }
        
        int[] count = new int[26];
        char freq = 'a';
        int max = 0;
        for (char c : S.toCharArray()) {
            int index = c - 'a';
            count[index]++;
            if (count[index] > max) {
                max = count[index];
                freq = c;
            }
        }
        int len = S.length();
        if (max > (len + 1) / 2) return "";
        char[] res = new char[len];
        int i = 0;
        while (i < len && count[freq - 'a'] > 0) {
            res[i] = freq;
            count[freq - 'a']--;
            i += 2;
        }
        
        for (int j = 0; j < 26; ++j) {
            char c = (char) (j + 'a');
            while (count[j] > 0) {
                if (i >= len) i = 1;
                res[i] = c;
                count[j]--;
                i += 2;
            }
        }
        
        return new String(res);
    }
}