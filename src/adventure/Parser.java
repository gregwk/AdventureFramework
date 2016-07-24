/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package adventure;

public interface Parser {

  /**
   * Returns Command object instance based on the userInput.
   * 
   * @param userInput the string text to be parsed.
   * 
   * @throw NullException if Dictionary instance is null
   */
  public Command parse(String userInput);
}
