package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import game.Commands;

public class CommandsTest {
	private Commands commands;
	List<Element> expected_commands;
	@Before
	public void createCommands(){
		this.commands = new Commands();
		expected_commands = new ArrayList<Element>();
		expected_commands.add(new Element("command").setAttribute("type","action").setText("go front"));
		expected_commands.add(new Element("command").setAttribute("type","action").setText("go back"));
		expected_commands.add(new Element("command").setAttribute("type","information").setText("help"));
		expected_commands.add(new Element("command").setAttribute("type","information").setText("get description"));
		this.commands.setCommands(expected_commands);
		
	}
	
	@Test
	public void test_setCommands(){
		Map<String,String> expected = new HashMap<String,String>();
		for(Element tmp : expected_commands){
			expected.put(tmp.getValue(), tmp.getAttributeValue("type"));
		}
		Assert.assertEquals(expected, this.commands.getMapOfCommand());
	}
	
	@Test
	public void test_toString(){
		String str = "This is all commands that you can use for this game :\n\t- get description\n\t- go back\n\t- go front\n\t- help";
		Assert.assertEquals(str, this.commands.toString());
	}
}
