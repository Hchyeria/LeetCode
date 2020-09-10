class Solution:
    def reverseBits(self, n: int) -> int:
        left, right = 31 , 0
        while left > right:
            leftBit = (n >> left) & 1
            rightBit = (n >> right) & 1
            if leftBit != rightBit:
                n ^= (1 << left) | (1 << right)
            right += 1
            left -= 1
        return n

    def reverseBits2(self, n: int) -> int:
        # n: 00000010100101000001111010011100
        # str(bin(n): 0b10100101000001111010011100
        print(str(bin(n)))
        # The zfill() method adds zeros (0) at the beginning of the string, until it reaches the specified length.
        return int(str(bin(n))[2:].zfill(32)[::-1], 2)