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
var inorderTraversal = function(root) {
    let res = []
    let stack = []
    let cur = root
    while (cur || stack.length) {
        if (cur) {
            stack.push(cur)
            cur = cur.left
        } else {
            let temp = stack.pop()
            res.push(temp.val)
            cur = temp.right
        }
    }
    return res;
};