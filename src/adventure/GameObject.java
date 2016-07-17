
/**
 * GameWorld Object general attributs description class;
 * @author Yeze
 * 
 */

package adventure;

public class GameObject {
	private String id;
	private String description;
	private Property property;
	private Responder responder;
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getDescription() {
		return description;
	}
	public final void setDescription(String description) {
		this.description = description;
	}
	public final Property getProperty() {
		return property;
	}
	public final void updateProperty(Property property) {
		this.property = property;
	}
	public final void addProperty(String property){
		this.property.addProperty(property);
	}
	public final Responder getResponder() {
		return responder;
	}
	public final void setResponder(Responder responder) {
		this.responder = responder;
	}
}
