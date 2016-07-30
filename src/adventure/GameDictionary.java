package adventure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

public class GameDictionary implements Dictionary {

	// singleton pattern

	private static final Dictionary INSTANCE = new GameDictionary();

	private GameDictionary() {
		GOs = new ArrayList<GameObject>();
		VWs = new ArrayList<VerbWord>();
	}

	public static Dictionary getInstance() {
		return INSTANCE;
	}

	// private fields
	private List<GameObject> GOs;
	private List<VerbWord> VWs;
	private Comparator<VerbWord> VWcomp = new Comparator<VerbWord>(){
		@Override
		public int compare(VerbWord o1, VerbWord o2) {
			return o1.compareTo(o2);
		}		
	};

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
	
	/*
	 * helper to return first word in string
	 */
	private String parseVerb(String Line){
		String[] result = Line.split("\\s");
		return result[0];
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
						VerbWord v = VWs.get(Collections.binarySearch(VWs, new VerbWord(word),VWcomp));
			return v.getVerb().equals(word);
		}
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

		for(VerbWord G: VWs){
			found = found || G.getVerb().equals(word);	// check word if a verb
			if(found) return true;	// return true when found, conclude for loop
		}

		return found;
	}

	/*
	 * Returns a string list of action IDs for the supplied verb. 
	 */
	@Override
	public List<String> getActions(String verb) {
		if(verb == null) throw new NullPointerException("Dictionary.getActions: supplied verb is null");
		if(!isDefined(verb)) throw new NoSuchElementException("Dictionary.getActions: supplied verb is not defined");

		List<String> AList = new ArrayList<String>();
		
		VerbWord search = new VerbWord(verb);

		int idx = Collections.binarySearch(VWs, search, VWcomp);
		if(VWs.get(idx).getVerb().equals(verb)){
			AList.addAll(VWs.get(idx).getIds());
		}

		return AList;
	}

	@Override
	public void addGameAction(GameAction action) {
		if(action == null){
			throw new NullPointerException("Dictionary.addGameAction: Supplied action is null");
		} else {
			Grammar grammar = GameGrammar.getInstance();
			if(!grammar.contains(action.getId())) grammar.addGameAction(action);
			
			for(String p: action.getPatterns()){
				VerbWord verb = new VerbWord(parseVerb(p));
				if(!VWs.contains(verb)){
					verb.addId(action.getId());
					VWs.add(verb);
				} else {
					int idx = Collections.binarySearch(VWs, verb, VWcomp);
					VWs.get(idx).addId(action.getId());
				}
			}
			VWs.sort(VWcomp); 	// sort so binary search works
		}
	}
	
	/*
	 * With the supplied verb, uses "isVerb" test, returns list of actionPattern pairs relevant for the verb
	 */
	public List<GameActionPattern> getActionPatterns(String verb){
		List<GameActionPattern> aps = new ArrayList<GameActionPattern>();
		
		if(isVerb(verb)){
			VerbWord v = VWs.get(Collections.binarySearch(VWs, new VerbWord(verb),VWcomp));
			
			for(String id:v.getIds()){
				GameActionPattern ap = new GameActionPattern(id);
				
				List<String> p = GameGrammar.getInstance().getPatterns(id);
				for(String x: p){
					if(parseVerb(x).equals(verb)){
						ap.addPattern(x);
					}
				}
				
				aps.add(ap);				
			}
		}
		
		return aps;
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
							objIDs.add(G.getId());
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

	private class VerbWord implements Comparable<VerbWord> {
		private String Verb;
		private List<String> ids;

		public VerbWord(String word){
			Verb = word;
			ids = new ArrayList<String>();
		}
		
		public String getVerb(){
			return Verb;
		}

		public void addId(String Id){
			if(!ids.contains(Id)){
				ids.add(Id);		  
			}
		}

		public List<String> getIds(){
			return ids;
		}
		
		public boolean equals(Object word){
			return Verb.equals(((VerbWord) word).Verb);
		}
		
		public int hashCode(){
			return Verb.hashCode();
		}

		public int compareTo(VerbWord word) {
			return Verb.compareTo(word.Verb);
		}
	}
}
