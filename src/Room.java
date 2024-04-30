import java.util.ArrayList;

public class Room {
    //Room object unique variables
    static private int roomNums;
    private final int roomNum;
    private final String name;
    private final Position position;
    private boolean searched;
    private boolean entered;
    private boolean locked;
    private final ArrayList<Item> items = new ArrayList<>();

    //List of Room objects
    private static final ArrayList<Room> rooms = new ArrayList<>();

    //Room constructor
    public Room(String name, Position position) {
        roomNums++;

        this.roomNum = roomNums;
        this.name = name;
        this.position = position;
        this.searched = false;
        this.locked = false;


        rooms.add(this);
    }

    //Accessor Methods
    public int getRoomNum() {
        return roomNum;
    }
    public String getName() {
        return name;
    }
    public boolean getEntered() {
        return entered;
    }
    public Position getPosition() {
        return position;
    }
    public boolean getSearched() {
        return searched;
    }
    public boolean getLocked() {
        return locked;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public String getStringItems() {
        //Create string of Item objects StringBuilder
        StringBuilder stringItems = new StringBuilder();
        //Loop through Item objects
        for(Item i : items) {
            // Append to StringBuilder
            stringItems.append(i.getName()).append(" (").append(i.getDescription()).append(")").append(", ");
        }
        //Remove last two spaces to get rid of ", "
        if (stringItems.length() > 2) {
            stringItems.replace(stringItems.length() - 2, stringItems.length(), "");
        }
        return stringItems.toString();
    }
    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    //Mutator Methods
    public void setEntered() {
        this.entered = true;
    }
    public void setEnteredVal(boolean bool) {
        this.entered = bool;
    }
    public void setSearched() {
        this.searched = true;
    }
    public void setLocked(boolean lock) {
        this.locked = lock;
    }
    public void addItem(Item item) {
        items.add(item);
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
}


