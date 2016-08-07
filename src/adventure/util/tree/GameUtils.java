package adventure.util.tree;

import java.util.Collection;

import adventure.GameObject;
import adventure.GameProperty;

public class GameUtils 
{
	public static void addPropertiesToGameObjects(Collection<GameObject> objects, GameProperty...properties)
	{
		for (GameObject o : objects)
		{
			for (GameProperty prop : properties)
			{
				o.addProperty(prop);
			}
		}
	}
	
	public static void removePropertiesFromGameObjects(Collection<GameObject> objects, GameProperty...properties)
	{
		for (GameObject o : objects)
		{
			for (GameProperty prop : properties)
			{
				if (o.containsProperty(prop))
					o.removeProperty(prop);
			}
		}
	}
}
