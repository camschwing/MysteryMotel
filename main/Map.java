package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Map {

	private String strRoom;
	private int id;
	private int num;
	private Room room;
	private static ArrayList<ArrayList<Integer>> xy = new ArrayList<ArrayList<Integer>>();
	private static List<Map> mapRooms = new ArrayList<Map>();
	
	int yIndex = MysteryMotel.yIndex;
	
	public Map(int num, Room room, int x, int y) {
		this.id = id(x, y);
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
	
	
	public static String conjoin(int i1, int i2) {
		String s1 = getMap(i1).getRoom();
		String s2 = getMap(i2).getRoom();
		
		String finalRoom = "";
		
		Scanner r1 = new Scanner(s1.toString());
		Scanner r2 = new Scanner(s2.toString());
		
		while (r1.hasNextLine()) {
			  String line = r1.nextLine();
			  String line2 = r2.nextLine();
			  
			  finalRoom = finalRoom + line + line2 + "\n";
		}
		return finalRoom;
	}
	
	private static ArrayList<Integer> sortArray(ArrayList<ArrayList<Integer>> a) {
		int tot;
		ArrayList<Integer> listID = new ArrayList<Integer>();
		for (ArrayList<Integer> b : a) {
			tot = id(b.get(0), b.get(1));
			listID.add(tot);
		}
		 
		Collections.sort(listID, Collections.reverseOrder()); 
		
		return listID;
	}
	
	public static void displayMap() {
		
		
		ArrayList<Integer> listID = sortArray(xy);
		
		for (int i : listID) {
			if (i % 3 == 1) {
				for (int a : listID) {
					if (a % 3 == 2 && !Arrays.asList(i, i+1, i+3).contains(a)) {
						System.out.println(conjoin(0, a));
					}
				}
			}
			
			if (listID.contains(i-3)) {
				System.out.println("room south of " + i);
			}
			if (listID.contains(i+3)) {
				System.out.println("room north of " + i);
			}
			if (listID.contains(i-1)) {
				System.out.println("room west of " + i);
			}
			if (listID.contains(i+1)) {
				System.out.println("room east of " + i);
			}
		}
	}
}
