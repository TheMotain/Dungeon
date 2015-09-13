/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import board.Dungeon;
import game.MainGame;

/**
 * @author Alex
 *
 */
public class MainGameTest {
	private MainGame controller_game;
	private Dungeon dungeon;
	
	@Before
	public void createMainGame(){
		this.dungeon = new Dungeon();
		this.dungeon.createRooms(4);
		this.dungeon.setEntrance(1);
		this.dungeon.setExit(3);
		List<Element> traps = new ArrayList<Element>();
		traps.add(new Element("trap").setAttribute("room","4").setText("true"));
		dungeon.setTraps(traps);
		this.controller_game = new MainGame(this.dungeon,null);
	}
	
	@Test
	public void test_isFinish(){
		this.controller_game.setCurrentRoom(this.dungeon.getStartRoom());
		Assert.assertFalse(this.controller_game.isFinish());
		this.controller_game.setCurrentRoom(this.dungeon.getRooms().get(2));
		Assert.assertFalse(this.controller_game.isFinish());
		this.controller_game.setCurrentRoom(this.dungeon.getRooms().get(3));
		Assert.assertTrue(this.controller_game.isFinish());
		this.controller_game.setCurrentRoom(this.dungeon.getRooms().get(4));
		Assert.assertTrue(this.controller_game.isFinish());
	}
	
	@Test
	public void test_isWin(){
		this.controller_game.setCurrentRoom(this.dungeon.getStartRoom());
		Assert.assertFalse(this.controller_game.isWin());
		this.controller_game.setCurrentRoom(this.dungeon.getRooms().get(2));
		Assert.assertFalse(this.controller_game.isWin());
		this.controller_game.setCurrentRoom(this.dungeon.getRooms().get(3));
		Assert.assertTrue(this.controller_game.isWin());
		this.controller_game.setCurrentRoom(this.dungeon.getRooms().get(4));
		Assert.assertFalse(this.controller_game.isWin());
	}
}
