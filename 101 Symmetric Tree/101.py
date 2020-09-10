# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        if not root:
            return True
        return checkSymmetric(root.left, root.right)
    
def checkSymmetric(left: TreeNode, right: TreeNode) -> bool:
    if not left and not right:
        return True
    elif not left or not right:
        return False
    elif left.val != right.val:
        return False
    else:
        return checkSymmetric(left.left, right.right) and checkSymmetric(left.right, right.left)