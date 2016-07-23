package adventure;

/**
 * This class represents a Door in the game framework.
 * 
 * @author Alex_Lappy_486
 *
 */
public class Door extends GameObject {
  private String parent;

  public Door(String name) {
    super(name);
  }

  public Door(String name, String desc) {
    super(name, desc);
  }

}
