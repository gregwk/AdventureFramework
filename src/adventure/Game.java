package adventure;

public class Game {
  
  private Dictionary dictionary = GameDictionary.getInstance();
  private GameWorld world = TreeGameWorld.getInstance();

  public Game() {}

  public void initialize() {

    Room kitchen = new Room("kitchen", "A normal kitchen.");
    Room garage = new Room("musty garage", "A dank, musty garage.");

    garage.addExit("east", kitchen);
    kitchen.addExit("west", garage);

    Thing key = new Thing("rusty iron key", "The key looks so old and rusty that you doubt it would open anything.");
    key.setParent("musty_garage");

    world.addGameObject(kitchen);
    dictionary.addGameObject(kitchen);
    
    world.addGameObject(garage);
    dictionary.addGameObject(kitchen);
    
    world.addGameObject(key);
    dictionary.addGameObject(key);

    Actor bob = new Actor("bob", "You look the same as always.");
    bob.setParent("kitchen");
    
    world.addGameObject(bob);
    world.setPlayer(bob);
    dictionary.addGameObject(bob);
    
  }
}
