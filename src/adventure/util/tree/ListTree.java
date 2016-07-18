package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Implements the Tree interface using a Java List.
 * 
 * @author Gregory K
 * @param <K>
 * @param <V>
 */
public class ListTree<K, V> implements Tree<K, V> {

    class Node {
        public K parent;
        public K key;
        public V value;
        public List<Node> children;
        public Node(K parentArg, K keyArg, V valueArg) {
            parent = parentArg;
            key = keyArg;
            value = valueArg;
            children = new LinkedList<>();
        }
    }
    
    private final Map<K, Node> map;
    private Node root;
    
    public ListTree() {
        map = new HashMap<>();
        root = null;
    }
    
    @Override
    public void addRoot(K key, V value) {
        if (root != null) throw new IllegalStateException();
        if (key == null) throw new IllegalArgumentException();
        if (value == null) throw new IllegalArgumentException();
        Node node = new Node(key, key, value);
        map.put(key, node);
        root = node;
    }

    @Override
    public void addChild(K parent, K key, V value) {
        if (!map.containsKey(parent)) throw new IllegalArgumentException();
        if (map.containsKey(key)) throw new IllegalArgumentException();
        if (key == null) throw new IllegalArgumentException();
        if (value == null) throw new IllegalArgumentException();
        Node node = new Node(parent, key, value);
        map.put(key, node);
        Node pNode = map.get(parent);
        pNode.children.add(node);
    }

    @Override
    public void addChild(int index, K parent, K key, V value) {
        if (!map.containsKey(parent)) throw new IllegalArgumentException();
        if (map.containsKey(key)) throw new IllegalArgumentException();
        if (key == null) throw new IllegalArgumentException();
        if (value == null) throw new IllegalArgumentException();
        if (index < 0 || index > childCount(parent)) throw new IndexOutOfBoundsException();
        Node node = new Node(parent, key, value);
        map.put(key, node);
        Node pNode = map.get(parent);
        pNode.children.add(index, node);
    }

    @Override
    public boolean contains(K key) {
        return map.containsKey(key);
    }
    
    @Override
    public boolean isRoot(K key) {
        if (!map.containsKey(key)) throw new IllegalArgumentException();
        return parent(key).equals(key);
    }

    @Override
    public K parent(K key) {
        if (!map.containsKey(key)) throw new IllegalArgumentException();
        return map.get(key).parent;
    }

    @Override
    public int childCount(K parent) {
        if (!map.containsKey(parent)) throw new IllegalArgumentException();        
        return map.get(parent).children.size();
    }

    @Override
    public V get(K key) {
        if (!map.containsKey(key)) throw new IllegalArgumentException();
        return map.get(key).value;
    }

    @Override
    public V set(K key, V value) {
        if (!map.containsKey(key)) throw new IllegalArgumentException();
        if (value == null) throw new IllegalArgumentException();
        Node node = map.get(key);
        V result = node.value;
        node.value = value;
        return result;
    }

    @Override
    public V remove(K key) {
        if (!map.containsKey(key)) throw new IllegalArgumentException();
        if (childCount(key) != 0) throw new IllegalArgumentException();
        Node node = map.get(key);
        Node pNode = map.get(node.parent);
        pNode.children.remove(node);
        map.remove(key);
        return node.value;
    }

    @Override
    public boolean isDescendent(K first, K second) {
        if (!map.containsKey(first)) throw new IllegalArgumentException();
        if (!map.containsKey(second)) throw new IllegalArgumentException();
        if (root.key.equals(second)) return true;
        if (first.equals(second)) return true;
        K parent = parent(first);
        while (!root.key.equals(parent)) {
            if (second.equals(parent)) return true;
            parent = parent(parent);
        }
        return false;
    }

    @Override
    public void moveSubTree(K subtreeRoot, K parent) {
        if (!map.containsKey(subtreeRoot)) throw new IllegalArgumentException();
        if (!map.containsKey(parent)) throw new IllegalArgumentException();
        if (isDescendent(parent, subtreeRoot)) throw new IllegalArgumentException();
        // disconnect subtree from parent
        Node node = map.get(subtreeRoot);
        Node origPNode = map.get(parent(subtreeRoot));
        origPNode.children.remove(node);
        // reconnect to new parent
        Node pNode = map.get(parent);
        pNode.children.add(node);
    }

    @Override
    public void moveSubTree(int index, K subtreeRoot, K parent) {
        if (!map.containsKey(subtreeRoot)) throw new IllegalArgumentException();
        if (!map.containsKey(parent)) throw new IllegalArgumentException();
        if (isDescendent(parent, subtreeRoot)) throw new IllegalArgumentException();
        if (index < 0 || index > childCount(parent)) throw new IndexOutOfBoundsException();
        // disconnect subtree from parent
        Node node = map.get(subtreeRoot);
        Node origPNode = map.get(parent(subtreeRoot));
        origPNode.children.remove(node);
        // reconnect to new parent
        Node pNode = map.get(parent);
        pNode.children.add(index, node);
    }
    
}
