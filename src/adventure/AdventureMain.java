package adventure;

public class AdventureMain {

	public static void main(String[] args) {
	 
		GameEngine engine = DefaultGameEngine.getInstance();
		engine.initializeGame();
		engine.run();
		
		 
	}

}
