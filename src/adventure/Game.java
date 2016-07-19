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
		
		Room garage = new Room("garage");
		garage.setDescription("A dank, musty garage");
		
		garage.addExit("east", kitchen);
		kitchen.addExit("west", garage);
		
		//Add objects to Garage
		Thing key = new Thing("key");
		key.setDescription("A rusty toolbox");
		key.setIsTakable(true);
		
		//Add key in toolbox
		garage.addObjectToRoom(key);
		
		//Add everything to GameWorld
		this.gameWorld.addObjectToGameWorld(kitchen);
		this.gameWorld.addObjectToGameWorld(garage);
		this.gameWorld.addObjectToGameWorld(key);
		
		//Actions??
		GameAction open = new GameAction("open", "open", "used to open containers");
		GameAction take = new GameAction("take", "take", "used to take items in the world and put them in your inventory");
		this.dictionary.addGameAction(open);
		this.dictionary.addGameAction(take);
	}
}
