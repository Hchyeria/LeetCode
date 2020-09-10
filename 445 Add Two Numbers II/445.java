/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<ListNode> stack1 = new LinkedList<>();
        Deque<ListNode> stack2 = new LinkedList<>();
        
        while (l1 != null) {
            stack1.offerFirst(l1);
            l1 = l1.next;
        }
        
        while (l2 != null) {
            stack2.offerFirst(l2);
            l2 = l2.next;
        }
        
        ListNode dummy = new ListNode(0);
        int add = 0, sum = 0;
        
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int left = 0, right = 0;
            if (!stack1.isEmpty()) {
                left = stack1.pollFirst().val;
            }
            if (!stack2.isEmpty()) {
                right = stack2.pollFirst().val;
            }
            
            sum = left + right + add;
            add = sum / 10;
            insert(sum % 10, dummy);
        }
        if (add == 1) {
            insert(1, dummy);
        }
        return dummy.next;
    }
    
    private void insert(int x, ListNode dummy) {
        ListNode next = null, newNode = null;
        next = dummy.next;
        newNode = new ListNode(x);
        dummy.next = newNode;
        newNode.next = next;
    }
    // Time = O(N)
    // Space = O(N)
}