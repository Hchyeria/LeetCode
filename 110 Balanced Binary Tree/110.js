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

// this method not fast enough

var isBalanced = function(root) {
    return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right)
};
var map = new Map();
var getHeight = function(root) {
    if (!root) {
        return 0;
    }
    if (map.has(root)) {
        return map[root];
    } else {
        map[root] = Math.max(getHeight(root.left), getHeight(root.right)) + 1
        
    }
    return map[root];
}