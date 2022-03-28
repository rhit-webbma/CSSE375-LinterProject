package data_source;

import java.util.List;

import org.objectweb.asm.Opcodes;

public class MyClassNode {

	public String name;
	public String superName;
	public List<String> interfaces;
	public List<MyFieldNode> fields;
	public List<MyMethodNode> methods;
	private boolean isInterface;
	
	public MyClassNode(String name, String superName, List<String> interfaces, List<MyFieldNode> fields,
			List<MyMethodNode> methods, int accessCode) {
		this.name = name;
		this.superName = superName;
		this.interfaces = interfaces;
		this.fields = fields;
		this.methods = methods;
		
		this.isInterface = (accessCode & Opcodes.ACC_INTERFACE) != 0;
	}
	
	public boolean isInterface() {
		return isInterface;
	}
	
}
