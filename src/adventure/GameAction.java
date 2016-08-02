package adventure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adeelahuma
 */

public class GameAction {
  
  private String id;
  private List<String> patterns;
  private Responder responder;

  public GameAction(String id) {
    if (id == null || id.trim().isEmpty()) {
      throw new IllegalArgumentException("Can not create GameAction with null or empty id");
    }

    this.id = id;
    patterns = new ArrayList<String>();
  }

  public String getId() {
    return id;
  }

  public void addPattern(String pattern) {
    if (pattern == null || pattern.trim().isEmpty()) {
      throw new IllegalArgumentException("Can not add null or empty pattern");
    }

    patterns.add(pattern);
  }

  public List<String> getPatterns() {
    return patterns;
  }


    public Responder getResponder() {
        return responder;
    }

    public void setResponder(Responder responder) {
        this.responder = responder;
    }
}
