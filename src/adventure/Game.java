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
public class Game {
  
  private Dictionary dictionary = GameDictionary.getInstance();
  private GameWorld world = TreeGameWorld.getInstance();

  /* Hardcoded Game Model descriptions */
  private final static String GARAGE_ID = "musty_garage";
  private final static String KITCHEN_ID = "kitchen";
  private final static String TAKABLE = "takable";
  private final static String OPENABLE = "openable";
  private final static String UNLOCKED = "unlocked";
  private final static String LOCKED = "locked";
  
  public Game() {}

  public void initialize() 
  {
	  //Initialize rooms and connections
	  Map<String, Room> rooms = initRooms(GARAGE_ID, KITCHEN_ID);
	  Room kitchen = rooms.get(KITCHEN_ID);
	  Room garage = rooms.get(GARAGE_ID);
	  RoomConnector garageToKitchenConnection = new RoomConnector(garage, kitchen, "east", "west");
	  initRoomDirections(rooms, garageToKitchenConnection);
	  
	  //Init Things in Garage
	  Thing toolbox = addThingToModel("Toolbox", "A standard workman's toolbox", garage.getId());
	  Thing key = addThingToModel("Key", "This rusty key might not be able to open anything", toolbox.getId());
	  Door garageToKitchenDoor = new Door("Kitchen Door", "This door connects the kitchen and the garage.");
	  
	  //Add locked door between garage and kitchen
	  garageToKitchenDoor.addProperty(LOCKED);
	  garage.addDoor("east", garageToKitchenDoor);
	  kitchen.addDoor("west", garageToKitchenDoor);
	  
	  //Set the properties of the objects in the game
	  key.addProperty(TAKABLE);
	  toolbox.addProperty(OPENABLE);
	  
	  //Initialize the player
	  Actor player = new Player("Alex");
	  player.setParent(garage.getId());
	  this.world.setPlayer(player);
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
		  addRoomToModel(r);
		  rooms.put(r.getId(),r);
	  }
	  
	  return rooms;
  }
  
  private void initRoomDirections(Map<String, Room> rooms, RoomConnector...connectors)
  {
	  for (RoomConnector connector : connectors)
	  {
		  Room sourceRoom = rooms.get(connector.getFromRoom().getId());
		  Room targetRoom = rooms.get(connector.getToRoom().getId());
		  sourceRoom.addExit(connector.getFromToDescription(), targetRoom);
		  
		  if (connector.isBidirectional())
			  targetRoom.addExit(connector.getToFromDescription(), sourceRoom);
	  }
  }

  
  private Thing addThingToModel(String name, String desc, String parentID)
  {
	  Thing thing = null;
	  if (desc == null)
		  thing = new Thing(name);
	  else
		  thing = new Thing(name, desc);
	  
	  thing.setParent(parentID);
	  this.dictionary.addGameObject(thing);
	  this.world.addThing(thing);
	  return thing;
  }
  
  private void addRoomToModel(Room room)
  {
	  this.dictionary.addGameObject(room);
	  this.world.addRoom(room);
  }
  
  public static void main(String[] args)
  {
	  Game game = new Game();
	  game.initialize();
  }
}
