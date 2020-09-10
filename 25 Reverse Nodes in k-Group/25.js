/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} k
 * @return {ListNode}
 */
var reverseKGroup = function(head, k) {
    let count = 0
    cur = head
    while(cur && count < k){
        cur = cur.next
        count++
    }
    if(count === k){
        last = reverseKGroup(cur, k)
        while(count-- > 0){
            nextNode = head.next
            head.next = last
            last = head
            head = nextNode
        }
        head = last
    }
    return head;
};