/**
 * This interface represents all of the public operations that can be performed by the TreeGameWorld
 * 
 * @author Yeze
 *
 */
package adventure;

public interface GameWorld {

  public boolean isInScope(String objId);

  public void move(String obj1Id, String obj2Id);

  public void addProperty(String objId, String prop);

  public void removeProperty(String objId, String prop);

  public boolean containsProperty(String objId, String prop);

  public boolean isInInventory(String objId);

}

