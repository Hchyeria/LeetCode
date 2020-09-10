import random
class Solution:
    array = None
    def __init__(self, nums: List[int]):
        self.array = nums

    def reset(self) -> List[int]:
        """
        Resets the array to its original configuration and return it.
        """
        return self.array
        

    def shuffle(self) -> List[int]:
        """
        Returns a random shuffling of the array.
        """
        temp = self.array[:]
        for i in range(len(temp) - 1, 0, -1):
            index = random.randrange(0, i + 1)
            temp[i], temp[index] = temp[index], temp[i]
        return temp

# Your Solution object will be instantiated and called as such:
# obj = Solution(nums)
# param_1 = obj.reset()
# param_2 = obj.shuffle()