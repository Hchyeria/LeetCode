public class QuickSortLinkedList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        quickSort(head, null);
        return head;
    }

    private void quickSort(ListNode head, ListNode end) {
        if (head == null || head == end) {
            return;
        }
        ListNode tail = head, cur = head.next;

        while (cur != end) {
            if (cur.val < head.val) {
                tail = tail.next;
                swap(tail, cur);
            }
            cur = cur.next;
        }
        swap(tail, head);
        quickSort(head, tail);
        quickSort(tail.next, end);
    }

    private void swap(ListNode i, ListNode j) {
        if (i == null || j == null || i == j) {
            return;
        }
        // can't write this
        // ListNode temp = i
        // i.val = j.val
        // the temp is a copy reference of i
        int temp = i.val;
        i.val = j.val;
        j.val = temp;
    }
}