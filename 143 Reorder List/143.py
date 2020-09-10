# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def reorderList(self, head: ListNode) -> None:
        """
        Do not return anything, modify head in-place instead.
        """
        if not head or not head.next or not head.next.next:
            return
        midNode = self.findMid(head)
        rightNode = midNode.next
        midNode.next = None
        rightNode = self.reverse(rightNode)
        head = self.merge(head, rightNode)
    
    def findMid(self, head: ListNode) -> ListNode:
        slow = head
        fast = head
        while fast.next and fast.next.next:
            slow = slow.next
            fast = fast.next.next
        return slow
    
    def reverse(self, head: ListNode) -> ListNode:
        if not head or not head.next:
            return head
        next_node = head.next
        newHead = self.reverse(next_node)
        next_node.next = head
        head.next = None

        return newHead
    
    def merge(self, head1: ListNode, head2: ListNode) -> ListNode:
        if not head1 or not head2:
            return head1 if head1 else head2
        dummy = ListNode(0)
        cur = dummy

        while head1 and head2:
            cur.next = head1
            head1 = head1.next
            cur = cur.next
            cur.next = head2
            head2 = head2.next
            cur = cur.next
            
        cur.next = head1 if head1 else head2

        return dummy.next