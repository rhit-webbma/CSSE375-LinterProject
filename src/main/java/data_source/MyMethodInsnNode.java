package data_source;

public class MyMethodInsnNode extends MyAbstractInsnNode{

	public String name;
	public String owner;
	private boolean invokeVirtual; 
	
	public MyMethodInsnNode(String name, String owner, boolean invokeVirtual) {
		this.name = name;
		this.owner = owner;
		this.invokeVirtual = invokeVirtual;
	}
	
	public boolean isInvokeVirtual() {
		return invokeVirtual;
	}

	@Override
	public int getType() {
		return super.METHOD_INSN;
	}

}
