package main;

import java.util.ArrayList;

public class Room extends MysteryMotel {
    private String name;
    public ArrayList<Item> items;
    private int roomNum;
    

    public Room(String name, int roomNum, ArrayList<Item> items) {
        this.name = name;
        this.roomNum = roomNum;
        this.items = items;
    }
    public String getName() {
        return name;
    }

    public int getNum() {
    	return roomNum;
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }
}
