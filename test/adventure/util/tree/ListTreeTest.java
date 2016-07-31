package adventure.util.tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
        assertTrue(tree1.parent("first").equals("root"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAddRootKeyIsNull() {
        tree0.addRoot(null, 0);
        fail();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testAddChildKeyIsNull(){
    	tree1.addChild("root", null, 1);
    	fail();
    }
    
	@Test
	public void testAddChildIntKKV() {
		tree1.addChild(0, "root", "first", new Integer(1));
		assertTrue(tree1.contains("first"));
		assertEquals(tree1.get("first"), new Integer(1));
	}

    @Test(expected=IllegalArgumentException.class)
    public void testAddChildIntKKVKeyIsNull(){
    	tree1.addChild(0,"root", null, 1);
    	fail();
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
	
	@Test(expected=IllegalArgumentException.class)
    public void testIsRootIsNull(){
    	tree1.isRoot("null");
    	fail();
    }
	
	@Test
	public void testParent() {	
		tree1.addChild("second", "third", 3);
		assertTrue(tree1.contains(tree1.parent("third")));
		assertEquals(tree1.parent("third"), "second");
		assertEquals(tree1.parent("second"), "root");
		assertTrue(tree1.isDescendent("third", "second"));
		assertTrue(tree1.isDescendent("third", "root"));
	}
	
	@Test(expected=IllegalArgumentException.class)
    public void testParentIsNull(){
    	tree1.parent("null");
    	fail();
    }

	@Test
	public void testChildCount() {
		assertTrue(tree1.childCount("root")==1);
		assertEquals(tree1.childCount("root"), 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChildCountIsNull() {
		assertTrue(tree1.childCount("null")==0);
		fail();
	}
	
	
	@Test
	public void testGet() {
		assertTrue(tree1.get("root")==0);
		assertEquals(tree1.get("root"), new Integer(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetIsNull() {
		assertTrue(tree1.get("null")==null);
		fail();
	}
	

	@Test
	public void testSet() {
		assertTrue(tree1.set("second",2)==2);
		assertEquals(tree1.set("second",2), new Integer(2));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetIsNull() {
		assertTrue(tree1.set("null",0)==0);
		assertTrue(tree1.set("root",null)==null);
		fail();
	}
	
	@Test
	public void testRemove() {
		tree1.remove("second");
		assertTrue(!tree1.contains("second"));
		assertEquals(tree1.contains("second"), false);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveIsNull() {
		tree1.remove("null");
		tree1.remove("second");
		assertTrue(!tree1.contains("null"));
		assertTrue(tree1.childCount("second")!=0);
		fail();
	}


	@Test
	public void testIsDescendent() {// to be focused on	
		tree1.addChild("second", "third", 3);
        assertTrue(tree1.isDescendent("second", "root"));
        assertEquals(tree1.isDescendent("second", "root"),true);
        assertTrue(tree1.isDescendent("third", "root"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIsDescendentIsNull() {	
		tree1.addChild("second", "third", 3);
        assertTrue(tree1.isDescendent("null", "root"));
        assertTrue(tree1.isDescendent("third", "null"));
        fail();
	}

	@Test
	public void testMoveSubTreeKK() {// to be focused on
		tree1.addChild("second", "third", 3);
		tree1.moveSubTree("second", "root");
		//here, the subtree with root of "second" and leaf of "third" should have been removed from "root"
		assertTrue(tree1.isDescendent("third", "root"));  //however "third" still is "root"'s descent
		assertTrue(tree1.isDescendent("second", "root"));//however "second" still is "root"'s descent
		assertTrue(tree1.parent("third").equals("second"));//however "third" still has a parent "second"
		assertTrue(tree1.parent("second").equals("root"));//however "second" still is "root"'s child
		assertEquals(tree1.parent("third"),"second");
       assertEquals(tree1.childCount("root"), 1);//"root" has one child , meaning "second" is not removed from the tree

	}

	@Test(expected=IllegalArgumentException.class)
	public void testMoveSubTreeKKIsNull() {
		tree1.addChild("second", "third", 3);
		tree1.moveSubTree("null", "root");
		tree1.moveSubTree("second", "null");
		//here, the subtree with root of "second" and leaf of "third" should have been removed from "root"
		assertTrue(tree1.isDescendent("third", "null"));  //just use "null" randomly, they all pass
		assertTrue(tree1.isDescendent("null", "root"));//just use "null" randomly, they all pass
		assertTrue(tree1.parent("third").equals("null"));//just use "null" randomly, they all pass
		assertTrue(tree1.parent("second").equals("null"));//just use "null" randomly, they all pass
		assertEquals(tree1.parent("null"),"second");
       assertEquals(tree1.childCount("null"), 1);//
       //is seems i do not have to use fail();
       

	}
	
	@Test
	public void testMoveSubTreeIntKK() {// the same with testMoveSubTree()
		tree1.addChild("second", "third", 3);
		tree1.moveSubTree(0,"second", "root");
		//here, the subtree with root of "second" and leaf of "third" should have been removed from "root"
		assertTrue(tree1.isDescendent("third", "root"));  //however "third" still is "root"'s descent
		assertTrue(tree1.isDescendent("second", "root"));//however "second" still is "root"'s descent
		assertTrue(tree1.parent("third").equals("second"));//however "third" still has a parent "second"
		assertTrue(tree1.parent("second").equals("root"));//however "second" still is "root"'s child
		assertEquals(tree1.parent("third"),"second");
       assertEquals(tree1.childCount("root"), 1);//"root" has one child , meaning "second" is not removed from the tree
	}


}
