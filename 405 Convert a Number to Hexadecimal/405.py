class Solution:
    def toHex(self, num: int) -> str:
        if not num:
            return '0'
        map = "0123456789abcdef"
        res = []
        zeroFlag = False
        for i in range(28, -4, -4):
            hexNum = (num >> i) & 0xF
            if hexNum != 0 or zeroFlag:
                res.append(map[hexNum])
                zeroFlag = True
        return ''.join(res)

    def toHex2(self, num: int) -> str:
        if not num:
            return '0'
        map = "0123456789abcdef"
        if num < 0:
            num += 0x100000000
        res = []
        while num:
            hexNum = num & 0xF
            res.append(map[hexNum])
            num >>= 4
        return ''.join(res[::-1])