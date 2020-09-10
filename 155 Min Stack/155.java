public class MinStack {
    /** initialize your data structure here. */
    private Deque<Integer> buffer;
    private Deque<Integer> miniMum;

    public MinStack() {
        buffer = new ArrayDeque<>();
        miniMum = new ArrayDeque<>();
    }

    public void push(int x) {
        buffer.push(x);
        // push() == addFirst()
        // pop() == removeFirst()
        // peek() == peekFirst()
        if (miniMum.peek() == null || x < miniMum.peek()) {
            miniMum.push(x);
        } else {
            miniMum.push(miniMum.peek());
        }
    }

    public void pop() {
        buffer.pop();
        miniMum.pop();
    }

    public Integer top() {
        return buffer.peek();
    }

    public Integer getMin() {
        return miniMum.peek();
    }
}
/*
*
* Follow up question:
* How to optimize the space **usage of stack2** assumptions there are a lot of **duplicate elements** in Stack ?
*
* answer: try to make the element in Stack2 a descending order and store an element in Stack2
* in format of <value, size of the Stack1 when this element is added to Stack2>
* */