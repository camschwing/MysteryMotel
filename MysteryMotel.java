//JACK AND CAM 
import java.util.ArrayList;
import java.util.Scanner;

public class MysteryMotel {
    static boolean investigatedCrimeScene = false;
    static boolean escaped = false;
    static Room room1;
    static Room room2;
    static Item key;
    static Item book;
    static ArrayList<Item> inventory = new ArrayList<>();
    static Room currentRoom;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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


    static void printRoom() {
        System.out.println(currentRoom.getDescription());
        System.out.println("Items in the room: " + getItemList(currentRoom.getItems()));
        
        ArrayList<Item> items = currentRoom.getItems();
        if (items != null && !items.isEmpty()) {
            System.out.println("Items in the room: " + getItemList(items));
        } else {
            System.out.println("No items in the room.");
        }
    }

    static String getItemList(ArrayList<Item> items) {
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

    static String getUserInput() {
        System.out.print("Enter command: ");
        return scanner.nextLine().toLowerCase();
    }

    static void processCommand(String command) {
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
            default:
                System.out.println("Invalid command. Try again.");
        }
    }

    static void move(String direction) {
        if ("north".equals(direction) && currentRoom.equals(room1)) {
            currentRoom = room2;
            System.out.println("You enter the motel lobby.");
        if (!investigatedCrimeScene) {
            investigateCrimeScene();
        }
        } else if ("south".equals(direction) && currentRoom.equals(room2)) {
            currentRoom = room1;
            System.out.println("You return to the dark room.");
}
    }

    static void search() {
        System.out.println("Searching the room...");
        System.out.println("You find: " + getItemList(currentRoom.items));
    }

    static void getItem() {
        if (!currentRoom.items.isEmpty()) {
            Item item = currentRoom.items.remove(0);
            inventory.add(item);
            System.out.println("You picked up a " + item.name + ".");
        } else {
            System.out.println("No items to pick up in this room.");
        }
    }

    static void useItem() {
        if (!inventory.isEmpty()) {
            Item item = inventory.remove(0);
            System.out.println("You used the " + item.name + ".");
        } else {
            System.out.println("Your inventory is empty.");
        }
    }

    
    static Character detective;
    static Character motelOwner;
    static Character stranger;

    static void initializeGame() {
        // Define characters
        detective = new Character("Detective Smith", false);
        motelOwner = new Character("Mr. Johnson", false);
        stranger = new Character("Mysterious Stranger", true);
        ArrayList<Item> room1Items = new ArrayList<>();
        room1Items.add(key);

        ArrayList<Item> room2Items = new ArrayList<>();
        room2Items.add(book);

        // Define items with descriptions
        Room room1 = new Room("You are in a dark room. Detective Smith, the seasoned investigator, is here.", room1Items);
        Room room2 = new Room("You are in the motel lobby. Mr. Johnson, the owner, is behind the counter.", room2Items);

        Item key = new Item("Key", "An old rusty key.");
        Item book = new Item("Book", "A book with strange symbols.");

        currentRoom = room1;
    }

    

    static void investigateCrimeScene() {
        System.out.println("Detective Smith asks you to investigate the crime scene.");
        System.out.println("You find a key and a book. The book has strange symbols that may be a clue.");
        System.out.println("The motel owner, Mr. Johnson, is acting nervously.");

        // Add logic to set investigatedCrimeScene to true and update the story
        investigatedCrimeScene = true;
    }

    static boolean accused = false;

    static void accuseMurderer() {
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

    static boolean diedInvestigating = false;

    static void dieInvestigating() {
        System.out.println("While investigating, the mysterious stranger attacks you!");
        System.out.println("Unfortunately, you didn't make it. Game over.");
        printEndMessage();
        diedInvestigating = true;
    }

    static boolean gameOver() {
        return accused || diedInvestigating || escaped;
    }

    static void printEndMessage() {
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
