package adventure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GwkGameWorldTest {

    GameWorld world = TreeGameWorld.getInstance();

    @Before
    public void setUp() throws Exception {

	world.clear();

	Room kitchen = new Room("kitchen");
	kitchen.setDescription("A small kitchen.");
	kitchen.addProperty("dark");
	world.addRoom(kitchen);

	Room garage = new Room("garage");
	garage.setDescription("A narrow garage.");
	world.addRoom(garage);

	Actor player = new Actor("bob");
	player.setDescription("Your name is Bob, and you look very handsome today.");
	player.setParent("kitchen");
	player.addProperty("proper");
	player.addProperty("male");
	player.addProperty("animate");
	world.addThing(player);
	world.setPlayer(player);

	Thing cardboardbox = new Thing("cardboard box");
	cardboardbox.setDescription("A small cardboard box");
	cardboardbox.setParent("kitchen");
	cardboardbox.addAdjective("small");
	cardboardbox.addProperty("container");
	world.addThing(cardboardbox);

	Thing woodenbox = new Thing("wooden box");
	woodenbox.setDescription("A large wooden box");
	woodenbox.setParent("garage");
	woodenbox.addAdjective("large");
	woodenbox.addProperty("container");
	world.addThing(woodenbox);

	Thing beachball = new Thing("beach ball");
	beachball.setDescription("A beach ball");
	beachball.setParent("wooden_box");
	world.addThing(beachball);

	Thing soccerball = new Thing("soccer ball");
	soccerball.setDescription("A soccer ball");
	soccerball.setParent("wooden_box");
	world.addThing(soccerball);
    }

    @Test
    public void testInit() {
	Actor player = world.getPlayer();
	Room currentRoom = world.getRoom(player.getId());
	Room soccerBallRoom = world.getRoom("soccer_ball");
	assertEquals("kitchen", currentRoom.getId());
	assertFalse(world.isInInventory("soccer_ball"));
	assertEquals("garage", soccerBallRoom.getId());
	assertTrue(world.containsProperty("wooden_box", "container"));
    }

    @Test
    public void testIsInScope() {
	assertTrue(world.isInScope("cardboard_box"));
	assertFalse(world.isInScope("wooden_box"));
    }

    @Test
    public void testMove() {
	world.move("soccer_ball", "bob");
	Room newSoccerBallRoom = world.getRoom("soccer_ball");
	assertTrue(world.isInInventory("soccer_ball"));
	assertEquals("kitchen", newSoccerBallRoom.getId());
    }

    @Test
    public void testAddRemoveProperty() {
	world.addProperty("wooden_box", "open");
	assertTrue(world.containsProperty("wooden_box", "open"));

	world.removeProperty("wooden_box", "open");
	assertFalse(world.containsProperty("wooden_box", "open"));
    }

}
