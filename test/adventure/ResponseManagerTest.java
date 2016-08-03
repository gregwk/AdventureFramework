package adventure;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class ResponseManagerTest {

	Message message = new Message();
	
	public void testEmpty() {
		Command command = command.action(" ");
		assertNull(command.action);
		assertEquals(command.errorMessage, message.parseEmptyMessage);
	}
	
	@Test
	public void testUnknownNoun() {
		Command command = new Command();
		command.action = "insert";
		command.object1 = "apple";
		assertEquals("I expected apple to be a game object, but apple is not defined as a noun.", command.errorMessage);
	}
	
	@Test
	public void testInsert() {
		Command command = new Command();
		command.action = "insert";
		command.object1 = "beachball";
		command.object2 = "woodenbox";
		assertNull(command.errorMessage);
		//assertEquals("Beach Ball", world.getGameObject(command.object1).getName());
		//assertEquals("Wooden Box", world.getGameObject(command.object2).getName());
		assertEquals("insret Beach Ball into Wooden Box",Response.meaasge)
		
	}

	@Test
	public void testUndefinedWords() {
		Command command = new Command();
		command.action = "lock";
		command.object1 = "icecream";
		command.object2 = "candy";
		assertEquals("The follwoing words are not defined: ", command.errorMessage);
		//assertEquals(Message.parseUndefinedWordsMessage("icecream", "candy") ", command.errorMessage);
	}

}
