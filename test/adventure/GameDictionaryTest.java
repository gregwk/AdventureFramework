package adventure;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameDictionaryTest {

	Dictionary dictionary = GameDictionary.getInstance();
	Grammar grammar = GameGrammar.getInstance();
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
	 * 
	 * getActionPatterns()
	 */
	
	private void setObjs(){
		GameAction gameact = new GameAction("put");
		gameact.addPattern("put {object} in {object}");
		gameact.addPattern("put {object} into {object}");
		
		GameAction gameact1 = new GameAction("insert");
		gameact1.addPattern("put {object} in {object}");
		gameact1.addPattern("insert {object} into {object}");
		
		GameAction gameact2 = new GameAction("drop");
		gameact2.addPattern("put {object} in {object}");
		gameact2.addPattern("drop {object} into {object}");
		
		
		dictionary.addGameAction(gameact);
		dictionary.addGameAction(gameact1);
		dictionary.addGameAction(gameact2);
		
		GameObject gameobj = new Thing("Box", "A box to hold things");
		gameobj.addAdjective("large");
		gameobj.addAdjective("cardboard");
		gameobj.addNoun("Box");		
		
		dictionary.addGameObject(gameobj);
		
		// check GameObject contains the added adjectives and noun
		Assert.assertTrue(gameobj.containsAdjective("large"));
		Assert.assertTrue(gameobj.containsAdjective("cardboard"));
		Assert.assertTrue(gameobj.containsNoun("Box"));		
		
		GameObject gameobj1 = new Thing("Box", "A box to hold things");
		
		dictionary.addGameObject(gameobj1);
	}
	
	@Test
	public void addGameAction(){
		setObjs();

		Assert.assertNotNull("should not be null", dictionary.getActions("put"));		
	}
	
	@Test
	public void addGameObject(){
		setObjs();
		
		List<String> objwords = new ArrayList<String>();
		objwords.add("Box");
		
		Assert.assertNotNull("Should return an id in a List", dictionary.getGameObjects(objwords));

	}
	
	@Test
	public void isDefined(){
		setObjs();
		
		Assert.assertTrue(dictionary.isDefined("large"));
		Assert.assertTrue(dictionary.isDefined("cardboard"));
		Assert.assertTrue(dictionary.isDefined("Box"));
		Assert.assertTrue(dictionary.isDefined("put"));
		Assert.assertFalse(dictionary.isDefined("restart"));
	}
	
	@Test
	public void isAdjective(){
		setObjs();
		
		Assert.assertTrue(dictionary.isAdjective("large"));
		Assert.assertTrue(dictionary.isAdjective("cardboard"));		
	}
	
	@Test
	public void isNoun(){
		setObjs();
		
		Assert.assertTrue(dictionary.isNoun("Box"));
	}
	
	@Test
	public void isVerb(){
		setObjs();
		
		Assert.assertTrue(dictionary.isVerb("put"));		
	}
	
	@Test
	public void getActionPatterns(){
		setObjs();
		
		List<String> testverbs = new ArrayList<String>();
		testverbs.add("drop");
		testverbs.add("put");
		testverbs.add("insert");
		
		for(String s: testverbs){
			for(GameActionPattern out : dictionary.getActionPatterns(s)){
				for(String gaps: out.getPatterns()){
					String[] tokens = gaps.split("\\s");
					Assert.assertTrue(s.equals(tokens[0]));
				}
			}		
		}
		
		
	}
	
	
}
