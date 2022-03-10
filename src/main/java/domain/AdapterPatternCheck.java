package domain;

import java.util.ArrayList;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyFieldNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;

public class AdapterPatternCheck implements MultiClassCheck {

	@Override
	public String runCheck(ArrayList<MyClassNode> classes) {
		String problems = "";
		for (MyClassNode classNode : classes) {
			problems += checkAdapter(classNode);
		}
		String out = (problems.equals("")) ? "" : "\nAdapter Pattern Checker: \n" + problems;
		return out;
	}

	private String checkAdapter(MyClassNode classNode) {
		String name = getClassName(classNode);
		boolean claimsAdapter = name.toLowerCase().contains("adapter");
		ArrayList<String> interfaceNames = getInterfaces(classNode);
		ArrayList<String> fieldTypes = getFieldTypes(classNode);
		if (interfaceNames.isEmpty() || fieldTypes.isEmpty()) {
			return (claimsAdapter) ? String.format(
					"	Class %s has \"adapter\" in name, but does not implement an interface and have a field of a user defined class to adapt. \n",
					name) : "";
		}
		boolean adaptsMethods = checkMethods(classNode, fieldTypes);
		if (adaptsMethods) {
			return String.format("	Class %s uses Adapter Pattern to adapt %s to %s \n", name, nameTypes(fieldTypes),
					nameInterfaces(interfaceNames));
		} else if (claimsAdapter) {
			return String.format(
					"	Class %s has \"adapter\" in name, but has methods that are not empty or calling methods of %s \n",
					name, nameTypes(fieldTypes));
		} else
			return "";
	}

	private String getClassName(MyClassNode classNode) {
		String[] nameSplit = classNode.name.split("/");
		return nameSplit[nameSplit.length - 1];
	}

	private String nameInterfaces(ArrayList<String> interfaces) {
		int size = interfaces.size();
		String interfaceNames = (size == 1) ? "Interface " : "Interfaces ";
		for (int i = 0; i < size; i++) {
			interfaceNames += interfaces.get(i);
			if (i < size - 2)
				interfaceNames += ", ";
			else if (i < size - 1)
				interfaceNames += ", and ";
		}
		return interfaceNames;
	}

	private String nameTypes(ArrayList<String> types) {
		int size = types.size();
		String typeNames = "Class ";
		for (int i = 0; i < size; i++) {
			typeNames += types.get(i);
			if (i < size - 2)
				typeNames += ", ";
			else if (i < size - 1)
				typeNames += ", or ";
		}
		return typeNames;
	}

	private ArrayList<String> getInterfaces(MyClassNode classNode) {
		ArrayList<String> out = new ArrayList<String>();
		for (String intf : classNode.interfaces) {
			String[] name = intf.split("/");
			out.add(name[name.length - 1]);
		}
		return out;
	}

	private ArrayList<String> getFieldTypes(MyClassNode classNode) {
		ArrayList<String> out = new ArrayList<String>();
		for (MyFieldNode field : classNode.fields) {
			String[] name = field.desc.split("/|;");
			if (!name[0].contains("java"))
				out.add(name[name.length - 1]);
		}
		return out;
	}

	private boolean checkMethods(MyClassNode classNode, ArrayList<String> fieldTypes) {
		for (MyMethodNode method : classNode.methods) {
			if (!checkMethod(method, fieldTypes))
				return false;
		}
		return true;
	}

	private boolean checkMethod(MyMethodNode method, ArrayList<String> fieldTypes) {
		if (method.name.equals("<init>"))
			return true;
		int methodInsns = 0;
		boolean callsFieldType = false;
		for (MyAbstractInsnNode insn : method.instructions) {
			if (insn instanceof MyMethodInsnNode) {
				MyMethodInsnNode mi = (MyMethodInsnNode) insn;
				methodInsns++;
				String[] ownerName = mi.owner.split("/");
				callsFieldType = callsFieldType || fieldTypes.contains(ownerName[ownerName.length - 1]);
			}
		}
		return (methodInsns == 0 || callsFieldType);
	}

	@Override
	public String getName() {
		return "Adapter Pattern";
	}

}
