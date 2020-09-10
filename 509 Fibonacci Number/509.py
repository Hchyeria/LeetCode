class Solution:
    def fib(self, N: int) -> int:
        a, b = 0, 1
        for i in range(N):
            c = a + b
            a, b = b, c
        return a