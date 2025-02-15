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

    /* Instance Variables */
    final double MAX_LOAD;

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

    public void clear() {

    }

    public boolean containsKey(K key) {
        int address = key.hashCode() % buckets.length;
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

    public V get(K key) {
        int address = key.hashCode() % buckets.length;
        Collection<Node> bucket = buckets[address];
        if (bucket == null) return null;
        for (Node n: bucket) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void put(K key, V val) {
        int address = key.hashCode() % buckets.length;
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
        bucket.add(new Node(key, val));
        size += 1;

        if (needResize()) {
            resize();
        }
    }


    public Set<K> keySet() {
        return null;
    }

    public V remove(K key) {
        return null;
    }

    public V remove(K key, V val) {
        return null;
    }

    public Iterator<K> iterator() {
        return null;
    }

    private boolean needResize() {
        double loadFactor = (double) size / buckets.length;
        return loadFactor >= MAX_LOAD;
    }

    private void resize() {
        Collection<Node>[] newTable = createTable(buckets.length * 2);
        for (Collection<Node> c: buckets) {
            if (c != null) {
                for (Node n: c) {
                    int newAddress = n.key.hashCode() % newTable.length;
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

    public void printTable() {
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

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>(16);
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            int randomInt = r.nextInt(122 - 97 + 1) + 97;
            char randomChar = (char) randomInt;
            map.put(randomInt, String.valueOf(randomChar));
        }
        map.printTable();
        map.put(9, "hello");
        System.out.println(map.get(9));
    }
}
