# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

from collections import deque
class Solution:
    def isSameTree(self, p: TreeNode, q: TreeNode) -> bool:
        if not p and not q:
            return True
        elif not p or not q:
            return False
        elif p.val != q.val:
            return False
        else:
            return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)

    def isSameTree2(self, p: TreeNode, q: TreeNode) -> bool:
        stack = deque()
        stack.append(p)
        stack.append(q)
        # can't write like this
        # while stack[0] -> this first element is not None, it not equal to the !isEmpty()
        while stack:
            right = stack.pop()
            left = stack.pop()
            if not right or not left:
                if right != left:
                    return False
            elif left.val != right.val:
                return False
            else:
                stack.append(left.left)
                stack.append(right.left)
                stack.append(left.right)
                stack.append(right.right)
        return True