package main;

import java.util.ArrayList;

public class Room extends MysteryMotel {
    public String name;
    public ArrayList<Item> items;

    public Room(String name, ArrayList<Item> items) {
        this.name = name;
        this.items = items;
    }
    public String getName() {
        return name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
