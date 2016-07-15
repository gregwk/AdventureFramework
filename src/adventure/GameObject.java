package adventure;

public class GameObject 
{
	private String id;
	private String description;
	private String displayName;
	private String [] nouns;
	private String[] adjectives;
	
	public GameObject(String id, String description, String displayName, String[] nouns, String[] adjectives) {
		super();
		this.id = id;
		this.description = description;
		this.displayName = displayName;
		this.nouns = nouns;
		this.adjectives = adjectives;
	}

	public GameObject(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String[] getNouns() {
		return nouns;
	}

	public void setNouns(String[] nouns) {
		this.nouns = nouns;
	}
	
	public void setNouns(String nouns) {
		String [] n = {nouns};
		this.nouns = n;
	}

	public String[] getAdjectives() {
		return adjectives;
	}

	public void setAdjectives(String[] adjectives) {
		this.adjectives = adjectives;
	}
	
	
}
