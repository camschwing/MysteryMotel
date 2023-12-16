package main;

import java.util.Scanner;

public class Gui {
    public static void displayGui(String gui, String CommandOut) {
        System.out.println(loadGui(gui, CommandOut));
    }

    public static String getItems() {
        StringBuilder items = new StringBuilder();
        for (Item i : MysteryMotel.getCurrentRoom().getItems()) {
            String description = String.format(" (%s)", i.getDescription());
            items.append(i.getName()).append(description).append(", ");
        }
        if (!items.isEmpty()) {
            items.delete(items.length() - 2, items.length() - 1);
        }
        else {
            items.append("Room is Empty");
        }
        return items.toString();
    }
    public static String getInventory() {
        StringBuilder items = new StringBuilder();
        for (Item i : Character.getInventory()) {
            items.append(i.getName()).append(", ");
        }
        if (!items.isEmpty()) {
            items.delete(items.length() - 2, items.length() - 1);
        }
        else {
            items.append("empty");
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

    private static String loadGui(String gui, String CommandOut) {
        Scanner scGui = new Scanner("");
        StringBuilder loadedGui = new StringBuilder();
        StringBuilder sbLine;
        scGui.reset();

        scGui = switch (gui) {
            case Strings.gui ->
                    new Scanner(String.format(gui, MysteryMotel.getCurrentRoom().getName(), getInventory(), ("* " + CommandOut)));
            case Strings.itemGui ->
                new Scanner(String.format(gui, MysteryMotel.getCurrentRoom().getName(), getItems(), getInventory(), ("* " + CommandOut)));

            case Strings.helpGui ->
                    new Scanner(gui);
            default -> scGui;
        };

        while (scGui.hasNextLine()) {
            String line = scGui.nextLine();
            sbLine = new StringBuilder(line);

            for(int x = line.length(); x >= 119; x--) {
                sbLine.deleteCharAt(x-2);
            }
            loadedGui.append(sbLine).append("\n");
        }

        if (gui.equals(Strings.helpGui)) {
            return loadedGui.deleteCharAt(loadedGui.length()-1).toString();
        }
        else {
            loadedGui.replace(0, loadedGui.length() - 1, loadMiniMap(loadedGui)).deleteCharAt(loadedGui.length() - 1);
        }
        return loadedGui.deleteCharAt(loadedGui.length()-1).toString();
    }
}