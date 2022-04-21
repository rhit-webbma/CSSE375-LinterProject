package presentation;

import java.util.Scanner;

public class InputManager {

	private static Scanner in;

	public static void main(String[] args) {
//		in = new Scanner(System.in);
//		boolean complete = false;
//		while (!complete) {
//			System.out.println("Would you like to input with GUI or Console? [GUI, Console]");
//			switch(in.nextLine().toLowerCase()) {
//			case "gui":
//				GUIManager guiManager = new GUIManager();
//				guiManager.filesToCheck();
//				complete = true;
//				break;
//				
//			case "console":
//				ConsoleManager consoleManager = new ConsoleManager(in);
//				consoleManager.userInterfaceLoop();
//				complete = true;
//				break;
//				
//			default:
//				System.out.println("Unrecognized command, please try again with either the command 'GUI' or 'Console'");
//				break;
//			}
//		}
		GUIManager guiManager = new GUIManager();
		guiManager.filesToCheck();
	}
}
