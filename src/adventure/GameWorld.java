package adventure;

/**
 * The game world uses a tree data structure to organize gameObjects.
 * It updates gameObjects, check the status of gameObjects and apply movements on gameObjects
 * This interface specifies the behavior of the GameWorld 
 * 
 * There is only one player in the GameWorld
 * 
 * @author Lulu
 *
 */
public interface GameWorld {
	
	/**
	 * Update the specified gameObject by a property assignment 
	 * @param gameObject the game object who is to be assigned a property 
	 * @param property the property of the to-be-updated gameObject
	 * @throw IllegalArgumentException if the gameObject is not in the scope
	 * @throw IllegalArgumentException if the property is null
	 */
	public void updateProperty(GameObject gameObject, Property property)throws IllegalArgumentException;
	
	/**
	 * Returns true if the gameObject is stored in the inventory taken by the current player 
	 * the inventory is a list of gameObject that belongs to the current player
	 * @param gameObject the gameObject that is checked for being in the inventory 
	 * of the current player 
     * @return true if the gameObject is in the inventory of the current Player 
     * @throw IllegalArgumentException if the gameObject is not in the scope
     * @throw IllegalArgumentException if the inventory in the player is null
	 */
	public boolean isInInventory(GameObject gameObject)throws IllegalArgumentException;

}