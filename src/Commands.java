import java.util.Objects;
import java.util.Scanner;


public class Commands {
    //Command output
    private static final StringBuilder commandOut = new StringBuilder("* ");
    //Scanner used to get input from user
    private static final Scanner sc = new Scanner(System.in);
    private static String[] task;
    //User Character object
    public static Character usr = new Character("User", new Position(0, 0));

    //Commands constructor
    public Commands() {}

    //Accessor methods
    public String getCommandOut() {
        return commandOut.toString();
    }

    public void processCommand() {
        commandOut.replace(1, commandOut.length(), "");
        //Split given user command into array
        String[] cmd = getUsrInput().split(" ");

        System.out.println("\n\n\n\n");

        //Get user input, then run corresponding method
        try {
            switch (cmd[0]) {
                //Movement
                case "north":
                case "south":
                case "east":
                case "west":
                    move(cmd[0]);
                    getAllRooms();
                    map();
                    break;
                case "help":
                    System.out.println("Help - Commands\n\n* Movement\nnorth - Move north\nsouth - Move south\neast  - Move east\nwest  - Move west\n\n* General\ntalk <person> - talk with someone\nsearch - Search room for items\ninventory - See your inventory\nget <item> - Add item to inventory\naccuse <person> - accuse whoever you think committed the crime");
                    break;
                case "get":
                    getAllRooms();
                    map();
                    getItem(cmd[1]);
                    break;
                case "talk":
                    getAllRooms();
                    map();
                    System.out.println(getDialogue(cmd[1]));
                    break;
                case "search":
                    getAllRooms();
                    map();
                    System.out.println(search());
                    break;
                case "inventory":
                    getAllRooms();
                    map();
                    System.out.println(inventory());
                    break;
                case "accuse":
                    accuse(cmd[1]);
                    break;
                case "use":
                    getAllRooms();
                    map();
                    commandOut.append(use(cmd[1]));
                    break;
                case "letter":
                    for(int j = 0; j < 15; j++){
                        Thread.sleep(250);
                        System.out.println();
                    }
                    String s = "╭──────────────────────────────────────────────────────╮\n| Dearest Alissa,                                      |\n|     I've been holding onto something I need to share.| \n| Despite the rejections, I can't ignore how much you  |\n| mean to me. Your laughter, kindness, and spirit have |\n| captivated me since we met. I wanted you to know how |\n| special you are to me. I cherish our moments together|\n| and look forward to more. I can't help it, I need you|\n| as my partner for life. Please take me, I'm not sure |\n| what I would do without you.                         |\n|                                                      |\n| With all my love and affection,                      |\n|     Victor                                           |\n╰──────────────────────────────────────────────────────╯";
                    //wait(1000);
                    printWithDelay(s);
                    break;
                case "unlock":
                    setAll(true);
                    map();
                    break;
                case "lock":
                    setAll(false);
                    map();
                    break;
                default:
                    //Default when given command does not match any know commands
                    commandOut.append("Invalid Command! Try again.");
            }
        } catch (Exception e) {
            commandOut.append("Invalid Command usage! Try again.");
        }
    }
    private static void setAll(boolean bool){
        for (Room r : Room.getRooms()){
            if(r.getRoomNum()!= 1) {
                r.setEnteredVal(bool);
            }
        }
    }
    private static void getAllRooms() {

        for (Room r : Room.getRooms()){
            if (r.getEntered()){
                System.out.print("Room number " + r.getRoomNum() + " = " + r.getName() + ";    ");
            }
        }
        System.out.println();
    }
    private static String getUsrInput() {
        //Prefix to user input
        System.out.print(": ");
        //Read and return user given command
        return sc.nextLine().toLowerCase();
    }

    //Return current Room that usr is in
    private static Room getCurrentRoom() {
        //Loop through Room objects in rooms ArrayList
        for (Room r : Room.getRooms()) {
            //Check Room Position toString matches usr Position toString
            if (Objects.equals(r.getPosition().toString(), usr.getPosition().toString())) {
                //Return current Room
                return r;
            }
        }
        //If none is found, return null
        return null;
    }

    //Add specified item from Room to inventory
    private static void getItem(String item) throws InterruptedException {
        //Check if currentRoom has been searched already
        if (Objects.requireNonNull(getCurrentRoom()).getSearched()) {
            //Find specified Item
            for (Item i : Objects.requireNonNull(getCurrentRoom()).getItems()) {
                if (i.getName().equalsIgnoreCase("key")){
                    System.out.println("🔑");
                }
                if (i.getName().equalsIgnoreCase("rose")){
                    System.out.println("🌹");
                }
                if (i.getName().equalsIgnoreCase("knife")){
                    System.out.println("🔪");
                }
                if (i.getName().equalsIgnoreCase("money")){
                    System.out.println("💵");
                }
                if (i.getName().equalsIgnoreCase("letter")){
                    String letter1 = "╭──────────────────────────────────────────╮\n" +
                            "| \\                                      / |\n" +
                            "|   \\                                  /   |\n" +
                            "|     \\                              /     |\n" +
                            "|       \\                          /       |\n" +
                            "|         \\                      /         |\n" +
                            "|           \\                  /           |\n" +
                            "|             \\              /             |\n" +
                            "|               \\          /               |\n" +
                            "|                 \\      /                 |\n" +
                            "|                   \\  /                   |\n" +
                            "|                    \\/                    |\n" +
                            "|                                          |\n" +
                            "╰──────────────────────────────────────────╯";

                    System.out.println(letter1);
                    System.out.println("Use command 'letter' to open.");
                }
                if (i.getName().equalsIgnoreCase(item)) {
                    usr.addInventory(i);
                    getCurrentRoom().removeItem(i);
                    commandOut.append("You picked up the ").append(i.getName());
                    return;
                }
            }

            commandOut.append("Item not found!");
        } else {
            commandOut.append("You have not searched the room yet!");
        }
    }

    //Get Characters dialogue
    public String getDialogue(String name) {
        //idk how this method works tbh

        //StringBuilder dialogue is returned
        //StringBuilder line is used to edit individual lines to be centered
        StringBuilder dialogue = new StringBuilder();
        StringBuilder line;

        if (name.equalsIgnoreCase("intro")) {
            for (Character.Dialogues d : Character.Dialogues.values()) {
                if (d.name().equalsIgnoreCase(name)) {
                    //Center each line
                    for (String s : d.dialogue) {
                        line = new StringBuilder(s);
                        for (int x = 1; x < 58 - (s.length() / 2); x++) {
                            line.insert(0, ' ');
                        }
                        dialogue.append(line).append("\n");
                    }
                }
                return dialogue.toString();
            }
        }
        if (name.equalsIgnoreCase("LETTER")) {
            for (Character.Dialogues d : Character.Dialogues.values()) {
                if (d.name().equalsIgnoreCase(name)) {
                    //Center each line
                    for (String s : d.dialogue) {
                        line = new StringBuilder(s);
                        for (int x = 1; x < 58 - (s.length() / 2); x++) {
                            line.insert(0, ' ');
                        }
                        dialogue.append(line).append("\n");
                    }
                }
                return dialogue.toString();
            }
        }
        if (name.equalsIgnoreCase("LETTER1")) {
            for (Character.Dialogues d : Character.Dialogues.values()) {
                if (d.name().equalsIgnoreCase(name)) {
                    //Center each line
                    for (String s : d.dialogue) {
                        line = new StringBuilder(s);
                        for (int x = 1; x < 58 - (s.length() / 2); x++) {
                            line.insert(0, ' ');
                        }
                        dialogue.append(line).append("\n");
                    }
                }
                return dialogue.toString();
            }
        }

        //Find Character
        for (Character c : Character.getCharacters()) {
            //Check if Character is Winston after usr talked to him already
            if (name.equalsIgnoreCase("winston") && c.getTalkedTo()) {
                for (String s : task) {
                    line = new StringBuilder(s);
                    for (int x = 1; x < 58 - (s.length() / 2); x++) {
                        line.insert(0, ' ');
                    }
                    dialogue.append(line).append("\n");
                }
                return dialogue.toString();
            } else {
                //Pass if given name matches Character name
                if (c.getName().equalsIgnoreCase(name) && !name.equalsIgnoreCase("intro")) {
                    //Check Character Position is the same as usr current Position
                    if (c.getPosition().toString().equals(usr.getPosition().toString())) {
                        //Find Characters dialogue
                        for (Character.Dialogues d : Character.Dialogues.values()) {
                            if (d.name().equalsIgnoreCase(name)) {
                                //Center each line
                                for (String s : d.dialogue) {
                                    line = new StringBuilder(s);
                                    for (int x = 1; x < 58 - (s.length() / 2); x++) {
                                        line.insert(0, ' ');
                                    }
                                    dialogue.append(line).append("\n");
                                }
                                if (!c.getTalkedTo()) {
                                    setTask(name);
                                    c.setTalkedTo();
                                }
                                return dialogue.toString();
                            }
                        }
                    }
                }
            }
        }
        return name + " was not found in the room!";
    }

    //Set Task
    private void setTask (String name){
        String taskString = switch (name.toLowerCase()) {
            case "winston" ->
                    "Head north to the motel lobby to meet the owner, Linus.\n Enter the command 'help' at any time to see the full list of commands!";
            case "linus" ->
                    "Linus recommended beginning we begin our search by talking with Victor in room #12, west of the Motel Lobby.";
            case "victor" ->
                    "Victor mentioned Eleanor was in a room further west. We should go talk with her.";
            case "eleanor" ->
                    "The only place we haven't checked is the conservatory north of the Motel Lobby. Eleanor said a women named Beatrice should be there.";
            case "beatrice" ->
                    "We talked with everyone in the motel, now you must accuse the person you think responsible.\n Choose from either Linus, Victor, Eleanor, or Beatrice.";
            default -> "";
        };

        task = taskString.split("\\r?\\n");
    }
    //Move to another room
    private static void move (String direction){
        //Get possible next position
        Position nextPosition = new Position(usr.getPosition().getX(), usr.getPosition().getY());

        switch (direction) {
            case "north":
                nextPosition.setY(usr.getPosition().getY() + 1);
                break;
            case "south":
                nextPosition.setY(usr.getPosition().getY() - 1);
                break;
            case "east":
                nextPosition.setX(usr.getPosition().getX() + 1);
                break;
            case "west":
                nextPosition.setX(usr.getPosition().getX() - 1);
                break;
        }

        //Check if room occupies next position
        for (Room r : Room.getRooms()) {
            if (r.getPosition().getX() == nextPosition.getX() && r.getPosition().getY() == nextPosition.getY()) {
                if (r.getLocked()) {
                    commandOut.append("Room is locked. Find the key!");
                    return;
                }
                usr.getPosition().setPosition(nextPosition);
                r.setEntered();
                commandOut.append("You entered ").append(r.getName());
                return;
            }
        }
        commandOut.append("Room not found!");
    }
    //Search Room for Items
    private static String search () {
        if (Objects.requireNonNull(getCurrentRoom()).getStringItems() == null)
            return "Room is empty!";
        else {
            Objects.requireNonNull(getCurrentRoom()).setSearched();
            return "You searched the room and found: \n    " + Objects.requireNonNull(getCurrentRoom()).getStringItems();
        }
    }
    //Get usr inventory
    private static String inventory () {
        //Check if inventory is empty
        if (usr.getInventory().isEmpty())
            return "Inventory is empty!";
        else {
            //Create StringBuilder for inventory
            StringBuilder inv = new StringBuilder("Inventory: \n   ");
            //Add each item in usr inventory to inventory StringBuilder
            for (Item i : usr.getInventory()) {
                inv.append(i.getName()).append(" (").append(i.getDescription()).append("), ");
            }
            //Remove last two spaces (", ")
            inv.replace(inv.length() - 2, inv.length(), "");
            return inv.toString();
        }
    }
    private String use(String name) {
        for (Item i : usr.getInventory()) {
            if (i.getName().equalsIgnoreCase(name)) {
                if (i.getName().equalsIgnoreCase("key")) {
                    for (Room r : Room.getRooms()) {
                        r.setLocked(false);
                    }
                    usr.removeInventory(i);
                    return "You used the Key!";
                }
                if (i.getName().equalsIgnoreCase("letter")) {
                    return getDialogue("letter");
                    /*wait(1000);
                    System.out.println(getDialogue("letter"));*/
                }
            }
            else {
                return "Item can not be used!";
            }
        }
        return null;
    }

    private static void accuse(String name) {
        //Create list for inventory w/o descriptions
        StringBuilder inv = new StringBuilder();

        if (usr.getInventory().isEmpty())
            inv.append("nothing");
        else {
            for (Item i : usr.getInventory()) {
                inv.append(i.getName()).append(", ");
            }
            //Remove last two spaces (", ")
            inv.replace(inv.length() - 2, inv.length(), "");
        }

        //If accuse eleanor print winning message
        if (name.equalsIgnoreCase("eleanor")) {
            System.out.println("You accuse Eleanor of the crime! She confesses immediately stating Victor paid her to kill Alissa after she rejected him. \nYou were able to collect " + inv + ". \nThank you For playing!");
            usr.setAlive();
        }
        //If accuse wrong person print losing message
        else if (name.equalsIgnoreCase("linus") || name.equalsIgnoreCase("victor") || name.equalsIgnoreCase("beatrice")) {
            System.out.println("You accuse " + name + "! They deny immediately. Later that night a mysterious figure approaches you and kills you! \n You collected " + inv + ". \n Try again!");
            usr.setAlive();
        }
        //If accused name does not match character, give user another chance
        else {
            System.out.println("Invalid name!");
        }
    }

    //Map methods
    //Modify map string to connect rooms
    private static String updateRoom (Room room, String s){
        StringBuilder printRoom = new StringBuilder(s);

        //Check if room is current room
        if (Objects.requireNonNull(getCurrentRoom()).getPosition().toString().equals(room.getPosition().toString()))
            //Mark current room
            printRoom.setCharAt(41, '*');

        for (Room r : Room.getRooms()) {
            //If another visited room is in a direction modify current printing room to connect
            if (r.getEntered()) {
                if (r.getPosition().getY() == room.getPosition().getY() + 1 && r.getPosition().getX() == room.getPosition().getX())
                    printRoom.setCharAt(5, '┴');
                if (r.getPosition().getY() == room.getPosition().getY() - 1 && r.getPosition().getX() == room.getPosition().getX())
                    printRoom.setCharAt(53, '┬');
                if (r.getPosition().getX() == room.getPosition().getX() + 1 && r.getPosition().getY() == room.getPosition().getY())
                    printRoom.setCharAt(34, '├');
                if (r.getPosition().getX() == room.getPosition().getX() - 1 && r.getPosition().getY() == room.getPosition().getY())
                    printRoom.setCharAt(24, '┤');
            }
        }
        return printRoom.toString();
    }
    //Conjoin two rooms or blank room
    private static String conjoin (String str1, String str2){
        //Create finalRoom which is String that is returned
        StringBuilder finalRoom = new StringBuilder();
        //Create scanner for each String to conjoin lines
        Scanner r1 = new Scanner(str1);
        Scanner r2 = new Scanner(str2);

        //Conjoining lines
        while (r1.hasNextLine()) {
            String line = r1.nextLine();
            String line2 = r2.nextLine();
            finalRoom.append(line).append(line2).append("\n");
        }

        return finalRoom.toString().replaceAll("[\n\r]$", "");
    }
    //Display Map
    private static void map () {
        //Assign opposite sides of map
        Position maxPos = new Position(-100, -100);
        Position minPos = new Position(100, 100);

        //Scale size of maps to minimum required
        for (Room r : Room.getRooms()) {
            if (r.getEntered()) {
                if (r.getPosition().getX() > maxPos.getX())
                    maxPos.setX(r.getPosition().getX());
                if (r.getPosition().getY() > maxPos.getY())
                    maxPos.setY(r.getPosition().getY());
                if (r.getPosition().getX() < minPos.getX())
                    minPos.setX(r.getPosition().getX());
                if (r.getPosition().getY() < minPos.getY())
                    minPos.setY(r.getPosition().getY());
            }
        }

        //Loop through each y level top to bottom
        for (int y = maxPos.getY(); y >= minPos.getY(); y--) {
            //Create empty space String
            String yString = """





                        """;
            xLoop:
            //Loop through x position in y level - left to right
            for (int x = minPos.getX(); x <= maxPos.getX(); x++) {
                //Find corresponding room to current xy position
                for (Room r : Room.getRooms()) {
                    //Check if matching
                    if (r.getPosition().getX() == x && r.getPosition().getY() == y && r.getEntered()) {
                        //Conjoin new room to the right of past x rooms
                        yString = conjoin(yString, updateRoom(r, String.format(
                                """
                                        ╭─────────╮
                                        │ ROOM %s │
                                        │         │
                                        │         │
                                        ╰─────────╯""", "0" + r.getRoomNum())));
                        continue xLoop;
                    }
                }
                //If no room is found, fill space with blank "room"
                yString = conjoin(yString,
                        """
                                          \s
                                          \s
                                          \s
                                          \s
                                          \s
                                """);
            }
            //Print y line of rooms
            System.out.println(yString);
        }
    }
    public static void printWithDelay(String str) {
        try {
            for (char c : str.toCharArray()) {
                System.out.print(c);
                Thread.sleep(90);
            }
            System.out.println(); // Move to the next line after printing the string
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}




