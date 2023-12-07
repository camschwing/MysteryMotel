import java.util.ArrayList;

public class Room extends MysteryMotel {
    public String description;
    public ArrayList<Item> items;

    public Room(String description, ArrayList<Item> items) {
        this.description = description;
        this.items = items;
    }
    public String getDescription() {
        return description;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}