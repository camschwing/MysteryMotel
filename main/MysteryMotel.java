//JACK AND CAM

package main;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MysteryMotel {       
	protected static ArrayList<Item> inventory = new ArrayList<>();
	protected static boolean investigatedCrimeScene = false;
	protected static boolean accused = false;
    protected static Character motelOwner;
	protected static Room currentRoom;
	protected static Item book;
	protected static boolean diedInvestigating = false;
	protected static boolean entered = false;
	protected static int yIndex;
	
    private static boolean escaped = false;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException, InterruptedException {
        initializeGame();
        
        while (true) {
            printRoom();
            String command = getUserInput();
            Commands.processCommand(command);
            
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
   	 	
        // Define rooms
    	new Map(0, 0);
        new Room(1, "dark room", new Item("Key", "An old rusty key."), 2, 0);
        new Room(2, "motel lobby", new Item("Book", "A book with strange symbols."), 2, 1);
        new Room(3, "Room #23", new Item("Bloody Knife", "A bloody kitchen knife"), 1, 0);
        new Room(4, "test Room", new Item("Bloody Knife", "A bloody kitchen knife"), 3, 0);
       
        Commands.enterRoom(1);
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
   
    private static void printRoom() {
    	if (entered == true) {
    		entered = false;
    		return;
    	}
    }
    
    public static String getUserInput() {
        System.out.print("Enter command: ");
        return scanner.nextLine().toLowerCase();
    }
    
    public static Room getCurrentRoom() {
    	return currentRoom;
    }
    
    private static boolean gameOver() {
        return accused || diedInvestigating || escaped;
    }
    
    protected static void printEndMessage() {
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