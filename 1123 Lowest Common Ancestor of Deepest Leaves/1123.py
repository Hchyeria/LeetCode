# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def lcaDeepestLeaves(self, root: TreeNode) -> TreeNode:
        if not root:
            return root
        res = [0]
        globalMax = [-float('inf')]
        helper(root, res, 0, globalMax)
        return res[0]
    
    def lcaDeepestLeaves2(self, root: TreeNode) -> TreeNode:
        if not root:
            return root
        
        def solve(root, deep):
            if not root:
                return root, deep
            left, deppLeft = solve(root.left, deep + 1)
            right, deepRight = solve(root.right, deep + 1)
            if deppLeft == deepRight:
                return root, deppLeft
            return (left, deppLeft) if deppLeft > deepRight else (right, deepRight)
        res, _ = solve(root, 0)
        return res
    
def helper(root, res, deep, globalMax):
    globalMax[0] = max(globalMax[0], deep)
    if not root:
        return deep
    left = helper(root.left, res, deep + 1, globalMax)
    right = helper(root.right, res, deep + 1, globalMax)
    if left == globalMax[0] and right == globalMax[0]:
        res[0] = root
    return max(left, right)
