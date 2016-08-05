package adventure;

public final class DefaultGameEngine implements GameEngine {
	
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public void run() {
        new AdventureGUI(engine).setVisible(true);
    }
	
}
