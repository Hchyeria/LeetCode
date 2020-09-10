class Solution:
    def hasAlternatingBits(self, n: int) -> bool:
        while n:
            # syntax error ????
            if n & 1 == ((n >>> 1) & 1):
                return False
            n >>>= 1
        return True

    def hasAlternatingBits2(self, n):
        return '00' not in bin(n) and '11' not in bin(n)

    def hasAlternatingBits3(self, n):
        if not n:
            return False
        num = n ^ (n >> 1) # 01111111..
        return not (num & (num + 1)) # 100000 & 0111111