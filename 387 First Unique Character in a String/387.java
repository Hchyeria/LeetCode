class Solution {
    class Solution {
        public int firstUniqChar(String s) {
            if (s == null || s.length() == 0) {
                return -1;
            }
            int[] count = new int[26];
            int n = s.length();
            for (int i = 0; i < n; ++i) {
                count[s.charAt(i) - 'a']++;
            } 
            for (int i = 0; i < n; ++i) {
                if (count[s.charAt(i) - 'a'] == 1) {
                    return i;
                }
            }
            return -1;
        }
    
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    // Follow up: if there is an unlimited stream?
    private static class Node {
        char key;
        Node pre, next;
        int index;
        
        Node() { }
        Node(char k, int i) {
            key = k;
            index = i;
        }
    }
    
    private final Node head = new Node();
    private final Node tail = new Node();
    private Map<Character, Node> map;
    
    public int firstUniqChar(String s) {
        map = new HashMap<>();
        head.next = tail;
        tail.pre = head;
        if (s == null || s.length() == 0) {
            return -1;
        }
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            read(s.charAt(i), i);
        }
        return firstNonRepeating();
    }
    

    private void read(char ch, int index) {
        if (!map.containsKey(ch)) {
            insert(new Node(ch, index));
        } else {
            Node node = map.get(ch);
            if (node != null) {
                remove(node);
            }
        }
    }

    private void insert(Node node) {
        map.put(node.key, node);
        tail.pre.next = node;
        node.pre = tail.pre;
        tail.pre = node;
        node.next = tail;
    }
    
    private void remove(Node node) {
        map.put(node.key, null);
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = node.next = null; 
    }

    public int firstNonRepeating() {
        if (head.next == tail) {
            return -1;
        }
        return head.next.index;
    }
}