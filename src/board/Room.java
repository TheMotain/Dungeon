package board;

import java.util.HashMap;
import java.util.Map;

/**
 * Describe a Room
 * 
 * @author Alex Dalencourt
 * @author Thibault Montois
 *
 */
public class Room {
	/**
	 * Contains all connection between the instance of room and other rooms. The key is the command ID for complied a movement
	 */
	private Map<Integer, Room> connections = new HashMap<Integer, Room>();
	/**
	 * Contains the room description
	 */
	private String description;
	/**
	 * indicate if the room is an entry
	 */
	private boolean entrance;
	/**
	 * indicate if the room is an exit
	 */
	private boolean exit;
	/**
	 * indicate if the room is a trap
	 */
	private boolean trap;

	/**
	 * Use for put a description to the room
	 * @param description
	 * take a string which contains a description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void addConnection(Integer condition, Room next_room) {
		this.connections.put(condition, next_room);
	}

	public Map<Integer, Room> getConnections() {
		return this.connections;
	}

	public void setEntrance(boolean entrance) {
		this.entrance = entrance;
		if(entrance){
			this.exit = false;
			this.trap = false;
		}
	}

	public boolean isEntrance() {
		return this.entrance;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
		if(exit){
			this.entrance = false;
			this.trap = false;
		}
	}

	public boolean isExit() {
		return this.exit;
	}
	
	public void setTrap(boolean trap){
		this.trap = trap;
		if(trap){
			this.entrance = false;
			this.exit = false;
		}
	}
	
	public boolean isTrap(){
		return this.trap;
	}
	
	public Room move(int id_command){
		return this.connections.get(id_command);
	}
}
