public class Item {
    //Item unique variables
    private final String name;
    private final String description;

    //Item constructor
    public Item(String name, String description, Room room) {
        this.name = name;
        this.description = description;

        room.addItem(this);
    }

    //Accessor methods
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
