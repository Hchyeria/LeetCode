# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from collections import deque
class Solution:
    def inorderTraversal(self, root: TreeNode) -> List[int]:
        res = []
        stack = deque()
        cur = root
        while cur or stack:
            if cur:
                stack.append(cur)
                cur = cur.left
            else:
                temp = stack.pop()
                res.append(temp.val)
                cur = temp.right
        return res