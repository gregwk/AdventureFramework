package adventure;

import java.util.List;

public interface Dictionary {
    public boolean isAdjective(String word); 
    public boolean isNoun(String word);
    public boolean isVerb(String word);
    public boolean isDefined(String word);
    public List<String> getPatterns(String word);
    public List<GameObject> getGameObjects(List<String> objectWords);
    public void addGameObject(GameObject obj);
    public void addGameAction(GameAction action);
}