package hashmap;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private class MapIterator implements Iterator<K> {
        private int visited = 0;
        private int tablePointer = 0;
        private Iterator<Node> bucketIter;
        private Collection<Node> currBucket;

        public MapIterator() {
            currBucket = getNextBucket();
            if (currBucket != null) {
                bucketIter = currBucket.iterator();
            }
        }

        public boolean hasNext() {
            return visited < size;
        }

        public K next() {
            if (!hasNext()) return null;
            if (currBucket == null || !bucketIter.hasNext()) {
                tablePointer += 1;
                currBucket = getNextBucket();
                bucketIter = currBucket.iterator();
            }
            visited += 1;
            Node n = bucketIter.next();
            return n.key;
        }

        private Collection<Node> getNextBucket() {
            while (hasNext() && buckets[tablePointer] == null) {
                tablePointer += 1;
            }
            return buckets[tablePointer];
        }
    }

    /* Instance Variables */
    private final double MAX_LOAD;

    private Collection<Node>[] buckets;
    private int size = 0;
    // You should probably define some more!

    /**
     * Constructors
     */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.MAX_LOAD = maxLoad;
        this.buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    @Override
    public void clear() {
        buckets = createTable(buckets.length);
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int address = getAddress(key, buckets);
        if (buckets[address] != null) {
            Collection<Node> bucket = buckets[address];
            for (Node n : bucket) {
                if (n.key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int address = getAddress(key, buckets);
        Collection<Node> bucket = buckets[address];
        if (bucket == null) return null;
        for (Node n : bucket) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V val) {
        int address = getAddress(key, buckets);
        if (buckets[address] == null) {
            buckets[address] = createBucket();
        }
        Collection<Node> bucket = buckets[address];
        for (Node n : bucket) {
            if (n.key.equals(key)) {
                n.value = val;
                return;
            }
        }
        bucket.add(createNode(key, val));
        size += 1;

        if (needResize()) {
            resize();
        }
    }


    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Collection<Node> c : buckets) {
            if (c != null) {
                for (Node n : c) {
                    keySet.add(n.key);
                }
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        V removedValue = null;
        int address = getAddress(key, buckets);
        Collection<Node> bucket = buckets[address];
        for (Node n : bucket) {
            if (n.key.equals(key)) {
                removedValue = n.value;
                bucket.remove(n);
            }
        }
        return removedValue;
    }

    @Override
    public V remove(K key, V val) {
        V removedValue = null;
        int address = getAddress(key, buckets);
        Collection<Node> bucket = buckets[address];
        for (Node n : bucket) {
            if (n.key.equals(key) && n.value.equals(val)) {
                removedValue = n.value;
                bucket.remove(n);
            }
        }
        return removedValue;
    }

    @Override
    public Iterator<K> iterator() {
        return new MapIterator();
    }

    private boolean needResize() {
        double loadFactor = (double) size / buckets.length;
        return loadFactor >= MAX_LOAD;
    }

    private void resize() {
        Collection<Node>[] newTable = createTable(buckets.length * 2);
        for (Collection<Node> c : buckets) {
            if (c != null) {
                for (Node n : c) {
                    int newAddress = getAddress(n.key, newTable);
                    if (newTable[newAddress] == null) {
                        newTable[newAddress] = createBucket();
                    }
                    Collection<Node> bucket = newTable[newAddress];
                    bucket.add(n);
                }
            }
        }
        buckets = newTable;
    }

    private int getAddress(K key, Collection<Node>[] table) {
        return Math.floorMod(key.hashCode(), table.length);
    }

    private void printTable() {
        for (int i = 0; i < buckets.length; i++) {
            System.out.print("bucket " + i + ":");
            if (buckets[i] == null) {
                System.out.println("empty bucket");
            } else {
                for (Node n : buckets[i]) {
                    System.out.print("--> { " + n.key + ": " + n.value + " }");
                }
            }
            System.out.print("\n");
        }
    }
}
