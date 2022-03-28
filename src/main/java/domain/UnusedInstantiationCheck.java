package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyFieldInsnNode;
import data_source.MyLineNumberNode;
import data_source.MyMethodNode;
import data_source.MyVarInsnNode;

public class UnusedInstantiationCheck implements ClassCheck {
	
	MyMethodNode method;
	ArrayList<MyFieldInsnNode> fieldStoring = new ArrayList<>();
	ArrayList<MyFieldInsnNode> fieldLoading = new ArrayList<>();
	
	@Override
	public String runCheck(ArrayList<MyClassNode> classes) {
		String printString = "";
		for (MyClassNode classNode : classes) {
			String classString = "";
			classString += "	Class: " + classNode.name + "\n";
			classString += this.unusedInstantiationCheck(classNode);
			if (!classString.equals("	Class: " + classNode.name + "\n")) {
				 printString += classString;
			}
		}
		
		if (printString == "") {
			return printString;
		}
		return "Unused Instantiation Check:\n" + printString;
	}
	
	private String unusedInstantiationCheck(MyClassNode classNode) {
		fieldStoring.removeAll(fieldStoring);
		fieldLoading.removeAll(fieldLoading);
		
		String varString = "";
		for (MyMethodNode method : classNode.methods) {
			varString += findVariablesMethods(method);
		}
		String printString = findUnusedFields(classNode);
		
		if (!printString.equals("")) {
			printString = "		Unused Variables: \n" + printString + varString;
		}
		return printString;
	}
	
	String findUnusedFields(MyClassNode classNode) {
		String printString = "";
		ArrayList<String> loadedNames = new ArrayList<>();
		for (MyFieldInsnNode var : fieldLoading) {
			loadedNames.add(var.name);
		}
		ArrayList<MyFieldInsnNode> unusedStored = new ArrayList<>();
		for (MyFieldInsnNode var : fieldStoring) {
			if (!loadedNames.contains(var.name)) {
				unusedStored.add(var);
			}
		}
		
		for (MyFieldInsnNode var : unusedStored) {
			boolean found = false;
			for (MyMethodNode method : classNode.methods) {
				int index = method.instructions.indexOf(var);
				int line = 0;
				if (index != -1) {
					this.method = method;
					line = findLineNumber(index);
					printString += "			Line " + line + ": Unused field named " + var.name + "\n";
					found = true;
				}
			}
			if (!found) {
				printString += "			Unknown line number: Unused field named " + var.name + "\n";
			}
		}
		
		return printString;
	}
	
	void determineFieldStatus(MyFieldInsnNode fInsn) {
		if (fInsn.isLoading()) {
			fieldLoading.add(fInsn);
		} else {						
			fieldStoring.add(fInsn);
		}
	}
	
	String findVariablesMethods(MyMethodNode method) {
		this.method = method;
		LinkedList<MyAbstractInsnNode> instructions = method.instructions;
		ArrayList<MyVarInsnNode> loading = new ArrayList<>();
		ArrayList<MyVarInsnNode> storing = new ArrayList<>();
		for (MyAbstractInsnNode insn : instructions) {
			if (insn.getType() == MyAbstractInsnNode.VAR_INSN) {
				MyVarInsnNode vInsn = (MyVarInsnNode) insn;
				if (vInsn.isLoading()) {
					loading.add(vInsn);
				} else if (vInsn.isStoring()) {
					storing.add(vInsn);
				}
			} else if (insn.getType() == MyAbstractInsnNode.FIELD_INSN) {
				MyFieldInsnNode fInsn = (MyFieldInsnNode) insn;
				determineFieldStatus(fInsn);
			}
		}
		return findUnusedVariables(loading, storing);
	}
	
	String findUnusedVariables(ArrayList<MyVarInsnNode> loaded, ArrayList<MyVarInsnNode> stored) {
		String printString = "";
		ArrayList<Integer> loadedIndexes = new ArrayList<>();
		for (MyVarInsnNode var : loaded) {
			loadedIndexes.add(var.var);
		}
		ArrayList<MyVarInsnNode> unusedStored = new ArrayList<>();
		for (MyVarInsnNode var : stored) {
			if (!loadedIndexes.contains(var.var)) {
				unusedStored.add(var);
			}
		}
		
		for (MyVarInsnNode var : unusedStored) {
			int line = findLineNumber(this.method.instructions.indexOf(var));
			if (var.var >= this.method.localVariables.size()) {
				printString += "			Line " + line + ": Unused variable in method " + this.method.name + "\n";
			} else {
				String name = this.method.localVariables.get(var.var).name;
				printString += "			Line " + line + ": Unused variable named " + name + " in method " + this.method.name + "\n";
			}
		}
		
		return printString;
	}
	
	int findLineNumber(int index) {
		MyAbstractInsnNode node = this.method.instructions.get(index);
		if (node.getType() == MyAbstractInsnNode.LINE) {
			MyLineNumberNode lNode = (MyLineNumberNode) node;
			return lNode.line;
		} else {
			return findLineNumber(index - 1);
		}
	}
	
	@Override
	public String getName() {
		return "Unused Instantiation";
	}
	
}
