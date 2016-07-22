package adventure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameObject {

  /**
   * A unique ID representing the object
   */
  private String id;

  /**
   * The name displayed to the user when referring to this object
   */
  private String displayName;

  /**
   * A description of the object
   */
  private String description;

  /**
   * A list of nouns contained in this object
   */
  private List<String> nouns;

  /**
   * A list of adjectives contained in this object
   */
  private List<String> adjectives;

  /**
   * A list of properties to be added, removed, or queried against
   */
  private Set<String> properties;

  public GameObject(String name) {
    String id = this.generateIdFromName(name);
    String desc = "It's a " + name;

    init(id, desc, name);

  }

  public GameObject(String name, String description) {
    String id = this.generateIdFromName(name);
    init(id, description, name);
  }

  private void init(String id, String description, String displayName) {
    this.id = id;
    this.description = description;
    this.nouns = new ArrayList<String>();
    this.adjectives = new ArrayList<String>();
    this.displayName = displayName;
    this.properties = new HashSet<String>();

    this.addNounsAndAdjectives(displayName);

  }

  private String generateIdFromName(String name) {
    return name.replaceAll("\\s+", "_");
  }

  /**
   * This method adds all but the last token in the display name to the list of adjectives, while
   * adding the last token to the list of nouns.
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

  public String getId() {
    return id;
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

  public List<String> getNouns() {
    return new ArrayList<>(this.nouns);
  }

  public void addNouns(String... nouns) {
    for (String s : nouns) {
      this.nouns.add(s);
    }
  }

  public List<String> getAdjectives() {
    return new ArrayList<>(this.adjectives);
  }

  public void addAdjectives(String... adjectives) {
    for (String s : adjectives) {
      this.adjectives.add(s);
    }
  }

  public void addProperty(String... props) {
    for (String prop : props) {
      this.properties.add(prop);
    }
  }

  public void removeProp(String... props) {
    for (String prop : props) {
      this.properties.remove(prop);
    }
  }

  public boolean containsProperty(String prop) {
    return this.containsProperty(prop);
  }

}
