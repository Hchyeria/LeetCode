/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
var lcaDeepestLeaves = function(root) {
    if (!root) {
        return root;
    }
    let res = [], globalMax = [-Infinity]
    helper(root, res, 0, globalMax)
    return res[0];
};

var helper = function(root, res, deep, globalMax) {
    globalMax[0] = Math.max(globalMax[0], deep)
    if (!root) {
        return deep;
    }
    let left = helper(root.left, res, deep + 1, globalMax)
    let right = helper(root.right, res, deep + 1, globalMax)
    if (left === globalMax[0] && right === globalMax[0]) {
        res[0] = root;
    }
    return Math.max(left, right);
}