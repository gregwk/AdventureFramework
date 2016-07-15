package adventure;

public class GameAction 
{
	/**
	 * A unique label identifying the action
	 */
	private String id;
	
	/**
	 * A name for the action (the verb name)
	 */
	private String actionName;
	
	/**
	 * A description of the action
	 */
	private String description;

	public GameAction(String id, String actionName, String description) {
		super();
		this.id = id;
		this.actionName = actionName;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
