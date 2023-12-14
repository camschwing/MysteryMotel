package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Map {

	private String strRoom;
	private int id;
	private int x;
	private int y;
	private int num;
	private Room room;
	private static ArrayList<ArrayList<Integer>> xy = new ArrayList<ArrayList<Integer>>();
	private static List<Map> mapRooms = new ArrayList<Map>();
	
	int yIndex = MysteryMotel.yIndex;
	
	public Map(int num, Room room, int x, int y) {
		this.id = id(x, y);
		this.x = x;
		this.y = y;
		this.num = num;
		this.room = room;
		
		strRoom = String.format(
					  "┌───────┐\n"
					+ "│ROOM %s│\n"
					+ "│       │\n"
					+ "│       │\n"
					+ "└───────┘\n", "0"+num);
		
		mapRooms.add(this);
		
		xy.add(new ArrayList<Integer>(Arrays.asList(x, y)));
		
		MysteryMotel.yIndex += 2;
	}
	
	public Map(int x, int y) {
		this.id = 0;
		
		strRoom = String.format(
				  "         \n"
				+ "         \n"
				+ "         \n"
				+ "         \n"
				+ "         \n");
		
		mapRooms.add(0, this);
	}

	
	public static List<Map> getMapRooms() {
		return mapRooms;
	}
	public static int id(int x, int y) {
		int id = (3*y) + x;
		return id;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public static int getRoomNum(int id) {
		for (Room r : Room.getRooms()) {
			if (r.getID() == id ) {
				return r.getNum();
			}
		}
		return 0;
	}
	
	public Room getObjRoom() {
		return room;
	}
	
	public String getNum() {
		return Integer.toString(num);
	}
	
	public int getID() {
		return id;
	}
	
	private String getRoom() {
		return strRoom;
	}
	
	private static Map getMap(int id) {
		for (Map m : mapRooms) {
			if (m != null) {
				if (m.getID() == id) {
					return m;
				}
			}
		}
		return null;
	}
	
	private static String directionRoom(Map m) {
		ArrayList<Integer> listID = new ArrayList<Integer>();
		StringBuilder printRoom = new StringBuilder(m.getRoom());
		int i = m.getID();
		int tot;
		
		for (ArrayList<Integer> b : xy) {
			tot = id(b.get(0), b.get(1));
			listID.add(tot);
		}
		
		Collections.sort(listID, Collections.reverseOrder()); 
		
		
		if (listID.contains(i-3)) {
			printRoom.setCharAt(44, '┬');
		}
		if (listID.contains(i+3)) {
			printRoom.setCharAt(4, '┴');
		}
		if (listID.contains(i-1)) {
			printRoom.setCharAt(20, '┤');
		}
		if (listID.contains(i+1)) {
			printRoom.setCharAt(28, '├');
		}
		return printRoom.toString();
	}
	
	public static String conjoin(String s1, String s2) {
		String finalRoom = "";
		
		Scanner r1 = new Scanner(s1.toString());
		Scanner r2 = new Scanner(s2.toString());
		
		while (r1.hasNextLine()) {
			  String line = r1.nextLine();
			  String line2 = r2.nextLine();
			  
			  finalRoom = finalRoom + line + line2 + "\n";
		}
		return finalRoom.replaceAll("[\n\r]$", "");
	}
	
	private static ArrayList<Map> sortArray(ArrayList<ArrayList<Integer>> a) {
		ArrayList<Integer> listID = new ArrayList<Integer>();
		ArrayList<Map> listRoom = new ArrayList<Map>();
		int tot;
		
		for (ArrayList<Integer> b : a) {
			tot = id(b.get(0), b.get(1));
			listID.add(tot);
		}
		 
		Collections.sort(listID, Collections.reverseOrder()); 
		
		for (int i : listID) {
			listRoom.add(getMap(i));
		}
		return listRoom;
	}
	
	public static void displayMap() {
		ArrayList<Map> listRoom = sortArray(xy);
		int minX = 100;
		int maxX = 0;
		int minY = 100;
		int maxY = 0;
		
		for (Map m : listRoom) {
			if (m.getX() < minX && m.getX() != 0) {
				minX = m.getX();
			}
			if (m.getX() > maxX) {
				maxX = m.getX();
			}
			if (m.getY() > maxY) {
				maxY = m.getY();
			}
			if (m.getY() < minY && m.getID() != 0) {
				minY = m.getY();
			}
		}
		
		for (int y = maxY; y >= minY; y--) {
			String yString = "\n" + "\n" + "\n" + "\n" + "\n";
			for (int x = minX; x <= maxX; x++) {
				int id = id(x, y);
				try { yString = conjoin(yString, directionRoom(getMap(id)).toString()); } catch(Exception e) { yString = conjoin(yString, getMap(0).getRoom()); } 
			}
			System.out.println(yString);
		}
	}
}
