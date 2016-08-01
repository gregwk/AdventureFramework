/**
 * 
 */
package adventure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brad Herald
 *
 */
public class GameActionPattern {
	private String actionId;
	private List<String> patterns;
	
	public GameActionPattern(String Id){
		actionId = Id;
		patterns = new ArrayList<String>();
	}
	
	public String getId(){
		return actionId;
	}
	
	public void addPattern(String Pattern){
		patterns.add(Pattern);
	}
	
	public List<String> getPatterns(){
		return patterns;
	}
}
