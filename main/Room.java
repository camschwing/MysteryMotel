package main;

import java.util.ArrayList;

public class Room {
    private static final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Item> items;
    private final String name;
    private final int roomNum;
    private final int roomID;
    private final int x;
    private final int y;
    private boolean searched = false;
    
    public Room(int roomNum, String name, Item item, int x, int y) {
    	items = new ArrayList<>();
        this.name = name;
        this.roomNum = roomNum;
        this.x = x;
        this.y = y;
        roomID = Commands.id(x, y);
        
        items.add(item);
        rooms.add(this);
    }
    public Room(int roomNum, String name, ArrayList<Item> item, int x, int y) {
        items = new ArrayList<>();
        this.name = name;
        this.roomNum = roomNum;
        this.x = x;
        this.y = y;
        roomID = Commands.id(x, y);

        items.addAll(item);
        rooms.add(this);
    }
    
    public void setSearched() {
        this.searched = true;
    }
    public void removeItem(Item a) {
        items.removeIf(i -> i == a);
    }

    public String getName() {
        return name;
    }
    public int getNum() {
    	return roomNum;
    }
    public int getID() {
    	return roomID;
    }
    public int getX() {
    	return x;
    }
    public int getY() {
    	return y;
    }
    public boolean getSearched() { return searched; }
    public ArrayList<Item> getItems() {
        return items;
    }
    public static ArrayList<Room> getRooms() {
    	return rooms;
    }
    public static Room getRoom(int num) {
        for (Room r : rooms) {
            if(r.getNum() == num) {
                return r;
            }
        }
        return null;
    }
}
