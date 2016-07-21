package adventure;
/**
 * @author SweetuPatel
 */
public interface Responder {
	 /**
     *  get Response by command
     *  @param command object
     *  @return Response - return command response to the GameWorld
     * */
	    public Response getResponse(Command command);

}
