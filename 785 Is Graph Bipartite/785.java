class Solution {
    class GraphNode {
        int val;
        List<GraphNode> neighbors;

        GraphNode(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }
    // Solution 1: BFS
    public boolean isBipartite(int[][] graph) {
        if (graph == null) {
            return false;
        }
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; ++i) {
            color[i] = -1;
        }
        // be care about that we not guarantee the node 0 is root
        // maybe they don't have any neighbors
        // like this {{},{2,4,6},{1,4,8,9}} case
        // and maybe there have many graph group not only one
        // so we need to iterate all
        int index = 0;
        for (int[] node : graph) {
            if (!helper(graph, color, index++)) {
                return false;
            }
        }

        return true;
    }

    private boolean helper(int[][] graph, int[] color, int index) {
        if (color[index] >= 0) {
            return true;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(new GraphNode(index));
        color[index] = 0;
        while (!queue.isEmpty()) {
            GraphNode p = queue.poll();
            int val = p.val;
            for (int neighbor : graph[val]) {
                if (color[neighbor] < 0) {
                    color[neighbor] = color[val] ^ 1;
                    queue.offer(new GraphNode(neighbor));
                } else {
                    if (color[neighbor] != (color[val] ^ 1)) {
                        return false;
                    }
                }
            }

        }
        return true;
    }
    // Notice ~0 == -1
    // ~1 == -2
    
    // Time complexity is O(V + E)
	// Space complexity is O(V)
	
	// Solution 2: DFS
    public boolean isBipartite2(int[][] graph) {
        if (graph == null) {
            return false;
        }
        int n = graph.length;
        int[] colors = new int[n];
        int index = 0;
        for (int[] node : graph) {
            if (colors[index] == 0) {
                if (!dfs(graph, index, colors, 1)) {
                    return false;
                }
            }
            index++;
        }

        return true;
    }

    private boolean dfs(int[][] graph, int index, int[] colors, int color) {
        // (colors[index] != 0 && colors[index] != color)
        if (colors[index] == -color) {
            return false;
        }
        colors[index] = color;
        for (int neighbor : graph[index]) {
            if (colors[neighbor] == 0) {
                // recursion to implement DFS
                if (!dfs(graph, neighbor, colors, -color)) {
                    return false;
                }
            } else if (colors[neighbor] != -color) {
                return false;
            }
        }
        return true;
    }

    // Time complexity is O(V + E)
    // Space complexity is O(V)
}