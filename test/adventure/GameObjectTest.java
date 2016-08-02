package adventure;

import static org.junit.Assert.*;

import org.junit.Test;

import adventure.*;

public class GameObjectTest {

	@Test
	public void testGameObject() 
	{
		GameObject object = new GameObject("first object");
		
		//Ensure that the id was generated properly
		assertTrue(object.getId().equals("first_object"));
		
		//Ensure that the adjectives and nouns are assigned correctly
		assertTrue(object.containsAdjective("first"));
		assertTrue(object.containsNoun("object"));
		
		//Ensure that the description was generated properly
		assertTrue(object.getDescription().equals("It's a first object"));
		
		//Ensure that we can add adjectives and nouns
		assertTrue(object.containsAdjective("cool"));
		assertTrue(object.containsAdjective("fun"));
		
		object.addNoun("Ball");
		assertTrue(object.containsNoun("Ball"));
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
		
		assertTrue(room.containsExit("east"));
		
		assertTrue(room2.containsDoor(door.getId()));
		
	}

}