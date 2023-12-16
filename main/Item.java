package main;

import java.util.ArrayList;

public class Item {
    public String name;
    private final String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public String getDescription() { return description; }
}