package adventure.util.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import adventure.GameObject;
import adventure.GameProperty;
import adventure.GameWorld;

public class GameUtils
{
    public static void addPropertiesToGameObjects(Collection<GameObject> objects, GameProperty...properties)
    {
        for (GameObject o : objects)
        {
            for (GameProperty prop : properties)
            {
                o.addProperty(String.valueOf(prop));
            }
        }
    }

    public static void removePropertiesFromGameObjects(Collection<GameObject> objects, GameProperty...properties)
    {
        for (GameObject o : objects)
        {
            for (GameProperty prop : properties)
            {
                if (o.containsProperty(String.valueOf(prop)))
                    o.removeProperty(String.valueOf(prop));
            }
        }
    }
    
    /**
     * An object is considered "in scope" if it is in the current room of the player AND
     * it is not marked as "concealed".
     * @param world
     * @param object
     * @return
     */
    public static boolean objectIsInScope(GameWorld world, GameObject object)
    {
        boolean isInScope = true;

        if (world.isInScope(object.getId()))
        {
            if(object.containsProperty(GameProperty.CONCEALED.getPropId())) // is object hidden
            {
                isInScope = false;
            }
        }
        else
        {
            isInScope = false;
        }

        return isInScope;
    }
    
    public static List<String> getAllObjectsContainedInObject(GameWorld world, String parentID)
    {
    	List<String> allContainedObjects = new ArrayList<String>();
    	List<String> objChildren = world.getChildren(parentID);
		for (String childID : objChildren)
		{
			GameObject child = world.getGameObject(childID);
			if (GameUtils.objectIsInScope(world, child))
			{
				List<String> childsChildren = getAllObjectsContainedInObject(world, childID);
				if (childsChildren != null)	
					allContainedObjects.addAll(childsChildren);
				allContainedObjects.add(childID);
			}
		}
		return allContainedObjects;
    }
}