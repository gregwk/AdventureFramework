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
	private String[] parseWord(String Line){
		return Line.split("\\s");
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

			int idx = Collections.binarySearch(VWs, new VerbWord(word),VWcomp);
			if(idx<0) return false;
			else {
				VerbWord v = VWs.get(idx);
				return v.getVerb().equals(word);
			}
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
			found = found || G.getVerb().equals(word) || G.containsPrep(word);	// check word if a verb
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
				VerbWord verb = new VerbWord(parseWord(p)[0]);
				if(!VWs.contains(verb)){
					verb.addId(action.getId());
					VWs.add(verb);
				} else {
					int idx = Collections.binarySearch(VWs, verb, VWcomp);
					verb = VWs.get(idx);
					verb.addId(action.getId());
				}
				
				for(String s : action.getPatterns()){
					String[] prep = parseWord(s);
					if(prep!=null){
						for(String w:prep){
							if(!w.contains("object")) 
								verb.addPreposition(w);
						}
					}
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
					if(parseWord(x)[0].equals(verb)){
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
			List<GameObject> firstobjIDs = new ArrayList<GameObject>(); // first iteration to get noun objects
			List<String> objIDs = new ArrayList<String>();	// final list with adjectives to the nouns
			List<String> innouns = new ArrayList<String>();	// collect nouns in incoming list
			List<String> inadjs = new ArrayList<String>();	// collect adjectives in incoming list
			for(String s : objectWords){
				if(s == null){
					throw new NullPointerException("Dictionary.getGameObjects: Supplied objectWords list contains a null element");
				} else if(!isDefined(s)) {	// throw exception if the list doesn't contain a word
						throw new NoSuchElementException("Dictionary.getGameObjects: GameObject list contains no such element");
				} else if(isNoun(s)){
					innouns.add(s);
				} else if(isAdjective(s)){
					inadjs.add(s);
				}
				    		
			}
			
			for(String s: innouns){
				for(GameObject G: GOs){
					if(G.containsNoun(s)){
						firstobjIDs.add(G);
					}
				}
			}
			
			for(GameObject g : firstobjIDs){
				if(inadjs.size()==0) {
					if(!objIDs.contains(g.getId())) objIDs.add(g.getId());
				} else {
					for(String a:inadjs){
						if(g.containsAdjective(a)){
							if(!objIDs.contains(g.getId())) objIDs.add(g.getId());
						}
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
			boolean found = false;
			for(GameObject g : GOs){
				if(g.getId().equals(object.getId())) found = true;
			}
			if(!found) GOs.add(object);
		}

	}

	private class VerbWord implements Comparable<VerbWord> {
		private String Verb;
		private List<String> ids;
		private List<String> preps; // preposition words

		public VerbWord(String word){
			Verb = word;
			ids = new ArrayList<String>();
			preps = new ArrayList<String>();
		}
		
		public String getVerb(){
			return Verb;
		}

		public void addId(String Id){
			if(!ids.contains(Id)){
				ids.add(Id);		  
			}
		}
		
		public void addPreposition(String prep){
			if(!preps.contains(prep)){
				preps.add(prep);
			}
		}
		
		public boolean containsPrep(String word){
			return preps.contains(word);
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
