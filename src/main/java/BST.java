import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {
    private Node root;
    private int size = 0;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public static class Entry<K, V> {
        private K key;
        private V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }
        Node curr = root;
        while (true) {
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) {
                if (curr.left == null) {
                    curr.left = new Node(key, val);
                    size++;
                    return;
                }
                curr = curr.left;
            } else if (cmp > 0) {
                if (curr.right == null) {
                    curr.right = new Node(key, val);
                    size++;
                    return;
                }
                curr = curr.right;
            } else {
                curr.val = val;
                return;
            }
        }
    }

    public V get(K key) {
        Node curr = root;
        while (curr != null) {
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else return curr.val;
        }
        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node curr = root;
        while (curr != null && !curr.key.equals(key)) {
            parent = curr;
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) curr = curr.left;
            else curr = curr.right;
        }
        if (curr == null) return;
        size--;
        if (curr.left == null || curr.right == null) {
            Node child = (curr.left != null) ? curr.left: curr.right;
            if (parent != null) {
                root = child;
            } else if (parent.left == curr) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
        else {
            Node successorParent = curr;
            Node successor = curr.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            curr.key = successor.key;
            curr.val = successor.val;
            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
        }
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Entry<K, V>> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator() {
            Node curr = root;
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        @Override
        public Entry<K, V> next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node curr = stack.pop();
            Entry<K, V> entry = new Entry<>(curr.key, curr.val);

            Node next = curr.right;
            while (next != null) {
                stack.push(next);
                next = next.left;
            }
            return entry;
        }
    }
}
