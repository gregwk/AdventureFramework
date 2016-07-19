package adventure;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

public class GameObjectTest {

	@Test
	public void testGameObject() 
	{
		GameObject object = new GameObject("first object");
		
		//Ensure that the id was generated properly
		assertTrue(object.getId().equals("first_object"));
		
		//Ensure that the adjectives and nouns are assigned correctly
		assertTrue(object.getAdjectives().contains("first"));
		assertTrue(object.getNouns().contains("object"));
		assertTrue(object.getAdjectives().size() == 1);
		assertTrue(object.getNouns().size() == 1);
		
		//Ensure that the description was generated properly
		assertTrue(object.getDescription().equals("It's a first object"));
		
		//Ensure that we can add adjectives and nouns
		object.addAdjectives("cool", "fun");
		assertTrue(object.getAdjectives().size() == 3);
		assertTrue(object.getAdjectives().contains("cool"));
		assertTrue(object.getAdjectives().contains("fun"));
		
		object.addNouns("Ball");
		assertTrue(object.getNouns().contains("Ball"));
	}
	
	@Test
	public void roomTest()
	{
		Room room = new Room("Dank Graveyard");
		Room room2 = new Room("Creepy Cabin");
		
		room.addExit("east", room2);
		room2.addExit("west", room);
		Door door = new Door("creaky door");
		room2.addDoor(door.getId(), door);
		
		Collection<Room> exits = room.getAllExits();
		assertTrue(exits.contains(room2));
		exits = room2.getAllExits();
		assertTrue(exits.contains(room));
		
		assertTrue(room2.containsDoor(door.getId()));
	}

}
