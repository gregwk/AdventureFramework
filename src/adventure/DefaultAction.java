package adventure;

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
                  Actor player = world.getPlayer();

                  Room room = world.getRoom(player.getId());


                  if(room.containsExit(command.object1))
                  {
                      /*room.getExit().getId();
                      world.move(player.getId(), );*/
                  }


                  return new Response("message", "you are in "+ command.object1);
              }
      );

      goAction.setResponder(responder);

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
                    Actor player = world.getPlayer();


                    if(world.isInInventory(command.object1))
                    {
                        if (!world.isInScope(command.object1))
                        {
                            return new Response("message", command.object1 +" not in scope");
                        }

                        GameObject gameObject = world.getGameObject(command.object1);


                        if(gameObject.containsProperty("takeable"))
                        {

                            if (gameObject instanceof Thing)
                            {
                                Thing thing = (Thing)gameObject;
                                String parentKey = thing.getParent();

                                GameObject parent = world.getGameObject(parentKey);

                                if(parent.containsProperty("open"))
                                {

                                    world.move(command.object1, player.getId());

                                    return new Response("message", "you have taken " + gameObject.getDescription());
                                }
                                else
                                {
                                    return new Response("message", "There is no such " + gameObject.getDescription());
                                }
                            }

                        }
                        else
                        {
                            return new Response("message", "There is no such "+ command.object1);
                        }
                    }
                    else
                    {
                        return new Response("message", "you don't have any such "+ command.object1);
                    }

                    return new Response("message", "you don't have any such "+ command.object1);
                }
        );

        takeAction.setResponder(responder);


        return takeAction;
    }

}
