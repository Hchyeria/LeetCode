// LintCode
// 599. Insert into a Cyclic Sorted List
/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param node: a list node in the list
     * @param x: An integer
     * @return: the inserted new list node
     */
    public ListNode insert(ListNode node, int x) {
        // write your code here
        ListNode dummy = new ListNode(0);
        
        dummy.next = new ListNode(x);
        ListNode cur = node;
        ListNode p = null;
        while (cur != null) {
            p = dummy;
            ListNode nextNode = cur.next;
            while (p.next != null && p.next.val < cur.val) {
                p = p.next;    
            }
            cur.next = p.next;
            p.next = cur;
            cur = nextNode;
            if (cur == node) {
                break;
            }
        }
        
        p = dummy.next;
        while (p.next != null) {
            p = p.next;
        }
        
        p.next = dummy.next;
        dummy.next = null;
        return p;
        
    }

    // Time = O(n)
    // Space = O(1)
}