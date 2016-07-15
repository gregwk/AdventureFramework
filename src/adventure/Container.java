package adventure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Container extends CompositeObject
{
	private Set<GameObject> containedObjects;

	public Container(String id, String description, String displayName, String[] nouns, String[] adjectives) {
		super(id, description, displayName, nouns, adjectives);
		containedObjects = new HashSet<GameObject>();
	}
	
	public Container(String id) {
		super(id);
		containedObjects = new HashSet<GameObject>();
	}

	@Override
	public boolean add(GameObject object) {
		return containedObjects.add(object);
	}

	@Override
	public boolean contains(GameObject object) {
		return containedObjects.contains(object);
	}

	@Override
	public boolean remove(GameObject object) {
		return containedObjects.remove(object);
	}
}
