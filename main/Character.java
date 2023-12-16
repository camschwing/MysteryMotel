package main;

import java.util.ArrayList;

public class Character {
    private String name;
    private static final ArrayList<Item> inventory = new ArrayList<>();

    public void Character(String name) {
        this.name = name;
    }

    public static void addInventory(Item item) {
        inventory.add(item);
    }
    public static void removeInventory(Item a) {
        inventory.removeIf(i -> i == a);
    }
    public String getName() {
        return name;
    }
    public static ArrayList<Item> getInventory() {
        return inventory;
    }
}
