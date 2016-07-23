package adventure;

/**
 * @author SweetuPatel
 */
public class ResponseManager {

  private GameWorld world = TreeGameWorld.getInstance();

  // singleton patter
  
  private static final ResponseManager INSTANCE = new ResponseManager();

  private ResponseManager() {
    //
  }

  public static ResponseManager getInstance() {
    return INSTANCE;
  }  
  
  Response getResponse(Command command) {
    
//    Response response = new Response();
//
//		Room room = world.getRoom(world.getPlayer().getId());
//
//		response = room.getResponder()
//		
//		//check that room is in scope 
//		Boolean scopeOfObject1 = room.inScope(command.obj1);
//
//		//command does not contain error then check scope of action, obj1 and obj2
//		if(!command.errorMessage)
//		{
//			if (scopeOfObject1)
//			{
//				//get obj1 name to display message 
//				String nameofthObject1 = gameObject.getName(command.obj1);
//				response = "Picked up the" + nameofthObject ;
//
//				//if obj1 is inscope then check scope for obj2
//				if(Boolean scopeOfObject1 = room.inScope(command.obj2))
//				{
//					String nameofthObject2 = gameObject.getName(command.obj2);
//					response = "Picked up the" + nameofthObject ;
//
//					//response to the action
//					response = actionResponse(command);
//				}
//
//				else
//				{
//					String nameofthObject2 = gameObject.getName(command.obj2);
//					response = "I don't see the" + nameofthObject2 + ; 
//				}
//			}
//
//			else
//			{
//				String nameofthObject1 = gameObject.getName(command.obj1);
//				response = "I don't see the" + nameofthObject1 +; 
//			}
//		}
//
//		else
//		{
//			//All possible error message interpretation
//			response = errorResponse(Command command);
//		}
//    return response; 
//	}
//
//  Response actionResponse(Command command) {
//    // Case statement to response according to passed action ;
//    return response;
//  }
//
//  public Response errorResponse(Command command) {
//    // Case statement to response according to passed error message
//    return response;
//  }

    return new Response();
  }
}

