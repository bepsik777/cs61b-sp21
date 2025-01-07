package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.lang.Comparable;

import static org.junit.Assert.assertTrue;

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

    private class BSTMapIterator implements Iterator<K> {
        ArrayList<K> visitedKeys;
        int pointer;

        public BSTMapIterator() {
            visitedKeys = new ArrayList<>();
            pointer = 0;
            inOrder(root);
        }

        private void inOrder(Node n) {
            if (n == null) {
                return;
            }

            inOrder(n.left);
            visitedKeys.add(n.key);
            inOrder(n.right);
        }

        public boolean hasNext() {
            return pointer < size;
        }


        public K next() {
            if (!hasNext()) {
                return null;
            }
            ;
            K curr = visitedKeys.get(pointer);
            pointer += 1;
            return curr;
        }
    }


    private Node root;
    private int size;
    private Node previouslyRemoved;


    public BSTMap() {
        root = null;
        size = 0;
        previouslyRemoved = null;
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

    private Node remove(K key, Node n) {
        if (n == null) {
            return null;
        }

        int cmp = key.compareTo(n.key);

        if (cmp < 0) {
            n.left = remove(key, n.left);
        } else if (cmp > 0) {
            n.right = remove(key, n.right);
        } else {
            size -= 1;
            previouslyRemoved = n;
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;

            if (root.key.compareTo(n.key) < 0) {
                deleteMin(root.right);
            } else {
                deleteMax(root.left);
            }

            n.key = previouslyRemoved.key;
            n.val = previouslyRemoved.val;
            previouslyRemoved = n;
        }
        return n;
    }

    @Override
    public V remove(K key) {
        root = remove(key, root);
        V target = previouslyRemoved.val;
        previouslyRemoved = null;
        return target;
    }

    private Node deleteMin(Node n) {
        if (n.left == null) {
            previouslyRemoved = n;
            size = size - 1;
            return n.right;
        }
        n.left = deleteMin(n.left);
        return n;
    }

    private Node deleteMax(Node n) {
        if (n.right == null) {
            previouslyRemoved = n;
            size = size - 1;
            return n.left;
        }
        n.right = deleteMin(n.right);
        return n;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
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
}
