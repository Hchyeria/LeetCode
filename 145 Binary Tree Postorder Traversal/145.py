# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from collections import deque
class Solution:
    def postorderTraversal(self, root: TreeNode) -> List[int]:
        res = []
        if not root:
            return res
        stack = deque()
        stack.append(root)
        cur = root
        while stack:
            cur = stack.pop()
            if cur.left:
                stack.append(cur.left)
            if cur.right:
                stack.append(cur.right)
            res.insert(0, cur.val)
        return res

    def postorderTraversal2(self, root: TreeNode) -> List[int]:
        res = []
        if not root:
            return res
        stack = deque()
        stack.append(root)
        cur = root
        pre = None
        while stack:
            cur = stack[-1]
            if not pre or pre.left == cur or pre.right == cur:
                if cur.left:
                    stack.append(cur.left)
                elif cur.right:
                    stack.append(cur.right)
                else:
                    res.append(cur.val)
                    stack.pop()
            elif pre == cur.left:
                if cur.right:
                    stack.append(cur.right)
                else:
                    res.append(cur.val)
                    stack.pop()
            else:
                res.append(cur.val)
                stack.pop()
            pre = cur
        return res
