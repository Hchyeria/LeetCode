class Solution {
    public String rearrangeString(String str, int k) {
        if (str == null || str.length() <= 1 || k <= 0) {
            return str;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            int index = c - 'a';
            map.put(index, map.getOrDefault(index, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) ->
                a.getValue().equals(b.getValue()) ? Integer.compare(a.getKey(), b.getKey()) : Integer.compare(b.getValue(), a.getValue()));

        pq.addAll(map.entrySet());
        List<Map.Entry<Integer, Integer>> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            int n = k;
            int i = res.size();
            while (n > 0 && !pq.isEmpty()) {
                Map.Entry<Integer, Integer> entry = pq.poll();
                entry.setValue(entry.getValue() - 1);
                res.add(entry);
                n--;
            }

            int right = res.size();
            for (; i < right; ++i) {
                if (res.get(i).getValue() > 0) pq.add(res.get(i));
            }
            if (pq.isEmpty()) break;
            if (n > 0) return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> i : res) {
            sb.append((char) (i.getKey() + 'a'));
        }

        return sb.toString();
    }

    // Time = O(n * log(n))
    // Space = O(n)
}