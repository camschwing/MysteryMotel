//JACK AND CAM
package main;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MysteryMotel {
    private static final boolean escaped = false;
    private static final Scanner scanner = new Scanner(System.in);

    protected static boolean diedInvestigating = false;
	protected static boolean accused = false;
    protected static boolean entered = false;
    protected static Room currentRoom;
	protected static int yIndex;

    public static String commandOutput = "";
    public static boolean printGui = true;


    public static void main(String[] args) throws IOException, InterruptedException {
        initializeGame();
        
        while (true) {
            printRoom();
            String command = getUserInput();
            String[] cmdArray = command.split(" ");
            Commands.processCommand(cmdArray);
            
            if (gameOver()) {
                System.out.println("Game Over");
                printEndMessage();
                break;
            }
        }
    }

    public static void initializeGame() throws IOException {
   	 	intro();

        ArrayList<Item> room3Items = new ArrayList<>() {{
            add(new Item("Key", "An old rusty key"));
            add(new Item("Clock", "A weird Clock"));
        }};

    	new Map();
        new Room(1, "dark room", room3Items, 2, 0);
        new Room(2, "motel lobby", new Item("Book", "A book with strange symbols"), 2, 1);
        new Room(3, "Room #23", new Item("Bloody Knife", "A bloody kitchen knife"), 1, 0);
       
        Commands.enterRoom(1);
   }
    
    private static void intro() throws IOException {
    	asciiMessage();

        /*
    	Thread.sleep(1700);
    	for(int y = 0; y < 38; y++) {
    		System.out.println();
    		Thread.sleep(175);
    	}
        */
    }
   
    private static void asciiMessage() throws IOException {
    	Path fileName = Path.of(System.getProperty("user.dir") + "/main/.asciiMessage.txt");
    	
    	String str = Files.readString(fileName);
    	
    	System.out.println(str);
    }
   
    private static void printRoom() {
    	if (entered) {entered = false;}
        if (printGui) {
            if (currentRoom.getSearched()) {
                Gui.displayGui(Strings.itemGui, Commands.getCommandOutput());
            }
            else {
                Gui.displayGui(Strings.gui, Commands.getCommandOutput());
            }
        }
        else {
            printGui = true;
        }
    }

    public static String getUserInput() {
        System.out.print(": ");
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