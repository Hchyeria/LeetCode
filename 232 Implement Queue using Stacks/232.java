import java.util.Deque;

class MyQueue {

    private Deque<Integer> inStack;
    private Deque<Integer> outStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        inStack = new ArrayDeque<> ();
        outStack = new ArrayDeque<> ();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public Integer pop() {
        move();
        return outStack.isEmpty() ? null : outStack.pop();
    }
    
    /** Get the front element. */
    public Integer peek() {
        move();
        return outStack.isEmpty() ? null : outStack.peek();
    }
    
    private void move() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */