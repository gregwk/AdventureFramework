package adventure;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameDictionaryTest {

	Dictionary dictionary = GameDictionary.getInstance();
	
	/*
	 * tested:
	 * addGameObject()
	 * getGameObjects()
	 * isDefined()
	 * isAdjective()
	 * isNoun()
	 * isVerb()
	 * addGameAction()
	 * getGameActions()
	 */
	
	@Test
	public void addGameObject(){

		GameObject gameobj = null;
		Assert.assertNull("Should be null since uninitialized.", gameobj);
		
		gameobj = new Thing("Box", "A box to hold things");
		
		List<String> objwords = new List<String>();
		//Assert.assertNull("Should be null before initialization", objwords);
		objwords.add("Box");
		
		// would fail = "NoSuchElementException" is returned for not including an object
		//Assert.assertNull("Should be null before adding", dictionary.getGameObjects(objwords));
		
		dictionary.addGameObject(gameobj);
		
		Assert.assertNotNull("Should return an id in a List", dictionary.getGameObjects(objwords));

	}
	
	@Test
	public void isDefined(){
		GameObject gameobj = null;
		GameAction gameact = null;
		Assert.assertNull("Should be null since uninitialized.", gameobj);
		Assert.assertNull("Should be null since uninitialized.", gameact);
		
		gameobj = new Thing("Box", "A box to hold things");
		gameobj.addAdjective("large");
		gameobj.addAdjective("cardboard");
		gameobj.addNoun("Box");		
		
		gameact = new GameAction("put");
		gameact.addPattern("put {object} in {object}");
		gameact.addPattern("put {object} into {object}");
		
		dictionary.addGameObject(gameobj);
		dictionary.addGameAction(gameact);
		
		// check GameObject contains the added adjectives and noun
		Assert.assertTrue(gameobj.containsAdjective("large"));
		Assert.assertTrue(gameobj.containsAdjective("cardboard"));
		Assert.assertTrue(gameobj.containsNoun("Box"));		
		
		Assert.assertTrue(dictionary.isDefined("large"));
		Assert.assertTrue(dictionary.isDefined("cardboard"));
		Assert.assertTrue(dictionary.isDefined("Box"));
		Assert.assertTrue(dictionary.isDefined("put"));
		Assert.assertFalse(dictionary.isDefined("insert"));
	}
	
	@Test
	public void isAdjective(){
		GameObject gameobj = null;
		GameAction gameact = null;
		Assert.assertNull("Should be null since uninitialized.", gameobj);
		Assert.assertNull("Should be null since uninitialized.", gameact);
		
		gameobj = new Thing("Box", "A box to hold things");
		gameobj.addAdjective("large");
		gameobj.addAdjective("cardboard");
		gameobj.addNoun("Box");		
		
		gameact = new GameAction("put");
		gameact.addPattern("put {object} in {object}");
		gameact.addPattern("put {object} into {object}");
		
		dictionary.addGameObject(gameobj);
		dictionary.addGameAction(gameact);
		
		// check GameObject contains the added adjectives and noun
		Assert.assertTrue(gameobj.containsAdjective("large"));
		Assert.assertTrue(gameobj.containsAdjective("cardboard"));
		Assert.assertTrue(gameobj.containsNoun("Box"));		
				
		Assert.assertTrue(dictionary.isAdjective("large"));
		Assert.assertTrue(dictionary.isAdjective("cardboard"));
		
	}
	
	@Test
	public void isNoun(){
		GameObject gameobj = null;
		GameAction gameact = null;
		Assert.assertNull("Should be null since uninitialized.", gameobj);
		Assert.assertNull("Should be null since uninitialized.", gameact);
		
		gameobj = new Thing("Box", "A box to hold things");
		gameobj.addAdjective("large");
		gameobj.addAdjective("cardboard");
		gameobj.addNoun("Box");		
		
		gameact = new GameAction("put");
		gameact.addPattern("put {object} in {object}");
		gameact.addPattern("put {object} into {object}");
		
		dictionary.addGameObject(gameobj);
		dictionary.addGameAction(gameact);
		
		// check GameObject contains the added adjectives and noun
		Assert.assertTrue(gameobj.containsAdjective("large"));
		Assert.assertTrue(gameobj.containsAdjective("cardboard"));
		Assert.assertTrue(gameobj.containsNoun("Box"));
		
		Assert.assertTrue(dictionary.isNoun("Box"));
	}
	
	@Test
	public void isVerb(){
		GameObject gameobj = null;
		GameAction gameact = null;
		Assert.assertNull("Should be null since uninitialized.", gameobj);
		Assert.assertNull("Should be null since uninitialized.", gameact);
		
		gameobj = new Thing("Box", "A box to hold things");
		gameobj.addAdjective("large");
		gameobj.addAdjective("cardboard");
		gameobj.addNoun("Box");		
		
		gameact = new GameAction("put");
		gameact.addPattern("put {object} in {object}");
		gameact.addPattern("put {object} into {object}");
		
		dictionary.addGameObject(gameobj);
		dictionary.addGameAction(gameact);
		
		// check GameObject contains the added adjectives and noun
		Assert.assertTrue(gameobj.containsAdjective("large"));
		Assert.assertTrue(gameobj.containsAdjective("cardboard"));
		Assert.assertTrue(gameobj.containsNoun("Box"));		
		
		Assert.assertTrue(dictionary.isVerb("put"));
		
	}
	
	@Test
	public void addGameAction(){
		GameAction gameact = null;
		Assert.assertNull("Should be null since uninitialized.", gameact);
		
		gameact = new GameAction("put");
		gameact.addPattern("put {object} in {object}");
		gameact.addPattern("put {object} into {object}");
		
		dictionary.addGameAction(gameact);

		Assert.assertNotNull("should not be null", dictionary.getActions("put"));
		
	}
	
	
	
	
}
