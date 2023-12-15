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
    
    public Room(int roomNum, String name, Item item, int x, int y) {
    	items = new ArrayList<>();
        this.name = name;
        this.roomNum = roomNum;
        this.x = x;
        this.y = y;
        roomID = id(x, y);
        
        items.add(item);
        rooms.add(this);
    }
    
    public int id(int x, int y) {
		return (3*y) + x;
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
    public ArrayList<Item> getItems() {
        return items;
    }
    public static Room getRoom(int num) {
    	for (Room r : rooms) {
    		if(r.getNum() == num) {
    			return r;
    		}
    	}
    	return null;
    }
    public static ArrayList<Room> getRooms() {
    	return rooms;
    }
}
