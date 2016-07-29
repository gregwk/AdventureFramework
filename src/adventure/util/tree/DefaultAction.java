package adventure.util.tree;

import adventure.*;

/**
 * @author adeelahuma
 *
 * This class initializes the GameActions
 */
public class DefaultAction
{
    private Grammar grammar = GameGrammar.getInstance();
    private GameWorld world = TreeGameWorld.getInstance();

    public void initialize()
    {
        grammar.addGameAction(getGoGameAction());
        grammar.addGameAction(getExamineAction());
        grammar.addGameAction(getTakeGameAction());
    }

    /**
     *  Go Action
     * */

  private GameAction getGoGameAction() {

    GameAction goAction = new GameAction("go");

    goAction.addPattern("go {direction}");

    //TODO: add responder

    return goAction;
  }

    private GameAction getExamineAction()
    {
        GameAction exAction = new GameAction("examine");
        exAction.addPattern("examine {object}");
        exAction.addPattern("x {object}");
        exAction.addPattern("look at {object}");

        //TODO: add responder


        return exAction;
    }

    /**
     *  Take Game Action
     * */

    private GameAction getTakeGameAction()
    {

        GameAction takeAction = new GameAction("take");

        takeAction.addPattern("take {object}");

        Responder responder = (
                command -> {
                    if (!world.isInScope(command.object1)) {
                        return new Response("message", command.object1 +" not in scope");
                    }

                    GameObject gameObject = world.getGameObject(command.object1);

                    //TODO : is this object take-able?
                   /* if(gameObject.isTakeable)
                    {

                    }*/

                    String description = world.getGameObject(command.object1).getDescription();

                    if (description.isEmpty()) {
                        return new Response("message", "");
                    } else
                    {
                        return new Response("message", description);
                    }
                }
        );

        takeAction.setResponder(responder);


        return takeAction;
    }

}
