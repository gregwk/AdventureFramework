package adventure;
/**
 * @author SweetuPatel
 */
public class ResponseManager {
 
	 Response getResponse(Command command) {
		 Response response = null;
		// find out what room the player is in. Alex needs to add playerLocation method in gameWorld
    	 Room room = gameWorld.playerLocation();
    	 //get response from Room 
    	 Boolean scopeOfObject1 = room.inScope(command.obj1);
         if(!command.errorMessage)
         {
    	 if (scopeOfObject1)
    	 {
    		 String nameofthObject1 = gameObject.getName(command.obj1);
    		 response = "Picked up the" + nameofthObject ;
    		 if(Boolean scopeOfObject1 = room.inScope(command.obj2))
    		 {
    			 String nameofthObject2 = gameObject.getName(command.obj2);
        		 response = "Picked up the" + nameofthObject ;
                 //response to the action
        		 response = actionResponse(command);
    		 }
    		 else
        	 {
        		 String nameofthObject2 = gameObject.getName(command.obj2);
        		 response = "I don't see the" + nameofthObject ; 
        	 }
    	 }
    	 else
    	 {
    		 String nameofthObject = gameObject.getName(command.obj1);
    		 response = "I don't see the" + nameofthObject ; 
    	 }
    	 else
    	 {
    		 //All possible error message interpretation
    		  response = errorResponse(Command command);
    	 }
    	 }
         
         
         }
    	
	 
	 if(!response.getKind.equals("none") ) return response;	  
         // get obj1
    	 
    	 
    	 
    	 
    	 response = obj1.getResponse(command);
    	 if(!response.getKind.equals("none") ) return response;	 
        
    	 //how to get default response??
    	 
    	 //(gameworld knows this)
         // get the response from the room's responder:
         // response = room.getResponse(command);
         // if the room has no response, get the response from the primary object (object1)
         // response = obj1.getResponse(command);
         // if the obj1 has no response, get the default response from the action
         // response = action.getResponse(command);
    }

     Response actionResponse(Command command)
     {
    	 // Case statement to response according to passed action 
     }
     Response errorResponse(Command command)
     {
    	 // Case statement to response according to passed error message 
     }
}
    
    