package adventure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GwkParserTest {

    private Parser parser;
    // private GameWorld world;
    private Grammar grammar;
    private Dictionary dictionary;

    private GameAction insert;
    private GameAction puton;
    private GameAction drop;
    private GameAction jump;
    private GameObject beachball;
    private GameObject soccerball;
    private GameObject woodbox;
    private GameObject cardboardbox;

    @Before
    public void setup() throws Exception {
	parser = GameParser.getInstance();
	grammar = GameGrammar.getInstance();
	dictionary = GameDictionary.getInstance();
        
        grammar.clear();
        dictionary.clear();

	insert = new GameAction("insert");
	insert.addPattern("put {object} in {object}");
	insert.addPattern("insert {object} into {object}");
	grammar.addGameAction(insert);

	puton = new GameAction("puton");
	puton.addPattern("put {object} on {object}");
	puton.addPattern("put {object} onto {object}");
	grammar.addGameAction(puton);

	drop = new GameAction("drop");
	drop.addPattern("drop {object}");
	grammar.addGameAction(drop);

	jump = new GameAction("jump");
	jump.addPattern("jump");
	grammar.addGameAction(jump);

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
    public void testEmpty() {
	Command command = parser.parse(" ");
	assertNull(command.action);
	assertNull(command.object1);
	assertNull(command.object2);
	assertEquals(Message.parseEmptyMessage(), command.errorMessage);
    }

    @Test
    public void testUndefinedFirstWord() {
	Command command = parser.parse("hello");
	assertEquals(Message.parseUndefinedWordMessage("hello"), command.errorMessage);	
    }
    
    @Test
    public void testUndefinedWord() {
	Command command = parser.parse("jump banana");
	assertEquals(Message.parseUndefinedWordMessage("banana"), command.errorMessage);
    }
    
    @Test
    public void testUndefinedWords() {
	Command command = parser.parse("jump orange banana");
	assertEquals(Message.parseUndefinedWordsMessage("orange", "banana"), command.errorMessage);
    }
    
    @Test
    public void testNonVerb() {
	Command command = parser.parse("cardboard box");
	assertEquals(Message.parseUnknownVerbMessage("cardboard"), command.errorMessage);
    }
    
    @Test
    public void testPutInvalidPattern() {
	Command command = parser.parse("put ball");
	assertTrue(command.errorMessage.contains("put {object} in {object}"));
	assertTrue(command.errorMessage.contains("put {object} on {object}"));
	assertTrue(command.errorMessage.contains("put {object} onto {object}"));
    }

    @Test
    public void testNonNoun() {
	Command command = parser.parse("drop cardboard");
	assertEquals(Message.parseUnknownNounMessage("cardboard", "cardboard"), command.errorMessage);
    }
    
    @Test
    public void testNonNoun2() {
	Command command = parser.parse("drop wooden cardboard");
	assertEquals(Message.parseUnknownNounMessage("cardboard", "wooden cardboard"), command.errorMessage);
    }

    @Test
    public void testNonAdjective() {
	Command command = parser.parse("drop ball box");
	assertEquals(Message.parseUnknownAdjectiveMessage("ball", "ball box"), command.errorMessage);
    }
    
    @Test
    public void testNonObject() {
	Command command = parser.parse("drop beach box");
	assertEquals(Message.parseUnknownPhraseMessage("beach box"), command.errorMessage);
    }
    
}
