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
    
    List<String> stopWords = Arrays.asList("a", "an", "the",);//TODO: this may come from dictionary or GameEngine??

    Dictionary dictionaryInstance;
    
    public GameParser(Dictionary dictionary)
    {
        dictionaryInstance = dictionary;
    }
    
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
        
        //4. Disambiguate words
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
