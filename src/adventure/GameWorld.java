package adventure;

import java.util.List;

/**
 * This interface represents all of the public operations that can be performed by the TreeGameWorld;
 * author: Yeze
 * 2016/07/28
 * 
 */
public interface GameWorld {

  /**
   * Returns the player object.
   * @return the player object
   */
  public Actor getPlayer();
  
  /**
   * Sets the player object.
   * @param the player object
   */
  public void setPlayer(Actor actor);
  
  /**
   * Returns the room that the specified object is in.
   * 
   * @param objectId identifier of the specified object
   * @return room the object is in
   */
  public Room getRoom(String objectId);

  /**
   * Returns true if the specified object is in scope.
   * 
   * @param objectId identifier of the specified object
   * @return 
   */
  public boolean isInScope(String objectId);
  
  /**
   * Returns true if the specified object is in the inventory.
   * 
   * @param objectId
   * @return
   */
  public boolean isInInventory(String objectId);
  
  /**
   * Move the specified object and everything it contains to the parent object.
   * 
   * @param objectId
   * @param parentObject
   */
  public void move(String objectId, String parentObject);

  /**
   * Returns the game object associated with the specified identifier.
   * 
   * @param objectId
   * @return
   */
  public GameObject getGameObject(String objectId);
  
  public List<GameObject> getChildrenOfGameObject(String objectId);
  
  /**
   * Adds the specified Thing to the game world.
   * 
   * @param obj
   */
  public void addThing(Thing thing);
  
  /**
   * Adds the specified Room to the game world.
   * 
   * @param obj
   */
  public void addRoom(Room room); 
  
  /**
   * Adds the property to object. 
   * 
   * @param objectId
   * @param prop
   */
  public void addProperty(String objectId, String prop);
  
  /**
   *  
   * 
   * @param objectId
   * @param prop
   * @return 
   */
  public boolean containsProperty(String objectId, String prop);
  
  /**
   * If the object contains the property.  
   * 
   * @param objectId
   * @param prop
   */
  public void removeProperty(String objectId, String prop);
    /**
   * Removes the property from the object.  
   * 
   * @param objectId
   * @param prop
   */
  public List<String> getGameObjects(List<String> objectWords);
  
    /**
   * get the GameObject from the a list of objectWords.  
   * 
   * @param objectId
   * @param prop
   */

  public List<String> getActions(String verb);
  
    /**
   * get a list of action string from s string of verb using gameDictionary.  
   * 
   * @param objectId
   * @param prop
   */

  void addGameAction(GameAction action);
  
    /**
   * add the GameAction to gameDictionary
   * 
   * @param objectId
   * @param prop
   */

  void addDirection(Direction direction);
    /**
   * add the direction directly under the tree root
   * 
   * @param objectId
   * @param prop
   */

  List<String> getChildren(String objectId);
    /**
   * get a list of childrenId from its parent id
   * 
   * @param objectId
   * @param prop
   */

  String getParent(String objectId);
    /**
   * get an object's parent by its id
   * 
   * @param objectId
   * @param prop
   */
  
  public void clear();
  
}



