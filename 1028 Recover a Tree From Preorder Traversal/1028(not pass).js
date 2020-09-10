/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {string} S
 * @return {TreeNode}
 */
// not pass
// Runtime Eroor
// TypeError:is not valid value for the expected return type TreeNode
// I don't know why?
var recoverFromPreorder = function(S) {
    let nodes = []
    S.replace(/(-*)(\d+)/g, (match, $1, $2) => {
        nodes.unshift([$1.length, +$2])
        return match;
    })
    var foo = function(level) {
        if (!nodes.length || level !== nodes[nodes.length - 1][0]) {
            return;
        }
        let node = new TreeNode(nodes.pop()[1])
        node.left = foo(level + 1)
        node.right = foo(level + 1)
        return node;
    }
    return foo(0);
};