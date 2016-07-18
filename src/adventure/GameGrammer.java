package adventure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adeelahuma
 */

public class GameGrammer implements Grammer
{
    /**
     *  Grammer is stored as a Hashmap
     *      key = actionId
     *      value = GameAction
     * */
    Map<String, GameAction> grammer = new HashMap<String, GameAction>();

    @Override
    public GameAction getGameAction(String actionId)
    {
        GameAction gameAction = null;

        if(actionId != null && grammer != null && !grammer.isEmpty())
        {
            gameAction = grammer.get(actionId);
        }

        return gameAction;
    }

    @Override
    public List<String> getPatterns(String actionId)
    {
        List<String> patterns = null;

        if(actionId != null && grammer != null && !grammer.isEmpty())
        {
            GameAction gameAction = grammer.get(actionId);

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
                throw new IllegalArgumentException("Missing ActionId for game action.");
            }

            if(gameAction.getPatterns() ==null || gameAction.getPatterns().isEmpty())
            {
                throw new IllegalArgumentException("Missing Patterns for game action.");
            }

            if(contains(gameAction.getId()))
            {
                throw new IllegalArgumentException("ActionId already exist in grammer");
            }


            grammer.put(gameAction.getId(), gameAction);
        }
        else
        {
            throw new IllegalArgumentException("can not add null game action");
        }

    }

    @Override
    public boolean contains(String actionId)
    {
        boolean isKey = false;

        if(actionId != null && grammer != null && !grammer.isEmpty())
        {
            isKey = grammer.containsKey(actionId);
        }
        return isKey;
    }
}
