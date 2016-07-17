
package adventure;

import tree.ListTree;

public class TreeGameWorld implements GameWorld {
	private ListTree<String, GameObject> treeGameWorld;

	TreeGameWorld(ListTree<String, GameObject> treeGameWorld){
		this.setTreeGameWorld(treeGameWorld);
	}
	
	@Override
	public boolean isInScope(GameObject obj) {
		return this.treeGameWorld.contains(obj.getId());
	}

	@Override
	public void move(GameObject obj1, GameObject obj2) {
		this.treeGameWorld.moveSubTree(obj1.getId(), obj2.getId());
	}

	@Override
	public void updateProperty(GameObject obj, Property prop) {
		this.treeGameWorld.get(obj.getId()).updateProperty(prop);
	}

	@Override
	public boolean isInInventory(GameObject obj) {
		return this.treeGameWorld.parent(obj.getId()).equals("player");
	}

	public boolean containProperty(GameObject obj, String prop) {
		return this.treeGameWorld.get(obj.getId()).getProperty().containProperty(prop);
	}
	/**
	 * @return the treeGameWorld
	 */
	public ListTree<String, GameObject> getTreeGameWorld() {
		return treeGameWorld;
	}

	/**
	 * @param treeGameWorld2 the treeGameWorld to set
	 */
	public void setTreeGameWorld(ListTree<String, GameObject> treeGameWorld2) {
		this.treeGameWorld = treeGameWorld2;
	}

}
