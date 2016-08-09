package adventure;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The purpose of this class is to initialize the following scenario, 
 * per Dr. K on Piazza: As an example, start with a two room game: 
 * garage and kitchen. You start in the garage but need to go east to 
 * the kitchen. There is a closed and locked door in the way. In the 
 * garage you have a (closed) toolbox. Inside the toolbox is a key. 
 * The player has to open the toolbox, take the key, and unlock the 
 * kitchen door.

 * @author Alex_Lappy_486
 *
 */
public class Game 
{
  private GameWorld world = TreeGameWorld.getInstance();

  /* Hardcoded Game Model descriptions */
  private final static String GARAGE_ID = "musty_garage";
  private final static String KITCHEN_ID = "kitchen";
  
  public Game() {}

  public void initialize() 
  {

      Room kitchen = new Room("kitchen");
      kitchen.setDescription("You are in a small kitchen. There is an exit to the east.");
      
      Room garage = new Room("garage");
      garage.setDescription("You are in a musty garage. There is an exit to the west.");

      kitchen.addExit("east", garage);
      garage.addExit("west", kitchen);
      
      world.addRoom(kitchen);
      world.addRoom(garage);
      
      Thing hammer = new Thing("hammer");
      hammer.setDescription("The hammer has a bright orange handle.");
      hammer.setParent("kitchen");
      world.addThing(hammer);
      
      Thing screwdriver = new Thing("screwdriver");
      screwdriver.setDescription("");
      screwdriver.setParent("kitchen");
      world.addThing(screwdriver);
            
      Thing toolbox = new Thing("toolbox");
      toolbox.setDescription("");
      toolbox.setParent("garage");
      toolbox.addProperty("container");
      toolbox.addProperty("openable");
      world.addThing(toolbox);

      Thing key = new Thing("key");
      key.setDescription("This rusty key might not be able to open anything.");
      key.setParent("toolbox");
      key.addProperty("takeable");
      key.addProperty("concealed");
      world.addThing(key);
      
      Player player = new Player("Alex");
      player.setDescription("You look like Alex.");
      player.setParent("kitchen");
      world.addThing(player);
      world.setPlayer(player);
      
//          //Initialize rooms and connections
//	  Map<String, Room> rooms = initRooms(GARAGE_ID, KITCHEN_ID);
//	  Room kitchen = rooms.get(KITCHEN_ID);
//	  Room garage = rooms.get(GARAGE_ID);
//	  RoomConnector garageToKitchenConnection = new RoomConnector(garage, kitchen, "east", "west");
//	  initRoomDirections(rooms, garageToKitchenConnection);
//	  
//	  //Init Things in Garage
//	  Thing toolbox = addThingToModel("Toolbox", "A standard workman's toolbox", garage.getId());
//	  Thing key = addThingToModel("Key", "This rusty key might not be able to open anything", toolbox.getId());
//	  Door garageToKitchenDoor = new Door("Kitchen Door", "This door connects the kitchen and the garage.");
//	  
//	  //Add locked door between garage and kitchen
//	  garageToKitchenDoor.addProperty(GameProperty.LOCKED, GameProperty.LOCKABLE);
//	  garage.addDoor("east", garageToKitchenDoor);
//	  kitchen.addDoor("west", garageToKitchenDoor);
//	  
//	  //Set the properties of the objects in the game. The key needs to be concealed until the toolbox is opened.
//	  key.addProperty(GameProperty.TAKABLE, GameProperty.CONCEALED);
//	  toolbox.addProperty(GameProperty.CONTAINER, GameProperty.OPENABLE);
//	  
//	  //Initialize the player
//	  Actor player = new Player("Alex");
//	  player.setParent(garage.getId());
//	  this.world.setPlayer(player);
  }
  
  /**
   * This method initializes a mapping of room names to Room objects.
   * @param roomNames a list of room names
   * @return a mapping of room names to Room objects
   */
  private Map<String, Room> initRooms(String...roomNames)
  {
	  Map<String, Room> rooms = new HashMap<>();
	  for (String s : roomNames)
	  {
		  //Init room and add it to the Gameworld model
		  Room r = new Room(s);
		  world.addRoom(r);
		  rooms.put(r.getId(),r);
	  }
	  
	  return rooms;
  }
  
  /**
   * This method creates connections between rooms
   * @param rooms a mapping of room IDs to rooms
   * @param connectors a list of RoomConnector objects, representing the way that the rooms are connected
   */
  private void initRoomDirections(Map<String, Room> rooms, RoomConnector...connectors)
  {
	  for (RoomConnector connector : connectors)
	  {
		  Room sourceRoom = rooms.get(connector.getFromRoom().getId());
		  Room targetRoom = rooms.get(connector.getToRoom().getId());
		  sourceRoom.addExit(connector.getFromToDescription(), targetRoom);
		  
		  //If the connection is bidirectional, add an inverse connection (target --> source_
		  if (connector.isBidirectional())
			  targetRoom.addExit(connector.getToFromDescription(), sourceRoom);
	  }
  }

  /**
   * This method creates a Thing object and adds it to the model of our game
   * @param name the name of the Thing
   * @param desc a description of the Thing. Can be null
   * @param parentID The ID of the Thing's parent object. Can be null
   * @return a newly-created Thing object
   */
  private Thing addThingToModel(String name, String desc, String parentID)
  {
	  Thing thing = null;
	  if (desc == null)
		  thing = new Thing(name);
	  else
		  thing = new Thing(name, desc);
	  
	  thing.setParent(parentID);
	  this.world.addThing(thing);
	  return thing;
  }
  
  public static void main(String[] args)
  {
	  Game game = new Game();
	  game.initialize();
  }
}
