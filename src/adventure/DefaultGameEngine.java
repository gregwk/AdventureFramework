package adventure;

public final class DefaultGameEngine implements GameEngine {
	
	private static final Parser parser = GameParser.getInstance();
	private static final GameWorld world = TreeGameWorld.getInstance();
    
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
		return ((TreeGameWorld)world).printGameWorld();
	    }
	    
	    Command command = parser.parse(input);
	    if (command.errorMessage != null && !"".equals(command.errorMessage)) {

		// System.out.println( ((TreeGameWorld)world).printGameWorld() );
		
		StringBuilder sb = new StringBuilder();
		Room currentRoom = world.getRoom(world.getPlayer().getId());
		sb.append("<h1>" + currentRoom.getName() + "</h1>");
		sb.append("<p>" + currentRoom.getDescription() + "</p>");
		sb.append("<p>" + command.errorMessage + "</p>");
		return sb.toString();
	    } else {
		return "command!";
	    }
	}
	
	public void run() {
        new AdventureGUI(engine).setVisible(true);
    }
	
}
