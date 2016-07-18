/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Implements the Parser interface 
 */
public class GameParser implements Parser {
    
    List<String> stopWords = Arrays.asList("a", "an", "the", "some");//TODO: this may come from dictionary or GameEngine??

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
    
    @Override
    public Command parse(String userInput) {
        
        Command outputCommand = new Command();
        
        if(userInput == null || userInput.trim().isEmpty() )
        {
            outputCommand.errorCode = "Empty Input";
            
            return outputCommand;
        }
        
        //1. Tokenize words
        String[] wordTokenList = tokenizeWords(userInput);
        
        //2. Remove stop words
        removeStopWords(wordTokenList);
        
        //3. Verify words exits in dictionary
        verifyWordsExitsInDictionary(wordTokenList);
        
        //4. Verify if the first word is a direction or verb
        verifyDirectionOrVerb(String firstWord);
        
        //5. If the first word is a verb fetch the corresponding patterns and their associated actions
           
        //6. Perform pattern matching using regular expressions
        
        //7. Identify objects and their adjectives to disambiguate in the next step
        
        //8. Disambiguate words
        outputCommand = disambiguateWords(wordTokenList);        
        
        return outputCommand;
    }

    /*
    * Returns array of strings in order as entered by user
    */
    private String[] tokenizeWords(String userInput) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    * Removes any stop words such as articles and others ??
    */
    private void removeStopWords(String[] wordTokenList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    * Verifies that all the words or group of words exits in the dictionary
    */
    private void verifyWordsExitsInDictionary(String[] wordTokenList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    private Command disambiguateWords(String[] wordTokenList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
