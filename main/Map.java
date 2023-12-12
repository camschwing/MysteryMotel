package main;

import java.util.ArrayList;
import java.util.List;

public class Map extends MysteryMotel {

	private String strRoom;
	private String Snum;
	private Room room;
	private int id;
	public static List<Map> mapRooms = new ArrayList<Map>();
	
	public Map(int num, Room room, int x, int y) {
		this.room = room;
		
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
			System.out.println("run" + i);
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
		System.out.println(x);
		System.out.println(y);
		int id = (3*y) + x;
		return id;
	}
	
	private StringBuilder directionRoom(Map m) {
		List<Map> maps = new ArrayList<Map>();
		
		maps.addAll(mapRooms);
		
		revlist(maps);
		
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
		
		//Reset map string
		String map = "";
		
		//reverse mapRooms list to print north to south
		revlist(mapRooms);
		
		//Loop through every element in mapRooms
		for (Map m : mapRooms) {
			//Check if element is not null
			if(m != null) {
				map = map + m.directionRoom(m);
			}
		}
		System.out.println(map);
		
		//Reverse mapRooms list south to north to add elements
		revlist(mapRooms);
	}
}
