from collections import deque

class MyQueue:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.inStack = deque();
        self.outStack = deque();
        

    def push(self, x: int) -> None:
        """
        Push element x to the back of queue.
        """
        self.inStack.append(x)

    def pop(self) -> int:
        """
        Removes the element from in front of queue and returns that element.
        """
        self.move()
        return None if not self.outStack else self.outStack.pop()
        

    def peek(self) -> int:
        """
        Get the front element.
        """
        self.move()
        return None if not self.outStack else self.outStack[-1]

    def empty(self) -> bool:
        """
        Returns whether the queue is empty.
        """
        return not self.inStack and not self.outStack
    
    def move(self) -> None:
        if not self.outStack:
            while self.inStack:
                self.outStack.append(self.inStack.pop())
        


# Your MyQueue object will be instantiated and called as such:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()