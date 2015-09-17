package game;

import java.io.File;
import java.util.Scanner;

import board.Dungeon;
import board.Room;

public class MainGame {
	private Dungeon dungeon;
	private Room current_room;
	private Commands commands;
	
	public static void main(String[] args) throws Exception {
		new MainGame("dungeon1.xml").start();
	}
	
	public MainGame(Dungeon dungeon,Commands commands){
		this.dungeon = dungeon;
		this.commands = commands;
	}
	
	public MainGame(String dungeon_path) throws Exception{
		this.dungeon = new Dungeon(new File(dungeon_path));
		this.commands = new Commands(new File(dungeon_path));
		this.current_room = this.dungeon.getStartRoom();
	}
	
	public void start(){
		String line;
		Room tmp;
		System.out.println("Hello welcome to the dungeon");
		System.out.println("For see all commands write help");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while(!this.isFinish()){
			System.out.println(this.current_room.getDescription());
			System.out.println("What do you want to do ?");
			System.out.print("> ");
			while(true){
				line = scanner.nextLine();
				if(this.commands.getMapOfCommand().containsKey(line)){
					if(this.commands.getMapOfCommand().get(line).equals("information")){
						if(line.equals("help")){
							System.out.println(this.commands);
						}
						if(line.equals("get description")){
							System.out.println(this.current_room.getDescription());
						}
					}
					if(this.commands.getMapOfCommand().get(line).equals("action")){
						tmp = this.current_room.move(line);
						if(tmp != null){
							this.current_room = tmp;
							break;
						} else {
							System.out.println("Commande impossible.");
						}
					}
				}
				else{
					System.out.println("Unknow command.");
				}
				System.out.println("What do you want to do ?");
				System.out.print("> ");
			}
			if(this.isWin()){
				System.out.println("Congratulations you are out of dungeon");
			}
			else{
				if(this.current_room.isTrap()){
					System.out.println("You loose");
					System.out.println("You are fall in a trap");
				}
			}
		}
	}
	
	public boolean isFinish(){ 
		return this.current_room.isExit() || this.current_room.isTrap();
	}
	
	public boolean isWin(){
		return this.current_room.isExit();
	}
	
	public void setCurrentRoom(Room current_room){
		this.current_room = current_room;
	}
	
	public Dungeon getDungeon(){
		return this.dungeon;
	}
}
