# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def deleteNode(self, root: TreeNode, key: int) -> TreeNode:
        if not root:
            return root
        
        return delete(root, key)


def delete(root: TreeNode, key: int) -> TreeNode:
    if not root:
        return root
    
    if root.val < key:
        root.right = delete(root.right, key)
        return root
    elif root.val > key:
        root.left = delete(root.left, key)
        return root
    return deleteRootNode(root)

def deleteRootNode(root: TreeNode) -> TreeNode:
    if not root:
        return root
    
    if not root.left:
        return root.right
    elif not root.right:
        return root.left
    
    if not root.right.left:
        root.right.left = root.left
        return root.right
    
    swapNode = deleteSmallest(root.right)
    swapNode.left = root.left
    swapNode.right = root.right
    return swapNode

def deleteSmallest(root: TreeNode) -> TreeNode:
    pre = None
    while root.left:
        pre = root
        root = root.left
    pre.left = root.right
    return root
