# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def isValidBST(self, root: TreeNode) -> bool:
        return helper(root, -math.inf, math.inf)


def helper(root, min_num, max_num):
    if not root:
        return True
    if root.val < min_num or root.val > max_num:
        return False
    if root.val == -math.inf and root.left \
        or root.val == math.inf and root.right:
        return False
    else:
        return helper(root.left, min_num, root.val - 1) and helper(root.right, root.val + 1, max_num)