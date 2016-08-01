package adventure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class GwkDictionaryTest {

	private GameAction insert;
	private GameAction puton;
	private GameAction drop;
	private GameAction jump;
	private GameObject beachball;
	private GameObject soccerball;
	private GameObject woodbox;
	private GameObject cardboardbox;
	
	private Dictionary dictionary;

	@Before
	public void setup() throws Exception {
		dictionary = GameDictionary.getInstance();
		
		insert = new GameAction("insert");
		insert.addPattern("put {object} in {object}");
		insert.addPattern("insert {object} into {object}");
		dictionary.addGameAction(insert);
		
		puton = new GameAction("puton");
		puton.addPattern("put {object} on {object}");
		puton.addPattern("put {object} onto {object}");
		dictionary.addGameAction(puton);
		
		drop = new GameAction("drop");
		drop.addPattern("drop {object}");
		dictionary.addGameAction(drop);
		
		jump = new GameAction("jump");
		jump.addPattern("jump");
		dictionary.addGameAction(jump);
		
		beachball = new Thing("beach ball");
		dictionary.addGameObject(beachball);
		
		soccerball = new Thing("soccer ball");
		dictionary.addGameObject(soccerball);
		
		woodbox = new Thing("wooden box");
		dictionary.addGameObject(woodbox);
		
		cardboardbox = new Thing("cardboard box");
		dictionary.addGameObject(cardboardbox);
	}

	@Test
	public void testIsDefined() {
		assertTrue(dictionary.isDefined("put"));
		assertTrue(dictionary.isDefined("in"));
		assertTrue(dictionary.isDefined("beach"));
		assertTrue(dictionary.isDefined("ball"));
		assertTrue(dictionary.isDefined("jump"));

		assertFalse(dictionary.isDefined("object"));
		assertFalse(dictionary.isDefined("{object}"));
		assertFalse(dictionary.isDefined("puton"));
		assertFalse(dictionary.isDefined("beach ball"));
		assertFalse(dictionary.isDefined("beach_ball"));
	}
	
	@Test
	public void testIsAdjective() {
		assertTrue(dictionary.isAdjective("cardboard"));		
		assertFalse(dictionary.isAdjective("box"));		
	}
	
	@Test
	public void testIsNoun() {
		assertTrue(dictionary.isNoun("box"));
		assertFalse(dictionary.isNoun("wooden"));
	}
	
	@Test
	public void testIsVerb() {
		assertTrue(dictionary.isVerb("put"));
		assertFalse(dictionary.isVerb("into"));	
	}
	
	@Test
	public void testGetActions() {
		List<String> actionIds;
		
		actionIds = dictionary.getActions("put");
		assertTrue(actionIds.contains("insert"));
		assertTrue(actionIds.contains("puton"));
		assertFalse(actionIds.contains("into"));
		
		actionIds = dictionary.getActions("drop");
		assertTrue(actionIds.contains("drop"));
		assertFalse(actionIds.contains("insert"));
		
		// actionIds = dictionary.getActions("lift");
		// assertTrue(actionIds.isEmpty());
	}

	@Test
	public void testGetGameObjects() {
		List<String> gos;
		
		gos = dictionary.getGameObjects(Arrays.asList(new String[]{"cardboard", "box"}));
		assertEquals(1, gos.size());
		assertTrue(gos.contains("cardboard_box"));
		
		gos = dictionary.getGameObjects(Arrays.asList(new String[]{"box"}));
		assertEquals(2, gos.size());
		assertTrue(gos.contains("cardboard_box"));
		assertTrue(gos.contains("wooden_box"));
		
	}
}
