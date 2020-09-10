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
var isSymmetric = function(root) {
    if (!root) {
        return true;
    }
    return checkSymmetric(root.left, root.right)
};

var checkSymmetric = function(left, right) {
    if (!left && !right) {
        return true;
    } else if (!left || !right) {
        return false;
    } else if (left.val != right.val) {
        return false
    } else {
        return checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left)
    }
}