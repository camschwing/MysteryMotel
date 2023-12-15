package main;

import java.util.Scanner;

public class Gui {
    private static final String gui = """
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
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            │                                                                                                                    │
            └────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘""";

    public static void displayGui() {
        System.out.println(loadGui());
    }

    public static String getItems() {
        StringBuilder items = new StringBuilder();
        for (Item i : MysteryMotel.getCurrentRoom().getItems()) {
            items.append(i.getName()).append(", ");
        }
        if (!items.isEmpty()) {
            items.delete(items.length() - 2, items.length() - 1);
        }
        else {
            items.append("Room is Empty");
        }
        return items.toString();
    }

    public static String loadMiniMap(StringBuilder gui) {
        Scanner scGui = new Scanner(gui.toString());
        Scanner scMiniMap = new Scanner(Map.displayMiniMap());
        StringBuilder scLine = new StringBuilder();
        StringBuilder finalMap = new StringBuilder();

        while (scGui.hasNext()) {
            String scGuiLine = scGui.nextLine();
            finalMap.append(scGuiLine).append("\n");

            while (scMiniMap.hasNext()) {
                String line = scMiniMap.nextLine();
                scLine.append(scGui.nextLine());

                scLine.replace((scLine.length()-line.length()-2), scLine.length()-2, line);
                finalMap.append(scLine).append("\n");
                scLine.setLength(0);
            }
        }

        return finalMap.toString();
    }

    private static String loadGui() {
        Scanner scGui = new Scanner(String.format(gui, MysteryMotel.getCurrentRoom().getName(), getItems()));
        StringBuilder loadedGui = new StringBuilder();
        StringBuilder sbLine;

        while (scGui.hasNextLine()) {
            String line = scGui.nextLine();
            sbLine = new StringBuilder(line);

            for(int x = line.length(); x >= 119; x--) {
                sbLine.deleteCharAt(x-2);
            }
            loadedGui.append(sbLine).append("\n");
        }

        loadedGui.replace(0, loadedGui.length()-1, loadMiniMap(loadedGui));

        return loadedGui.deleteCharAt(loadedGui.length()-1).toString();
    }
}