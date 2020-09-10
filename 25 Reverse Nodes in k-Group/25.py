# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def rem (self, node):
        count = 0
        if node:
            while(node):
                node = node.next
                count += 1
        return count

    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        if (self.rem(head) < k):
            return head
        prev = None
        current = head
        n = k
        while (current and n > 0):
            next = current.next
            current.next = prev
            prev, current = current, next
            n-=1
        if current:
            head.next = self.reverseKGroup(current, k)
        return prev


class Solution:
    def reverseKGroup(self, head, k):
        count = 0
        current = head
        while (current and count < k):
            current = current.next
            count += 1
        if (count == k):
            last = self.reverseKGroup(current, k)
            while (count > 0):
                next = head.next
                head.next = last
                # next.next = head <- don't need this line
                head, last = next, head
                count -= 1
            head = last
        return head