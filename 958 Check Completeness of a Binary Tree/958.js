/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isCompleteTree = function(root) {
    if (!root) {
        return true;
    }
    let flag = false;
    let queue = []
    queue.push(root)
    while (queue.length) {
        let node = queue.shift()
        if (!node.left) {
            flag = true
        } else if (flag) {
            return false;
        } else {
            queue.push(node.left)
        }

        if (!node.right) {
            flag = true
        } else if (flag) {
            return false;
        } else {
            queue.push(node.right)
        }
    }
    return true;
};