/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var preorderTraversal = function(root) {
    let res = []
    if (!root) {
        return res
    }
    let cur = root;
    let stack = [root]
    while (stack.length) {
        cur = stack.pop()
        if (cur.right) {
            stack.push(cur.right)
        }
        if (cur.left) {
            stack.push(cur.left)
        }
        res.push(cur.val)
        
    }
    return res;
};