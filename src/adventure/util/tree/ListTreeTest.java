package tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author gregory
 *  @version Jul 12, 2016
 */
public class ListTreeTest {

    // instance variables
    private Tree<String, Integer> tree0;
    private Tree<String, Integer> tree1;

    /**
     * Place a description of your method here.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        tree0 = new ListTree<>();
        tree1 = new ListTree<>();
        tree1.addRoot("root", 0);
    }

    @Test
    public void testEmptyTree() {
        assertTrue(!tree0.contains("root"));
        // do we need an isEmpty method?
    }

    @Test
    public void testAddRoot() {
        assertTrue(tree1.isRoot("root"));
        assertTrue(tree1.contains("root"));
        assertEquals(tree1.parent("root"), "root"); //
        assertEquals(tree1.childCount("root"), 0);
    }

    @Test
    public void testAddChild() {
        tree1.addChild("root", "first", 1);
        assertTrue(tree1.contains("first"));
        assertEquals(tree1.get("first"), new Integer(1));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAddRootKeyIsNull() {
        tree0.addRoot(null, 0);
        fail();
    }
}
