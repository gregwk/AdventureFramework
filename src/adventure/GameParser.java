/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package adventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
  
  //Initialize instance of TreeGameWorld
  GameWorld gameWorld = TreeGameWorld.getInstance();

  // Make GameParser a singleton class
  private static GameParser instance = new GameParser();

  private GameParser() {}

  public static GameParser getInstance() {
    return instance;
  }
  
  /**
   * Reset the Parser instance so that any remnants from previous parse 
   * invocation do not affect the current exection
   */
  private void resetParser(){
      command = new Command();
      obj1NounWords = obj2NounWords = null;
  }

  @Override
  public Command parse(String userInput) {
      
    resetParser();
    
    if (userInput == null || userInput.trim().isEmpty()) {
      command.errorMessage = "Empty Input";

      return command;
    }

    // 1. Tokenize words
    String[] wordTokens = tokenizeWords(userInput.toLowerCase());
    if (wordTokens == null || wordTokens.length == 0) {
        command.errorMessage = "Invalid Input";
        return command;
    }

    // 2. Remove stop words
    wordTokens = removeStopWords(stopWords, wordTokens);
    if(wordTokens == null || wordTokens.length <= 0){
        command.errorMessage = "Invalid Input";
        return command;
    }    

    // 3. Verify if the first word is a verb
    //In this release we are assumming that direction will be
    //preceeded by verb 'go'
    if(!dictionary.isVerb(wordTokens[0])){
        command.errorMessage = "Invalid first word";
        return command;
    }

    // 4. If the first word is a verb fetch the corresponding actions
    if (dictionary.isVerb(wordTokens[0])) {
      try {
        List<String> actionList = dictionary.getActions(wordTokens[0]);
        if (actionList == null || actionList.isEmpty()) {
          command.errorMessage = "Invalid action";
        } else {
          for (String action : actionList) {
              //Get the patterns for this action from Grammar
              if (action != null || !action.isEmpty()) {
                  List<String> patternList = grammar.getPatterns(action);
              if (patternList == null || patternList.isEmpty()) 
              {
                  command.errorMessage = "Invalid action";
              } 
              else 
              {
                  for (String pattern : patternList) {
                      if (match(wordTokens, pattern)) {
                          command.action = action;
                          //Disambiguate objects from noun words
                          if(obj1NounWords != null && obj1NounWords.length > 0){
                            command.object1 
                                        = disambiguateNounWords(obj1NounWords);
                            if (!command.errorMessage.equals("")) {
                                return command;
                            }
                          }
                          
                          if(obj2NounWords != null && obj2NounWords.length > 0){
                            command.object2 
                                        = disambiguateNounWords(obj2NounWords);
                            if (!command.errorMessage.equals("")) {
                                return command;
                            }
                          }
                          break; // No need to try other patterns
                      }
                  }
              }
              if((command.action != null && !"".equals(command.action)) 
                   || !command.errorMessage.equals(""))
                  //If there is error or the action is determined 
                  //then no need to proceed with other actions
                  break;
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

  /**
   * Removes any stop words such as articles
   * @param stopWords The array of String to be filtered from user input
   * @param wordTokenList User input from which stop words are to be filtered
   * @return Return String array after filtering stop words from wordTokenList
   */
  public String[] removeStopWords(String[] stopWords, String[] wordTokenList) {
    if(stopWords == null || stopWords.length == 0)
        return wordTokenList;
    else if (wordTokenList == null || wordTokenList.length == 0)
        return null;
    
    //Create a list to make easy to remove and resize the array
    List<String> wordList = new ArrayList<>(Arrays.asList(wordTokenList));
    for(String sWord: stopWords)
    {
        wordList.removeAll(Arrays.asList(sWord));
    }    
    return wordList.toArray(new String[0]);
  }
  
  /**
   * 
   * @param nounWords
   * @return 
   */
  private String disambiguateNounWords(String[] nounWords) {
      //First verify that the words exist in dictionary
      verifyWordsDefined(nounWords);
      if (!command.errorMessage.equals("")) {
        return "";
      }
     
      /*
      //Can we assume if there are more than 1 words in the input nounWords
      //then the last word is always a noun?
      //Then we can do something like this:
      if(!dictionary.isNoun(nounWords[nounWords.length - 1])){
          command.errorMessage = "Disambiguation Error";
          return "";
      }
      
      //How do I get game object Id list from dictionary??
      List<String> gameObjectIdList = dictionary.getGameObjects(???);
      
      for(String gameObjectId: gameObjectIdList){
          GameObject gameObj = gameWorld.getGameObject(gameObjectId);
          //If the last word is noun then any words before it must be adjectives
          //So check if all the words are adjectives of the object
          
          if(nounWords.length > 1){
              boolean isValidNoun = true;
              for(int i = 0; i < nounWords.length - 1; i++){
                  if(!gameObj.containsAdjective(nounWords[i])){
                      isValidNoun = false;
                      break;
                  }
              }
              if(!isValidNoun)
                  continue;
          }
          
          if(gameWorld.isInScope(gameObjectId))
              return gameObjectId;
      }
      */
     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  /**
   * 
   * @param words Array of strings
   * @param pattern
   * @return Boolean indicating if the pattern matching was successful 
   */
  public boolean match(String[] words, String pattern)
  {
      //The pattern will not have regex in it so we need to convert it from
      //human readable pattern to regex pattern.
      //The assumption is that the pattern will have replacable words surrounded
      //by braces {} - example: put {objec1} in {object2}
      //So we need to replace {*} with (\\D*) or (\\w*)
      String regexPattern = pattern.replaceAll("\\{(.*?)\\}", "(\\\\D*)");
      
      String Sentence = String.join(" ", words);
      Pattern patternToCheck = Pattern.compile(regexPattern
                                               ,Pattern.CASE_INSENSITIVE);
      Matcher matchedPattern = patternToCheck.matcher(Sentence);
      
      if(matchedPattern.find( )){
          int matchedGroup = matchedPattern.groupCount();
          if(matchedGroup > 0){
              //command.object1 = matchedPattern.group(1);
              obj1NounWords = matchedPattern.group(1).split("\\s+");
              if(matchedGroup == 2){
                  //command.object2 = matchedPattern.group(2);
                  obj2NounWords = matchedPattern.group(2).split("\\s+");
              }
          }
          return true;
          
          //TODO: We need to capture and store the values in the original 
          //      pattern, the values inside braces, {}. 
          //      Example: put {thing} in {container}
          //      As Dr. K mentioned in class, the pattern could be something
          //      like this where the values in the braces are the properties
          //      of the objects to be disambiguated. 
      }
      return false;
  }
    
  /**
   * Verifies that all the words or group of words exist in the dictionary
   * @param words 
   */
  public void verifyWordsDefined(String[] words) {
    if(words != null || words.length > 0){        
        for(String word : words){
            if(!dictionary.isDefined(word)){
                command.errorMessage = String.format("%s is not defined.", word);
                return;
            }
        }
    }
  }
}
