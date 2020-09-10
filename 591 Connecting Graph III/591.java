public class ConnectingGraph3 {
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    
    private int[] parent;
    private int count;
    
    private int find(int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
    
    public ConnectingGraph3(int n) {
        // initialize your data structure here.
        parent = new int[n + 1];
        count = n;
        for (int i = 0; i <= n; ++i) {
            parent[i] = i;
        }
    }
    
    public void connect(int a, int b) {
        // write your code here
        int i = find(a);
        int j = find(b);
        if (i == j) {
            return;
        }
        parent[i] = j;
        count--;
    }

    /**
     * @return: An integer
     */
    public int query() {
        // write your code here
        return count;
    }
}