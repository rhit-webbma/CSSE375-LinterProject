package domain;

import java.util.ArrayList;
import java.util.LinkedList;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyLineNumberNode;
import data_source.MyMethodNode;

public class MethodLengthCheck implements ClassCheck {

	private final int maxLength = 35;

	@Override
	public String runCheck(ArrayList<MyClassNode> classes) {
		String printString = "";
		for (MyClassNode classNode : classes) {
			String classString = "";
			classString += "	Class: " + classNode.name + "\n";
			classString += this.methodLengthCheck(classNode);
			if (!classString.equals("	Class: " + classNode.name + "\n")) {
				 printString += classString;
			}
		}
		
		if (printString == "") {
			return printString;
		}
		return "Method Length Check:\n" + printString;
	}
	
	private String methodLengthCheck(MyClassNode classNode) {
		String classString = "";
		for (MyMethodNode method : classNode.methods) {
			String methodLength = checkLength(method);
			if (!methodLength.equals("")) {
				classString += "		Method: " + method.name + "\n";
				classString += methodLength;
			}
		}
		return classString;
	}

	private String checkLength(MyMethodNode method) {
		int length = this.getMethodLength(method);
		return (length > maxLength)
				? String.format("			Method too long: (%d lines) Shorten it to %d lines or less. \n", length,
						maxLength)
				: "";
	}

	private int getMethodLength(MyMethodNode method) {
		LinkedList<MyAbstractInsnNode> instructions = method.instructions;
		ArrayList<Integer> lines = new ArrayList<Integer>();
		int methodLength = 0;
		for (MyAbstractInsnNode insn : instructions) {
			if (insn instanceof MyLineNumberNode) {
				MyLineNumberNode ln = (MyLineNumberNode) insn;
				int line = ln.line;
				if (!lines.contains(line)) {
					lines.add(line);
					methodLength++;
				}
			}
		}
		return methodLength;
	}
	
	@Override
	public String getName() {
		return "Method Length";
	}

}
