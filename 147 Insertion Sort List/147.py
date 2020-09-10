# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def insertionSortList(self, head: ListNode) -> ListNode:
        if not head or not head.next:
            return head
        cur = head
        dummy = ListNode(0)
        # don't need to append the head to initail the sorted list
        # it will be tedious
        """
        dummy.next = head
        cur = head.next
        head.next = null
        """
        while cur:
            now_node = cur
            cur = cur.next
            p = dummy
            while p.next and p.next.val < now_node.val:
                p = p.next
            next_node = p.next
            p.next = now_node
            now_node.next = next_node
        return dummy.next