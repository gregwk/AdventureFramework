package adventure;

public class AdventureMain {

	public static void main(String[] args) {
	 
		GameEngine engine = DefaultGameEngine.getInstance();
		engine.initializeGame();
		
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new AdventureGUI(engine).setVisible(true);
	            }
	        });		
		
//		engine.run();
		
		 
	}

}
