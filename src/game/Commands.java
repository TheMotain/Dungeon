package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
public class Commands {
	private Map<String,String> commands;
	
	public Commands(){
		this.commands = new HashMap<String,String>();
	}
	
	
	public Commands(File input) throws Exception{
		this();
		SAXBuilder saxb = new SAXBuilder();
		Document doc;
		try {
			doc = saxb.build(input);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("The input xml file contains error and he can not be parsed");
		}
		Element root = doc.getRootElement();
		this.setCommands(root.getChildren("commands"));
	}
	
	public void setCommands(List<Element> commands){
		for(Element tmp : commands){
			this.commands.put(tmp.getValue(), tmp.getAttributeValue("type"));
		}
	}


	public Map<String,String> getMapOfCommand() {
		return this.commands;
	}
	
	public String toString(){
		String str = "This is all commands that you can use for this game :";
		List<String> sorted_commands = new ArrayList<String>();
		for(String tmp : this.commands.keySet()){
			sorted_commands.add(tmp);
		}
		Collections.sort(sorted_commands);
		for(String tmp : sorted_commands){
			str += "\n\t- " + tmp;
		}
		return str;
	}
}
