package main;

import java.util.*;

public class Map {

	private static final ArrayList<ArrayList<Integer>> xy = new ArrayList<>();
	private static final List<Map> mapRooms = new ArrayList<>();
	private final String strRoom;
	private Room room;
	private final int id;
	private int x;
	private int y;

	public Map(int num, Room room, int x, int y) {
		this.id = Commands.id(x, y);
		this.x = x;
		this.y = y;
        this.room = room;

		if(num < 10) {
			strRoom = String.format(Strings.Room, "0"+num);
		}
		else {
			strRoom = String.format(Strings.Room, num);
		}
		
		xy.add(new ArrayList<>(Arrays.asList(x, y)));
		MysteryMotel.yIndex += 2;
		mapRooms.add(this);
	}
	
	public Map() {
		this.id = 0;
		strRoom = Strings.EmptyRoom;
		
		mapRooms.addFirst(this);
	}

	public static List<Map> getMapRooms() { return mapRooms; }
	private String getRoom() { return strRoom; }
	public Room getObjRoom() { return room; }
	public int getID() { return id; }
	public int getX() { return x; }
	public int getY() { return y; }
	
	public static int getRoomNum(int id) {
		for (Room r : Room.getRooms()) {
			if (r.getID() == id ) {
				return r.getNum();
			}
		}
		return 0;
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
	
	private static String updateRoom(Map m) {
		ArrayList<Integer> listID = new ArrayList<>();
		StringBuilder printRoom = new StringBuilder(m.getRoom());
		int cID = MysteryMotel.getCurrentRoom().getID();
		int i = m.getID();
		int tot;
		
		for (ArrayList<Integer> b : xy) {
			tot = Commands.id(b.get(0), b.get(1));
			listID.add(tot);
		} 
		listID.sort(Collections.reverseOrder());
		
		if (cID == i) {printRoom.setCharAt(34, '*');}
		if (listID.contains(i-3)) {printRoom.setCharAt(44, '┬');}
		if (listID.contains(i+3)) {printRoom.setCharAt(4, '┴');}
		if (listID.contains(i-1)) {printRoom.setCharAt(20, '┤');}
		if (listID.contains(i+1)) {printRoom.setCharAt(28, '├');}
		
		return printRoom.toString();
	}

	private static String updateMiniRoom(Map m) {
		ArrayList<Integer> listID = new ArrayList<>();
		StringBuilder printRoom = new StringBuilder(Strings.miniRoom);
		int cID = MysteryMotel.getCurrentRoom().getID();
		int i = m.getID();
		int tot;

		for (ArrayList<Integer> b : xy) {
			tot = Commands.id(b.get(0), b.get(1));
			listID.add(tot);
		}
		listID.sort(Collections.reverseOrder());

		if (cID == i) {printRoom.setCharAt(8, '*');}
		if (listID.contains(i-3)) {printRoom.setCharAt(14, '┬');}
		if (listID.contains(i+3)) {printRoom.setCharAt(2, '┴');}
		if (listID.contains(i-1)) {printRoom.setCharAt(6, '┤');}
		if (listID.contains(i+1)) {printRoom.setCharAt(10, '├');}

		return printRoom.toString();
	}

	private static String conjoin(String s1, String s2) {
		StringBuilder finalRoom = new StringBuilder();
		Scanner r1 = new Scanner(s1);
		Scanner r2 = new Scanner(s2);

		while (r1.hasNextLine()) {
			String line = r1.nextLine();
			String line2 = r2.nextLine();
			finalRoom.append(line).append(line2).append("\n");
		}

		return finalRoom.toString().replaceAll("[\n\r]$", "");
	}
	
	private static ArrayList<Map> sortArray() {
		ArrayList<Integer> listID = new ArrayList<>();
		ArrayList<Map> listRoom = new ArrayList<>();
		int tot;
		
		for (ArrayList<Integer> b : Map.xy) {
			tot = Commands.id(b.get(0), b.get(1));
			listID.add(tot);
		}
		listID.sort(Collections.reverseOrder());
		
		for (int i : listID) {listRoom.add(getMap(i));}
		
		return listRoom;
	}
	
	public static void displayMap() {
		Commands.pausePrintGui();
		ArrayList<Map> listRoom = sortArray();
		int minX = 100;
		int minY = 100;
		int maxX = 0;
		int maxY = 0;
		
		for (Map m : listRoom) {
			if (m.getX() < minX && m.getID() != 0) {minX = m.getX();}
			if (m.getY() < minY && m.getID() != 0) {minY = m.getY();}
			if (m.getX() > maxX) {maxX = m.getX();}
			if (m.getY() > maxY) {maxY = m.getY();}
		}
		
		for (int y = maxY; y >= minY; y--) {
			String yString = """





                    """;
			for (int x = minX; x <= maxX; x++) {
				int id = Commands.id(x, y);
				try {yString = conjoin(yString, updateRoom(Objects.requireNonNull(getMap(id))));}
				catch(Exception e) {yString = conjoin(yString, Objects.requireNonNull(getMap(0)).getRoom());}
			}
			System.out.println(yString);
		}
	}

	public static String displayMiniMap() {
		ArrayList<Map> listRoom = sortArray();
		StringBuilder finalMap = new StringBuilder();
		int minX = 100;
		int minY = 100;
		int maxX = 0;
		int maxY = 0;

		for (Map m : listRoom) {
			if (m.getX() < minX && m.getID() != 0) {minX = m.getX();}
			if (m.getY() < minY && m.getID() != 0) {minY = m.getY();}
			if (m.getX() > maxX) {maxX = m.getX();}
			if (m.getY() > maxY) {maxY = m.getY();}
		}

		for (int y = maxY; y >= minY; y--) {
			String yString = """

 
 
                    """;
			for (int x = minX; x <= maxX; x++) {
				int id = Commands.id(x, y);
				try {yString = conjoin(yString, updateMiniRoom(Objects.requireNonNull(getMap(id))));}
				catch(Exception e) {yString = conjoin(yString, Strings.miniEmptyRoom);}
			}
			finalMap.append(yString).append("\n");
		}
		return finalMap.deleteCharAt(finalMap.length()-1).toString();
	}
}