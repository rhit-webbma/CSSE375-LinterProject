package data_source;

import org.objectweb.asm.Opcodes;

public class MyMethodInsnNode extends MyAbstractInsnNode{

	public String name;
	public String owner;
	private boolean invokeVirtual; 
	
	public MyMethodInsnNode(String name, String owner, int opcode) {
		this.name = name;
		this.owner = owner;
		
		this.invokeVirtual = ((opcode & Opcodes.INVOKEVIRTUAL) != 0);
	}
	
	public boolean isInvokeVirtual() {
		return invokeVirtual;
	}

	@Override
	public int getType() {
		return super.METHOD_INSN;
	}

}
