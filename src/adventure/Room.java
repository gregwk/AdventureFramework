package adventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Room extends GameObject
{
	protected Set<GameObject> containedObjects;
	protected Map<String, Door> doors;
	protected Map<String, Room> exits;
	
	public Room(String name, String description) 
	{
		super(name, description);
		init();
	}
	public Room(String name) 
	{
		super(name);
		init();
	}
	
	private void init()
	{
		this.doors = new HashMap<String, Door>();
		this.exits = new HashMap<String, Room>();
	}
	
	public void addExit(String roomKey, Room room)
	{
		this.exits.put(roomKey, room);
	}
	
	public Room removeExit(String roomKey)
	{
		return this.exits.remove(roomKey);
	}
	
	public boolean containsExit(String roomKey)
	{
		return this.exits.containsKey(roomKey);
	}
	
	public void addDoor(String doorKey, Door door)
	{
		this.doors.put(doorKey, door);
	}
	
	public Door removeDoor(String doorKey)
	{
		return this.doors.remove(doorKey);
	}
	
	public boolean containsDoor(String doorKey)
	{
		return this.containsDoor(doorKey);
	}
	
	public void addObjectToRoom(GameObject o)
	{
		this.containedObjects.add(o);
	}
	
	public boolean removeObjectFromRoom(GameObject o)
	{
		return this.containedObjects.remove(o);
	}
	
	public boolean hasObjectInRoom(GameObject o)
	{
		return this.containedObjects.contains(o);
	}
}
