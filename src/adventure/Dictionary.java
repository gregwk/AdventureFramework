package adventure;

import java.util.List;

public interface Dictionary {

  /*
   * Returns true or false if the supplied word is an Adjective
   * 
   * @param word the word to determine if it is an Adjective
   * 
   * @throw NullException if the word is null
   * 
   * @throw NotDefinedException if word is not defined
   */
  public boolean isAdjective(String word);

  /*
   * Returns true or false if the supplied word is a Noun
   * 
   * @param word the word to determine if it is a Noun
   * 
   * @throw NullException if the word is null
   * 
   * @throw NotDefinedException if word is not defined
   */
  public boolean isNoun(String word);

  /*
   * Returns true or false if the supplied word is a Verb
   * 
   * @param word the word to determine if it is a Verb
   * 
   * @throw NullException if the word is null
   * 
   * @throw NotDefinedException if word is not defined
   */
  public boolean isVerb(String word);

  /*
   * Returns true or false if the supplied word is defined
   * 
   * @param word the word to determine if it is defined
   * 
   * @throw NullException if the word is null
   */
  public boolean isDefined(String word);

  /*
   * Returns a list of Pattern S objects for the supplied word.
   * 
   * @param verb the verb to get the patterns
   * 
   * @throw NullException if the supplied word is null
   * 
   * @throw NotDefinedException if word is not defined
   * 
   * @throw NullWordPatternException if the word does not have any patterns
   */
  public List<String> getActions(String verb);

  /*
   * Returns a List of GameObjects for all of the supplied List of objectWords
   * 
   * @param objectWords list of words to obtain GameObjects
   * 
   * @throw NullListException if the objectWords List is null
   * 
   * @throw NullException if the word is null within the List
   * 
   * @throw NotDefinedException if any word in the List is not defined
   */
  public List<String> getGameObjects(List<String> objectWords);

  /*
   * Supply a new GameObject.
   * 
   * @param object the GameObject, but really storing its name
   * 
   * @throw NullException if the supplied object is null
   */
  public void addGameObject(GameObject object);

  /*
   * Add a GameAction which is associated with a verb
   * 
   * @param action the GameAction to store
   * 
   * @throw NullException if the supplied object is null
   */
  public void addGameAction(GameAction action);

}


