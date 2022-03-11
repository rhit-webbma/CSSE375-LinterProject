package presentation;

import java.util.ArrayList;
import java.util.Scanner;

import domain.AdapterPatternCheck;
import domain.CheckRunner;
import domain.CompositionCheck;
import domain.FacadePatternCheck;
import domain.HollywoodCheck;
import domain.MethodLengthCheck;
import domain.NamesCheck;
import domain.RedundantInterfaceCheck;
import domain.StrategyPatternCheck;
import domain.UnusedInstantiationCheck;

public class ConsoleManager {

	public static void main(String[] args) {
		userInterfaceLoop(args);
	}
	
	private static void userInterfaceLoop(String[] args) {
		CheckRunner runner = new CheckRunner(args);
		System.out.println("Classes inputted: \n" + runner.classNames());
		
		Scanner consoleReader = new Scanner(System.in);
		ArrayList<String> added = new ArrayList<>();
		boolean complete = false;
		while (!complete) {
			System.out.print("Input the name of the check you would like to run:");
			String checkName = consoleReader.next().toLowerCase();
			switch (checkName) {
			case "help":
				System.out.println("Possible commands:\n"
						+ "'all' : Runs all of the checks listed below\n"
						+ "'name' : Checks for name convention violations in classes, methods, and fields\n"
						+ "'instantiation' : Checks for variables and fields that are instantiated but never used\n"
						+ "'length' : Checks for methods that are too long\n"
						+ "'hollywood' : Checks for violations of the Hollywood Principle\n"
						+ "'composition' : Checks for places where composition could be used instead of inheritance\n"
						+ "'interface' : Checks for interfaces that might be redundant\n"
						+ "'pattern' : Detects use of Strategy, Adapter, and Facade patterns\n"
						+ "'run' : Runs the checks that have been added\n"
						+ "'done' : Exits the program");
				break;
				
			case "all":
				runner.resetChecks();
				runner.addSingleCheck(new NamesCheck());
				runner.addSingleCheck(new UnusedInstantiationCheck());
				runner.addSingleCheck(new MethodLengthCheck());
				runner.addMultiCheck(new HollywoodCheck());
				runner.addMultiCheck(new CompositionCheck());
				runner.addMultiCheck(new RedundantInterfaceCheck());
				runner.addMultiCheck(new StrategyPatternCheck());
				runner.addMultiCheck(new FacadePatternCheck());
				runner.addMultiCheck(new AdapterPatternCheck());
				added.add("all");
				break;
				
			case "name":
				if (!added.contains("name") && !added.contains("all")) {
					runner.addSingleCheck(new NamesCheck());
					added.add("name");
				} else {
					System.out.println("This check has already been added.");
				}
				break;
				
			case "instantiation":
				if (!added.contains("instantiation") && !added.contains("all")) {
					runner.addSingleCheck(new UnusedInstantiationCheck());
					added.add("instantiation");
				} else {
					System.out.println("This check has already been added.");
				}
				break;
				
			case "length":
				if (!added.contains("length") && !added.contains("all")) {
					runner.addSingleCheck(new MethodLengthCheck());
					added.add("length");
				} else {
					System.out.println("This check has already been added.");
				}
				break;
				
			case "hollywood":
				if (!added.contains("hollywood") && !added.contains("all")) {
					runner.addMultiCheck(new HollywoodCheck());
					added.add("hollywood");
				} else {
					System.out.println("This check has already been added.");
				}
				break;
				
			case "composition":
				if (!added.contains("composition") && !added.contains("all")) {
					runner.addMultiCheck(new CompositionCheck());
					added.add("composition");
				} else {
					System.out.println("This check has already been added.");
				}
				break;
				
			case "interface":
				if (!added.contains("interface") && !added.contains("all")) {
					runner.addMultiCheck(new RedundantInterfaceCheck());
					added.add("interface");
				} else {
					System.out.println("This check has already been added.");
				}
				break;
				
			case "pattern":
				if (!added.contains("pattern") && !added.contains("all")) {
					runner.addMultiCheck(new StrategyPatternCheck());
					runner.addMultiCheck(new FacadePatternCheck());
					runner.addMultiCheck(new AdapterPatternCheck());
					added.add("pattern");
				} else {
					System.out.println("This check has already been added.");
				}
				break;
				
			case "run":
				System.out.println(runner.runChecks());
				runner.resetChecks();
				added.removeAll(added);
				break;
				
			case "done":
				complete = true;
				break;
			
			default:
				System.out.println("Not a valid input, type 'help' for valid inputs: ");
			}
		}
	}
	
	
	
}