package board;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * @author Alex
 *
 */
public class Dungeon {
	private Map<Integer, Room> rooms = new HashMap<Integer, Room>();
	private Room start_room;

	public Dungeon() {
	}

	public Dungeon(File file) throws Exception {
		SAXBuilder saxb = new SAXBuilder();
		Document document = null;
		Element root = null;
		try {
			document = saxb.build(file);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("The input xml file contains error and he can not be parsed");
		}
		root = document.getRootElement();
		this.createRooms(Integer.parseInt(root.getChild("number_of_room").getValue()));
		this.setEntrance(Integer.parseInt(root.getChild("entrance").getAttributeValue("room")));
		this.setExit(Integer.parseInt(root.getChild("exit").getAttributeValue("room")));
		this.setLinks(root.getChild("link_between_room").getChildren("link"));
		this.setTraps(root.getChild("traps").getChildren("trap"));
		this.setRoomsDescription(root.getChild("room_description").getChildren("room"));
	}

	public void setEntrance(Integer entrance) {
		this.rooms.get(entrance).setEntrance(true);
		this.start_room = this.rooms.get(entrance);
	}

	public void setExit(Integer exit) {
		this.rooms.get(exit).setExit(true);
	}

	public void setLinks(List<Element> links) {
		Room room = null;
		for (Element tmp : links) {
			room = this.rooms.get(Integer.parseInt(tmp.getAttributeValue("current_room")));
			room.addConnection(tmp.getValue(), this.rooms.get(Integer.parseInt(tmp.getAttributeValue("next_room"))));
		}
	}

	public void setTraps(List<Element> traps) {
		for (Element tmp : traps) {
			this.rooms.get(Integer.parseInt(tmp.getAttributeValue("room"))).setTrap(true);
		}
	}

	public void setRoomsDescription(List<Element> descriptions) {
		for(Element tmp : descriptions){
			this.rooms.get(Integer.parseInt(tmp.getAttributeValue("room"))).setDescription(tmp.getValue());
		}
	}

	public void createRooms(int number) {
		for (int i = 1; i <= number; i++) {
			rooms.put(i, new Room());
		}
	}

	public Map<Integer, Room> getRooms() {
		return this.rooms;
	}
	
	public Room getStartRoom(){
		return this.start_room;
	}
}
