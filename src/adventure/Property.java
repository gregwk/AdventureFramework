/**
 *
 * @author Yeze
 * 
 */

package adventure;

import java.util.HashSet;

public class Property {
  
  private HashSet<String> properties = new HashSet<String>();

  public HashSet<String> getProperties() {
    return properties;
  }

  public void addProperty(String property) {
    this.properties.add(property);
  }

  public boolean containProperty(String prop) {
    return this.properties.contains(prop);
  }
  
}
