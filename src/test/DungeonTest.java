/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import board.Dungeon;
import board.Room;

/**
 * @author Alex
 *
 */
public class DungeonTest {
	private Dungeon dungeon;

	@Before
	public void createDungon() {
		this.dungeon = new Dungeon();
		this.dungeon.createRooms(4);
		this.dungeon.setEntrance(1);
		this.dungeon.setExit(3);
		List<Element> links = new ArrayList<Element>();
		links.add(new Element("link").setAttribute("current_room", "1").setAttribute("next_room", "2")
				.setText("go front"));
		links.add(new Element("link").setAttribute("current_room", "2").setAttribute("next_room", "3")
				.setText("go east"));
		links.add(new Element("link").setAttribute("current_room", "2").setAttribute("next_room", "4")
				.setText("go west"));
		links.add(new Element("link").setAttribute("current_room", "2").setAttribute("next_room", "1")
				.setText("go back"));
		this.dungeon.setLinks(links);
		List<Element> traps = new ArrayList<Element>();
		traps.add(new Element("trap").setAttribute("room","4").setText("true"));
		this.dungeon.setTraps(traps);
		List<Element> descriptions = new ArrayList<Element>();
		descriptions.add(new Element("room").setAttribute("room", "1").setText("Entrance"));
		descriptions.add(new Element("room").setAttribute("room", "2").setText("Intersection"));
		descriptions.add(new Element("room").setAttribute("room", "3").setText("Exit"));
		descriptions.add(new Element("room").setAttribute("room", "4").setText("Trap"));
		this.dungeon.setRoomsDescription(descriptions);
	}

	@Test
	public void test_createRooms() {
		Set<Integer> rooms_expected = new HashSet<Integer>();
		rooms_expected.add(1);
		rooms_expected.add(2);
		rooms_expected.add(3);
		rooms_expected.add(4);
		Assert.assertEquals(rooms_expected.size(), dungeon.getRooms().size());
		Assert.assertEquals(rooms_expected, dungeon.getRooms().keySet());
	}
	
	@Test
	public void test_setEntrance(){
		Assert.assertTrue(this.dungeon.getRooms().get(1).isEntrance());
		Assert.assertEquals(this.dungeon.getRooms().get(1), this.dungeon.getStartRoom());
		Assert.assertFalse(this.dungeon.getRooms().get(2).isEntrance());
		Assert.assertFalse(this.dungeon.getRooms().get(3).isEntrance());
		Assert.assertFalse(this.dungeon.getRooms().get(4).isEntrance());
	}
	
	@Test
	public void test_setExit(){
		Assert.assertTrue(this.dungeon.getRooms().get(3).isExit());
		Assert.assertFalse(this.dungeon.getRooms().get(1).isExit());
		Assert.assertFalse(this.dungeon.getRooms().get(2).isExit());
		Assert.assertFalse(this.dungeon.getRooms().get(4).isExit());
	}

	@Test
	public void test_setLinks() {
		Map<Integer, Room> rooms = this.dungeon.getRooms();
		Map<String, Room> links_expected = new HashMap<String, Room>();
		links_expected.put("go front", rooms.get(2));
		Assert.assertEquals(links_expected, this.dungeon.getRooms().get(1).getConnections());
		links_expected.clear();
		links_expected.put("go east", rooms.get(3));
		links_expected.put("go back", rooms.get(1));
		links_expected.put("go west", rooms.get(4));
		Assert.assertEquals(links_expected, this.dungeon.getRooms().get(2).getConnections());
		links_expected.clear();
		Assert.assertEquals(links_expected, this.dungeon.getRooms().get(3).getConnections());
	}

	@Test
	public void test_setTraps(){
		Assert.assertTrue(this.dungeon.getRooms().get(4).isTrap());
		Assert.assertFalse(this.dungeon.getRooms().get(1).isTrap());
		Assert.assertFalse(this.dungeon.getRooms().get(2).isTrap());
		Assert.assertFalse(this.dungeon.getRooms().get(3).isTrap());
	}
	
	@Test
	public void test_setDescriptions() {
		Assert.assertEquals("Entrance", this.dungeon.getRooms().get(1).getDescription());
		Assert.assertEquals("Intersection", this.dungeon.getRooms().get(2).getDescription());
		Assert.assertEquals("Exit", this.dungeon.getRooms().get(3).getDescription());
		Assert.assertEquals("Trap", this.dungeon.getRooms().get(4).getDescription());
	}
}
