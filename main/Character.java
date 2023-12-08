package main;

public class Character extends MysteryMotel {
    private String name;
    public boolean isMurderer;

    public Character(String name, boolean isMurderer) {
        this.name = name;
        this.isMurderer = isMurderer;
    }
    public boolean isMurderer() {
        return isMurderer;
    }
    public String getName() {
    	return name;
    }
}
