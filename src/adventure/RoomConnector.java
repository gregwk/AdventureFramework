package adventure;

/**
 * This class represents the information needed to connect two rooms together in the game world.
 * @author Alex_Lappy_486
 *
 */
public class RoomConnector 
{
	/**
	 * The source room
	 */
	private Room fromRoom;
	
	/**
	 * The target room
	 */
	private Room toRoom;
	
	/**
	 * A description which describes the direction of the target room
	 */
	private String fromToDescription;
	
	private String toFromDescription;
	
	/**
	 * True if the player can go from the toRoom to the fromRoom,
	 * false if the player can ONLY go from the fromRoom to the toRoom
	 */
	private boolean bidirectional;
	
	/**
	 * Use this constructor if you want to represent a one-way connection between rooms
	 * @param fromRoom
	 * @param toRoom
	 * @param desc
	 */
	public RoomConnector(Room fromRoom, Room toRoom, String desc)
	{
		this.fromRoom = fromRoom;
		this.toRoom = toRoom;
		this.fromToDescription = desc;
		this.bidirectional = false;
	}
	
	/**
	 * Use this constructor if you want to represent a two-way connection between rooms
	 * @param fromRoom
	 * @param toRoom
	 * @param fromToDesc
	 * @param toFromDesc
	 */
	public RoomConnector(Room fromRoom, Room toRoom, String fromToDesc, String toFromDesc)
	{
		this.fromRoom = fromRoom;
		this.toRoom = toRoom;
		this.fromToDescription = fromToDesc;
		this.toFromDescription = toFromDesc;
		this.bidirectional= true;
	}

	public Room getFromRoom() {
		return fromRoom;
	}

	public Room getToRoom() {
		return toRoom;
	}

	public String getFromToDescription() {
		return fromToDescription;
	}

	public String getToFromDescription() {
		return toFromDescription;
	}

	public boolean isBidirectional() {
		return bidirectional;
	}
}
