package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.lang.Comparable;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node {
        K key;
        V val;
        Node left;
        Node right;

        public Node(K k, V v) {
            key = k;
            val = v;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;


    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }


    private boolean containsKey(K key, Node n) {
        if (n == null) {
            return false;
        }

        int cmp = key.compareTo(n.key);
        if (cmp > 0) {
            return containsKey(key, n.right);
        } else if (cmp < 0) {
            return containsKey(key, n.left);
        } else {
            return true;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(key, root);
    }

    private V get(K key, Node n) {
        if (n == null) {
            return null;
        }

        int cmp = key.compareTo(n.key);
        if (cmp > 0) {
            return get(key, n.right);
        } else if (cmp < 0) {
            return get(key, n.left);
        } else {
            return n.val;
        }
    }

    @Override
    public V get(K key) {
        return get(key, root);
    }

    @Override
    public int size() {
        return size;
    }

    private Node put(K key, V value, Node n) {
        if (n == null) {
            size += 1;
            return new Node(key, value);
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = put(key, value, n.left);
        } else if (cmp > 0) {
            n.right = put(key, value, n.right);
        } else {
            n.val = value;
        }
        return n;
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private void printInOrder(Node n) {
        if (n == null) {
            return;
        }

        if (n.left != null) {
            printInOrder(n.left);
        }

        System.out.println("key: " + n.key + " value: " + n.val);

        if (n.right != null) {
            printInOrder(n.right);
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }

    public static void main(String[] args) {
        BSTMap<Integer, String> bst = new BSTMap<>();
        bst.put(5, "cat");
        bst.put(1, "hello");
        bst.put(8, "de nada");
        bst.put(3, "whatever");
        bst.put(3, "nice");
        bst.put(11, null);
        bst.printInOrder();
        System.out.println(bst.containsKey(11));
    }

}
