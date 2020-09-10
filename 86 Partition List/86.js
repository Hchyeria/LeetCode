/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} x
 * @return {ListNode}
 */
var partition = function(head, x) {
    if (!head || !head.next) {
        return head;
    }
    let dummySmall = new ListNode(0), dummyLarge = new ListNode(0);
    let curSmall = dummySmall, curLarge = dummyLarge;

    while (head) {
        if (head.val < x) {
            curSmall.next = head
            curSmall = curSmall.next
        } else {
            curLarge.next = head
            curLarge = curLarge.next
        }
        head = head.next
    }

    curSmall.next = dummyLarge.next
    curLarge.next = null
    return dummySmall.next;
};