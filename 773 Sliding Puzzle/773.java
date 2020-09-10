class Solution {
    public int slidingPuzzle(int[][] board) {
        int start = convertInt(board);
        int end = 123450;
        if (start == 123450) {
            return 0;
        }

        // BFS
        Map<Integer, Integer> visited = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited.put(start, 0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int path = visited.get(node);

            List<Integer> next = getNext(node);
            for (int nextNode : next) {
                if (!visited.containsKey(nextNode)) {
                    queue.offer(nextNode);
                    visited.put(nextNode, path + 1);
                    if (nextNode == end) {
                        return path + 1;
                    }
                }
            }
        }
        return -1;
    }

    private List<Integer> getNext(int node) {
        List<Integer> res = new ArrayList<>();
        int[][] intArray = convertBoard(node);
        // Get the position of zero
        int i = 0;
        for ( ; i < 6; ++i) {
            if (intArray[i / 3][i % 3] == 0) {
                break;
            }
        }
        int x = i / 3, y = i % 3;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : dirs) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            if (isArea(nextX, nextY)) {

                swap(intArray, x, y, nextX, nextY);
                res.add(convertInt(intArray));
                swap(intArray, x, y, nextX, nextY);
            }
        }
        return res;
    }

    private void swap(int[][] array, int x, int y, int nextX, int nextY) {
        int temp = array[x][y];
        array[x][y] = array[nextX][nextY];
        array[nextX][nextY] = temp;
    }

    private boolean isArea(int x, int y) {
        return x >= 0 && x < 2  && y >= 0 && y < 3;
    }

    private int convertInt(int[][] board) {
        int res = board[0][0];
        for (int i = 1; i < 6; ++i) {
            res = res * 10 + board[i / 3][i % 3];
        }
        return res;
    }
    private int[][] convertBoard(int node) {

        int[][] res = new int[2][3];
        for (int i = 0; i < 6; ++i) {
            res[i / 3][i % 3] = (node / (int)(Math.pow(10, 6 - i - 1))) % 10;
        }
        return res;
    }
    
    // Time Complexity: O(n!), V = n!
    // Space Complexity: O(n!)
}