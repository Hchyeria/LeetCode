/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} key
 * @return {TreeNode}
 */
var deleteNode = function(root, key) {
    if (!root) {
        return root;
    }
    
    let cur = root, pre
    while (cur && cur.val != key) {
        pre = cur;
        if (cur.val < key) {
            cur = cur.right
        } else {
            cur = cur.left
        }  
    }
    
    // cur === undefined || cur.val = key
    if (!pre) {
        return deleteRootNode(cur);
    }
    
    if (pre.left === cur) {
        pre.left = deleteRootNode(cur)
    } else {
        pre.right = deleteRootNode(cur)
    }
    return root;
};

var deleteRootNode = function(root) {
    if (!root) {
        return root;
    }
    
    if (!root.left) {
        return root.right;
    } else if (!root.right) {
        return root.left;
    }
    
    if (!root.right.left) {
        root.right.left = root.left
        return root.right;
    }
    
    let swapNode = deleteSmallest(root.right)
    swapNode.left = root.left
    swapNode.right = root.right
    return swapNode;
    
}

var deleteSmallest = function(root) {
    let pre;
    while (root.left) {
        pre = root
        root = root.left
    }
    
    pre.left = root.right
    return root;
}