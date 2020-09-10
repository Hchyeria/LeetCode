class Solution(object):
    def isAnagram(self, s, t):
        return { x: s.count(x) for x in set(s) } == { x: t.count(x) for x in set(t) }



class Solution(object):
    def isAnagram(self, s: str, t: str) -> bool:
        if (len(s) != len(t)): 
            return False
        temp = [0] * 26
        a = ord('a')
        for i, value in enumerate(list(s)):
            temp[ord(value) - a] += 1
            temp[ord(t[i]) - a] -= 1
        for j in temp:
            if (j != 0):
                return False
        return True


class Solution:
    def isAnagram(self, s, t):
        if len(s) != len(t):
            return False
        for char in set(s):
            if s.count(char) != t.count(char):
                return False
        return True