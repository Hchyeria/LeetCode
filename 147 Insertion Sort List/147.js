/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var insertionSortList = function(head) {
    if (!head || !head.next) {
        return head;
    }

    let cur = head
    let dummy = new ListNode(0)
    while (cur) {
        let nextCur = cur.next
        let p = dummy
        while (p.next && p.next.val < cur.val) {
            p = p.next
        }
        let nextNode = p.next
        p.next = cur
        cur.next = nextNode
        cur = nextCur
    }

    return dummy.next;
};