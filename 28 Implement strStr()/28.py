class Solution:
    # Solution 1: simple Rabin-Karp algorithm
    # this method can't pass the situation that have large data
    # might be overflow
    def strStr(self, haystack: str, needle: str) -> int:
        if needle == '':
            return 0
        if not haystack or not needle:
            return -1
        if len(needle) > len(haystack):
            return -1
        prime = 26
        target_hash = compute_hash(needle, len(needle), prime, 0)[0]
        first_hash, post_hash = compute_hash(haystack, len(needle), prime, 0)
        if first_hash == target_hash and equal(haystack, needle, 0):
            return 0
        for hash_value, index in hash_model(post_hash, prime, haystack, len(needle)):
            if hash_value == target_hash and equal(haystack, needle, index):
                return index
        return -1


def equal(large: str, small: str, index: int) -> bool:
    for i in range(index, index + len(small)):
        if large[i] != small[i - index]:
            return False
    return True


def compute_hash(string: str, length: int, prime: int, index: int):
    pre_hash = ord(string[index]) * pow(prime, length - 1)
    post_hash = 0
    for i in range(index + 1, index + length):
        p = length - 1 - (i - index)
        post_hash += ord(string[i]) * pow(prime, p)
    return [pre_hash + post_hash, post_hash]


def hash_model(post_hash: int, prime: int, large: str, length: int):
    n, hash_value = 1, 0
    while n <= len(large) - length:
        hash_value = post_hash * prime + ord(large[n + length - 1])
        post_hash = hash_value - ord(large[n]) * pow(prime, length - 1)
        yield [hash_value, n]
        n += 1
    return 'done'


class Solution:
    # Solution 2: Rabin-Karp algorithm
    def strStr(self, haystack: str, needle: str) -> int:
        if needle == '':
            return 0
        if not haystack or not needle:
            return -1
        if len(needle) > len(haystack):
            return -1
        prime = 26
        target_hash = compute_hash(needle, len(needle), prime, 0)[0]
        first_hash, post_hash = compute_hash(haystack, len(needle), prime, 0)
        if first_hash == target_hash and equal(haystack, needle, 0):
            return 0
        for hash_value, index in hash_model(post_hash, prime, haystack, len(needle)):
            if hash_value == target_hash and equal(haystack, needle, index):
                return index
        return -1
