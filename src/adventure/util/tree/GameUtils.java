package adventure.util.tree;

import java.util.Collection;

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
}