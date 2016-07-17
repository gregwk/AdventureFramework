/**
 * This interface represents all of the public operations that can be performed by the TreeGameWorld
@Yeze
 *
 */

package adventure;

public interface GameWorld {
	public boolean isInScope(GameObject obj);
	public void move(GameObject obj1, GameObject obj2);
	public void updateProperty(GameObject obj, Property prop);
	public boolean isInInventory(GameObject obj);
}
