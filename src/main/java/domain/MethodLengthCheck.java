package domain;

import data_source.MyClassNode;
import data_source.MyMethodNode;

public class MethodLengthCheck implements SingleClassCheck {

	private final int maxLength = 35;

	@Override
	public String runCheck(MyClassNode classNode) {
		String classString = "";
		for (MyMethodNode method : classNode.methods) {
			String methodLength = checkLength(method);
			if (!methodLength.equals("")) {
				classString += "	Method: " + method.name + "\n";
				classString += methodLength;
			}
		}
		return classString;
	}

	private String checkLength(MyMethodNode method) {
		int length = method.getLength();
		return (length > maxLength)
				? String.format("		Method too long: (%d lines) Shorten it to %d lines or less. \n", length,
						maxLength)
				: "";
	}
	
	@Override
	public String getName() {
		return "Method Length";
	}

}
