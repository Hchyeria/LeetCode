# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from collections import deque
class Solution:
    def isCompleteTree(self, root: TreeNode) -> bool:
        if not root:
            return True
        flag = False
        queue = deque()
        queue.append(root)
        while queue:
            node = queue.popleft()
            if not node.left:
                flag = True
            elif flag:
                return False
            else:
                queue.append(node.left)
            
            if not node.right:
                flag = True
            elif flag:
                return False
            else:
                queue.append(node.right)
        return True