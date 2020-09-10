
// Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
// The successor of a node p is the node with the smallest key greater than p.val. 
// You will have direct access to the node but not to the root of the tree. 
// Each node will have a reference to its parent node. A node is defined as the following:
// Definition for a Node.
// class Node {
//     public int val;
//     public Node left;
//     public Node right;
//     public Node parent;
// };

public class Solution {
    public Node inorderSuccessor(Node x) {
        if (x == null) {
            return null;
        }
    
        if (x.right != null) {
            x = x.right;
            while (x.left != null) {
                x = x.left;
            }
            return x;
        }
    
        // case 2: right child is not null
        while (x != null) {
            Node p = x.parent;
            if (p != null && p.left == x) {
                return p;
            }
            x = p;
        }
    
        return x;
    }
    //  Time = O(height)
    // Space = O(1)
}