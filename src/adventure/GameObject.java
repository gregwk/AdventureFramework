package adventure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameObject {

  private String id;
  private String displayName;
  private String description;
  private List<String> adjectives;
  private List<String> nouns;
  private Set<String> properties;
  private Responder responder;

  public GameObject(String name) {
    String id = this.generateIdFromName(name);
    String desc = "";

    init(id, desc, name);

  }

  /**
   * Constructs a game object with the specified display name and the specified description.
   * Creates an object identifier that is the display name with underscores instead of spaces.
   * If the display name consists of only one word, it becomes a noun and there are no adjectives.
   * If the display name consists of more than one word, the last word becomes a noun and all
   * others become adjectives.
   * 
   * @param name
   * @param description
   */
  public GameObject(String name, String description) {
    String id = this.generateIdFromName(name);
    init(id, description, name);
    responder = ( command -> new Response() );
  }

  private void init(String id, String description, String displayName) {
    this.id = id;
    this.description = description;
    this.nouns = new ArrayList<>();
    this.adjectives = new ArrayList<>();
    this.displayName = displayName;
    this.properties = new HashSet<>();

    this.addNounsAndAdjectives(displayName);

  }

  private String generateIdFromName(String name) {
    return name.replaceAll("\\s+", "_");
  }

  /**
   * This method adds all but the last word in the display name to the list of adjectives, while
   * adding the last word to the list of nouns.
   * 
   * @param name a name of an object, where words are separated by whitespace
   */
  private void addNounsAndAdjectives(String name) {
    String[] tokens = id.split("_");
    for (int i = 0; i < tokens.length; i++) {
      if (i < (tokens.length - 1))
        this.adjectives.add(tokens[i]);
      else
        this.nouns.add(tokens[i]);
    }
  }

  /**
   * Returns the unique identifier for this game object.
   * 
   * @return
   */
  public String getId() {
    return id;
  }

  /**
   * Returns the description of this game object.
   * 
   * @return
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of this game object.
   * 
   * @param description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Returns the display name of this game object.
   * 
   * @return
   */
  public String getName() {
    return displayName;
  }

  /**
   * Sets the display name of this game object.
   * 
   * @param displayName
   */
  public void setName(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Adds a noun to this game object.
   * 
   * @param noun
   */
  public void addNoun(String noun) {
    nouns.add(noun);
  }

  /**
   * Returns true if this game object contains the specified noun.
   * 
   * @param noun
   * @return
   */
  public boolean containsNoun(String noun) {
    return nouns.contains(noun);
  }

  /**
   * Adds an adjective to this game object.
   * 
   * @param adjective
   */
  public void addAdjective(String adjective) {
    adjectives.add(adjective);
  }

  /**
   * Returns true if this game object contains the specified adjective.
   * 
   * @param adjective
   * @return
   */
  public boolean containsAdjective(String adjective) {
    return adjectives.contains(adjective);
  }

  /**
   * Adds the specified property to the game object.
   * @param property
   */
  public void addProperty(String...props) 
  {
	  for (String prop : props)
	  {
		  this.properties.add(prop);
	  }
  }
  
  /**
   * Adds the specified property to the game object.
   * @param property
   */
  public void addProperty(GameProperty...gameProperties) {
    for (GameProperty prop : gameProperties)
    {
    	this.properties.add(prop.getPropId());
    }
  }

  /**
   * Removes the specified property from the game object.
   * @param property
   */
  public void removeProperty(String property) {
    properties.remove(property);
  }

  /**
   * Returns true if the game object contains the specified property.
   * @param property
   * @return
   */
  public boolean containsProperty(String property) {
    return properties.contains(property);
  }
  
  public void setResponder(Responder responder) {
    this.responder = responder;
  }
  
  public Responder getResponder() {
    return responder;
  }

}
