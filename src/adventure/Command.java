/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

/**
 *
 * @author Kshitij
 */
public class Command {
    
    /*
    * Identifier for GameAction
    */
    public String action;
    
    /*
    * Identifier for First GameObject
    */
    public String object1;
    
    /*
    * Identifier for Second GameObject
    */
    public String object2;
    
    /*
    * Error code to be returned by Parser
    */
    public String errorCode;
    
    /*
    * Error message or any other message
    */
    public String errorMessage;
    
}
