package adventure.util;

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

      Responder responder = (
              command -> {

                  // command.object1 holds direction
                  if (!world.isInScope(command.object1)) {
                      return new Response("message", "There is nothing in " + command.object1 + " direction");
                  }

                  return new Response("message", "Going towards "+ command.object1 + " direction");
              }
      );

      goAction.setResponder(responder);

    return goAction;
  }

    /**
     * Examine game action
     * */

    private GameAction getExamineAction()
    {
        GameAction exAction = new GameAction("examine");
        exAction.addPattern("examine {object}");
        exAction.addPattern("x {object}");
        exAction.addPattern("look at {object}");

        Responder responder = (
                command -> {

                    if (!world.isInScope(command.object1)) {
                        return new Response("message", command.object1 +" not in scope");
                    }

                    String description = world.getGameObject(command.object1).getDescription();


                    if (description.isEmpty()) {
                        return new Response("message", "");
                    } else
                    {
                        return new Response("message", "You are looking at "+ description);
                    }
                }
        );

        exAction.setResponder(responder);


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

                    if(!gameObject.containsProperty("takeable"))
                    {
                        return new Response ("message", gameObject.getDescription() + " is not take able");
                    }

                    /*//TODO: is already taken? where we are setting it? gameworld?*/

                    String description = gameObject.getDescription();


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
