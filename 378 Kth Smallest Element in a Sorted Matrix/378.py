from queue import PriorityQueue
class Solution:

    def kthSmallest(self, matrix: List[List[int]], k: int) -> int:
        class Cell:
            row = 0
            col = 0
            val = 0
            def __init__(self, row, col, val):
                self.row = row
                self.col = col
                self.val = val
                
            def __lt__(self, other):
                return self.val < other.val
        
        m = len(matrix)
        n = len(matrix[0])
        visited = [[0 for i in range(n)] for j in range(m)]
        minHeap = PriorityQueue()
        minHeap.put((matrix[0][0], Cell(0, 0, matrix[0][0])))
        visited[0][0] = 1
        # notice here is the range(k - 1)
        for i in range(k - 1):
            val, cell = minHeap.get()
            if cell.row < m - 1 and not visited[cell.row + 1][cell.col]:
                minHeap.put((matrix[cell.row + 1][cell.col], 
                    Cell(cell.row + 1, cell.col, matrix[cell.row + 1][cell.col])))
                visited[cell.row + 1][cell.col] = 1
            if cell.col < n - 1 and not visited[cell.row][cell.col + 1]:
                minHeap.put((matrix[cell.row][cell.col + 1],
                    Cell(cell.row, cell.col + 1, matrix[cell.row][cell.col + 1])))
                visited[cell.row][cell.col + 1] = 1
        return minHeap.queue[0][-1].val

    def kthSmallest2(self, matrix: List[List[int]], k: int) -> int:
        m, n = len(matrix), len(matrix[0])
        low = matrix[0][0]
        high = matrix[-1][-1]
        while low < high:
            mid = low + (high - low) // 2
            count = 0
            for i in range(m):
                count += countSmallerAndEqual(matrix[i], mid)
            if count < k:
                low = mid + 1
            else:
                high = mid
        return low

def countSmallerAndEqual(array, target):
    if target >= array[-1]:
        return len(array)
    if target < array[0]:
        return 0
    left, right = 0, len(array)
    while left < right:
        mid = left + (right - left) // 2
        if array[mid] <= target:
            left = mid + 1
        else:
            right = mid
    return left
    
