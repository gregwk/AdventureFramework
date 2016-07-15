package adventure;

public abstract class CompositeObject extends GameObject
{
	public CompositeObject(String id, String description, String displayName, String[] nouns, String[] adjectives) 
	{
		super(id, description, displayName, nouns, adjectives);
	}
	
	public CompositeObject(String id)
	{
		super(id);
	}
	
	public abstract boolean add(GameObject object);
	
	public abstract boolean contains(GameObject object);
	
	public abstract boolean remove(GameObject object);
}
