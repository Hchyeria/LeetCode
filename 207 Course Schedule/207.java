import java.util.Set;

// DFS
class Solution {
    private static class Graph {
        Map<Integer, List<Integer>> nodes;
        
        Graph() {
            nodes = new HashMap<>();
        }
        
        void addEdge(int src, int des) {
            nodes.computeIfAbsent(src, (k) -> new ArrayList<>())
                .add(des);
        }
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        Set<Integer> visited = new HashSet<>();
        Graph graph = new Graph();
        for (int[] p : prerequisites) {
            graph.addEdge(p[1], p[0]);
        }
        
        for (int k : graph.nodes.keySet()) {
            if (dfs(k, graph, visited)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean dfs(int k, Graph g, Set<Integer> visited) {
        List<Integer> list = g.nodes.get(k);
        if (list == null) {
            return false;
        }
        if (!visited.add(k)) {
            return true;
        }
        for (int node : list) {
            if (dfs(node, g, visited)) {
                return true;
            }
        }
        visited.remove(k);
        return false;
    }
}
// Time = O(V ^ 2)
// Space = O(V)

// BFS
// BFS is much faster than DFS
class Solution {
    private static class Graph {
        Map<Integer, List<Integer>> nodes;
        
        Graph() {
            nodes = new HashMap<>();
        }
        
        void addEdge(int src, int des) {
            nodes.computeIfAbsent(src, (k) -> new ArrayList<>())
                .add(des);
        }
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        Set<Integer> visited = new HashSet<>();
        Graph graph = new Graph();
        int[] degree = new int[numCourses];
        for (int[] p : prerequisites) {
            graph.addEdge(p[1], p[0]);
            degree[p[0]]++;
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (degree[i] == 0) {
                queue.offer(i);
                count++;
            }
        }
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Integer> list = graph.nodes.get(node);
            if (list != null) {
                for (int child : list) {
                    degree[child]--;
                    if (degree[child] == 0) {
                        queue.offer(child);
                        count++;
                    }
                }
            }
        }
        
        return count == numCourses;
    }
    
}
// Time = O(V + E)
// Space = O(V)