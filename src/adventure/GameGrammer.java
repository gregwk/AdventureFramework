package adventure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adeelahuma
 */

public class GameGrammer implements Grammer
{

    private static GameGrammer INSTANCE = new GameGrammer();


    private GameGrammer()
    {
        /**
         *  private constructor
         * */
    }

    /**
     *  Grammer is stored as a Hashmap
     *      key = actionId
     *      value = GameAction
     * */
    Map<String, GameAction> actionMap = new HashMap<String, GameAction>();

    @Override
    public GameAction getGameAction(String actionId)
    {

        if(!contains(actionId))
        {
            throw new IllegalArgumentException("ActionId does not exist");
        }

        return actionMap.get(actionId);
    }

    @Override
    public List<String> getPatterns(String actionId)
    {
        List<String> patterns = null;

        if(actionId != null)
        {
            GameAction gameAction = actionMap.get(actionId);

            if(gameAction != null )
            {
                patterns = gameAction.getPatterns();
            }
        }

        return patterns;
    }


    @Override
    public void addGameAction(GameAction gameAction)
    {

        if(gameAction != null)
        {
            if(gameAction.getId() == null || gameAction.getId().trim().isEmpty())
            {
                throw new IllegalArgumentException("Missing ActionId for game action");
            }

            if(gameAction.getPatterns() == null || gameAction.getPatterns().isEmpty())
            {
                throw new IllegalArgumentException("Missing Patterns for game action");
            }

            if(contains(gameAction.getId()))
            {
                throw new IllegalArgumentException("ActionId already exists");
            }


            actionMap.put(gameAction.getId(), gameAction);

        }
        else
        {
            throw new IllegalArgumentException("can not add null game action");
        }

    }

    @Override
    public boolean contains(String actionId)
    {
        return  actionMap.containsKey(actionId);
    }


    public static GameGrammer getInstance()
    {
        return INSTANCE;
    }
}
