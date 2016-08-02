/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Manasa
 */
public class GameParserTest {
    private final String[] stopWords = {"a", "an", "the"};
    
    Dictionary mockDictionary = new Dictionary(){
        @Override
        public boolean isAdjective(String word) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isNoun(String word) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isVerb(String word) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isDefined(String word) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<String> getActions(String verb) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<String> getGameObjects(List<String> objectWords) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        @Override
        public void addGameObject(GameObject object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void addGameAction(GameAction action) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    public GameParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of parse method, of class GameParser.
     */
    @Test
    public void testParseEmptyUserInput() {
        System.out.println("parse");
        String userInput = "";
        GameParser instance = GameParser.getInstance();
        Command result = instance.parse(userInput);
        assertNotNull(result);
        assertEquals("Empty Input", result.errorMessage);
    }
    
    //Test match function when input matches with the pattern and contains two objects
    @Test
    public void testParseForMatch() {
        System.out.println("parse");
        String userInput = "abc";
        GameParser instance = GameParser.getInstance();
        Command result = instance.parse(userInput);
        assertNotNull(result);
        assertEquals("basket ball", result.object1);
        assertEquals("basket box ", result.object2);
    }

    @Test
    public void testMatchWithMatchingParameters() {
        System.out.println("parse");
        String userInput = "";
        GameParser instance = GameParser.getInstance();
        
        String[] wLIst = new String[]{"Put","basket", "ball","in","basket","box"};
        String Pattern = "Put (\\D*) in (\\D*)";
  
        boolean op = instance.match(wLIst, Pattern);
        assertNotNull(op);
        assertEquals(true, op);
    }
    
    /**
     * Test of tokenizeWord with multi-worded unser input
     */
    @Test
    public void testTokenizeMultipleWordUserInput(){
        System.out.println("tokenize");
        String userInput = "This is test";
        GameParser instance = GameParser.getInstance();
        String[] result = instance.tokenizeWords(userInput);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals("This", result[0]);
        assertEquals("is", result[1]);
        assertEquals("test", result[2]);
    }
    
    /**
     * Test removeStopWords with null word token
     */
    @Test
    public void testRemoveStopWordWithNullWordToken()
    {
        System.out.println("remove stop words");
        String[] wordTokens = null;
        GameParser instance = GameParser.getInstance();
        instance.removeStopWords(stopWords, wordTokens);
        assertNull(wordTokens);
    }
    
    /**
     * Test removeStopWords with empty word token
     */
    @Test
    public void testRemoveStopWordWithEmptyWordToken()
    {
        System.out.println("remove stop words");
        String[] wordTokens = {};
        GameParser instance = GameParser.getInstance();
        instance.removeStopWords(stopWords, wordTokens);
        assertNotNull(wordTokens);
        assertEquals(0, wordTokens.length);
    }
    
    /**
     * Test removeStopWords with single word token
     * which is a stop word
     */
    @Test
    public void testRemoveStopWordOnlyWithSingleStopWordEntry()
    {
        System.out.println("remove stop words");
        String[] wordTokens = new String[]{"a"};
        GameParser instance = GameParser.getInstance();
        wordTokens = instance.removeStopWords(stopWords, wordTokens);
        assertNotNull(wordTokens);
        assertEquals(0, wordTokens.length);
    }
    
    /**
     * Test removeStopWords with multiple words 
     * where all the words are stop words
     */
    @Test
    public void testRemoveStopWordOnlyWithMultipleStopWordEntry()
    {
        System.out.println("remove stop words");
        String[] wordTokens = new String[]{"a", "an", "the"};
        GameParser instance = GameParser.getInstance();
        wordTokens = instance.removeStopWords(stopWords, wordTokens);
        assertNotNull(wordTokens);
        assertEquals(0, wordTokens.length);
    }
    
    /**
     * Test removeStopWords with no stop words
     */
    @Test
    public void testRemoveStopWordNoStopWordEntry()
    {
        System.out.println("remove stop words");
        String[] wordTokens = new String[]{"This", "is", "test"};
        GameParser instance = GameParser.getInstance();
        wordTokens = instance.removeStopWords(stopWords, wordTokens);
        assertNotNull(wordTokens);
        assertEquals(3, wordTokens.length);
        assertEquals("This", wordTokens[0]);
        assertEquals("is", wordTokens[1]);
        assertEquals("test", wordTokens[2]);
    }
    
    /**
     * Test removeStopWords with one stop word
     * and 2 or more non-stop words
     */
    @Test
    public void testRemoveStopWordWithStopWordAndOtherEntry()
    {
        System.out.println("remove stop words");
        String[] wordTokens = new String[]{"This", "is", "a", "test"};
        GameParser instance = GameParser.getInstance();
        wordTokens = instance.removeStopWords(stopWords, wordTokens);
        assertNotNull(wordTokens);
        assertEquals(3, wordTokens.length);
        assertEquals("This", wordTokens[0]);
        assertEquals("is", wordTokens[1]);
        assertEquals("test", wordTokens[2]);
    }
    
    /**
     * Test removeStopWords with two stop words
     * and 2 or more non-stop words
     */
    @Test
    public void testRemoveStopWordWithMultipleStopWordsAndOtherEntry()
    {
        System.out.println("remove stop words");
        String[] wordTokens = new String[]{"Put", "the", "ball", "in", "the", "box"};
        GameParser instance = GameParser.getInstance();
        wordTokens = instance.removeStopWords(stopWords, wordTokens);
        assertNotNull(wordTokens);
        assertEquals(4, wordTokens.length);
        assertEquals("Put", wordTokens[0]);
        assertEquals("ball", wordTokens[1]);
        assertEquals("in", wordTokens[2]);
        assertEquals("box", wordTokens[3]);
    }
}
