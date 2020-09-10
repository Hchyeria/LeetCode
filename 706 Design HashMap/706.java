import java.util.Arrays;

public class HashMapSeparateChaining<K, V> {
    // entry class
    // why public static??
    // 1. define a class Node for each Node in the HashMap
    // since it is closely bounded with HashMapSeparateChaining<K, V> and we
    // may need access it from outer class (public), so we need to use
    // inner static class (Map.Entry<K, V>)
    // HashMapSeparateChaining.Node to avoid rename collision
    // it is static, so there is no need to create a HashMap object
    // to utilize them
    public static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    // static final variable are global constants
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] array;
    private int size;
    private float loadFactor;

    public HashMapSeparateChaining() {
        // call public HashMapSeparateChaining(int capacity, float loadFactor)
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMapSeparateChaining(int capacity) {
        // call public HashMapSeparateChaining(int capacity, float loadFactor)
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMapSeparateChaining(int capacity, float loadFactor) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity should larger than 0");
        }
        this.size = 0;
        this.loadFactor = loadFactor;
        // not allow new generics array
        // new Node<K, V>[capacity]
        // should use cast
        // create an array and make it usable for generics
        this.array = (Node<K, V>[]) new Node[capacity];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        Arrays.fill(this.array, null);
        size = 0;
    }

    // non-negative hash value
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        // 1. int code = key.hashCode();
        // return code >= 0 ? code : -code;
        // int range = [-2^31, 2^31 - 1]
        // might result in overflow if code equals Integer.MIN_VALUE
        // 2. in Java, % returns remainder, instead of modulus,
        // and remainder could be negative
        // - 2 % 4 = -2
        // so we use a bit operation trick here
        // 7FFFFFFF = 01111111 11111111 11111111 11111111
        // key.hashCode() & 0X7FFFFFFF can flop negative bit 1 to 0
        // without change other bits 1 or 0
        // key.hashCode() & Integer.MAX_VALUE; is same with
        // but not intuitive, when bit operation
        return key.hashCode() & 0x7FFFFFFF;
    }

    private int getIndex(K key) {
        return hash(key) % array.length;
    }

    private boolean equalsKey(K k1, K k2) {
        // Objects.equals(k1, k2)
        return (k1 == k2) || (k1 != null && k1.equals(k2));
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (equalsKey(node.getKey(), key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private boolean equalsValue(V v1, V v2) {
        /*
            if (v1 == null && v2 == null) {
                return true;
            }
            if (v1 == null || v2 == null) {
                return false;
            }
            return v1.equals(v2);
            or
        */
        return (v1 == v2) || (v1 != null && v1.equals(v2));
    }

    // time complexity is O(n)
    public boolean containsValue(V value) {
        if (isEmpty()) {
            return false;
        }
        for (Node<K, V> node : array) {
            while (node != null) {
                if (equalsValue(node.getValue(), value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }
    // here we allow value to be null
    // in some text books, they delete the key-value pair, when value is null
    public V put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = array[index];
        Node<K, V> p = head;
        while (p != null) {
            // if given key is in the map bucket
            // update its value and return old value
            if (equalsKey(p.getKey(), key)) {
                V res = p.getValue();
                p.setValue(value);
                return res;
            }
            p = p.next;
        }
        // up to this point, key does NOT exist in the HashMap
        // insert to the head of this bucket, and update the head
        Node<K, V> newHead = new Node<>(key, value);
        newHead.next = head;
        array[index] = newHead;
        size++;
        if (needRehash()) {
            rehash();
        }
        return null;
    }

    // if key does NOT exist in the HashMap, return null
    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (equalsKey(node.getKey(), key)) {
                return node.getValue();
            }
            node = node.next;
        }
        return null;
    }

    private boolean needRehash() {
        float ratio = (size + 0.0f) / array.length;
        return ratio >= loadFactor;
    }

    private void rehash() {
        HashMapSeparateChaining<K, V> temp = new HashMapSeparateChaining<>(array.length * 2, loadFactor);
        for (Node<K, V> node : array) {
            while (node != null) {
                temp.put(node.getKey(), node.getValue());
                node = node.next;
            }
        }
        this.array = temp.array;
        // this.size does NOT change, this.loadFactor does NOT change
        // this.size = temp.size;
        // this.loadFactor = temp.loadFactor;
    }

    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        // delete operation on a linked-list
        // key does NOT exist in this HashMap
        if (node == null) {
            return null;
        }
        if (equalsKey(node.getKey(), key)) {
            array[index] = node.next;
            node.next = null; // to be garbage collected
            size--;
            return node.getValue();
        }
        Node<K, V> pre = node;
        node = node.next;
        while (node != null) {
            if (equalsKey(node.getKey(), key)) {
                pre.next = node.next;
                node.next = null; // to be garbage collected
                size--;
                return node.getValue();
            }
            pre = node;
            node = node.next;
        }

        // key does NOT exist in this HashMap
        return null;
    }


}
