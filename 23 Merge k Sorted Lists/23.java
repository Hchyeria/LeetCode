import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return lists;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int k = lists.length;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<> (k + 1, (a, b) -> (a.val - b.val));

        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            cur.next = minNode;
            cur = cur.next;

            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }

        return dummy.next;
    }
}

// Time = O(N*log(k)) 
// Space = O(k) (implemented with heap)