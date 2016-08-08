
package adventure.util.tree;

import java.util.List;

/**
 * A tree is a data structure that maintains the object values it holds in
 * the form of a rooted, ordered tree, whose nodes have unique keys.
 * The tree data structure in generic: the unique keys of the nodes
 * have type parameter K; the object values have type parameter V.
 * The tree is <strong>rooted</strong> because it contains a single root node
 * that serves as the ancestor to all other nodes in the tree.
 * The tree is <strong>ordered</strong> because the children of a given parent
 * node have a specific order from the first child (at index 0) to the last
 * child (at index childCount(parent) - 1).
 * 
 * @author Gregory K
 * @param <K> The type of the unique keys for each node
 * @param <V> The type of the object values the tree contains
 */
public interface Tree <K, V> {

    /**
     * Adds a root node with the specified key-value pair to an empty tree.
     * @param key the key that will designate the root node.
     * @param value the value to be added.
     * @throw IllegalStateException if the tree is not empty
     * @throw IllegalArgumentException if key is null
     * @throw IllegalArgumentException if value is null
     */
    public void addRoot(K key, V value);

    /**
     * Adds a child node with the specified key-value pair as the last child
     * of the specified parent node.
     * @param parent the key of the parent node
     * @param key the key of the node to be added
     * @param value the value in the node to be added
     * @throw IllegalArgumentException if the parent is not in the tree
     * @throw IllegalArgumentException if key is already in the tree
     * @throw IllegalArgumentException if key is null
     * @throw IllegalArgumentException if value is null
     */
    public void addChild(K parent, K key, V value);

    /**
     * Adds a child node with the specified key-value pair to the specified
     * parent at the specified index.
     * @param parent the key of the parent node
     * @param key the key of the node to be added
     * @param value the value in the node to be added
     * @param index the index of the node to be added
     * @throw IllegalArgumentException if the parent is not in the tree
     * @throw IllegalArgumentException if the key is already in the tree
     * @throw IllegalArgumentException if the key is null
     * @throw IllegalArgumentException if the value is null
     * @throw IndexOutOfBoundsException if the index is less than 0 or
     *        greater than the number of children
     */
    public void addChild(int index, K parent, K key, V value);

    /**
     * Returns true if the node is in the tree.
     * @param key the node to be checked
     * @return true if the node is in the tree
     */
    public boolean contains(K key);
    
    /**
     * Returns true if the node is the root node.
     * @param key the node to be checked
     * @return true if the node is the root node
     * @throw IllegalArgumentException if the node is not in the tree
     */
    public boolean isRoot(K key);

    /**
     * Returns the parent of the specified node.
     * @param key the node whose parent is to be returned
     * @return the parent of the specified node
     * @throw IllegalArgumentException if the node is not in the tree
     * @throw IllegalArgumentException if the node is the root
     */
    public K parent(K key);

    /**
     * Returns the number of children the parent node contains
     * @param parent the parent whose child count is to be returned
     * @return the number of children the parent node contains
     * @throw IllegalArgumentException if the parent is not in the tree
     */
    public int childCount(K parent);

    /**
     * Returns the value in the specified node
     * @param key the key of the node whose value is to be returned
     * @return the value in the specified node
     * @throw IllegalArgumentException if the node is not in the tree
     */
    public V get(K key);

    /**
     * Updates the value in the specified node with the specified value
     * and returns the original value
     * @param key the node whose value is to be updated
     * @param value the new (updated) value
     * @throw IllegalArgumentException if the node is not in the tree
     * @throw IllegalArgumentException if the new value is null
     */
    public V set(K key, V value);

    /**
     * Removes the specified leaf node and returns its value
     * @param key the leaf node to be removed
     * @return the value of the specified node
     * @throw IllegalArgumentException if the node is not in the tree
     * @throw IllegalArgumentException if the node is not a leaf node
     */
    public V remove(K key);

    /**
     * Returns true if the first node is a descendent of second node
     * @param first the node that is checked for being the descendent
     * @param second the node that is checked for being the ancestor
     * @return true if the first node is a descendent of the second node
     * @throw IllegalArgumentException if the first node is not in the tree
     * @throw IllegalArgumentException if the second node is not in the tree
     */
    public boolean isDescendent(K first, K second);

    /**
     * Moves the sub tree rooted at the specified node to the specified parent
     * @param subtreeRoot the root of the sub-tree to be moved
     * @param parent the parent node under which the sub tree is to be moved
     * @throw IllegalArgumentException if the subtree root is not in the tree
     * @throw IllegalArgumentException if the parent is not in the tree
     * @throw IllegalArgumentException if the parent is a descendent of the
     * node to be moved
     */
    public void moveSubTree(K subtreeRoot, K parent);

    /**
     * Moves the sub tree rooted at the specified node to the specified parent
     * @param subtreeRoot the root of the sub-tree to be moved
     * @param parent the parent node under which the sub tree is to be moved
     * @param index the child index where the subtree will be moved
     * @throw IllegalArgumentException if the subtree root is not in the tree
     * @throw IllegalArgumentException if the parent is not in the tree
     * @throw IllegalArgumentException if the parent is a descendent of the
     * node to be moved
     * @throw IndexOutOfBoundsException if the index is less than 0 or
     *        greater than the number of children
	 */
    public void moveSubTree(int index, K subtreeRoot, K parent);
    
    /**
     * Returns the children of the specified node.
     * 
     * @throw IllegalArgumentException if the node is not in the tree
     * @param key The specified node.
     * @return	the children of the specified node.
     */
    public List<V> getChildren(K key);
    
    /**
     * Clear tree
     */
    public void clear();
 
}
