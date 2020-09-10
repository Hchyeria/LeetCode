# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

import re

class Solution(object):
    def recoverFromPreorder(self, S):
        nodes = [(len(s[0]) ,int(s[1])) 
                for s in re.findall('(-*)(\d+)', S)[::-1]]
        def foo(level):
            if not nodes or level != nodes[-1][0]:
                return
            node = TreeNode(nodes.pop()[1])
            node.left = foo(level + 1)
            node.right = foo(level + 1)
            return node
        return foo(0)
            