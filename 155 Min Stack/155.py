from collections import deque
class MinStack:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.buffer = deque()
        self.miniMum = deque()

    def push(self, x: int) -> None:
        self.buffer.append(x)
        if not self.miniMum or self.miniMum[-1] > x:
            self.miniMum.append(x)
        else:
            self.miniMum.append(self.miniMum[-1])


    def pop(self) -> None:
        self.buffer.pop()
        self.miniMum.pop()

    def top(self) -> int:
        return self.buffer[-1]

    def getMin(self) -> int:
        return self.miniMum[-1]


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(x)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
