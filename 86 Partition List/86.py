# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def partition(self, head: ListNode, x: int) -> ListNode:
        if not head or not head.next:
            return head;
        
        dummySmall = ListNode(0)
        curSmall = dummySmall
        dummyLarge = ListNode(0)
        curLarge = dummyLarge

        while head:
            if head.val < x:
                curSmall.next = head
                curSmall = curSmall.next
            else:
                curLarge.next = head
                curLarge = curLarge.next
            head = head.next
        
        curSmall.next = dummyLarge.next
        curLarge.next = None
        return dummySmall.next