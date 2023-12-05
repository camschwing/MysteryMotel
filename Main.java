//CAM AND JACK --- MYSTERY MOTEL
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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

    static void initializeGame() {
        // Define your rooms and items here
        Item key = new Item("Key");
        Item book = new Item("Book");

        ArrayList<Item> room1Items = new ArrayList<>();
        room1Items.add(key);

        ArrayList<Item> room2Items = new ArrayList<>();
        room2Items.add(book);

        Room room1 = new Room("You are in a dark room.", room1Items);
        Room room2 = new Room("You are in a Janitor's closet.", room2Items);

        currentRoom = room1;
    }

    static void printRoom() {
        System.out.println(currentRoom.description);
        System.out.println("Items in the room: " + getItemList(currentRoom.items));
    }

    static String getItemList(ArrayList<Item> items) {
        if (items.isEmpty()) {
            return "None";
        }

        StringBuilder itemList = new StringBuilder();
        for (Item item : items) {
            itemList.append(item.name).append(", ");
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
            // case "bonusCommand":
            //     bonusFunctionality();
            //     break;
            default:
                System.out.println("Invalid command. Try again.");
        }
    }

    static void move(String direction) {
        // Implement movement logic based on the direction
        // Update currentRoom accordingly
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

    static boolean gameOver() {
        // Implement game over conditions
        return false;
    }

    static void printEndMessage() {
        // Print the final message
    }
}
