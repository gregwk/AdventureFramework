package adventure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameObject {

  /**
   * A unique string representing the object
   */
  private String id;
  
  /**
   * The string to display when referenced by the Adventure Game GUI
   */
  private String displayName;
  
  /**
   * A description of the object. To be displayed in the GUI when appropriate
   */
  private String description;
  
  /**
   * A collection of adjectives describing the object
   */
  private List<String> adjectives;
  
  /**
   * A collection of nouns associated with this object
   */
  private List<String> nouns;
  
  /**
   * A collection of properties currently associated with the object (ex: "takable")
   */
  private Set<String> properties;
  
  /**
   * A responder associated with this game object.
   */
  private Responder responder;

  /**
   * This constructor creates a game object using the given name. It initializes the
   * description for this object to an empty string.
   * @param name
   */
  public GameObject(String name) {
    String id = this.generateIdFromName(name);
    String desc = "";
    init(id, desc, name);
    responder = ( command -> new Response() );
  }

  /**
   * This constructor creates a Game object from a name and a description
   * @param name the name of the object
   * @param description a short description of the object
   */
  public GameObject(String name, String description) {
    String id = this.generateIdFromName(name);
    init(id, description, name);
    responder = ( command -> new Response() );
  }

  /**
   * This method initializes all of the fields for this game object.
   * @param id the unique id for this object
   * @param description the description for this object
   * @param displayName the display name of the object
   */
  private void init(String id, String description, String displayName) {
    this.id = id;
    this.description = description;
    this.nouns = new ArrayList<>();
    this.adjectives = new ArrayList<>();
    this.displayName = displayName;
    this.properties = new HashSet<>();

    this.addNounsAndAdjectives(displayName);

  }

  /**
   * This method generates
   * @param name
   * @return
   */
  private String generateIdFromName(String name) 
  {
	//Do we want to ensure that this is TRULY unique? We could append a UUID to the end of this string...
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
    	this.addProperty(prop.getPropId());
    }
  }

  /**
   * Removes the specified property from the game object.
   * @param property
   */
  public void removeProperty(String property) 
  {
    properties.remove(property);
  }
  
  public void removeProperty(GameProperty property) 
  {
    properties.remove(property.getPropId());
  }

  /**
   * Returns true if the game object contains the specified property.
   * @param property
   * @return
   */
  public boolean containsProperty(String property) {
    return properties.contains(property);
  }
  
  public boolean containsProperty(GameProperty property) {
	    return properties.contains(property.getPropId());
	 }
  
  public void setResponder(Responder responder) {
    this.responder = responder;
  }
  
  public Responder getResponder() {
    return responder;
  }

}
