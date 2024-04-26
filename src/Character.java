import java.util.ArrayList;

public class Character {
    //Enum Dialogues contains all dialogue for the characters organized under their name
    enum Dialogues {
        INTRO(new String[]{
                "Welcome to the Mystery Motel!",
                "You and your partner Winston are two detectives assigned the murder of Alissa in a remote motel.",
                "Interview the people at the motel to uncover the truth and arrest the murderer!",
                "Type 'help' at anytime to view the full list of commands or talk with Winston if you are not sure what to do.",
                "Talk with Winston to begin your investigation!"
        }),
        WINSTON(new String[]{
                "Howdy detective, I'm Winston, I'll be your partner for this case.",
                "We should start our search by talking with the owner Linus, north of this here room in the Motel Lobby.",
                "I'll follow along with you, just ask if you ever need any help.",
                "Make sure to search each room to collect more clues!"
        }),
        LINUS(new String[]{
                "Hello detective, My name's Linus, I'm the owner of this here motel.",
                "I don't doubt you're here to investigate the murder that took place last night?",
                "I trust you will find who ever is responsible for this.",
                "You should start your search over in room #12, west of this here lobby. A fellow named Victor stays there."
        }),
        VICTOR(new String[]{
                "Hello detective, I'm Victor, is there something that I can help you with?",
                "If you are here to question me about that murder then don't bother.",
                "I have done nothing wrong and have no reason to hide anything.",
                "",
                "Instead of bothering me, why don' you go bother Eleanor down the hall from here.",
                ""
        }),
        ELEANOR(new String[]{
                "Howdy, detective! I'm Eleanor. I'm in charge of keeping this motel nice and tidy.",
                "Alice was indeed a very bright woman. I did hear her arguing a few nights prior to that awful accident.",
                "If you have asked everyone else you should go check out the conservatory north of the lobby. The women who works there is named Beatrice."
        }),
        BEATRICE(new String[]{
                "Well, hello there, detective. I'm Beatrice. I thought you were going to come by here.",
                "I tend to the flowers here in the conservatory. I promise I am quite innocent. I am here day and night.",
                "Who do you think has done this terrible crime."
        });

        final String[] dialogue;

        Dialogues(String[] dialogue) {
            this.dialogue = dialogue;
        }
    }

    //Character unique variables
    private final String name;
    private boolean alive;
    private boolean talkedTo;
    private final Position position;
    private final ArrayList<Item> inventory = new ArrayList<>();
    private static final ArrayList<Character> characters = new ArrayList<>();

    //Character constructor
    public Character(String name, Position position) {
        this.name = name;
        this.position = position;
        this.alive = true;
        this.talkedTo = false;

        characters.add(this);
    }

    //Accessor methods
    public String getName() {
        return name;
    }
    public Position getPosition() {
        return position;
    }
    public boolean getAlive() {
        return alive;
    }
    public boolean getTalkedTo() {
        return talkedTo;
    }
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public static ArrayList<Character> getCharacters() {
        return characters;
    }

    //Mutator methods
    public void setTalkedTo() {
        this.talkedTo = true;
    }
    public void setAlive() {
        this.alive = false;
    }
    public void addInventory(Item item) {
        inventory.add(item);
    }
    public void removeInventory(Item item) {
        inventory.remove(item);
    }
}
