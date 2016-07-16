/**
 * 
 */

import java.util.List;

final class GameObject{};
final class GameAction{};
final class data{};

/**
 * @param S is the type of object the word is
 */
public interface Dictionary<S> {
	
	/* Need to decide how to set up the dictionary  */
	
	/*
	 * Returns true if successful adding data to the Dictionary.
	 * @param fileblob contains structured data to read for initializing the Dictionary (words, patterns, word type)
	 * @throw DataFormatException the fileblob isn't formatted correctly
	 */
	public boolean Initialize(data fileblob);
	
	
	
	/*
	 * Returns true or false if the supplied word is an Adjective
	 * @param word the word to determine if it is an Adjective
	 * @throw NullException if the word is null 
	 * @throw NotDefinedException if word is not defined
	 */
	public boolean isAdjective(S word);
	
	/*
	 * Returns true or false if the supplied word is a Noun
	 * @param word the word to determine if it is a Noun
	 * @throw NullException if the word is null 
	 * @throw NotDefinedException if word is not defined
	 */
	public boolean isNoun(S word);
	
	/*
	 * Returns true or false if the supplied word is a Verb
	 * @param word the word to determine if it is a Verb
	 * @throw NullException if the word is null 
	 * @throw NotDefinedException if word is not defined
	 */
	public boolean isVerb(S word);
	
	/*
	 * Returns true or false if the supplied word is defined
	 * @param word the word to determine if it is defined
	 * @throw NullException if the word is null 
	 */
	public boolean isDefined(S word);
	
	/*
	 * Returns a list of Pattern S objects for the supplied word.
	 * @param word the word to get the patterns
	 * @throw NullException if the supplied word is null
	 * @throw NotDefinedException if word is not defined
	 * @throw NullWordPatternException if the word does not have any patterns
	 */
	public List<S> getPatterns(S word);
	
	/*
	 * Returns a List of GameObjects for all of the supplied List of objectWords
	 * @param objectWords list of words to obtain GameObjects
	 * @throw NullListException if the objectWords List is null
	 * @throw NullException if the word is null within the List
	 * @throw NotDefinedException if any word in the List is not defined
	 */
	public List<S> getGameObjects(List<S> objectWords);
	
	/*
	 * 
	 * @throw NullException if the supplied object is null
	 * @throw NotDefinedException if word is not defined
	 */	
	public void addGameObject(GameObject obj);
	
	public void addGameAction(GameAction action);
	

}
