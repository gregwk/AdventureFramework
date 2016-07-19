package adventure.util.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *  Unit testing of ListTree class
 *
 *  @author gregory
 *  @author Lulu 
 *  @version Jul 19, 2016
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
        //add a second child to the root for testing
        tree1.addChild("root", "second", new Integer(2));
    }

    @Test
    public void testEmptyTree() {
        assertTrue(!tree0.contains("root"));
        // do we need an isEmpty method?
        //I think it might be not necessary, because there is not an emptyTree operation to be tested
    }

    @Test
    public void testAddRoot() {
        assertTrue(tree1.isRoot("root"));
        assertTrue(tree1.contains("root"));
        assertEquals(tree1.parent("root"), "root"); 
        assertEquals(tree1.childCount("root"), 1);
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
    
	@Test
	public void testAddChildIntKKV() {
		tree1.addChild(0, "root", "first", new Integer(1));
		assertTrue(tree1.contains("first"));
		assertEquals(tree1.get("first"), new Integer(1));
	}


	
	@Test
	public void testContains() {
		assertTrue(tree1.contains("root"));
		assertEquals(tree1.get("root"), new Integer(0));
	}

	@Test
	public void testIsRoot() {
		assertTrue(tree1.isRoot("root"));
		assertEquals(tree1.isRoot("root"), true);
	}
	
	@Test
	public void testParent() {		
		assertTrue(tree1.contains(tree1.parent("second")));
		assertEquals(tree1.parent("second"), "root");
	}

	@Test
	public void testChildCount() {
		assertTrue(tree1.childCount("root")==1);
		assertEquals(tree1.childCount("root"), 1);
	}
	
	
	@Test
	public void testGet() {
		assertTrue(tree1.get("root")==0);
		assertEquals(tree1.get("root"), new Integer(0));
	}

	@Test
	public void testSet() {
		assertTrue(tree1.set("second",2)==2);
		assertEquals(tree1.set("second",2), new Integer(2));
	}
	
	@Test
	public void testRemove() {
		tree1.remove("second");
		assertTrue(!tree1.contains("second"));
		assertEquals(tree1.contains("second"), false);
	}

	@Test
	public void testIsDescendent() {// to be focused on		
        assertTrue(tree1.isDescendent("second", "root"));
        assertEquals(tree1.isDescendent("second", "root"),true);
        
	}


	@Test
	public void testMoveSubTreeKK() {// to be focused on
		tree1.addChild("second", "third", new Integer(3));
		tree1.moveSubTree("third", "root");
		assertTrue(tree1.isDescendent("third", "root"));
        assertEquals(tree1.childCount("root"), 2);

	}

	@Test
	public void testMoveSubTreeIntKK() {// to be focused on
		tree1.addChild(0, "second", "third", new Integer(3));
		tree1.moveSubTree(0, "third", "root");
		assertTrue(tree1.isDescendent("third", "root"));
        assertEquals(tree1.childCount("root"), 2);
	}


}

