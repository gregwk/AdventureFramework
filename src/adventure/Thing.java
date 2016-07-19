package adventure;

public class Thing extends GameObject
{
	private String takableKey = "isTakable";
	private String staticKey = "isStatic";
	
	public Thing(String name, String description) 
	{
		super(name, description);
	}

	public Thing(String name) 
	{
		super(name);
	}
	
	public void setIsTakable(boolean isTakable)
	{
		if (isTakable)
			this.addProperty(takableKey);
		else
			this.removeProp(takableKey);
	}
	
	public boolean isTakable()
	{
		return this.containsProperty(takableKey);
	}
	
	public void setIsStatic(boolean isStatic)
	{
		if (isStatic)
			this.addProperty(staticKey);
		else
			this.removeProp(staticKey);
	}
	
	public boolean isStatic()
	{
		return this.containsProperty(staticKey);
	}


}
