package data_source;

public abstract class MyAbstractInsnNode {

	public final static int METHOD_INSN = 5;
	public final static int FIELD_INSN = 4;
	public final static int LINE = 15;
	public final static int VAR_INSN = 2;
	
	public abstract int getType();	
}
