package data_source;

import java.util.List;

public class MyClassNode {

	private String name;
	private String superName;
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
	
	public String getFullName() {
		return name;
	}
	
	public String getCleanName() {
		String toPrint = "";
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == '/') {
				toPrint = "";
			} else if (name.charAt(i) == ';') {
				
			} else {
				toPrint += name.charAt(i);
			}
		}
		return toPrint;
	}
	
	public String getFullSuperName() {
		return superName;
	}
	
	public String getCleanSuperName() {
		String toPrint = "";
		for (int i = 0; i < superName.length(); i++) {
			if (superName.charAt(i) == '/') {
				toPrint = "";
			} else if (superName.charAt(i) == ';') {
				
			} else {
				toPrint += superName.charAt(i);
			}
		}
		return toPrint;
	}
}
