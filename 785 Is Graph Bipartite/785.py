from collections import deque
class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        if not graph:
            return False
        n = len(graph)
        colors = [-1 for i in range(n)]
        for index, node in enumerate(graph):
            if node and colors[index] < 0:
                colors[index] = 0
                if not helper(graph, index, colors):
                    return False
        return True


def helper(graph, index, colors):
    queue = deque()
    queue.append(index)
    while queue:
        node = queue.popleft()
        for item in graph[node]:
            if colors[item] < 0:
                colors[item] = colors[node] ^ 1
                queue.append(item)
            elif colors[item] != colors[node] ^ 1:
                return False
    return True