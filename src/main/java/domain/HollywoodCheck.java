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
					ArrayList<String> curMethodNames = curClass.getMethodNames();
					ArrayList<String> superMethodNames = superClass.getMethodNames();
					for (String curName : curMethodNames) {
						superMethodNames.remove(curName);
					}
					
					ArrayList<String> curFieldNames = curClass.getFieldNames();
					ArrayList<String> superFieldNames = superClass.getFieldNames();
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
			ArrayList<String> localVarNames = method.getVarNames();
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
	
	@Override
	public String getName() {
		return "Hollywood Principle";
	}
}
