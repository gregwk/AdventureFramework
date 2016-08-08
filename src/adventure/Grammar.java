package adventure;

import java.util.List;

/**
 * @author adeelahuma
 */
public interface Grammar {
  /**
   * get GameAction by actionId
   * 
   * @param actionId
   * @return GameAction
   */
  public GameAction getGameAction(String actionId);

  /**
   * Get Patterns for an action by actionId
   * 
   * @param actionId
   * @return List<String> - List of patterns associated with an actionId For example: 1. put
   *         {object} in {object} 2. put {object} on {object}
   */
  public List<String> getPatterns(String actionId);

  /**
   * Add gameAction to Grammer
   * 
   * @param gameAction object
   */
  public void addGameAction(GameAction gameAction);

  /**
   *
   * Check if Grammer contains actionId
   */
  public boolean contains(String actionId);

  public void clear();

}
