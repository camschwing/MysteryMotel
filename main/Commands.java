package main;

import java.util.ArrayList;
import java.util.List;

class Commands extends MysteryMotel {
	
	static boolean admin = false;
	 
	public Commands() {
		
	}
	
	public static void processCommand(String command) {
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
                    
                case "investigate":
                    investigateCrimeScene();
                    break;
                case "room":
                	System.out.println(String.format("You are in the %s.", currentRoom.getName()));
                    break;
                    
                case "map":
                	Map.displayMap();
                	break;
                	
                case "admin", "a":
                	admin = true;
                	while (admin == true) {
                        String c = getUserInput();
                        adminCommand(c);
                        
                	}
                	break;
                default:
                    System.out.println("Invalid command. Try again.");
            }
    }
	
	private static void adminCommand(String command) {
		List<Map> mapRooms = Map.getMapRooms();
		
		switch (command) {
		case "quit", "q":
			admin = false;
			break;
			
    	case "map r": 
    		int c = 0;
        	for (Map m : mapRooms) {
        		if (m != null) {
    			System.out.println("room " + m.getNum() + " : " + c);
        		}
        		else {
        			System.out.println(m + " : " + c);
        		}
    			c++;
    		}
        	break;
        	
    	case "open r":
    		move("north");
    		move("south");
    		move("west");
    		processCommand("map");
    		break;
    		
    	}
	}
	
	protected static void move(String direction) {
    	List<Map> mapRooms = new ArrayList<Map>();
    	
    	mapRooms.addAll(Map.getMapRooms());
    	
    	Room room = currentRoom;
    	int roomID = room.getID();

    	switch (direction) {
    	case "north":
    		try { enterRoom(Map.getRoomNum(roomID+3)); } catch (Exception e) { System.out.println("Room not found!"); currentRoom = room; };
    		break;
    	case "south":
    		try { enterRoom(Map.getRoomNum(roomID-3)); } catch (Exception e) {System.out.println("Room not found!"); currentRoom = room; };
    		break;
    	case "east":
    		try { enterRoom(Map.getRoomNum(roomID+1)); } catch (Exception e) { System.out.println("Room not found!"); currentRoom = room; };
    		break;
    	case "west":
    		try { enterRoom(Map.getRoomNum(roomID-1)); } catch (Exception e) { System.out.println("Room not found!"); currentRoom = room;  };
    		break;
    	}
	}
	protected static void enterRoom(int rNum) {
    	List<Map> mapRooms = Map.getMapRooms();
    	Room room = Room.getRoom(rNum);
    	currentRoom = room;
    	int id = room.getID();
    	int num = room.getNum();
    	int x = room.getX();
    	int y = room.getY();
    	
    	if(mapRooms.size() == 0 || mapRooms.size() < id) {
    		new Map(num, currentRoom, x, y);
    		System.out.println(String.format("You entered the %s", currentRoom.getName()) + ".");
    		entered = true;
    		return;
    	}
    	
    	for (Map m : mapRooms) {
    		if (m != null) {
    			if (m.getObjRoom() == currentRoom) {
    				System.out.println(String.format("You entered the %s", currentRoom.getName()) + ".");
    				entered = true;
    				return;
    			}
    		}
    	}
    	entered = true;
    	new Map(num, currentRoom, x, y);
    	System.out.println(String.format("You entered the %s", currentRoom.getName()) + ".");
    }
	protected static void search() {
        System.out.println("Searching the room...");
        System.out.println("You find: " + getItemList(currentRoom.getItems()));
    }
	protected static void useItem() {
        if (!inventory.isEmpty()) {
            Item item = inventory.remove(0);
            System.out.println("You used the " + item.name + ".");
        } else {
            System.out.println("Your inventory is empty.");
        }
    }
	protected static void getItem() {
        if (!currentRoom.getItems().isEmpty()) {
            Item item = currentRoom.getItems().remove(0);
            inventory.add(item);
            System.out.println("You picked up a " + item.name + ".");
        } else {
            System.out.println("No items to pick up in this room.");
        }
    }
	protected static String getItemList(ArrayList<Item> items) {
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
	protected static void investigateCrimeScene() {
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
	protected static void dieInvestigating() {
	       System.out.println("While investigating, the mysterious stranger attacks you!");
	       System.out.println("Unfortunately, you didn't make it. Game over.");
	       printEndMessage();
	       diedInvestigating = true;
	   }
	protected static void accuseMurderer() {
	       if (inventory.contains(book)) {
	           if (currentRoom.getNum() == 2 && motelOwner.isMurderer) {
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
	
}