package adventure;

/**
 * @author SweetuPatel
 *
 *  updated By
 * @author adeelahuma
 */
public class Response {
  
  private String kind;

    private String message;


    public Response(String kind, String message) {
        this.kind = kind;
        this.message = message;
    }

    public Response() {
    }
}
