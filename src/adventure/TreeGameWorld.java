/**
 * TreeGameWorld implements GameWorld class;
 * @author Yeze, Lulu
 * 26/07/2016
 * 
 */

package adventure;

import adventure.util.tree.ListTree;

public class TreeGameWorld implements GameWorld {

  // instance variables

  private ListTree<String, GameObject> treeGameWorld;
  private Actor player = null;
  

  // singleton pattern

  private static final GameWorld INSTANCE = new TreeGameWorld();

  private TreeGameWorld() {}

  public static GameWorld getInstance() {
    return INSTANCE;
  }

  // public methods
  public TreeGameWorld(ListTree<String, GameObject> treeGameWorld){
	  		this.setTreeGameWorld(treeGameWorld);	
  }
  
  @Override
  public boolean isInScope(String objectId) {
    return this.treeGameWorld.contains(objectId);
  }

  @Override
  public void move(String objectId, String parent) {
    this.treeGameWorld.moveSubTree(objectId, parent);
  }

  @Override
  public boolean isInInventory(String objectId) {
	
    return this.treeGameWorld.parent(objectId).equals(player.getId());
  }

  @Override
  public Actor getPlayer() {
    // TODO Auto-generated method stub
    return player;
  }

  @Override
  public void setPlayer(Actor actor) {
    // TODO Auto-generated method stub
	if(player != null)
		return;
    player = actor;
    addGameObject(player);
  }
  
  @Override
  public Room getRoom(String objectId) {
    // TODO Auto-generated method stub
	return (Room) this.treeGameWorld.get(objectId);
  }

  @Override
  public GameObject getGameObject(String objectId) {
    // TODO Auto-generated method stub
	return this.treeGameWorld.get(objectId);
  }

  /**
   * If there need addThing(Thing thing)?
   * GameObject haven't parent property.
   */
  @Override
  public void addGameObject(GameObject obj) {
    // TODO Auto-generated method stub
	this.treeGameWorld.addRoot(obj.getId(), obj);
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
