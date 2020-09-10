class Solution {
    // BFS
    public int kSimilarity(String A, String B) {
        if (A.equals(B)) return 0;
        int n = A.length();
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        char[] s = A.toCharArray();
        q.offer(A);
        visited.add(A);

        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            res++;
            while (size-- > 0) {
                String item = q.poll();
                char[] c = item.toCharArray();
                int i = 0;
                while (c[i] == B.charAt(i)) i++;
                for (int j = i + 1; j < n; ++j) {
                    if (c[j] == B.charAt(j) || c[j] != B.charAt(i)) {
                        continue;
                    }
                    swap(c, i, j);
                    String next = new String(c);
                    swap(c, i, j);
                    if (next.equals(B)) return res;
                    if (visited.add(next)) {
                        q.offer(next);
                    }

                }
            }
        }
        return res;
    }


    private void swap(char[] c, int i, int j) {
        if (i != j) {
            char tmp = c[i];
            c[i] = c[j];
            c[j] = tmp;
        }
    }
}

public class KSimilarStrings {
    private int n;
    // if A, B length <= 9, the nwe can convert string to Integer without overflow
    public int kSimilarity(String A, String B) {
        if (A.equals(B)) return 0;
        n = A.length();
        Set<Long> visited = new HashSet<>();
        long target = computeCode(B.toCharArray());
        int[] targetArray = code2Array(target);
        Queue<Long> q = new LinkedList<>();

        long init = computeCode(A.toCharArray());
        q.offer(init);
        visited.add(init);

        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            res++;
            while (size-- > 0) {
                long item = q.poll();
                int[] itemArray = code2Array(item);

                int i = 0;
                while (itemArray[i] == targetArray[i]) i++;
                for (int j = i + 1; j < n; ++j) {
                    if (itemArray[i] == itemArray[j] || itemArray[j] != targetArray[i]) {
                        continue;
                    }
                    swap(itemArray, i, j);
                    long next = computeCode(itemArray);
                    if (next == target) return res;
                    if (visited.add(next)) {
                        q.offer(next);
                    }

                }
            }
        }
        return res;
    }

    private int[] code2Array(long i) {
        int[] res = new int[n];
        int index = n - 1;
        while (i != 0) {
            res[index--] = (int) (i % 10);
            i /= 10;
        }
        return res;
    }

    private long computeCode(char[] s) {
        long res = 0;
        for (char c : s) {
            res = 10 * res + (c - 'a');
        }
        return res;
    }

    private long computeCode(int[] s) {
        long res = 0;
        for (int value : s) {
            res = 10 * res + value;
        }
        return res;
    }

    private void swap(int[] array, int i, int j) {
        if (i != j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }
}