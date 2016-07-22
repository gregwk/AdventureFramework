package adventure;

import java.util.List;

public class GameDictionary implements Dictionary {

  // singleton pattern
  
  private static final Dictionary INSTANCE = new GameDictionary();

  private GameDictionary() {
    //
  }

  public static Dictionary getInstance() {
    return INSTANCE;
  }

  // public methods
  
  @Override
  public boolean isAdjective(String word) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isNoun(String word) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isVerb(String word) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isDefined(String word) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<String> getActions(String verb) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String> getGameObjects(List<String> objectWords) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addGameObject(GameObject object) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addGameAction(GameAction action) {
    // TODO Auto-generated method stub

  }

}
