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
        public void addGameObject(String nameId) {
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
    
     //Test match function when input matches with the pattern and contains one object
    @Test
    public void testParseForMatch2() {
        System.out.println("parse");
        String userInput = "abc";
        GameParser instance = GameParser.getInstance();
        Command result = instance.parse(userInput);
        assertNotNull(result);
        assertEquals("basket ball ", result.object1);
    }

    @Test
    public void testMatchWithMatchingParameters2() {
        System.out.println("parse");
        String userInput = "";
        GameParser instance = GameParser.getInstance();
        
        String[] wLIst = new String[]{"Examine","basket", "ball"};
        String Pattern = "Examine (\\D*)";
  
        boolean op = instance.match(wLIst, Pattern);
        assertNotNull(op);
        assertEquals(true, op);
    }
    
    //Test match function when input does not match with the pattern
    @Test
    public void testMatchWithoutMatchingParameters() {
        System.out.println("parse");
        String userInput = "";
        GameParser instance = GameParser.getInstance();
        
        String[] wLIst = new String[]{"Put","basket", "ball","into","wooden","box"};
        String Pattern = "Put (\\D*) in (\\D*)";
  
        boolean op = instance.match(wLIst, Pattern);
        assertNotNull(op);
        assertEquals(false, op);
    }
    
    //Test match function when input does not match with the pattern
    @Test
    public void testMatchWithoutMatchingParameters2() {
        System.out.println("parse");
        String userInput = "";
        GameParser instance = GameParser.getInstance();
        
        String[] wLIst = new String[]{"Put","basket", "ball"};
        String Pattern = "Put (\\D*) in (\\D*)";
  
        boolean op = instance.match(wLIst, Pattern);
        assertNotNull(op);
        assertEquals(false, op);
    }
    
    //Test match function when input matches with the pattern and contains no objects
    @Test
    public void testMatchWithoutMatchingParameters2() {
        System.out.println("parse");
        String userInput = "";
        GameParser instance = GameParser.getInstance();
        
        String[] wLIst = new String[]{"Inventory"};
        String Pattern = "Inventory";
  
        boolean op = instance.match(wLIst, Pattern);
        assertNotNull(op);
        assertEquals(true, op);
    }
}
