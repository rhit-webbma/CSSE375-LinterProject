package domain;

import java.util.ArrayList;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyFieldInsnNode;
import data_source.MyFieldNode;
import data_source.MyLocalVariableNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;


public class HollywoodCheck implements ClassCheck {

	@Override
	public String runCheck(ArrayList<MyClassNode> classes) {
		String toPrint = "\nHollywood Principle Violations: \n";
		for (MyClassNode curClass : classes) {
			if (curClass.getFullSuperName() != null) {
				MyClassNode superClass = null;
				for (MyClassNode curClass2 : classes) {
					if (curClass2.getCleanName().equals(curClass.getCleanSuperName())) {
						superClass = curClass2;
					}
				}
				
				if (superClass != null) {
					ArrayList<String> curMethodNames = this.getMethodNames(curClass);
					ArrayList<String> superMethodNames = this.getMethodNames(superClass);
					for (String curName : curMethodNames) {
						superMethodNames.remove(curName);
					}
					
					ArrayList<String> curFieldNames = this.getFieldNames(curClass);
					ArrayList<String> superFieldNames = this.getFieldNames(superClass);
					for (String curName : curFieldNames) {
						superFieldNames.remove(curName);
					}
	
					toPrint += checkHollywoodViolations(curClass, superFieldNames, superMethodNames, superClass.getCleanName());
				}
			}
		}
		if (toPrint.equals("\nHollywood Principle Violations: \n")) {
			 return "";
		}
		return toPrint;
	}
	
	protected String checkHollywoodViolations(MyClassNode curClass, ArrayList<String> superFieldNames,
												ArrayList<String> superMethodNames, String superName) {
		String toPrint = "";
		for (MyMethodNode method : curClass.methods) {
			ArrayList<String> superFieldNamesTemp = superFieldNames;
			ArrayList<String> localVarNames = this.getVarNames(method);
			for (String localName : localVarNames) {
				superFieldNamesTemp.remove(localName);
			}
			for (MyAbstractInsnNode insn : method.instructions) {
				if (insn instanceof MyMethodInsnNode) {
					MyMethodInsnNode methodInsn = (MyMethodInsnNode) insn;
					if (superMethodNames.contains(methodInsn.name)) {
						toPrint += "	Class " + curClass.getCleanName() + " calls method " + methodInsn.name + " from " + 
								superName + " in method " + method.name + "\n";
					}
				} else if (insn instanceof MyFieldInsnNode) {
					MyFieldInsnNode fieldInsn = (MyFieldInsnNode) insn;
					if (superFieldNamesTemp.contains(fieldInsn.name)) {
						toPrint += "	Class " + curClass.getCleanName() + " uses field " + fieldInsn.name + " from " + 
								superName + " in method " + method.name + "\n";
					}
				}
			}
		}
		return toPrint;
	}

	private ArrayList<String> getVarNames(MyMethodNode curMethod) {
		ArrayList<String> names = new ArrayList<>();
		if (curMethod.localVariables != null) {
			for (MyLocalVariableNode var : curMethod.localVariables) {
				names.add(var.name);
			}
		}
		return names;
	}

	private ArrayList<String> getFieldNames(MyClassNode curClass) {
		ArrayList<String> names = new ArrayList<>();
		for (MyFieldNode field : curClass.fields) {
			names.add(field.name);
		}
		return names;
	}

	private ArrayList<String> getMethodNames(MyClassNode curClass) {
		ArrayList<String> names = new ArrayList<>();
		for (MyMethodNode method : curClass.methods) {
			names.add(method.name);
		}
		return names;
	}
	
	@Override
	public String getName() {
		return "Hollywood Principle";
	}
}
