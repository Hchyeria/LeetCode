/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} p
 * @param {TreeNode} q
 * @return {boolean}
 */
var isSameTree = function(p, q) {
    if (!p && !q) {
        return true;
    } else if (!q || !p) {
        return false;
    } else if (p.val != q.val) {
        return false;
    } else {
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }
};

var isSameTree2 = function(p, q) {
    let stack = []
    stack.push(p)
    stack.push(q)
    while (stack.length) {
        console.log(stack.length)
        let right = stack.pop()
        let left = stack.pop()
        if (!left || !right) {
            if (left != right) {
                return false;
            }
        } else if (left.val != right.val) {
            return false;
        } else {
            stack.push(left.left)
            stack.push(right.left)
            stack.push(left.right)
            stack.push(right.right)
        }
    }
    return true;
};