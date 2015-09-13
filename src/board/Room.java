package board;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
 *
 */
public class Room {
	private Map<String, Room> connections = new HashMap<String, Room>();
	private String description;
	private boolean entrance;
	private boolean exit;
	private boolean trap;

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void addConnection(String condition, Room next_room) {
		this.connections.put(condition, next_room);
	}

	public Map<String, Room> getConnections() {
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
	
	public Room move(String command){
		return this.connections.get(command);
	}
}
