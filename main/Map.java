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
		int id = id(x, y);
		
		this.id = id;
		
		Snum = Integer.toString(num);
		
		strRoom = String.format(
					  "┌───────┐\n"
					+ "│ROOM %s│\n"
					+ "│       │\n"
					+ "│       │\n"
					+ "└───────┘\n", "0"+Snum);
		
		
		if (id(x,y)-1 > mapRooms.size() || mapRooms.size() == 0) {
			for(int i = mapRooms.size(); i < id(x,y); i++) {
				mapRooms.add(i, null);
			}
		}
		
		if (mapRooms.get(id(x,y)-1) == null) {
		mapRooms.add(id(x,y), this);
		}
		
		
		/*
		int id = id(x, y);
		
		this.id = id;
		
		int c = 0;
		
		if (mapID.size() == 0) {
			mapRooms.add(0, this);
		}
		
		for (Integer m : mapID) {
			System.out.println(m);
			if (m > id) {
				mapRooms.add(c, this);
			}
			c++;
		}
		
		mapID.add(id);
		*/
	}
	
	public static int id(int x, int y) {
		int id = 0;
		for (int a = 0; a < y; a++) {
			id += 3;
		}
		id += x;
		
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
	
	public static <T> void revlist(List<T> list) {
        if (list.size() <= 1 || list == null)
            return;
       
        T value = list.remove(0);
        revlist(list);
        list.add(value);
    }
	
	
	public static void displayMap() {
		String map = "";
		revlist(mapRooms);
		
		for (Map m : mapRooms) {
			if(m != null) {
				StringBuilder printRoom = new StringBuilder(m.getRoom());
				map = map + printRoom;
			}
		}
		System.out.println(map);
		revlist(mapRooms);
	}
}
