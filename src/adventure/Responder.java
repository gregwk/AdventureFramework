package adventure;

/**
 * @author SweetuPatel
 */
public interface Responder {
  
  /**
   * Returns the Response based on the specified command. Potentially updates the GameWorld.
   * 
   * @param command object
   * @return appropriate response for the specified command
   */
  public Response getResponse(Command command);

}
