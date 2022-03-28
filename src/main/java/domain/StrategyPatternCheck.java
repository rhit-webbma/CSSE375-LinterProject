package domain;

import java.util.ArrayList;
import java.util.List;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyFieldInsnNode;
import data_source.MyFieldNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;

public class StrategyPatternCheck implements ClassCheck {

	/* Things I need to check to detect a strategy pattern:
	 * There is a class that has another class dependency injected into it
	 * 	(for DI check out fields in <init> and compare with globals)
	 * The injected class is an interface (check has access Opcodes.ACC_INTERFACE)
	 * The original class uses a method from that interface (maybe owner will work for this)
	*/ 
	
	@Override
	public String runCheck(ArrayList<MyClassNode> classes) {
		String toPrint = "\nStrategy Pattern Implementations: \n";
		for (MyClassNode curClass : classes) {
			MyMethodNode constructor = this.getConstructor(curClass.methods);
			if (constructor != null) {
				// Setup
				List<MyFieldNode> fields = curClass.fields;
				List<String> argTypes = constructor.getCleanArgTypes();
				
				
				// Find fields that are getting set in the constructor, build up arraylists of the fields themselves and their type names
				ArrayList<String> constructedFieldTypes = new ArrayList<>();
				for (MyAbstractInsnNode insn : constructor.instructions) {
					if (insn instanceof MyFieldInsnNode) {
						MyFieldInsnNode fieldInsn = (MyFieldInsnNode) insn;
						for (MyFieldNode field : fields) {
							if (field.name.equals(fieldInsn.name)) {
								if (argTypes.contains(field.getCleanDesc())) {
									constructedFieldTypes.add(field.getCleanDesc());
								}
							}
						}
					}
				}
				
				// Find, from the field list constructed above, which of those are interfaces
				ArrayList<String> interfaceConstructedFields = new ArrayList<>();
				for (int i = 0; i < constructedFieldTypes.size(); i++) {
					MyClassNode fieldClass = null;
					for (MyClassNode otherClass : classes) {
						if (constructedFieldTypes.get(i).equals(otherClass.getCleanName())) {
							fieldClass = otherClass;
						}
					}
					
					if (fieldClass != null) {
						if (fieldClass.isInterface()) {
							interfaceConstructedFields.add(constructedFieldTypes.get(i));
						}
					}
				}
				
				
				// Find if the curClass ever calls a method from any of the items in interfaceConstructedFields
				// If so, add the strategy pattern implementation to the list
				boolean strategyDone = false;
				for (String icf : interfaceConstructedFields) {
					strategyDone = false;
					for (MyMethodNode method : curClass.methods) {
						if (!strategyDone) {
							for (MyAbstractInsnNode insn : method.instructions) {
								if ((insn instanceof MyMethodInsnNode) && (!strategyDone)) {
									MyMethodInsnNode methodInsn = (MyMethodInsnNode) insn;
									if ((icf.equals(methodInsn.getCleanOwner()))
											&& (methodInsn.isInvokeVirtual())) {
										toPrint += "	Implemented in " + curClass.getCleanName()
													+ " class using interface " + icf + ", and invoked for the first time in method "
													+ method.name + "\n";
										strategyDone = true;
									}
								}
							}
						}
					}
					if (!strategyDone) {
						toPrint += "	Strategy pattern is nearly implemented in " + curClass.getCleanName()
									+ " using interface " + icf + ". To finish implementing strategy pattern, the function/functions "
											+ "called from the interface must be used\n";
					}
				}
			}
		}
		if (toPrint.equals("\nStrategy Pattern Implementations: \n")) {
			 return "";
		}
		return toPrint;
	}
	
	private MyMethodNode getConstructor(List<MyMethodNode> methods) {
		for (MyMethodNode method : methods) {
			if (method.name.equals("<init>")) {
				return method;
			}
		}
		return null;
	}
	
	@Override
	public String getName() {
		return "Strategy Pattern";
	}

}
