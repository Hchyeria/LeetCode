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
var postorderTraversal = function(root) {
    let res = []
    if (!root) {
        return res;
    }
    let stack = []
    let cur = root
    stack.push(root)
    while (stack.length) {
        cur = stack.pop()
        if (cur.left) {
            stack.push(cur.left)
        }
        if (cur.right) {
            stack.push(cur.right)
        }
        res.unshift(cur.val)
    }
    return res;
};