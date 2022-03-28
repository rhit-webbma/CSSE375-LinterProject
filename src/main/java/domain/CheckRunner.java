package domain;

import java.util.ArrayList;

import data_source.MyClassNode;

import data_source.ASMReader;
import data_source.FileWriter;

public class CheckRunner {

	private ArrayList<MyClassNode> classes;
	private ArrayList<ClassCheck> checks;
	private FileWriter fileWriter;
	
	public CheckRunner(String[] args) {
		this.classes = ASMReader.generateClassNodes(args);
		this.checks = new ArrayList<>();
		this.fileWriter = new FileWriter("LintOutput.txt");
	}
	
	public String classNames() {
		String printString = "";
		for (int i = 0; i < classes.size(); i++) {
			printString += "	" + classes.get(i).name + "\n";
		}
		return printString;
	}
	
	public String runChecks() {
		String printString = "";

		for (ClassCheck check : checks) {
			printString += check.runCheck(this.classes);
		}
		if (printString.equals("")) {
			printString += "\nNo principle violations detected!\n";
		}
		printString = getChecks() + "\nOn classes: \n" + classNames() + "\n" + printString ;
		fileWriter.writeToFile(printString);
		return printString;
	}
	
	public String getChecks() {
		String printString = "";
		printString += "Running checks: \n";
		for (ClassCheck check : checks) {
			printString += "	" + check.getName() + "\n";
		}
		return printString;
	}
	
	
	public void addCheck(ClassCheck check) {
		checks.add(check);
	}
	
	public void resetChecks() {
		checks.removeAll(checks);
	}
	
}