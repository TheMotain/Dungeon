/**
 * 
 */
package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import board.Room;

/**
 * @author Alex
 *
 */
public class RoomTest {
	private Room room;
	
	@Before
	public void createRoom(){
		this.room = new Room();
	}
	
	@Test
	public void test_setDescription(){
		String description = "this is a description";
		this.room.setDescription(description);
		Assert.assertEquals(description, this.room.getDescription());
	}
	
	@Test
	public void test_addConnection(){
		Room next_room = new Room();
		Map<Integer,Room> result = new HashMap<Integer,Room>();
		result.put(1, next_room);
		this.room.addConnection(1, next_room);
		Assert.assertEquals(result, this.room.getConnections());
	}
	
	@Test
	public void test_setEntrance(){
		this.room.setEntrance(true);
		Assert.assertTrue(this.room.isEntrance());
		Assert.assertFalse(this.room.isExit());
		Assert.assertFalse(this.room.isTrap());
	}
	
	@Test
	public void test_setExit(){
		this.room.setExit(true);
		Assert.assertTrue(this.room.isExit());
		Assert.assertFalse(this.room.isEntrance());
		Assert.assertFalse(this.room.isTrap());
	}
	
	@Test
	public void test_setTrap(){
		this.room.setTrap(true);
		Assert.assertTrue(this.room.isTrap());
		Assert.assertFalse(this.room.isEntrance());
		Assert.assertFalse(this.room.isExit());
	}
	
	@Test
	public void test_move(){
		this.room.addConnection(2, room);
		Assert.assertNotNull(this.room.move(2));
		Assert.assertNull(this.room.move(3));
	}
}
