package adventure;

import java.util.List;

import adventure.util.tree.GameUtils;

/**
 * @author adeelahuma
 *
 * This class initializes the GameActions
 */
public class DefaultAction
{
    private Grammar grammar = GameGrammar.getInstance();
    private GameWorld world = TreeGameWorld.getInstance();
    private static final String OPEN = "open";
    private static final String TAKE = "take";
    private static final String GO = "go";

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

        Responder responder = (
                command -> {
                    if (!world.isInScope(command.object1)) {
                        return new Response("message", command.object1 +" not in scope");
                    }

                    String description = world.getGameObject(command.object1).getDescription();


                    if (description.isEmpty())
                    {
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
                    Actor player = world.getPlayer();


                    if(world.isInInventory(command.object1))
                    {
                        if (!world.isInScope(command.object1))
                        {
                            return new Response("message", command.object1 +" not in scope");
                        }

                        GameObject gameObject = world.getGameObject(command.object1);


                        if(gameObject.containsProperty(GameProperty.TAKABLE.getPropId()))
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
    
    /**
     * This method initializes the "Open" game action. The algorithm is as follows:
     * 1. If the object is NOT in scope, return a "not-in-scope" message
     * 2. If the object is not openable, return a "not openable" message
     * 3. If the object is locked, return an "is locked" message
     * 4. If 1-3 are false, then do the following:
     *     a. If the object is a container, remove the "concealed" property from its children.
     *     b. Add the "open" property to the container.
     *     c. Return a message saying that the object is opened.
     * @return A GameAction representig the "Open" command
     */
    private GameAction getOpenGameAction()
    {
    	//Initialize the Game Action and its associated pattern
        GameAction takeAction = new GameAction(OPEN);
        takeAction.addPattern(OPEN+" {object}");

        Responder responder = 
        (
        	command -> 
        	{
        		GameObject object1 = this.world.getGameObject(command.object1);
        		
        		if (objectIsInScope(world, object1)) 
        			return getNotInScopeMessage(object1);
        		else if (!object1.containsProperty(GameProperty.OPENABLE))
        			return new Response("message", object1.getName()+" is not openable");
        		else if (object1.containsProperty(GameProperty.LOCKED))
        			return new Response("message", object1.getName()+" is locked");
        		else //We passed all of the negative tests.
        		{
        			//If this object has children, make all of them visible
        			if (object1.containsProperty(GameProperty.CONTAINER))
        			{
        				List<GameObject> children = world.getChildrenOfGameObject(object1.getId());
        				GameUtils.removePropertiesFromGameObjects(children, GameProperty.CONCEALED);
        			}
        			
        			//Mark the container as opened
        			object1.addProperty(GameProperty.OPEN);
        			return new Response("message", "You opened the "+object1.getName());
        		}
            }
        );
        takeAction.setResponder(responder);
        return takeAction;
    }
    
    private Response getNotInScopeMessage(GameObject object)
    {
    	return new Response("message", object.getName()+" not in scope");
    }
    
    /**
     * An object is considered "in scope" if it is in the current room of the player AND
     * it is not marked as "concealed".
     * @param world
     * @param object
     * @return
     */
    private boolean objectIsInScope(GameWorld world, GameObject object)
    {
    	return !world.isInScope(object.getId()) || object.containsProperty(GameProperty.CONCEALED);
    }
}