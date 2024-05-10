
//Jack, Cam, Luke
public class Main {
    //Commands object to call Commands methods and variables
    private static final Commands c = new Commands();

    public static void main(String[] args) throws InterruptedException {
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
    public static void initializeGame() throws InterruptedException {
        //Instantiate Room objects
        Room room1 =  new Room("Entrance", new Position(0,0));
        Room room2 = new Room("Motel Lobby", new Position(0,1));
        Room room3 = new Room("Room #12", new Position(-1, 1));
        Room room4 = new Room("Room #17", new Position(-2, 1));
        Room room5 = new Room("Conservatory", new Position(0,2));
        Room room6 = new Room("Room #32", new Position(1,1));


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
        new Item("tulip", "tulip pedal with a light purple color", room6);

        //String s = "╭──────────────────────────────────────────────────────╮\n| Dearest Alissa,                                      |\n|     I've been holding onto something I need to share.| \n| Despite the rejections, I can't ignore how much you  |\n| mean to me. Your laughter, kindness, and spirit have |\n| captivated me since we met. I wanted you to know how |\n| special you are to me. I cherish our moments together|\n| and look forward to more. I can't help it, I need you|\n| as my partner for life. Please take me, I'm not sure |\n| what I would do without you.                         |\n|                                                      |\n| With all my love and affection,                      |\n|     Victor                                           |\n╰──────────────────────────────────────────────────────╯";
        String s = " ___ ___ __ __  ___________   ___ ____  __ __      ___ ___  ___  ______   ___ _     \n" +
                "|   |   |  |  |/ ___/      | /  _]    \\|  |  |    |   |   |/   \\|      | /  _] |    \n" +
                "| _   _ |  |  (   \\_|      |/  [_|  D  )  |  |    | _   _ |     |      |/  [_| |    \n" +
                "|  \\_/  |  ~  |\\__  |_|  |_|    _]    /|  ~  |    |  \\_/  |  O  |_|  |_|    _] |___ \n" +
                "|   |   |___, |/  \\ | |  | |   [_|    \\|___, |    |   |   |     | |  | |   [_|     |\n" +
                "|   |   |     |\\    | |  | |     |  .  \\     |    |   |   |     | |  | |     |     |\n" +
                "|___|___|____/  \\___| |__| |_____|__|\\_|____/     |___|___|\\___/  |__| |_____|_____|";

        room1.setEntered();
        room2.setLocked(true);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(s);
        for(int j = 0; j < 28; j++){
            Thread.sleep(175);
            System.out.println();
        }
        System.out.println(c.getDialogue("intro"));
        //System.out.println(s);



    }
}