package adventure;

import java.util.Collection;

/**
 * This interface represents all of the public operations that can be performed by the GameWorld
 * @author Alex_Lappy_486
 *
 */
public interface GameWorld 
{
	/**
	 * This method checks whether the give GameObject is "in scope".
	 * In this context, "in scope" means that the given object is 
	 * accessible or visible in the context of the current GameState
	 * 
	 * @param gameObject a GameObject
	 * @return true if the object is in scope, false if not (or if the object is null)
	 */
	public boolean isInScope(GameObject gameObject);
	
	/**
	 * 
	 * This method moves one object (item to move) into another object (container).
	 * This covers any scenario where an object can be placed, consumed, or overlapped
	 * by another object (ex: putting an object in a box -> move(object, box); ).
	 * This method will also handle any updating to the GameWorld or GameState to
     * reflect this operation.
	 *  
	 * @param itemToMove the item to move
	 * @param container the item that will contain the item being moved
	 * @throws IllegalArgumentException thrown when one or more given parameters are null
	 */
	public void move(GameObject itemToMove, GameObject container) throws IllegalArgumentException;
	
	/**
	 * This method updates the given GameObject by assigning a property to it, 
     * represented by the given Property Object. Optionally, a user may choose 
     * to overwrite property values if they are already assigned, or do nothing 
     * if the game object already has an assigned key for the given property.
	 */
	public GameObject getGameObject(String id);
	
	/**
	 * This method will check to see if a given game object is contained within 
     * the Player's Inventory, which is another name for the collection of objects 
     * that the player possesses in the current Game State.
	 * 
	 * @param gameObject the object we are looking for in the player's inventory
	 * @return true if the object is contained in the inventory, false if not (or if the given object is null).
	 */
	public boolean isInInventory(GameObject gameObject);
	
	public void addObjectToGameWorld(GameObject o);
	
	public void addObjectsToGameWorld(Collection<GameObject> objects);
}