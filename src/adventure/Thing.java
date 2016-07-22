package adventure;

public class Thing extends GameObject {
  
  private String parent;
  
  public Thing(String name, String description) {
    super(name, description);
    parent = "";
  }

  public Thing(String name) {
    super(name);
  }

  /**
   * @return the parent
   */
  public String getParent() {
    return parent;
  }

  /**
   * @param parent the parent to set
   */
  public void setParent(String parent) {
    this.parent = parent;
  }
  
}
