package adventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Room extends CompositeObject
{
	protected Set<GameObject> containedObjects;
	protected Map<String, Room> connectedRooms;
	
	public Room(String id, String description, String displayName, String[] nouns, String[] adjectives)
	{
		super(id, description, displayName, nouns, adjectives);
		containedObjects = new HashSet<GameObject>();
		connectedRooms = new HashMap<String, Room>();
	}
	
	public Room(String id)
	{
		super(id);
		containedObjects = new HashSet<GameObject>();
		connectedRooms = new HashMap<String, Room>();
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
	
	public void addConnectedRoom(String direction, Room room)
	{
		this.connectedRooms.put(direction, room);
	}
	
	public Room getConnectedRoom(String direction, Room room)
	{
		return this.connectedRooms.get(room);
	}
	

}
