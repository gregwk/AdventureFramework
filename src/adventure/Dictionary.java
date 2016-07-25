package adventure;

import java.util.List;

public interface Dictionary {

  /**
   * Returns true if the specified word is an adjective
   * 
   * @param word the word to determine if it is an Adjective
   * @throw NullPointerException if the word is null
   * @throw NoSuchElementException if word is not defined
   */
  public boolean isAdjective(String word);

  /**
   * Returns true or false if the supplied word is a Noun
   * 
   * @param word the word to determine if it is a Noun
   * @throw NullPointerException if the word is null
   * @throw NoSuchElementException if word is not defined
   */
  public boolean isNoun(String word);

  /**
   * Returns true or false if the supplied word is a Verb
   * 
   * @param word the word to determine if it is a Verb
   * @throw NullPointerException if the word is null
   * @throw NoSuchElementException if word is not defined
   */
  public boolean isVerb(String word);

  /**
   * Returns true or false if the supplied word is defined
   * 
   * @param word the word to determine if it is defined
   * @throw NullPointerException if the word is null
   */
  public boolean isDefined(String word);

  /**
   * Returns a list of string ActionID objects for the supplied word.
   * 
   * @param verb the verb to get the patterns
   * @throw NullPointerException if the supplied word is null
   * @throw NoSuchElementException if word is not defined
   * @throw NoSuchElementException if the word does not have any patterns
   */
  public List<String> getActions(String verb);

  /**
   * Returns a List of GameObjectsIDs for all of the supplied List of objectWords
   * 
   * @param objectWords list of words to obtain GameObjects
   * @throw NullPointerException if the objectWords List is null
   * @throw NullPointerException if the word is null within the List
   * @throw NoSuchElementException if any word in the List is not defined
   */
  public List<String> getGameObjects(List<String> objectWords);

  /**
   * Supply a new GameObject.
   * 
   * @param object the GameObject, but really storing its name
   * @throw NullPointerException if the supplied object is null
   */
  public void addGameObject(GameObject object);

  /**
   * Add a GameAction which is associated with a verb
   * 
   * @param action the GameAction to store
   * @throw NullPointerException if the supplied object is null
   */
  public void addGameAction(GameAction action);

}


