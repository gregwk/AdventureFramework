package adventure;

import java.util.HashMap;
import java.util.Map;

public class Room extends GameObject {

    private Map<String, Door> doors;
    private Map<String, Room> exits;

    public Room(String name, String description) {
        super(name, description);
        init();
    }

    public Room(String name) {
        super(name);
        init();
    }

    private void init() {
        this.doors = new HashMap<String, Door>();
        this.exits = new HashMap<String, Room>();
    }

    public void addExit(String direction, Room room) {
        this.exits.put(direction, room);
    }

    public Room removeExit(String direction) {
        return this.exits.remove(direction);
    }

    public boolean containsExit(String direction){
        return this.exits.containsKey(direction);
    }

    public Room getExit(String direction){
        return this.exits.get(direction);
    }

    public void addDoor(String direction, Door door) {
        this.doors.put(direction, door);
    }

    public Door removeDoor(String direction) {
        return this.doors.remove(direction);
    }

    public boolean containsDoor(String direction) {
        return this.doors.containsKey(direction);
    }

    public Door getDoor(String doorID)
    {
        return this.doors.get(doorID);
    }

}