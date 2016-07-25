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
    
    /**
     * Test of tokenizeword method with empty user input
     */
    @Test
    public void testTokenizeEmptyString(){
        System.out.println("tokenize");
        String userInput = "";
        GameParser instance = GameParser.getInstance();
        String[] result = instance.tokenizeWords(userInput);
        assertNull(result);
    }
    
    /**
     * Test of tokenizeWord method with single word
     */
    @Test
    public void testTokenizeSingleWordUserInput(){
        System.out.println("tokenize");
        String userInput = "Test";
        GameParser instance = GameParser.getInstance();
        String[] result = instance.tokenizeWords(userInput);
        assertNotNull(result);
        assertEquals(1, result.length);
        assertEquals("Test", result[0]);
    }
    
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
}
