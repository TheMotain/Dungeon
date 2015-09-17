package test;

import game.Command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author dalencourt
 *
 */
public class CommandTest {
	private Command command;
	
	@Before
	public void createCommand(){
		this.command = new Command("go front", 1, "movement");
	}
	
	@Test
	public void test_initCommand(){
		Assert.assertEquals("go front", this.command.getDescription());
		Assert.assertEquals(1, this.command.getID());
		Assert.assertEquals("movement", this.command.getType());
	}
}
