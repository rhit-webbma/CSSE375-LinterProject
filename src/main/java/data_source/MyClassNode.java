package data_source;

import java.util.ArrayList;
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
	
	private String sanitizeString(String s) {
		String[] nameSplit = s.split("/");
		return nameSplit[nameSplit.length-1];
	}
	
	public String getName() {
		return sanitizeString(name);
	}
	
	public String getSuperName() {
		return sanitizeString(superName);
	}
	
	public boolean isSuperBuiltIn() {
		String[] nameSplit = superName.split("/");
		return nameSplit[1].contains("java");
	}
	
	public ArrayList<String> getInterfaces() {
		ArrayList<String> output = new ArrayList<>();
		for (String name : interfaces) {
			output.add(sanitizeString(name));
		}
		return output;
	}
	
	public ArrayList<String> getNonBuiltInFieldTypes() {
		ArrayList<String> output = new ArrayList<>();
		for (MyFieldNode field : fields) {
			if (!field.isBuiltIn()) output.add(field.getTypeName());
		}
		return output;
	}
	
	public ArrayList<String> getMethodNames() {
		ArrayList<String> output = new ArrayList<>();
		for (MyMethodNode method : methods) {
			output.add(method.name);
		}
		return output;
	}
	
	public ArrayList<String> getFieldNames() {
		ArrayList<String> output = new ArrayList<>();
		for (MyFieldNode field : fields) {
			output.add(field.name);
		}
		return output;
	}
	
	public MyMethodNode getConstructor() {
		for (MyMethodNode method : methods) {
			if (method.isConstructor()) return method;
		}
		return null;
	}
	
}
