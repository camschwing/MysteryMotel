package main;

import java.util.ArrayList;
import java.util.List;

public class Map extends MysteryMotel {

	private String strRoom;
	private String Snum;
	private Room room;
	public static List<Map> mapRooms = new ArrayList<Map>();
	
	public Map(int num, Room room, int x, int y) {
		this.room = room;
		
		Snum = Integer.toString(num);
		strRoom = String.format(
					  "┌───────┐\n"
					+ "│ROOM %s│\n"
					+ "│       │\n"
					+ "│       │\n"
					+ "└───────┘\n", "0"+Snum);
		
		
		//Add filler spaces in list
		if (id(x,y)-1 > mapRooms.size() || mapRooms.size() == 0) {
			for(int i = mapRooms.size(); i < id(x,y); i++) {
				mapRooms.add(i, null);
			}
		}
		//Add room to mapRooms at index id
		if (mapRooms.get(id(x,y)-1) == null) {
		mapRooms.add(id(x,y), this);
		}
	}
	
	
	//calculate id, given x and y
	public static int id(int x, int y) {
		int id = 0;
		for (int a = 0; a < y; a++) {
			id += 3;
		}
		id += x;
		
		return id;
	}
	
	
	//Accessor methods
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
				//Get mapRoom room string
				StringBuilder printRoom = new StringBuilder(m.getRoom());
				//append maproom string to map
				map = map + printRoom;
			}
		}
		System.out.println(map);
		
		//Reverse mapRooms list south to north to add elements
		revlist(mapRooms);
	}
}
