/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[][]}
 */
var levelOrder = function(root) {
    let res = []
    if (!root) {
        return res;
    }
    let queue = []
    queue.push(root)
    while (queue.length) {
        let size = queue.length
        let item = []
        for (let i = 0; i < size; ++i) {
            let node = queue.shift()
            item.push(node.val)
            if (node.left) {
                queue.push(node.left)
            }
            if (node.right) {
                queue.push(node.right)
            }
        }
        res.push(item)
    }
    return res;
};