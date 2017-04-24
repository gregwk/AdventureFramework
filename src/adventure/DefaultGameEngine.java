package adventure;

import java.util.ArrayList;
import java.util.List;

import adventure.util.tree.GameUtils;

public final class DefaultGameEngine implements GameEngine {
	
	private static final Parser parser = GameParser.getInstance();
	private static final GameWorld world = TreeGameWorld.getInstance();
	private static final ResponseManager responseManager = ResponseManager.getInstance();
    
	private static final GameEngine engine = new DefaultGameEngine();
	
	private DefaultGameEngine() {
	} 

	public static GameEngine getInstance()
	{
		return engine;
	}
	
	@Override
	public void initializeGame() {
		Game game = new Game();
		game.initialize();
		DefaultAction defaultAction = new DefaultAction();
		defaultAction.initialize();
		
	}

	@Override
	public String processInput(String input) {
	    
	    if (input.equals("tree")) {
		
		return getRoomDescription() + "<p>" + ((TreeGameWorld)world).printGameWorld() + "</p>";
	    }
	    
	    Command command = parser.parse(input);
	    if (command.errorMessage != null && !"".equals(command.errorMessage)) {

		// System.out.println( ((TreeGameWorld)world).printGameWorld() );
		
		return getRoomDescription() + "<p>" + command.errorMessage + "</p>";
	    } else {
		
		Response response = responseManager.generateResponse(command);
		
		return getRoomDescription() + "<p>" + response.message + "</p>";
	    }
	}
	
    private String getRoomDescription() {

	StringBuilder sb = new StringBuilder();
	Room currentRoom = world.getRoom(world.getPlayer().getId());
	sb.append("<h1>" + currentRoom.getName() + "</h1>");
	sb.append("<p>" + currentRoom.getDescription() + "</p>");
	List<String> contents = GameUtils.getAllObjectsContainedInObject(world, currentRoom.getId());
	if (!contents.isEmpty()) {
	    sb.append("<p>You see: ");
	    for (String objectId : contents) {
		sb.append(objectId);
		sb.append(" ");
	    }
	    sb.append("</p>");
	    
	 sb.append("Exits are "+currentRoom.getAllExitIds());
	}
	return sb.toString();
    }
    
    
	
	public void run() {
        new AdventureGUI(engine).setVisible(true);
    }
	
}
