//JACK AND CAM

package main;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MysteryMotel {                                                                                                                           
    private static boolean investigatedCrimeScene = false;
    private static boolean diedInvestigating = false;
    private static boolean accused = false;
    private static boolean escaped = false;
    private static Room currentRoom;
    private static Room room1;
    private static Room room2;
    private static Room room3;
    private static Item book;
    private static Character motelOwner;
    private static ArrayList<Item> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static Map m;
    
  
    public static void main(String[] args) throws IOException, InterruptedException {
        initializeGame();
        
        while (true) {
            printRoom();
            String command = getUserInput();
            processCommand(command);
            
            if (gameOver()) {
                System.out.println("Game Over");
                printEndMessage();
                break;
            }
        }
    }

    
    //Interface Methods
    public static void initializeGame() throws IOException, InterruptedException {
   	 intro();
   	 
   	 new Character("Detective Smith", false);
        new Character("Mr. Johnson", false);
        new Character("Mysterious Stranger", true);
       
        Item key = new Item("Key", "An old rusty key.");
        Item book = new Item("Book", "A book with strange symbols.");
        Item bloodyKnife = new Item("Bloody Knife", "A bloody kitchen knife");
       
       
        ArrayList<Item> room1Items = new ArrayList<>();
        room1Items.add(key);

        ArrayList<Item> room2Items = new ArrayList<>();
        room2Items.add(book);
       
        ArrayList<Item> room3Items = new ArrayList<>();
        room3Items.add(bloodyKnife);

        // Define items with descriptions
        Room room1 = new Room("dark room", 1, room1Items);
        Room room2 = new Room("motel lobby", 2, room2Items);
        Room room3 = new Room("Room #23", 3, room3Items);
       
       
        MysteryMotel.room1 = room1;
        MysteryMotel.room2 = room2;
        MysteryMotel.room3 = room3;
        
        enterRoom(room1, 1, 2, 0);
   }
    private static void intro() throws InterruptedException, IOException {
    	asciiMessage();
    	
    	Thread.sleep(1700);
    	for(int y = 0; y < 38; y++) {
    		System.out.println();
    		Thread.sleep(175);
    	}
    	
    }
    private static void asciiMessage() throws IOException {
    	Path fileName = Path.of(System.getProperty("user.dir") + "/main/.asciiMessage.txt");
    	
    	String str = Files.readString(fileName);
    	
    	System.out.println(str);
    }
    private static void enterRoom(Room room, int num, int x, int y) {
    	currentRoom = room;
    	
    	if(Map.mapRooms.size() == 0 || Map.mapRooms.size() < Map.id(x, y)) {
    		new Map(num, currentRoom, x, y);
    		System.out.println(String.format("You are now in : %s", currentRoom.getName()));
    		return;
    	}
    	
    	for (Map m : Map.mapRooms) {
    		if (m != null) {
    			if (m.getObjRoom() == currentRoom) {
    				System.out.println(String.format("You are now in : %s", currentRoom.getName()));
    				return;
    			}
    		}
    	}
    	
    	new Map(num, currentRoom, x, y);
    	
    	System.out.println(String.format("You are now in : %s", currentRoom.getName()));
    }
    private static void move(String direction) {
        if (direction.equals("north") && currentRoom.equals(room1)) {
        enterRoom(room2, 2, 2, 1);
        System.out.println("Would you like to investigate the crime scene?");
        } else if ("south".equals(direction) && currentRoom.equals(room2)) {
        enterRoom(room1, 1, 2, 0);
        } else if (direction.equals("west") && currentRoom.equals(room1)) {
        enterRoom(room3, 3, 1, 0);
        }
    }
    private static void printRoom() {
        ArrayList<Item> items = currentRoom.getItems();
        
        if (items != null && !items.isEmpty()) {
            System.out.println("Items in the room: " + getItemList(items));
        } else {
            System.out.println("No items in the room.");
        }
    }
    private static void processCommand(String command) {
        for (int i = 0; i < 50; ++i) System.out.println();
       
            switch (command) {
                case "north":
                case "east":
                case "south":
                case "west":
                    move(command);
                    break;
                case "search":
                    search();
                    break;
                case "get":
                    getItem();
                    break;
                case "use":
                    useItem();
                    break;
                case "yes":
                    investigateCrimeScene();
                case "room":
                	System.out.println(String.format("Current room: %s", currentRoom.getName()));
                    break;
                case "map":
                	Map.displayMap();
                	break;
                case "admin", "a":
                	String aCommand = getUserInput();
                	switch (aCommand) {
                	case "maprooms": 
                		int c = 0;
                    	for (Map m : Map.mapRooms) {
                    		if (m != null) {
                			System.out.println("room " + m.getNum() + " : " + c);
                    		}
                    		else {
                    			System.out.println(m + " : " + c);
                    		}
                			c++;
                		}
                    	break;
                	case "openrooms":
                		processCommand("north");
                		processCommand("south");
                		processCommand("west");
                		processCommand("map");
                		break;
                	}
                	break;
                default:
                    System.out.println("Invalid command. Try again.");
            }
    }
    private static String getUserInput() {
        System.out.print("Enter command: ");
        return scanner.nextLine().toLowerCase();
    }
    
    
    //Story Control Methods
    private static void investigateCrimeScene() {
        System.out.println("Detective Smith asks you to investigate the crime scene.");
        System.out.println("You find a key and a book. The book has strange symbols that may be a clue.");
        System.out.println("The motel owner, Mr. Johnson, is acting nervously.");

        investigatedCrimeScene = true;
       
        if (inventory.contains(book)) {
            System.out.println("You remember seeing similar symbols in the book you picked up.");
            System.out.println("Maybe the book is connected to the crime. Keep it in your inventory for now.");
        } else {
            System.out.println("You didn't find the book in the crime scene. It might be in another room.");
        }
   }
   private static void accuseMurderer() {
       if (inventory.contains(book)) {
           if (currentRoom.equals(room2) && motelOwner.isMurderer) {
               System.out.println("You accuse Mr. Johnson, the motel owner, of the murder.");
               System.out.println("He confesses, and you solve the case!");
               accused = true;
           } else {
               System.out.println("Accusing the wrong person can be dangerous. Be careful!");
           }
       } else {
           System.out.println("You need to find more clues before making an accusation.");
       }
   }
   private static void dieInvestigating() {
       System.out.println("While investigating, the mysterious stranger attacks you!");
       System.out.println("Unfortunately, you didn't make it. Game over.");
       printEndMessage();
       diedInvestigating = true;
   }
   
   
   //Character Item Management Methods
    private static void search() {
        System.out.println("Searching the room...");
        System.out.println("You find: " + getItemList(currentRoom.items));
    }
    private static void getItem() {
        if (!currentRoom.items.isEmpty()) {
            Item item = currentRoom.items.remove(0);
            inventory.add(item);
            System.out.println("You picked up a " + item.name + ".");
        } else {
            System.out.println("No items to pick up in this room.");
        }
    }
    private static String getItemList(ArrayList<Item> items) {
        if (items.isEmpty()) {
            return "None";
        }
        
        StringBuilder itemList = new StringBuilder();
        
        if (!items.isEmpty()) {
            itemList.append(items.get(0).getName()).append(", ");
        } else {
            System.out.println("No items in the room.");
        }

    return itemList.substring(0, itemList.length() - 2);
    }
    private static void useItem() {
        if (!inventory.isEmpty()) {
            Item item = inventory.remove(0);
            System.out.println("You used the " + item.name + ".");
        } else {
            System.out.println("Your inventory is empty.");
        }
    }
    
    
    private static boolean gameOver() {
        return accused || diedInvestigating || escaped;
    }
    private static void printEndMessage() {
        if (accused) {
            System.out.println("You solved the murder mystery! Congratulations!");
        } else if (diedInvestigating) {
            System.out.println("You were killed while investigating the murder. Better luck next time.");
        } else if (escaped) {
            System.out.println("You managed to escape with your life. The murderer is still at large.");
        }
        System.out.println("End of the game.");
    }
}
