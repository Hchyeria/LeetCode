class LRUCache {
    private static class Node {
        Node prev, next;
        int value;
        int key;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }
    private final int CAPACITY;
    private Node head;
    private Node tail;
    private Map<Integer, Node> cache;
    
    public LRUCache(int capacity) {
        CAPACITY = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        remove(node);
        insert(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            remove(node);
        } else if (cache.size() < CAPACITY) {
            node = new Node(key, value);
        } else {
            node = head.next;
            remove(node);
            node = new Node(key, value);
        }
        insert(node);
    }
    

    private Node insert(Node node) {
        cache.put(node.key, node);
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;
        node.next = tail;
        return node;
    }

    private Node remove(Node node) {
        cache.remove(node.key, node);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
        return node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */