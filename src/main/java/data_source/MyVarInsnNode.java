package data_source;

public class MyVarInsnNode extends MyAbstractInsnNode{

	public int var;
	private boolean isLoading;
	private boolean isStoring;

	public MyVarInsnNode(int var, boolean isLoading, boolean isStoring) {
		this.var = var;
		this.isLoading = isLoading;
		this.isStoring = isStoring;
	}
	
	@Override
	public int getType() {
		return super.VAR_INSN;
	}
	
	public boolean isLoading() {
		return isLoading;
	}
	
	public boolean isStoring() {
		return isStoring;
	}
}
