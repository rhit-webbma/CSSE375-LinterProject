package data_source;

import java.util.List;

public class MyClassNode {

	public String name;
	public String superName;
	public List<String> interfaces;
	public List<MyFieldNode> fields;
	public List<MyMethodNode> methods;
	private boolean isInterface;
	
	public MyClassNode(String name, String superName, List<String> interfaces, List<MyFieldNode> fields,
			List<MyMethodNode> methods, boolean isInterface) {
		this.name = name;
		this.superName = superName;
		this.interfaces = interfaces;
		this.fields = fields;
		this.methods = methods;
		this.isInterface = isInterface;
	}
	
	public boolean isInterface() {
		return isInterface;
	}
	
}
