package board;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * Class which load and stock a dungeon
 * 
 * @author Alex Dalencourt
 * @author Thibault Montois
 * 
 * 
 */
public class Dungeon {
	/**
	 * Contains all room of the dungeon
	 */
	private Map<Integer, Room> rooms = new HashMap<Integer, Room>();
	/**
	 * Remember the dungeon entrance
	 */
	private Room start_room;

	/**
	 * Standard constructor with no parameter just for test the class
	 */
	public Dungeon() {
	}

	/**
	 * Second constructor which take a xml file in parameter
	 * 
	 * @param file
	 *            the xml file which contains description of dungeon
	 * @throws Exception
	 *             Throw an exception when a error occurred during the parsing
	 */
	public Dungeon(File file) throws Exception {
		SAXBuilder saxb = new SAXBuilder();
		Document document = null;
		Element root = null;
		try {
			document = saxb.build(file);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception(
					"The input xml file contains error and he can not be parsed");
		}
		root = document.getRootElement();
		this.createRooms(Integer.parseInt(root.getChild("number_of_room")
				.getValue()));
		this.setEntrance(Integer.parseInt(root.getChild("entrance")
				.getAttributeValue("room")));
		this.setExit(Integer.parseInt(root.getChild("exit").getAttributeValue(
				"room")));
		this.setLinks(root.getChild("link_between_room").getChildren("link"));
		this.setTraps(root.getChild("traps").getChildren("trap"));
		this.setRoomsDescription(root.getChild("room_description").getChildren(
				"room"));
	}

	/**
	 * This method set the entrance of the dungeon
	 * 
	 * @param entrance
	 *            Take the ID of the entrance room
	 */
	public void setEntrance(Integer entrance) {
		this.rooms.get(entrance).setEntrance(true);
		this.start_room = this.rooms.get(entrance);
	}

	/**
	 * This method set the exit of the dungeon
	 * 
	 * @param exit
	 *            Take the ID of the exit room
	 */
	public void setExit(Integer exit) {
		this.rooms.get(exit).setExit(true);
	}

	/**
	 * This method set all links between rooms
	 * 
	 * @param links
	 *            Take a list of xml elements with three attribute:
	 *            current_room, id_command and next_room
	 */
	public void setLinks(List<Element> links) {
		Room room = null;
		for (Element tmp : links) {
			room = this.rooms.get(Integer.parseInt(tmp
					.getAttributeValue("current_room")));
			room.addConnection(Integer.parseInt(tmp
					.getAttributeValue("id_command")), this.rooms.get(Integer
					.parseInt(tmp.getAttributeValue("next_room"))));
		}
	}

	/**
	 * This method set all trap rooms
	 * 
	 * @param traps
	 *            take a list of xml element that contains just one attribute:
	 *            the id of trap room call "room"
	 */
	public void setTraps(List<Element> traps) {
		for (Element tmp : traps) {
			this.rooms.get(Integer.parseInt(tmp.getAttributeValue("room")))
					.setTrap(true);
		}
	}

	/**
	 * This method set room description
	 * 
	 * @param descriptions
	 *            take a list of xml element where the value is the description
	 *            and the unique attribute call room contains the room id
	 */
	public void setRoomsDescription(List<Element> descriptions) {
		for (Element tmp : descriptions) {
			this.rooms.get(Integer.parseInt(tmp.getAttributeValue("room")))
					.setDescription(tmp.getValue());
		}
	}

	/**
	 * Create a new room with specific id
	 * 
	 * @param number
	 *            ID of the room
	 */
	public void createRooms(int number) {
		for (int i = 1; i <= number; i++) {
			rooms.put(i, new Room());
		}
	}

	/**
	 * Return all rooms of dungeon
	 * 
	 * @return return a map where the key is a room ID and the value is a room
	 */
	public Map<Integer, Room> getRooms() {
		return this.rooms;
	}

	/**
	 * Return entrance of dungeon
	 * 
	 * @return Return a room
	 */
	public Room getStartRoom() {
		return this.start_room;
	}
}
