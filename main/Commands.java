package main;

import java.util.List;

class Commands extends MysteryMotel {
	static boolean admin = false;

	public Commands() {}

	public static void processCommand(String[] command) {
		for (int i = 0; i < 50; ++i)  System.out.println();

		switch (command[0]) {
			case "north":
			case "east":
			case "south":
			case "west":
				move(command[0]);
				break;

			case "help":
				Gui.displayGui(Strings.helpGui, getCommandOutput());
				pausePrintGui();
				break;

			case "search":
				search();
				break;

			case "get":
				getItem(command[1]);
				break;

			case "use":
				useItem(command[1]);
				break;

			case "map":
				Map.displayMap();
				break;

			case "back":
				Gui.displayGui(Strings.gui, getCommandOutput());
				break;

			case "admin", "a":
				admin = true;
				while (admin) {
					String c = getUserInput();
					adminCommand(c);

				}
				break;
			default:
				setCommandOutput("Invalid command. Try again.");
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
    		Map.displayMap();
    		break;

    	}
	}

	protected static int id(int x, int y) {
		return (3*y) + x;
	}

	protected static void setCommandOutput(String CommandOut) {
		MysteryMotel.commandOutput = CommandOut;
	}

	protected static void pausePrintGui() {
		MysteryMotel.printGui = false;
	}

	protected static String getCommandOutput() {
		String co = MysteryMotel.commandOutput;
		setCommandOutput("");
		return co;
	}

	protected static void move(String direction) {
    	Room room = currentRoom;
    	int roomID = room.getID();

    	switch (direction) {
    	case "north":
    		try { enterRoom(Map.getRoomNum(roomID+3)); } catch (Exception e) { setCommandOutput("Room not found!"); currentRoom = room; }
            break;
    	case "south":
    		try { enterRoom(Map.getRoomNum(roomID-3)); } catch (Exception e) { setCommandOutput("Room not found!"); currentRoom = room; }
            break;
    	case "east":
    		try { enterRoom(Map.getRoomNum(roomID+1)); } catch (Exception e) { setCommandOutput("Room not found!"); currentRoom = room; }
            break;
    	case "west":
    		try { enterRoom(Map.getRoomNum(roomID-1)); } catch (Exception e) { setCommandOutput("Room not found!"); currentRoom = room;  }
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
    		setCommandOutput(String.format("You entered the %s", currentRoom.getName()) + ".");
    		entered = true;
    		return;
    	}

    	for (Map m : mapRooms) {
    		if (m != null) {
    			if (m.getObjRoom() == currentRoom) {
    				setCommandOutput(String.format("You entered the %s", currentRoom.getName()) + ".");
    				entered = true;
    				return;
    			}
    		}
    	}
    	entered = true;
    	new Map(num, currentRoom, x, y);
    	setCommandOutput(String.format("You entered the %s", currentRoom.getName()) + ".");
    }
	protected static void search() {
        setCommandOutput("You searched the room");
		MysteryMotel.currentRoom.setSearched();
    }
	protected static void useItem(String i) {
		Item item = Character.getInventory().getFirst();
		for (Item a : Character.getInventory()) {
			if (a.getName().equals(i)) {
				item = a;
			}
		}

        if (!Character.getInventory().isEmpty()) {
			Character.removeInventory(item);
            setCommandOutput("You used the " + item.name + ".");
        } else {
            setCommandOutput("Your inventory is empty.");
        }
    }
	protected static void getItem(String name) {
        if (!currentRoom.getItems().isEmpty()) {
			for (Item i : currentRoom.getItems()) {
				if (i.getName().toLowerCase().equals(name)) {
					Character.addInventory(i);
					currentRoom.removeItem(i);
					setCommandOutput("You picked up a " + i.getName());
					return;
				}
			}
			setCommandOutput(name + " not found");
        } else {
            setCommandOutput("No items to pick up in this room");
        }
    }
}