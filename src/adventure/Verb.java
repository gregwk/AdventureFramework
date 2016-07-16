package adventure;

public class Verb extends Word {
	List<Action> Actions;
	List<Pattern> Patterns;
	
	public Verb(){
		Actions = new ArrayList<Action>();
		Patterns = new ArrayList<Pattern>();
	}
	
}
