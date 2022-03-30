package data_source;

public class MyMethodInsnNode extends MyAbstractInsnNode{

	public String name;
	private String owner;
	private boolean invokeVirtual; 
	
	public MyMethodInsnNode(String name, String owner, boolean invokeVirtual) {
		this.name = name;
		this.owner = owner;
		this.invokeVirtual = invokeVirtual;
	}
	
	public boolean isInvokeVirtual() {
		return invokeVirtual;
	}
	
	public String getOwnerName() {
		String[] nameSplit = owner.split("/");
		return nameSplit[nameSplit.length-1];
	}

	@Override
	public int getType() {
		return super.METHOD_INSN;
	}
	
	public String getFullOwner() {
		return owner;
	}
	
	public String getCleanOwner() {
		String toPrint = "";
		for (int i = 0; i < owner.length(); i++) {
			if (owner.charAt(i) == '/') {
				toPrint = "";
			} else if (owner.charAt(i) == ';') {
				
			} else {
				toPrint += owner.charAt(i);
			}
		}
		return toPrint;
	}

}
