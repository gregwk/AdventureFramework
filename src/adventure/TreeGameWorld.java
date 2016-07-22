/**
 * TreeGameWorld implements GameWorld class;
 * @author Yeze
 * 
 */

package adventure;

import adventure.util.tree.ListTree;

public class TreeGameWorld implements GameWorld {

  // singleton pattern
  
  private static final GameWorld INSTANCE = new TreeGameWorld();

  private TreeGameWorld() {}

  public static GameWorld getInstance() {
    return INSTANCE;
  }  
  
  // instance variables
  
  private ListTree<String, GameObject> treeGameWorld;

	public TreeGameWorld(ListTree<String, GameObject> treeGameWorld){
		this.setTreeGameWorld(treeGameWorld);
	}
	
	@Override
	public boolean isInScope(String objId) {
		return this.treeGameWorld.contains(objId);
	}

	@Override
	public void move(String obj1Id, String obj2Id) {
		this.treeGameWorld.moveSubTree(obj1Id, obj2Id);
	}

	@Override
	public void addProperty(String objId, String prop) {
		this.treeGameWorld.get(objId).addProperty(prop);
	}

	@Override
	public boolean isInInventory(String objId) {
		return this.treeGameWorld.parent(objId).equals("player");
	}

	@Override
	public boolean containsProperty(String objId, String prop) {
		return this.treeGameWorld.get(objId).containsProperty(prop);
	}
	
	@Override
	public void removeProperty(String objId, String prop) {
		// TODO Auto-generated method stub
		this.treeGameWorld.get(objId).removeProperty(prop);
	}
	
	/**
	 * @return the treeGameWorld
	 */
	public ListTree<String, GameObject> getTreeGameWorld() {
		return treeGameWorld;
	}

	/**
	 * @param treeGameWorld2 the treeGameWorld to set
	 */
	public void setTreeGameWorld(ListTree<String, GameObject> treeGameWorld2) {
		this.treeGameWorld = treeGameWorld2;
	}

}

