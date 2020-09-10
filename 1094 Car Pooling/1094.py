class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        for k, v in sorted(
            x 
            for n, s, e in trips
            for x in [[s, n], [e, -n]]):
            capacity -= v
            if capacity < 0:
                return False
        return True