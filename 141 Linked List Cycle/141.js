/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} head
 * @return {boolean}
 */
var hasCycle = function(head) {
    if (!head) {
        return false;
    }

    let slow = head, fast = head.next;

    while (fast && fast.next && slow !== fast) {
        slow = slow.next
        fast = fast.next.next
    }

    return slow === fast;
};