package data_source;

import java.util.LinkedList;
import java.util.List;

public class MyMethodNode {

	public String name;
	public String desc;
	public LinkedList<MyAbstractInsnNode> instructions;
	public List<MyLocalVariableNode> localVariables;
	public List<String> argTypeNames;
	
	public MyMethodNode(String name, String desc, LinkedList<MyAbstractInsnNode> instructions,
			List<MyLocalVariableNode> localVariables, List<String> argTypeNames) {
		this.name = name;
		this.desc = desc;
		this.instructions = instructions;
		this.localVariables = localVariables;
		this.argTypeNames = argTypeNames;
	}
}
