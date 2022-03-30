package data_source;

public class MyFieldInsnNode extends MyAbstractInsnNode{

	public String name;
	private String owner;
	private boolean isLoading;
	private boolean isStoring;
	
	public MyFieldInsnNode(String name, String owner, boolean isLoading, boolean isStoring) {
		this.name = name;
		this.owner = owner;
		this.isLoading = isLoading;
		this.isStoring = isStoring;
	}
	
	@Override
	public int getType() {
		return super.FIELD_INSN;
	}
	
	public boolean isLoading() {
		return isLoading;
	}
	
	public boolean isStoring() {
		return isStoring;
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
