package adventure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adeelahuma
 */

public class GameGrammar implements Grammar {

  private static final GameGrammar INSTANCE = new GameGrammar();

  private GameGrammar() {}

  private Map<String, GameAction> actionMap = new HashMap<String, GameAction>();

  public static GameGrammar getInstance() {
    return INSTANCE;
  }

  @Override
  public GameAction getGameAction(String actionId) {

    if (!contains(actionId)) {
      throw new IllegalArgumentException("ActionId does not exist");
    }

    return actionMap.get(actionId);
  }

  @Override
  public List<String> getPatterns(String actionId) {
    List<String> patterns = null;

    if (actionId != null) {
      GameAction gameAction = actionMap.get(actionId);

      if (gameAction != null) {
        patterns = gameAction.getPatterns();
      }
    }

    return patterns;
  }


  @Override
  public void addGameAction(GameAction gameAction) {

    if (gameAction != null) {
      if (gameAction.getId() == null || gameAction.getId().trim().isEmpty()) {
        throw new IllegalArgumentException("Missing ActionId for game action");
      }

      if (gameAction.getPatterns() == null || gameAction.getPatterns().isEmpty()) {
        throw new IllegalArgumentException("Missing Patterns for game action");
      }

      if (contains(gameAction.getId())) {
        throw new IllegalArgumentException("ActionId already exists");
      }


      actionMap.put(gameAction.getId(), gameAction);

    } else {
      throw new IllegalArgumentException("can not add null game action");
    }

  }

  @Override
  public boolean contains(String actionId) {
    return actionMap.containsKey(actionId);
  }


    private GameAction getGoGameAction()
    {
        /**
         *  Go Action
         * */
        GameAction goAction = new GameAction("go");

        goAction.addPattern("go {direction}");

        return goAction;
    }

    private GameAction getExamineAction()
    {
        GameAction exAction = new GameAction("examine");
        exAction.addPattern("examine {object}");
        exAction.addPattern("x {object}");
        exAction.addPattern("look at {object}");

        return exAction;
    }

    private GameAction getTakeGameAction()
    {

        /**
         *  Take Game Action
         * */
        GameAction takeAction = new GameAction("take");

        takeAction.addPattern("take {object}");

        return takeAction;
    }

    /**
     *  Initialize Grammer with Go, Take, Examine actions
     * */
    public void init()
    {
        addGameAction(getGoGameAction());
        addGameAction(getExamineAction());
        addGameAction(getTakeGameAction());
    }

}
