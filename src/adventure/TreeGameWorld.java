/**
 * TreeGameWorld implements GameWorld class;
 * 
 * @author Yeze
 * 
 */

package adventure;

import adventure.util.tree.ListTree;

public class TreeGameWorld implements GameWorld {

	// instance variables

	private ListTree<String, GameObject> treeGameWorld;
	private Actor player = null;

	// singleton pattern

	private static final GameWorld INSTANCE = new TreeGameWorld();

	private TreeGameWorld() {
	}

	public static GameWorld getInstance() {
		return INSTANCE;
	}

	// public methods

	@Override
	public boolean isInScope(String objectId) {
		return this.treeGameWorld.contains(objectId);
	}

	@Override
	public void move(String objectId, String parent) {
		this.treeGameWorld.moveSubTree(objectId, parent);
	}

	@Override
	public boolean isInInventory(String objectId) {
		return this.treeGameWorld.parent(objectId).equals(player.getId());
	}

	@Override
	public Actor getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public void setPlayer(Actor actor) {
		// TODO Auto-generated method stub
		if (player != null)
			return;
		player = actor;
		addThing(player);
	}

	@Override
	public Room getRoom(String objectId) {
		// TODO Auto-generated method stub
		return (Room) this.treeGameWorld.get(objectId);
	}

	@Override
	public GameObject getGameObject(String objectId) {
		// TODO Auto-generated method stub
		return this.treeGameWorld.get(objectId);
	}

	@Override
	public void addThing(Thing thing) {
		// TODO Auto-generated method stub
		treeGameWorld.addChild(thing.getParent(), thing.getId(), thing);
	}

	@Override
	public void addRoom(Room room) {
		// TODO Auto-generated method stub
		treeGameWorld.addRoot(room.getId(), room);
	}

	@Override
	public void addProperty(String objectId, String prop) {
		// TODO Auto-generated method stub
		treeGameWorld.get(objectId).addProperty(prop);
	}

	@Override
	public boolean containProperty(String objectId, String prop) {
		// TODO Auto-generated method stub
		return treeGameWorld.get(objectId).containsProperty(prop);
	}

	@Override
	public void removeProperty(String objectId, String prop) {
		// TODO Auto-generated method stub
		treeGameWorld.get(objectId).removeProperty(prop);
	}

}
