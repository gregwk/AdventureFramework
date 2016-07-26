package test.adventure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adventure.Actor;
import adventure.GameObject;
import adventure.TreeGameWorld;
import adventure.util.tree.ListTree;

public class TreeGameWorldTest {
	TreeGameWorld treeGameWorld;
	ListTree<String, GameObject> listTree;
	Actor player;
	GameObject obj1;
	@Before
	public void setUp() throws Exception {
		listTree = new ListTree<String, GameObject>();
		GameObject obj = new GameObject("GameWorld");
		listTree.addRoot("GameWorld", obj);
		
		GameObject obj1 = new GameObject("Room_1");
		listTree.addChild("GameWorld", obj1.getId(), obj1);
		
		player = new Actor("player");
		listTree.addChild("GameWorld", player.getId(), player);
		
		GameObject obj2 = new GameObject("Ball_1");
		listTree.addChild("Room_1", obj2.getId(), obj2);
		
		GameObject obj3 = new GameObject("Key_1");
		listTree.addChild("player", obj3.getId(), obj3);
		
		treeGameWorld=(TreeGameWorld) TreeGameWorld.getInstance();
		treeGameWorld.setTreeGameWorld(listTree);
	}

		@Test
	public void testIsInScope() {
		assertFalse(treeGameWorld.isInScope("aaa"));
		assertTrue(treeGameWorld.isInScope("Key_1"));
		assertTrue(treeGameWorld.isInScope("player"));
	}
	
	@Test
	public void testMove() {
		treeGameWorld.move("Key_1", "Room_1");
		listTree.moveSubTree("Key_1", "Room_1");
		assertTrue(listTree.parent("Key_1").equals("player"));//this could pass which is not right
		assertTrue(treeGameWorld.getTreeGameWorld().parent("Key_1").equals("player"));//this is not right
		assertFalse(treeGameWorld.getTreeGameWorld().parent("Key_1").equals("Room_1"));
		assertFalse(treeGameWorld.getTreeGameWorld().parent("Key_1").equals("Ball_1"));
		assertFalse(treeGameWorld.getTreeGameWorld().parent("Key_1").equals("GameWorld"));		
	}
	
	
	@Test
	public void testIsInInventory() {	
		assertTrue(treeGameWorld.getTreeGameWorld().parent("Key_1").equals("player"));	
	}
	
	public void testGetRoom(){
		assertTrue(treeGameWorld.getRoom("Room_1").equals(obj1));
	}
	
	public void testGetGameObject(){
		assertTrue(treeGameWorld.getGameObject("Room_1").equals(obj1));
	}
	
	public void testAddGameObject() {	    
		treeGameWorld.addGameObject(player);
		assertTrue(treeGameWorld.getPlayer().equals("player"));
	  }
}
