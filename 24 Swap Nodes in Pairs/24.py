# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        return reverseKGroup(head, 2)

def reverseKGroup(head, k):
        count = 0
        current = head
        while (current and count < k):
            current = current.next
            count += 1
        if (count == k):
            last = reverseKGroup(current, k)
            while (count > 0):
                next = head.next
                head.next = last
                # next.next = head <- don't need this line
                head, last = next, head
                count -= 1
            head = last
        return head