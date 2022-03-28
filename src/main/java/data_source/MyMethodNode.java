package data_source;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyMethodNode {

	public String name;
	private String desc;
	public LinkedList<MyAbstractInsnNode> instructions;
	public List<MyLocalVariableNode> localVariables;
	private List<String> argTypeNames;
	
	public MyMethodNode(String name, String desc, LinkedList<MyAbstractInsnNode> instructions,
			List<MyLocalVariableNode> localVariables, List<String> argTypeNames) {
		this.name = name;
		this.desc = desc;
		this.instructions = instructions;
		this.localVariables = localVariables;
		this.argTypeNames = argTypeNames;
	}
	
	public String getFullDesc() {
		return desc;
	}
	
	public String getCleanDesc() {
		String toPrint = "";
		for (int i = 0; i < desc.length(); i++) {
			if (desc.charAt(i) == '/') {
				toPrint = "";
			} else if (desc.charAt(i) == ';') {
				
			} else {
				toPrint += desc.charAt(i);
			}
		}
		return toPrint;
	}
	
	public String getFullArgTypes() {
		return desc;
	}
	
	public List<String> getCleanArgTypes() {
		ArrayList<String> argTypes = new ArrayList<>();
		String toPrint = "";
		for (String argType : argTypeNames) {
			toPrint = "";
			for (int i = 0; i < argType.length(); i++) {
				if (argType.charAt(i) == '/') {
					toPrint = "";
				} else if (argType.charAt(i) == ';') {
					
				} else {
					toPrint += argType.charAt(i);
				}
			}
			argTypes.add(toPrint);
		}
		return argTypes;
	}
}
