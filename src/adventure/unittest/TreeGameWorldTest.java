
/**
 *
 * @author Yeze
 * 
 */

package adventure.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adventure.GameObject;
import adventure.Property;
import adventure.TreeGameWorld;
import adventure.util.tree.ListTree;

public class TreeGameWorldTest {
	TreeGameWorld treeGameWorld;

	@Before
	public void setUp() throws Exception {
		ListTree<String, GameObject> listTree = new ListTree<String, GameObject>();
		GameObject obj = new GameObject();
		obj.setId("Game World");
		listTree.addRoot("Game World", obj);
		
		GameObject obj1 = new GameObject();
		obj1.setId("Room_1");
		obj1.setDescription("A room");
		listTree.addChild("Game World", obj1.getId(), obj1);
		
		GameObject player = new GameObject();
		player.setId("player");
		player.setDescription("player");
		listTree.addChild("Game World", player.getId(), player);
		
		GameObject obj2 = new GameObject();
		obj2.setId("Ball_1");
		obj2.setDescription("A ball");
		listTree.addChild("Room_1", obj2.getId(), obj2);
		
		GameObject obj3 = new GameObject();
		obj3.setId("Key_1");
		obj3.setDescription("A key");
		listTree.addChild("Room_1", obj3.getId(), obj3);
		
		treeGameWorld = new TreeGameWorld(listTree);
	}

	@Test
	public void testIsInScope() {
		GameObject obj = new GameObject();
		obj.setId("aaa");
		assertTrue(!treeGameWorld.isInScope(obj));
		obj.setId("Key_1");
		assertTrue(treeGameWorld.isInScope(obj));
	}
	
	@Test
	public void testMove() {
		GameObject obj1 = new GameObject();
		obj1.setId("Key_1");
		
		GameObject obj2 = new GameObject();
		obj2.setId("player");
		
		treeGameWorld.move(obj1, obj2);
		assertFalse(treeGameWorld.getTreeGameWorld().parent("Key_1").equals("Room_1"));
		assertTrue(treeGameWorld.getTreeGameWorld().parent("Key_1").equals("player"));
	}
	
	@Test
	public void testUpdateProperty() {
		GameObject obj = new GameObject();
		obj.setId("Room_1");
		Property prop = new Property();
		prop.addProperty("static");
		treeGameWorld.updateProperty(obj, prop);
		
		assertTrue(treeGameWorld.containProperty(obj, "static"));
	}
	
	@Test
	public void testIsInInventory() {
		GameObject obj1 = new GameObject();
		obj1.setId("Key_1");
		
		GameObject obj2 = new GameObject();
		obj2.setId("player");
		
		treeGameWorld.move(obj1, obj2);
		
		assertTrue(treeGameWorld.isInInventory(obj1));
	}

}
