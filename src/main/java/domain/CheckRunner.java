package domain;

import java.util.ArrayList;

import data_source.MyClassNode;

import data_source.ASMReader;
import data_source.FileWriter;

public class CheckRunner {

	private ArrayList<MyClassNode> classes;
	private ArrayList<MultiClassCheck> multiChecks;
	private ArrayList<SingleClassCheck> singleChecks;
	private FileWriter fileWriter;
	
	public CheckRunner(String[] args) {
		this.classes = ASMReader.generateClassNodes(args);
		this.multiChecks = new ArrayList<>();
		this.singleChecks = new ArrayList<>();
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
		// Style Checks
		for (MyClassNode classNode : this.classes) {
			String classString = "";
			classString += "Class: " + classNode.name + "\n";
			
			for (SingleClassCheck check : singleChecks) {
				classString += check.runCheck(classNode);
			}
			
			classString += "\n";
			if (!classString.equals("Class: " + classNode.name + "\n\n")) {
				 printString += classString;
			}
		}
		
		for (MultiClassCheck check : multiChecks) {
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
		for (SingleClassCheck check : singleChecks) {
			printString += "	" + check.getName() + "\n";
		}
		for (MultiClassCheck check : multiChecks) {
			printString += "	" + check.getName() + "\n";
		}
		return printString;
	}
	
	public void addSingleCheck(SingleClassCheck check) {
		singleChecks.add(check);
	}
	
	public void addMultiCheck(MultiClassCheck check) {
		multiChecks.add(check);
	}
	
	public void resetChecks() {
		singleChecks.removeAll(singleChecks);
		multiChecks.removeAll(multiChecks);
	}
	
}