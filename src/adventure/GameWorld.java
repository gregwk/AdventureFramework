
package adventure;

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
  
  /**
   * Adds the specified object to the game world.
   * 
   * @param obj the game object ot be added
   */
  public void addGameObject(GameObject obj);
  
}

