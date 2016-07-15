package adventure;

public class Thing extends GameObject
{
	protected boolean isTakable;
	protected String condition;
	protected boolean isStatic;
	
	public Thing(String id, String description, String displayName, String[] nouns, String[] adjectives, boolean isTakable,
			String condition, boolean isStatic) 
	{	
		super(id, description, displayName, nouns, adjectives);
		this.isStatic = isStatic;
		this.condition = condition;
		this.isStatic = isStatic;
	}
	
	public Thing(String id, String description, String displayName, String[] nouns, String[] adjectives, boolean isTakable,
			boolean isStatic) 
	{	
		super(id, description, displayName, nouns, adjectives);
		this.isStatic = isStatic;
		this.isStatic = isStatic;
	}
	
	public Thing(String id)
	{
		super(id);
	}

	public boolean isTakable() {
		return isTakable;
	}

	public void setTakable(boolean isTakable) {
		this.isTakable = isTakable;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
}
