package data_source;

public class MyLineNumberNode extends MyAbstractInsnNode{
	
	public int line;

	public MyLineNumberNode(int line) {
		this.line = line;
	}
	
	@Override
	public int getType() {
		return super.LINE;
	}
	
}
