package adventure;

public class Game 
{
	private Dictionary dictionary;
	private GameWorld gameWorld;
	
	public Game(Dictionary dict, GameWorld world)
	{
		this.dictionary = dict;
		this.gameWorld = world;
	}
	
	public void initialize()
	{
		//Set basic room layout
		Room kitchen = new Room("kitchen");
		kitchen.setDescription("A normal kitchen");
		kitchen.setNouns("kitchen");
		
		Room garage = new Room("garage");
		garage.setDescription("A dank, musty garage");
		garage.setNouns("garage");
		
		garage.addConnectedRoom("east", kitchen);
		kitchen.addConnectedRoom("west", garage);
		
		//Add objects to Garage
		Container container = new Container("toolbox");
		container.setNouns("toolbox");
		
		Thing key = new Thing("key");
		key.setDescription("A rusty toolbox");
		key.setNouns("key");
		key.setTakable(true);
		
		//Add key in toolbox
		container.add(key);
		
		//Add everything to GameWorld
		this.gameWorld.addObjectToGameWorld(kitchen);
		this.gameWorld.addObjectToGameWorld(garage);
		this.gameWorld.addObjectToGameWorld(container);
		this.gameWorld.addObjectToGameWorld(key);
		
		//Actions??
		GameAction open = new GameAction("open", "open", "used to open containers");
		GameAction take = new GameAction("take", "take", "used to take items in the world and put them in your inventory");
		this.dictionary.addGameAction(open);
		this.dictionary.addGameAction(take);
		
	}
}
