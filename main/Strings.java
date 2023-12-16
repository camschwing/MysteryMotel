package main;

public class Strings {
    public static final String Room = """
                        ┌───────┐
                        │ROOM %s│
                        │       │
                        │       │
                        └───────┘
                        """;
    public static final String EmptyRoom = """
                        \s
                        \s
                        \s
                        \s
                        \s
                """;
    public static final String miniRoom = """
			┌───┐
			│   │
			└───┘
			""";
    public static final String miniEmptyRoom = """
                    \s
                    \s
                    \s
                """;
    public static final String itemGui = """
            ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
            │ Current Room: %s                                                                                                   │
            │                                                                                                                    │
            │ Items in Room: %s                                                                                                  │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │ Inventory: %s                                                                                                      │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │%s                                                                                                                  │
            └────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘""";
    public static final String gui = """
            ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
            │ Current Room: %s                                                                                                   │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │ Inventory: %s                                                                                                      │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │%s                                                                                                                  │
            └────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘""";

    public static final String helpGui = """
            ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
            │ Help - Commands                                                                                                    │
            │                                                                                                                    │
            │ * Movement                                                                                                         │
            │           north - Move north                                                                                       │
            │           south - Move south                                                                                       │
            │           east  - Move east                                                                                        │
            │           west  - Move west                                                                                        │
            │                                                                                                                    │
            │ * General                                                                                                          │
            │          map - View large map                                                                                      │
            │          search - Search room for items                                                                            │
            │          use <item> - Use item in inventory                                                                        │
            │          back - Return to main screen                                                                              │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            └────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘""";
}