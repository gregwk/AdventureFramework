package adventure;

public interface GameWorld {
	public boolean isInScope(GameObject obj);
	public void move(GameObject obj1, GameObject obj2);
	public void updateProperty(GameObject obj, Property prop);
	public boolean isInInventory(GameObject obj);
}
