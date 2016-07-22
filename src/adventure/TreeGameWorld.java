/**
 * TreeGameWorld implements GameWorld class;
 * 
 * @author Yeze
 * 
 */

package adventure;

import adventure.util.tree.ListTree;

public class TreeGameWorld implements GameWorld {

  // instance variables

  private ListTree<String, GameObject> treeGameWorld;

  // singleton pattern

  private static final GameWorld INSTANCE = new TreeGameWorld();

  private TreeGameWorld() {}

  public static GameWorld getInstance() {
    return INSTANCE;
  }

  // public methods

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
    return this.treeGameWorld.parent(objectId).equals("player");
  }

  @Override
  public Actor getPlayer() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setPlayer(Actor actor) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public Room getRoom(String objectId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public GameObject getGameObject(String objectId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addGameObject(GameObject obj) {
    // TODO Auto-generated method stub
    
  }

}

