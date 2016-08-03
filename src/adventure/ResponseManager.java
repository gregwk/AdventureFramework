package adventure;

/**
 * @author SweetuPatel
 */
public class ResponseManager {

  private GameWorld world = TreeGameWorld.getInstance();
  
  private Command command = new Command();
  // singleton patte r
  
  private static final ResponseManager INSTANCE = new ResponseManager();

  private ResponseManager() {
    //
  }

  public static ResponseManager getInstance() {
    return INSTANCE;
  }  
  
  public Response generateResponse(Command command) {

		Response response = new Response();
		
		Room room = world.getRoom(world.getPlayer().getId());
				
		response = processResponse(command, room.getResponder());
		if (!"".equals(response.kind)) return response;
		
		// get the primary object (if any) from the command
		String obj1 = command.object1();
		response = processResponse(command, obj1.getResponder());
		if (!"".equals(response.kind)) return response;

		// if all else fails, get the action's default response
		String action = command.action();
		response = processResponse(command, action.getResponder());
		return response;
		
	}

	private Response processResponse(Command command, Responder responder) {
		Response result = responder.getResponse(Command command);
	}
	
  
}