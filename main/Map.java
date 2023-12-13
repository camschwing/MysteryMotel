package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {

	private String strRoom;
	private String Snum;
	private Room room;
	private int id;
	private int x;
	private int y;
	private static List<Map> mapRooms = new ArrayList<Map>();
	public static List<Map> printedRooms = new ArrayList<Map>();
	
	public Map(int num, Room room, int x, int y) {
		this.room = room;
		this.x = x;
		this.y = y;
		this.id = id(x, y);
		
		Snum = Integer.toString(num);
		
		strRoom = String.format(
					  "┌───────┐\n"
					+ "│ROOM %s│\n"
					+ "│       │\n"
					+ "│       │\n"
					+ "└───────┘\n", "0"+Snum);
		
		
		//Add filler spaces in list
		if (mapRooms.size() == 0) {
			mapRooms.add(0, null);
		}
		
		for(int i = mapRooms.size(); i <= id; i++) {
			mapRooms.add(i, null);
		}
		
		//Add room to mapRooms at index id
		
		if (mapRooms.get(id-1) == null) {
		mapRooms.set(id, this);
		}
	}
	
	public Map() {}

	public static List<Map> getMapRooms() {
		return mapRooms;
	}
	
	public int id(int x, int y) {
		int id = (3*y) + x;
		return id;
	}
	
	private static StringBuilder directionRoom(Map m) {
		List<Map> maps = new ArrayList<Map>();
		
		maps.addAll(mapRooms);
		
		int roomID = m.getID();
		
		StringBuilder printRoom = new StringBuilder(m.getRoom());
		
		//South
		try { Map a = maps.get(roomID-3); if (a != null) { printRoom.setCharAt(44, '┬'); } } catch (Exception e) { };
		//North
		try { Map b = maps.get(roomID+3); if (b != null) { printRoom.setCharAt(4, '┴'); } } catch (Exception e) { };
		//East
		try { Map c = maps.get(roomID-1); if (c != null) { printRoom.setCharAt(20, '┤'); } } catch (Exception e) { };
		//West
		try { Map d = maps.get(roomID+1); if (d != null) { printRoom.setCharAt(28, '├'); } } catch (Exception e) { };
		
		maps.clear();
		return printRoom;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	//Accessor methods
	public int getID() {
		return id;
	}
	private int getX() {
		return x;
	}
	private int getY() {
		return y;
	}
	private String getRoom() {
		return strRoom;
	}
	
	private static List<Map> getSurrounding(Map m) {
		List<Map> surRooms = new ArrayList<Map>();
		int roomID = m.getID();
		
		try { Map a = mapRooms.get(roomID-3); if (a != null) { surRooms.add(a); } } catch (Exception e) { };
		try { Map a = mapRooms.get(roomID+3); if (a != null) { surRooms.add(a); } } catch (Exception e) { };
		try { Map a = mapRooms.get(roomID-1); if (a != null) { surRooms.add(a); } } catch (Exception e) { };
		try { Map a = mapRooms.get(roomID+1); if (a != null) { surRooms.add(a); } } catch (Exception e) { };
		
		return surRooms;
	}
	public Room getObjRoom() {
		return room;
	}
	public static int getRoomNum(int id) {
		for (Room r : Room.getRooms()) {
			if (r.getID() == id ) {
				return r.getNum();
			}
		}
		return 0;
	}
	public String getNum() {
		return Snum;
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
	
	private static String mapSurRooms(Map m, String map) {
		List<Map> surRooms = getSurrounding(m);
		
		if(surRooms.size() == 0) {
			map = map + m.getRoom();
			return map;
		}
		
		if (surRooms.size() > 0) {
			int sX = m.getX();
			int sY = m.getY();
			
			for (Map d : surRooms) {
				if (printedRooms.contains(d)) {
					continue;
				}
				
				int mX = d.getX();
				int mY = d.getY();
				
				//West
				if(mX < sX) {
					Map m2 = getMap(m.getID()-1);
					String finalRoom = westRoom(m2);
					map = map + finalRoom;
					printedRooms.add(m);
					continue;
				}
				//East
				if(mX > sX) {
					String finalRoom = westRoom(m);
					map = map + finalRoom;
					printedRooms.add(m);
					continue;
				}
				//South
				if(mY < sY) {
					map = map + directionRoom(d);
				}
				//North
				if(mY > sY) {
					map = directionRoom(d) + map;
				}
				printedRooms.add(d);
			}
		}
		
		if (!printedRooms.contains(m) && getSurrounding(m).size() != 0) {
			int sX = m.getX();
			
			for (Map a : getSurrounding(m)) {
				int aX = a.getX();
				
				if (sX == aX) {
					map =  map + directionRoom(m).toString();
				}
				if (sX < aX) {
					map = indentRoom(sX, aX, m) + map;
				}
			}
		}
		else if (!printedRooms.contains(m) && getSurrounding(m).size() == 0) {
			
			map = map + directionRoom(m).toString();
		}
		printedRooms.add(m);
		return map;
	}
	
	public static String indentRoom(int sX, int mX, Map m) {
		String finalRoom = directionRoom(m).toString();
		
		if (sX < mX) {
			finalRoom = "";
			Scanner r1 = new Scanner(directionRoom(m).toString());
			while (r1.hasNextLine()) {
				  String line = r1.nextLine();
				  
				  finalRoom = finalRoom + "         " + line + "\n";
			}
		}
		m.setX(m.getX()+1);
		return finalRoom;
	}
	
	//Reverse given list
	public static <T> void revlist(List<T> list) {
        if (list.size() <= 1 || list == null)
            return;
       
        T value = list.remove(0);
        revlist(list);
        list.add(value);
    }
	
	public static String westRoom(Map m1) {
		String finalRoom = "";
		try { Map d = mapRooms.get(m1.getID()+1); if (d != null) { 
			Scanner r1 = new Scanner(directionRoom(m1).toString());
			Scanner r2 = new Scanner(directionRoom(d).toString());
			
			while (r1.hasNextLine()) {
				  String line = r1.nextLine();
				  String line2 = r2.nextLine();
				  
				  finalRoom = finalRoom + line + line2 + "\n";
			}
			printedRooms.add(m1);
			printedRooms.add(d);
			d.setX(d.getX()+1);
			r1.close();
			r2.close(); 
			} } catch (Exception e) { };
		return finalRoom;
	}
	
	
	//Display map
	public static void displayMap() {
		List<Map> surRooms = new ArrayList<Map>();
		List<Map> newSurRooms = new ArrayList<Map>();
		
		printedRooms.clear();
		
		Map startRoom = new Map();
		String map = "";	
		
		//Find first room
		for (Map m : mapRooms) {
			if(m != null) {
				startRoom = m;
				surRooms = getSurrounding(startRoom);
				break;
			}
		}
		
		//Update map to sur of first room
		map = mapSurRooms(startRoom, map);
		printedRooms.add(startRoom);
		
		//While there are surrounding rooms
		while (surRooms.size() != 0) {
			
			//Loop through surrounding rooms
			for(Map m : surRooms) {
				
				if(!printedRooms.contains(m)) {
					map = mapSurRooms(m, map);
				}
				
				//Loop through surrounding rooms of the surround room
				for(Map d : getSurrounding(m)) {
					
					//Check if room has already been printed
					if (!printedRooms.contains(d)) {
						
						//add new room to surrounding room list
						newSurRooms.add(d);
					}
				}
			}
			//add new surrounding rooms
			surRooms.addAll(newSurRooms);
			//remove any rooms that were already printed
			surRooms.removeAll(printedRooms);
		}
		
		System.out.println(map);
	}
	
}
