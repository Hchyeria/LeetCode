/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode[]} lists
 * @return {ListNode}
 */
// Merge with Divide And Conquer
var mergeKLists = function(lists) {
    if (!lists || !lists.length) {
        return lists;
    }
    if (lists.length == 1) {
        return lists[0];
    }

    return mergeHelper(lists, 0, lists.length - 1);
};

/**
 * @param {ListNode[]} lists
 * @param {int} start
 * @param {int} end
 * @return {ListNode}
 */
var mergeHelper = function(lists, start, end) {
    if (start === end) {
        return lists[start];
    }
    let mid = start + ((end - start) >> 1)
    let left = mergeHelper(lists, start, mid)
    let right = mergeHelper(lists, mid + 1, end)
    return mergeTwoLists(left, right);
};



/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */

var mergeTwoLists = function(l1, l2) {
    if (!l1 || !l2) {
        return !l1 ? l2 : l1; 
    }

    let dummy = new ListNode(0);
    let cur = dummy;
    while (l1 && l2) {
        if (l1.val <= l2.val) {
            cur.next = l1;
            l1 = l1.next;
        } else {
            cur.next = l2;
            l2 = l2.next;
        }
        cur = cur.next;
    }

    cur.next = !l1 ? l2 : l1;

    return dummy.next;
};

// Time Complexity: O(log(k) * N)
// Space Complexity: O(log(k)) call-stack, if they are array and not the linked list the space will be O(N)