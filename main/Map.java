package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map extends MysteryMotel {

	private String strRoom;
	private String Snum;
	private Room room;
	private int id;
	private int x;
	public static List<Map> mapRooms = new ArrayList<Map>();
	
	public Map(int num, Room room, int x, int y) {
		this.room = room;
		this.x = x;
		
		id = id(x, y);
		
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
		
		for(int i = mapRooms.size(); i <= id(x,y); i++) {
			mapRooms.add(i, null);
		}
		
		//Add room to mapRooms at index id
		if (mapRooms.get(id(x,y)-1) == null) {
		mapRooms.set(id(x,y), this);
		}
	}
	
	
	//Mutator Methods
	//calculate id, given x and y
	public static int id(int x, int y) {
		int id = (3*y) + x;
		return id;
	}
	
	private static StringBuilder directionRoom(Map m) {
		List<Map> maps = new ArrayList<Map>();
		
		maps.addAll(mapRooms);
		
		//revlist(maps);
		
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
	
	
	//Accessor methods
	private int getX() {
		return x;
	}
	private int getID() {
		return id;
	}
	private String getRoom() {
		return strRoom;
	}
	public Room getObjRoom() {
		return room;
	}
	public String getNum() {
		return Snum;
	}
	
	
	//Reverse given list
	public static <T> void revlist(List<T> list) {
        if (list.size() <= 1 || list == null)
            return;
       
        T value = list.remove(0);
        revlist(list);
        list.add(value);
    }
	
	
	//Display map
	public static void displayMap() {
		int minX = 2;
		StringBuilder printRoom = new StringBuilder();
		List<Integer> printedIDs = new ArrayList<Integer>();
		
		//Reset map string
		String map = "";		
		
		for (Map m : mapRooms) {
			if(m != null) {
				if (m.getX() < minX) {
					minX = m.getX();
				}
			}
		}
		
		for (Map m : mapRooms) {
			if(m != null && !printedIDs.contains(m.getID())) {
				
				printRoom = directionRoom(m);
				
				try { Map d = mapRooms.get(m.getID()+1); if (d != null) { 
					String finalRoom = "";
					
					Scanner r1 = new Scanner(printRoom.toString());
					Scanner r2 = new Scanner(directionRoom(d).toString());
					
					while (r1.hasNextLine()) {
						  String line = r1.nextLine();
						  String line2 = r2.nextLine();
						  
						  finalRoom = finalRoom + line + line2 + "\n";
					}
					map = map + finalRoom;
					printedIDs.add(m.getID());
					printedIDs.add(m.getID()+1);
					r1.close();
					r2.close(); 
					continue;
					} } catch (Exception e) { };
				
				try { Map a = mapRooms.get(m.getID()-3); if (a != null) { 
					String finalRoom = "";
					Scanner r1 = new Scanner(printRoom.toString());
					
					if (minX == 1 && m.getX() != 1) {
						while (r1.hasNextLine()) {
							String line = r1.nextLine();
							finalRoom = finalRoom + "         " + line + "\n";
						}
					}
					map = finalRoom + map;
					printedIDs.add(m.getID());
					r1.close();
					continue;
				} } catch (Exception e) { };
				
				if (minX == 1 && m.getX() != 1) {
					
				}
				map = map + printRoom.toString();
				printedIDs.add(m.getID());
			}
		}
		System.out.println(map);
		printedIDs.clear();
	}
}
