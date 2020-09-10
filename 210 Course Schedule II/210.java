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
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];

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
                res[count++] = i;
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
                        res[count++] = child;
                    }
                }
            }
        }
        
        return count == numCourses ? res : new int[0];   
    }

    // Time = O(V)
    // Space = O(V)
}