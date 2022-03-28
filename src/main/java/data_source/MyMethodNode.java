package data_source;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.Type;

public class MyMethodNode {

	public String name;
	public String desc;
	public LinkedList<MyAbstractInsnNode> instructions;
	public List<MyLocalVariableNode> localVariables;
	public List<String> argTypeNames;
	
	public MyMethodNode(String name, String desc, LinkedList<MyAbstractInsnNode> instructions,
			List<MyLocalVariableNode> localVariables) {
		this.name = name;
		this.desc = desc;
		this.instructions = instructions;
		this.localVariables = localVariables;
		this.argTypeNames = getArgTypeNames();
	}
	
	private ArrayList<String> getArgTypeNames() {
		ArrayList<String> argTypeNames = new ArrayList<>();
		Type[] argTypes = Type.getArgumentTypes(desc);
		for (Type type : argTypes) {
			argTypeNames.add(type.getInternalName());
		}
		return argTypeNames;
	}
}
