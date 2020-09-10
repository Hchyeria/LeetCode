# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None
import heap
class Solution:
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        if not lists or not len(lists):
            return lists
        if len(lists) == 1:
            return lists[0]
        heap = []
        dummy = ListNode(0)
        cur = dummy
        for list_node in lists:
            # be careful about [[], []] this case
            if list_node:
                heapq.heappush(heap, (list_node.val, id(list_node), list_node))

        while heap:
            next_item = heapq.heappop(heap)[2]
            cur.next = next_item
            cur = cur.next
            if next_item.next:
                heapq.heappush(heap, (next_item.next.val, id(next_item.next), next_item.next))

        return dummy.next


""" 
TypeError '<' not supported between instances of 'ListNode' and 'ListNode'

this error occurs because ListNode definition does not include __lt__ method. 
To avoid this error, we can use a wrapper class with __lt__ method for ListNode

or use the index heapq.heappush(heap, (list_node.val, id(list_node), list_node))
My solution is insert a increasing value as second item 
so that no comparison will be made between ListNodes because no 2 itemindex are equal.

"""
from queue import PriorityQueue

class Solution():
    def mergeKLists(self, lists):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """
        class Wrapper():
            def __init__(self, node):
                self.node = node
            def __lt__(self, other):
                return self.node.val < other.node.val
        
        head = point = ListNode(0)
        q = PriorityQueue()
        for l in lists:
            if l:
                q.put(Wrapper(l))
        while not q.empty():
            node = q.get().node
            point.next = node
            point = point.next
            node = node.next
            if node:
                q.put(Wrapper(node))
        return head.next

