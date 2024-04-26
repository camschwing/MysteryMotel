//Jack, Cam, Luke
public class Main {
    //Commands object to call Commands methods and variables
    private static final Commands c = new Commands();

    public static void main(String[] args) {
        initializeGame();

        //While user is still alive continue the game
        while (Commands.usr.getAlive()) {
            c.processCommand();
            if (!c.getCommandOut().equals("*")) {
                System.out.println(c.getCommandOut());
            }
        }
    }

    //Initialize game by creating all necessary objects
    public static void initializeGame() {
        //Instantiate Room objects
        Room room1 =  new Room("Entrance", new Position(0,0));
        Room room2 = new Room("Motel Lobby", new Position(0,1));
        Room room3 = new Room("Room #12", new Position(-1, 1));
        Room room4 = new Room("Room #17", new Position(-2, 1));
        Room room5 = new Room("Conservatory", new Position(0,2));

        //Instantiate Character objects
        new Character("Winston", new Position(0,0));
        new Character("Linus", new Position(0,1));
        new Character("Victor", new Position(-1,1));
        new Character("Eleanor", new Position(-2,1));
        new Character("Beatrice", new Position(0,2));

        //Instantiate Items
        new Item("Key", "old rusty key", room1);
        new Item("Knife", "very shiny, dull knife", room2);
        new Item("Letter", "letter professing love to Alissa, but returned to sender", room3);
        new Item("Money", "roll of money amounting to 10 thousands dollars", room4);
        new Item("Rose", "rose pedal with a very deep red color", room5);

        room1.setEntered();
        room2.setLocked(true);
        System.out.println("\n\n");
        System.out.println(c.getDialogue("intro"));
    }
}