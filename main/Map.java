package main;

import java.io.IOException;
import java.nio.file.*;


public class Map extends MysteryMotel {
	
	Path fileName = Path.of(System.getProperty("user.dir") + "/main/.map.txt");
	String str = Files.readString(fileName);
	StringBuilder sbMap = new StringBuilder(str);
	
	public Map() throws IOException {
		resetMap();
		sbMap.setCharAt(150, '*');
	}
	
	public void resetMap() {
		sbMap.setCharAt(150, ' ');
		sbMap.setCharAt(70, ' ');
		sbMap.setCharAt(82, ' ');
	}
	
	public void updateRoom(int room) {
		resetMap();
		
		switch (room) {
		case 1: 
			sbMap.setCharAt(150, '*');
			break;
		case 2:
			sbMap.setCharAt(70, '*');
			break;
		case 3:
			sbMap.setCharAt(82, '*');
		}
	}
	
	public String displayMap() {
		return sbMap.toString();
	}
}
