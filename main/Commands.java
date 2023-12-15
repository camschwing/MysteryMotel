package main;

import java.util.ArrayList;
import java.util.List;

class Commands extends MysteryMotel {

	static boolean admin = false;

	public static void processCommand(String command) {
		for (int i = 0; i < 50; ++i)  System.out.println();
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
				System.out.printf("You are in the %s.%n", currentRoom.getName());
				break;

			case "map":
				Map.displayMap();
				break;

			case "admin", "a":
				admin = true;
				while (admin) {
					String c = getUserInput();
					adminCommand(c);

				}
				break;
			default:
				System.out.println("Invalid command. Try again.");
		}
	}

	private static void adminCommand(String command) {
        switch (command) {
		case "quit", "q":
			admin = false;
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
    	Room room = currentRoom;
    	int roomID = room.getID();

    	switch (direction) {
    	case "north":
    		try { enterRoom(Map.getRoomNum(roomID+3)); } catch (Exception e) { System.out.println("Room not found!"); currentRoom = room; }
            break;
    	case "south":
    		try { enterRoom(Map.getRoomNum(roomID-3)); } catch (Exception e) {System.out.println("Room not found!"); currentRoom = room; }
            break;
    	case "east":
    		try { enterRoom(Map.getRoomNum(roomID+1)); } catch (Exception e) { System.out.println("Room not found!"); currentRoom = room; }
            break;
    	case "west":
    		try { enterRoom(Map.getRoomNum(roomID-1)); } catch (Exception e) { System.out.println("Room not found!"); currentRoom = room;  }
            break;
    	}
	}
	protected static void enterRoom(int rNum) {
    	List<Map> mapRooms = Map.getMapRooms();
    	Room room = Room.getRoom(rNum);
    	currentRoom = room;
        assert room != null;
        int id = room.getID();
    	int num = room.getNum();
    	int x = room.getX();
    	int y = room.getY();

    	if(mapRooms.isEmpty() || mapRooms.size() < id) {
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
            Item item = inventory.removeFirst();
            System.out.println("You used the " + item.name + ".");
        } else {
            System.out.println("Your inventory is empty.");
        }
    }
	protected static void getItem() {
        if (!currentRoom.getItems().isEmpty()) {
            Item item = currentRoom.getItems().removeFirst();
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

        itemList.append(items.getFirst().getName()).append(", ");

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
}