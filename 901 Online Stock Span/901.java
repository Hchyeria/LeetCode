class StockSpanner {
    
    private static class Node {
        int val;
        int index;
        Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
    
    private Deque<Node> stack;
    private int index;
    public StockSpanner() {
        stack = new LinkedList<>();
        index = 0;
    }
    
    public int next(int price) {
        // pay attention about stack.peekFirst().val <= price
        // not stack.peekFirst().val < price
        // because we consider in equal situation too
        while (!stack.isEmpty() && stack.peekFirst().val <= price) {
            stack.pollFirst();
        }
        int res = stack.isEmpty() ? index + 1 : index - stack.peekFirst().index;
        stack.offerFirst(new Node(price, index++));
        return res;
    }
    // Time = O(n)
    // Space = O(n)
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */