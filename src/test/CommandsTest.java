package test;

import game.Command;
import game.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandsTest {
	private Commands commands;
	List<Element> expected_commands;

	@Before
	public void createCommands() {
		this.commands = new Commands();
		expected_commands = new ArrayList<Element>();
		expected_commands.add(new Element("command")
				.setAttribute("type", "movement").setAttribute("id", "1")
				.setText("go front"));
		expected_commands.add(new Element("command")
				.setAttribute("type", "movement").setAttribute("id", "2")
				.setText("go back"));
		this.commands.setCommands(expected_commands);
		expected_commands.add(new Element("command")
				.setAttribute("type", "information").setAttribute("id", "0")
				.setText("help"));
		expected_commands.add(new Element("command")
				.setAttribute("type", "information").setAttribute("id", "0")
				.setText("get description"));
	}

	@Test
	public void test_setCommands() {
		Map<String, Command> expected = new HashMap<String, Command>();
		for (Element tmp : expected_commands) {
			expected.put(
					tmp.getValue(),
					new Command(tmp.getValue(), Integer.parseInt(tmp
							.getAttributeValue("id")), tmp
							.getAttributeValue("type")));
		}
		Assert.assertEquals(expected, this.commands.getMapOfCommand());
	}

	@Test
	public void test_toString() {
		final String str = "This is all commands that you can use for this game :\n\t- get description\n\t- go back\n\t- go front\n\t- help";
		Assert.assertEquals(str, this.commands.toString());
	}

	@Test
	public void test_getIDCommand() {
		Assert.assertEquals(0, this.commands.getIDCommand("help"));
		Assert.assertEquals(1, this.commands.getIDCommand("go front"));
	}
}
