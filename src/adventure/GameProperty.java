package adventure;

public enum GameProperty
{
    /**
     * The object has been removed and should not appear in the game upon reload
     */
    ABSENT("absent"),

    /**
     * The object is considered to be "alive"
     */
    ANIMATE("animate"),

    /**
     * The object can be worn by Actors
     */
    CLOTHING("clothing"),

    /**
     * The object is present in the scope but not in view of the player, i.e. absent from descriptions
     */
    CONCEALED("concealed"),

    /**
     * Contains objects
     */
    CONTAINER("container"),

    /**
     * The object can be opened or closed, and it blocks the passageway to another room
     */
    DOOR("door"),

    /**
     * The object can be eaten by an Actor
     */
    EDIBLE("edible"),

    /**
     * The object can be entered by the player
     */
    ENTERABLE("enterable"),

    /**
     * The object is female, and is referred to by female pronouns
     */
    FEMALE("female"),

    GENERAL("general"),

    /**
     * The object emits light
     */
    LIGHT("light"),

    /**
     * The object is lockable
     */
    LOCKABLE("lockable"),

    /**
     * The object is currently locked
     */
    LOCKED("locked"),

    /**
     * The object is male, and is referred to by male pronouns
     */
    MALE("male"),

    /**
     * The object is (or has been) held by the player
     */
    MOVED("moved"),

    /**
     * The object is neither male or female
     */
    NEUTER("neuter"),

    /**
     * The object has been switched into its "on" position (not necessarily electronic)
     */
    ON("on"),

    /**
     * The object is open (container, door, etc)
     */
    OPEN("open"),

    /**
     * The object can be opened
     */
    OPENABLE("openable"),

    /**
     * The object should be referred to by plural nouns (they)
     */
    PLURAL("plural"),

    /**
     * The object should be referred to as a proper noun (ex: Alex)
     */
    PROPER("proper"),

    /**
     * The object cannot be interacted with (aside from being examined)
     */
    SCENERY("scenery"),

    /**
     * The player gets points for picking this object up
     */
    SCORED("scored"),

    /**
     * The object remains in place if the player tries to move it
     */
    STATIC("static"),

    /**
     * "Things can be put on top off it"
     */
    SUPPORTER("supporter"),

    /**
     * An object that can be switched ON
     */
    SWITCHABLE("switchable"),

    /**
     * The object can be talked to
     */
    TALKABLE("talkable"),

    /**
     * The object can be put into the player's inventory
     */
    TAKABLE("takable"),

    /**
     * The object's contents are visible and in scope
     */
    TRANSPARENT("transparent"),

    /**
     * This object has been visited by the player (a room should be the only object to have this property)
     */
    VISITED("visited"),

    /**
     * This object is being worn by the player
     */
    WORN("worn");

    private String propertyId;

    private GameProperty(String propID)
    {
        this.propertyId = propID;
    }

    public String getPropId()
    {
        return this.propertyId;
    }
}