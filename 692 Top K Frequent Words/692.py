class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        hash_map = collections.Counter(words)
        heap = [(-freq, word) for word, freq in hash_map.items()]
        heapq.heapify(heap)
        return [heapq.heappop(heap)[1] for _ in range(k)]