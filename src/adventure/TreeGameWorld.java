/**
 * TreeGameWorld implements GameWorld class;
 * 
 * @author Yeze
 * 2016/08/05
 * 
 */

package adventure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import adventure.util.tree.ListTree;
import adventure.util.tree.Tree;

public class TreeGameWorld implements GameWorld {

	// instance variables

	private Tree<String, GameObject> gameTree;
	private Dictionary gameDic;
	private Actor player = null;

	// singleton pattern

	private static final GameWorld INSTANCE = new TreeGameWorld();

	private TreeGameWorld() {
		gameTree = new ListTree<>();
		gameDic = GameDictionary.getInstance();
	}

	public static GameWorld getInstance() {
		return INSTANCE;
	}

	// public methods

	@Override
	public boolean isInScope(String objectId) {
		boolean bInScope = this.gameTree.contains(objectId);
		
		// Some properties effect if it is in scope.
		//...
		return bInScope;
	}

	@Override
	public void move(String objectId, String parent) {
		this.gameTree.moveSubTree(objectId, parent);
	}

	@Override
	public boolean isInInventory(String objectId) {
		return this.gameTree.parent(objectId).equals(player.getId());
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
		String roomId = objectId;
		while(!this.gameTree.isRoot(roomId)){
			roomId = this.gameTree.parent(roomId);
		}
		
		return (Room) this.gameTree.get(roomId);
	}

	@Override
	public GameObject getGameObject(String objectId) {
		// TODO Auto-generated method stub
		return this.gameTree.get(objectId);
	}

	@Override
	public void addThing(Thing thing) {
		// TODO Auto-generated method stub
		gameTree.addChild(thing.getParent(), thing.getId(), thing);
		gameDic.addGameObject(thing);
	}

	@Override
	public void addRoom(Room room) {
		// TODO Auto-generated method stub
		gameTree.addRoot(room.getId(), room);
		gameDic.addGameObject(room);
	}

	@Override
	public void addProperty(String objectId, GameProperty... props) {
		// TODO Auto-generated method stub
		gameTree.get(objectId).addProperty(props);
	}

	@Override
	public boolean containsProperty(String objectId, String prop) {
		// TODO Auto-generated method stub
		return gameTree.get(objectId).containsProperty(prop);
	}

	@Override
	public void removeProperty(String objectId, String prop) {
		// TODO Auto-generated method stub
		gameTree.get(objectId).removeProperty(prop);
	}
	
	@Override
	public List<String> getGameObjects(List<String> objectWords){
		// TODO Auto-generated method stub
		return gameDic.getGameObjects(objectWords);
	}
	
	@Override
	public List<String> getActions(String verb){
		// TODO Auto-generated method stub
		return gameDic.getActions(verb);
	}
	
	@Override
	public void addGameAction(GameAction action) {
		// TODO Auto-generated method stub
		gameDic.addGameAction(action);
	}

	@Override
	public void addDirection(Direction direction) {
		// TODO Auto-generated method stub
		gameTree.addRoot(direction.getId(), direction);
		gameDic.addGameObject(direction);
	}

    public void removeGameObject(String objectId){
		gameTree.remove(objectId);
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		gameDic.clear();
		gameTree.clear();
		player = null;
	}

	@Override
	public List<String> getChildren(String objectId) {
		// TODO Auto-generated method stub
		List<GameObject> childrenGameObjects = gameTree.getChildren(objectId);
		List<String> childrenIds = new ArrayList<>();
		Iterator<GameObject> iter = childrenGameObjects.iterator();
		while (iter.hasNext()){
			childrenIds.add(iter.next().getId());
		}
		return childrenIds;
	}

	@Override
	public String getParent(String objectId) {
		// TODO Auto-generated method stub
		return gameTree.parent(objectId);
	}

}
