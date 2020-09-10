/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
function ListNode(val) {
 this.val = val;
this.next = null;
}
/**
 * @param {ListNode} head
 * @return {void} Do not return anything, modify head in-place instead.
 */
var reorderList = function(head) {
    if (!head || !head.next || !head.next.next) {
        return;
    }

    let midNode = findMid(head)
    let right = reverse(midNode.next)
    midNode.next = null
    head = merge(head, right)
};

/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var findMid = function(head) {
    let slow = head, fast = head;
    while (fast.next && fast.next.next) {
        slow = slow.next
        fast = fast.next.next
    }
    return slow
}

var reverse = function(head) {
    if (!head.next) {
        return head;
    }

    let nextNode = head.next
    let newHead = reverse(nextNode)
    nextNode.next = head
    head.next = null
    return newHead
}

/**
 * @param {ListNode} head1
 * @param {ListNode} head2
 * @return {ListNode}
 */
var merge = function(head1, head2) {
    let dummy = new ListNode(0)
    let cur = dummy

    while (head1 && head2) {
        cur.next = head1
        head1 = head1.next
        cur = cur.next
        cur.next = head2
        head2 = head2.next
        cur = cur.next   
    }
   /* 
        can't write like this !!! be careful
        while (head1 && head2) {
            cur.next = head1
            cur = cur.next
            cur.next = head2
            cur = cur.next
            head1 = head1.next
            head2 = head2.next
        }
        head1 and head2 have already changed when change the cur.next!

    */

    cur.next = head1 ? head1 : head2
    return dummy.next
}

