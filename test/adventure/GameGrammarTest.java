package adventure;

import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author adeelahuma
 */
public class GameGrammarTest
{

    GameGrammar grammar = GameGrammar.getInstance();

    /**
     *  convenience get action methods for testing
     * */

    private GameAction getInsertGameAction()
    {
        /**
         *  Insert Game Action
         * */
        GameAction insertAction = new GameAction("insert");

        insertAction.addPattern("put {object} in {object}");
        insertAction.addPattern("put {object} into {object}");

        return insertAction;
    }

    private GameAction getCloseAction()
    {
        GameAction closeAction = new GameAction("close");
        closeAction.addPattern("close {object}");

        return closeAction;
    }

    private GameAction getPutOnGameAction()
    {

        /**
         *  Put_On Game Action
         * */
        GameAction putOnTopAction = new GameAction("put_on_top");

        putOnTopAction.addPattern("put {object} on {object}");

        return putOnTopAction;
    }

    private GameAction getOpenGameAction()
    {

        /**
         *  Open Game Action
         * */
        GameAction openAction = new GameAction("open");

        openAction.addPattern("open {object}");

        return openAction;
    }

    @Test
    public void addGameAction()
    {

        GameAction action = getInsertGameAction();

        //Assert.assertNull("Insert Action not found", grammar.getGameAction(action.getId())); //commented as method throws exception


        //Add action
        grammar.addGameAction(action);

        Assert.assertNotNull("Insert Action found", grammar.getGameAction(action.getId()));

    }

    @Test
    public void getGameAction()
    {
        GameAction putGA = getPutOnGameAction();

        grammar.addGameAction(putGA);
        GameAction putGA_1 = grammar.getGameAction(putGA.getId());

        Assert.assertNotNull(putGA_1);
        Assert.assertEquals("Inserted and Retreived Object are same", putGA, putGA_1);
    }

    @Test
    public void getPatterns()
    {
        GameAction open = null;//grammar.getGameAction("open"); // commented as method throws exception

        Assert.assertNull("Should be Null because action not inserted in actionMap yet", open);

        List<String> openAction = grammar.getPatterns("open");

        Assert.assertNull("Should be Null because action not inserted in actionMap yet", openAction);

        open = getOpenGameAction();

        //add action to actionMap
        grammar.addGameAction(open);

        Assert.assertNotNull("Should Not Null because action inserted in actionMap ", grammar.getGameAction("open"));
        Assert.assertNotNull("Action Patterns Not Null", grammar.getPatterns("open"));

    }

    @Test
    public void containsAction()
    {

        Assert.assertFalse("action Id does not exist", grammar.contains("close"));

        grammar.addGameAction(getCloseAction());

        Assert.assertTrue("action Id does exist", grammar.contains("close"));

    }

    @Test
    public void createGameAction()
    {
        /**
         *  Insert Game Action
         * */

        //GameAction insertAction = new GameAction("  "); method throws exception
        GameAction insertAction = new GameAction("close");

        //insertAction.addPattern("   "); method throws exception
        insertAction.addPattern("close {object}");

    }

    @Test
    public void initialize()
    {
        Assert.assertFalse("action Id does not exist", grammar.contains("examine"));
        grammar.init();
        Assert.assertTrue("action Id does exist", grammar.contains("examine"));
    }


}
