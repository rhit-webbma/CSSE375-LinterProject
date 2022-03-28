package data_source;

import org.objectweb.asm.Opcodes;

public class MyFieldNode {

	public String name;
	public String desc;
	private boolean isStatic;
	private boolean isFinal;
	
	public MyFieldNode(String name, String desc, int accessCode) {
		this.name = name;
		this.desc = desc;
		
		this.isStatic = (accessCode & Opcodes.ACC_STATIC) != 0;
		this.isFinal = (accessCode & Opcodes.ACC_FINAL) != 0;
	}
	
	public boolean isStatic() {
		return isStatic;
	}
	
	public boolean isFinal() {
		return isFinal;
	}
	
}
