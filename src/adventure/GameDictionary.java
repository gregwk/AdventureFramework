package adventure;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

public class GameDictionary implements Dictionary {

  // singleton pattern
  
  private static final Dictionary INSTANCE = new GameDictionary();

  private GameDictionary() {
    //
	  GOs = new ArrayList<GameObject>();
	  GAs = new ArrayList<GameAction>();
  }

  public static Dictionary getInstance() {
    return INSTANCE;
  }

  
  // private fields
  private List<GameObject> GOs;
  private List<GameAction> GAs;
  
  // private methods
  
  private interface CheckWordFunc{
	  boolean func(String word, GameObject go);
  }
  
  /*
   * helper method to search through GameObject list and apply specific function
   */
  private boolean getGameObjectWordType(String word, CheckWordFunc CW){
	 boolean found = false;
	 for(GameObject G: GOs){
		 found = CW.func(word, G);
		 if(found) return true;	// return true when found, stop for loop
	 }
	 
	 return found; // return false
 }
  
  // public methods
  
  @Override
  public boolean isAdjective(String word) {
	if(word == null) throw new NullPointerException("Dictionary.isAdjective: Supplied word is null");
	else {
		if(!isDefined(word)) throw new NoSuchElementException("Dictionary.isAdjective: word is not defined");
	
		return getGameObjectWordType(word, new CheckWordFunc(){
			public boolean func(String word, GameObject go){
				return go.containsAdjective(word);
			}
		});
	}
  }

  @Override
  public boolean isNoun(String word) {
	if(word == null) throw new NullPointerException("Dictionary.isNoun: Supplied word is null");
	else {
	  	if(!isDefined(word)) throw new NoSuchElementException("Dictionary.isNoun: word is not defined");
			
	  	return getGameObjectWordType(word, new CheckWordFunc(){
			public boolean func(String word, GameObject go){
				return go.containsNoun(word);
			}
		});
	}
  }

  @Override
  public boolean isVerb(String word) {
  	if(word == null){
		throw new NullPointerException("Dictionary.isVerb: supplied word is null");
	} else {
		if(!isDefined(word)) throw new NoSuchElementException("Dictionary.isVerb: word is not defined");
  		for(GameAction A: GAs){
			if(A.getId().equals(word)){
				return true;	// word found as 
			}
		}
	}
	return false;
  }

  @Override
  public boolean isDefined(String word) {
	  if(word == null) throw new NullPointerException("Dictionary.isDefined: supplied word is null");
	  boolean found = false;
	  
	  found = getGameObjectWordType(word, new CheckWordFunc(){	// check word for adjective and noun
			public boolean func(String word, GameObject go) {
				return (go.containsAdjective(word) || go.containsNoun(word));
			}
	  });
	  
	  for(GameAction G: GAs){
		  found = found || G.getId().equals(word);	// check word if a verb
		  if(found) return true;	// return true when found, conclude for loop
	  }
	  
	  return found;
  }

  /*
   * Returns a string list of actionIDs for the supplied verb. 
   */
  @Override
  public List<String> getActions(String verb) {
	  if(verb == null) throw new NullPointerException("Dictionary.getActions: supplied verb is null");
	  if(!isDefined(verb)) throw new NoSuchElementException("Dictionary.getActions: supplied verb is not defined");
	  
	  List<String> AList = new ArrayList<String>();
	  
	  for(GameAction G: GAs){
		  if(G.getId().equals(verb)){
			  AList.add(G.getId());
		  }
	  }
	  
	  return AList;
  }

  @Override
  public List<String> getGameObjects(List<String> objectWords) {
    if(objectWords == null){ // throw exception if argument is null
    	throw new NullPointerException("Dictionary.getGameObjects: Supplied objectWords list is null");
    } else {
    	List<String> objIDs = new ArrayList<String>();
    	for(String s : objectWords){
    		if(s == null){
    			throw new NullPointerException("Dictionary.getGameObjects: Supplied objectWords list contains a null element");
    		} else {
    			boolean found = false;	// flag to determine if word was found in the GameObject list
    			for(GameObject G: GOs){
    				if(G.containsAdjective(s) || G.containsNoun(s)){
    					objIDs.add(new String(G.getId()));
    					found = true;	// object found in list
    				}
    			}
    			if(!found) {	// throw exception if the list doesn't contain a word
    				throw new NoSuchElementException("Dictionary.getGameObjects: GameObject list contains no such element");
    			}
    		}    		
    	}
    	return objIDs;
    }
  }
  @Override
  public void addGameObject(GameObject object) {
    if(object == null){
    	throw new NullPointerException("Dictionary.addGameObject: Supplied object is null");
    } else {
    	GOs.add(object);
    }

  }

  @Override
  public void addGameAction(GameAction action) {
	  if(action == null){
		  throw new NullPointerException("Dictionary.addGameAction: Supplied action is null");
	  } else {
		  GAs.add(action);
	  }
 }

}
