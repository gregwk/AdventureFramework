/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package adventure;

import java.util.List;

/**
 *
 * Implements the Parser interface
 */
public class GameParser implements Parser {

  private final String[] stopWords = {"a", "an", "the"};

  private Command command = new Command();

  private String[] obj1NounWords, obj2NounWords;

  // Initialize instance variable of Dictionary
  Dictionary dictionary = GameDictionary.getInstance();

  // Initialize instance variable of Grammar
  Grammar grammar = GameGrammar.getInstance();

  // Make GameParser a singleton class
  private static GameParser instance = new GameParser();

  private GameParser() {}

  public static GameParser getInstance() {
    return instance;
  }

  @Override
  public Command parse(String userInput) {

    if (userInput == null || userInput.trim().isEmpty()) {
      command.errorMessage = "Empty Input";

      return command;
    }

    // 1. Tokenize words
    String[] wordTokens = tokenizeWords(userInput);
    if (!command.errorMessage.equals("")) {
      return command;
    }

    // 2. Remove stop words
    removeStopWords(stopWords, wordTokens);

    // 3. Verify words exits in dictionary
    verifyWordsDefined(wordTokens);
    if (!command.errorMessage.equals("")) {
      return command;
    }

    // 4. Verify if the first word is a direction or verb
    Boolean isDirectionOrVerb = verifyDirectionOrVerb(wordTokens[0]);
    if (!command.errorMessage.equals("")) {
      return command;
    }

    // 5. If the first word is a verb fetch the corresponding patterns and their associated actions
    if (dictionary.isVerb(wordTokens[0])) {
      try {
        List<String> actionPatterns = dictionary.getActions(wordTokens[0]);
        if (actionPatterns == null || actionPatterns.isEmpty()) {
          command.errorMessage = "Invalid action";
        } else {
          for (String pattern : actionPatterns) {
            if (pattern != null || !pattern.isEmpty()) {
              if (match(wordTokens, pattern)) {
                // TODO: set action id in command
                // command.action = ??

                disambiguateNounWords(wordTokens);
                break; // No need to try other patterns
              }
            }
          }
        }
      } catch (Exception ex) {
        // TODO: How should the exception when retriving patterns from dictionary be handled??
        command.errorMessage = "System Exception: Error while retrieving patterns from dictionary";
      }
    } else {
      // The first word is direction
      // so the implicit verb is "go" and Object1 is the direction
      command.action = "go";// TODO: this will eventually need to be retrieved from Dictionary
      command.object1 = wordTokens[0];

      // Here we need to check that there is no additional user input
      // Can we just assume that if the length of the word token is more than 1 then it is error?
      if (wordTokens.length > 1) {
        command.errorMessage = "Invalid user input.";
      }

      return command;
    }

    return command;
  }

  /*
   * Returns array of strings in order as entered by user
   */
  public String[] tokenizeWords(String userInput) {
    String[] wordTokens = userInput.split("\\s+");
    if(wordTokens.length == 1 && "".equals(wordTokens[0]))
    {
        //If the user input is empty string or white space
        //then return null
        wordTokens = null;
    }
    
    return wordTokens;
  }

  /*
   * Removes any stop words such as articles and others ??
   */
  private void removeStopWords(String[] stopWords, String[] wordTokenList) {
    if(stopWords == null || stopWords.length == 0 || wordTokenList == null || wordTokenList.length == 0)
        return;
  }

  /*
   * Verifies that all the words or group of words exits in the dictionary
   */
  private void verifyWordsDefined(String[] words) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  private Command disambiguateNounWords(String[] words) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  private Boolean verifyDirectionOrVerb(String firstWord) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  private boolean match(String[] words, String pattern) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
