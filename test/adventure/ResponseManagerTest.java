package adventure;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class ResponseManagerTest {

    
	private GameWorld world = TreeGameWorld.getInstance();
	@Test
	public void testEmpty() {
		Command command = new Command();
		command.action = " ";
		assertEquals("Did you just say something?", Message.parseEmptyMessage());
	}
	
	@Test
	public void testUnknownNoun() {
		Command command = new Command();
		command.action = "insert";
		command.object1 = "apple";
		assertEquals("The word 'apple' is not defined.", Message.parseUndefinedWordMessage("apple"));
	}
	
	/*@Test
	public void testInsert() {
		Command command = new Command();
		command.action = "insert";
		command.object1 = "beachball";
		command.object2 = "woodenbox";
		assertNull(command.errorMessage);
		ResponseManager instance = ResponseManager.getInstance();
		Response response = instance.generateResponse(command);
	  //  assertEquals("Beach Ball", world.getGameObject(command.object1).getName());
	    GameAction gameAction = new GameAction(command.action);
		assertEquals(gameAction.getResponder(),response);
		
	}*/

	@Test
	public void testUndefinedWords() {
		Command command = new Command();
		command.action = "lock";
		command.object1 = "icecream";
		command.object2 = "candy";
		//assertEquals("The follwoing words are not defined: ", command.errorMessage);
		//assertEquals(Message.parseUndefinedWordsMessage("icecream", "candy") ", command.errorMessage);
	}

}
