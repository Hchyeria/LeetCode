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
var levelOrderBottom = function(root) {
    let res = []
    if (!root) {
        return res;
    }
    helper(res, root, 0)
    return res;
};

var helper = function(res, root, level) {
    if (!root) {
        return;
    }
    
    if (level >= res.length) {
        res.unshift([])
    }
    
    helper(res, root.left, level + 1)
    helper(res, root.right, level + 1)
    res[res.length - 1 - level].push(root.val)
}