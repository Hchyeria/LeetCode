class Solution:
    def canReach(self, arr: List[int], start: int) -> bool:
        if len(arr) == 1:
            return True
        return dfs(arr, start)
    
def dfs(arr, index):
    if index < 0 or index >= len(arr) or arr[index] >= len(arr):
        return False
    if arr[index] == 0:
        return True
    jump = arr[index]
    arr[index] += len(arr)
    return dfs(arr, index - jump) or dfs(arr, index + jump)