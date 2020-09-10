public class HashMapLinearProbing<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int size;
    private float loadFactor;
    private K[] keys;
    private V[] values;

    public HashMapLinearProbing(int capacity, float loadFactor) {
        if (capacity < 0) {
            // handle exception
            throw new IllegalArgumentException("capacity should larger than 0");
        }
        this.size = 0;
        this.loadFactor = loadFactor;
        this.keys = (K[]) new Object[capacity];
        this.values = (V[]) new Object[capacity];
    }

    public HashMapLinearProbing() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMapLinearProbing(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        this.keys = (K[]) new Object[keys.length];
        this.values = (V[]) new Object[keys.length];
        size = 0;
    }

    // non-negative hash value
    private int hash(K key) {
        // in separate chaining hash table, if K key is null, it is always
        // mapped to bucket 0, but bucket 0 can have other key-value pairs
        // in linear probing, we don't allow key or value to be null
        return key.hashCode() & 0x7FFFFFFF;
    }

    private int getIndex(K key) {
        return hash(key) % keys.length;
    }

    private boolean equalsKey(K k1, K k2) {
        return (k1 == k2) || (k1 != null && k1.equals(k2));
    }

    private boolean containsKey(K key) {
        // not allow null key
        if (key == null) {
            return false;
        }
        for (int index = getIndex(key); this.keys[index] != null; index = (index + 1) % this.keys.length) {
            if (equalsKey(this.keys[index], key)) {
                return true;
            }
        }
        return false;
    }

    private boolean equalsValue(V v1, V v2) {
        return (v1 == v2) || (v1 != null && v1.equals(v2));
    }

    private boolean containsValue(V v) {
        if (isEmpty() || v == null) {
            return false;
        }
        for (V value : values) {
            if (equalsValue(value, v)) {
                return true;
            }
        }
        return false;
    }

    public V put(K key, V value) {
        if (key == null) {
            return null;
        }
        // when value == null
        if (value == null) {
            return remove(key);
        }
        int index = getIndex(key);
        for ( ; keys[index] != null; index = (index + 1) % keys.length) {
            if (equalsKey(keys[index], key)) {
                V res = values[index];
                values[index] = value;
                return res;
            }
        }
        keys[index] = key;
        values[index] = value;
        size++;
        if (needRehash()) {
            rehash();
        }
        return null;
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        for (int index = getIndex(key); keys[index] != null; index = (index + 1) % keys.length) {
            if (equalsKey(keys[index], key)) {
                return values[index];
            }
        }
        return null;
    }

    private boolean needRehash() {
        float ratio = (size + 0.0f) / keys.length;
        return ratio >= loadFactor;
    }

    private void rehash() {
        HashMapLinearProbing<K, V> temp = new HashMapLinearProbing<>(keys.length * 2, loadFactor);
        for (int index = 0; index < keys.length; ++index) {
            if (keys[index] != null) {
                temp.put(keys[index], values[index]);
            }
        }
        this.keys = temp.keys;
        this.values = temp.values;
    }

    public V remove(K key) {
        for (int index = getIndex(key); keys[index] != null; index = (index + 1) % keys.length) {
            if (equalsKey(keys[index], key)) {
                V res = values[index];
                keys[index] = null;
                values[index] = null;
                size--;
                // NOTE: need to rehash entries after the deleted entry
                // not i=index+1; i<keys.length; ++i
                for (int i = index + 1; keys[i] != null; i = (i + 1) % keys.length) {
                    K tempK = keys[i];
                    V tempV = values[i];
                    keys[i] = null;
                    values[i] = null;
                    size--;
                    put(tempK, tempV);
                }
                return res;
            }
        }
        return null;
    }
}