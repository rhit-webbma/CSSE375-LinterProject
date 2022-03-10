package data_source;

public class MyFieldNode {

	public String name;
	public String desc;
	private boolean isStatic;
	private boolean isFinal;
	
	public MyFieldNode(String name, String desc, boolean isStatic, boolean isFinal) {
		this.name = name;
		this.desc = desc;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
	}
	
	public boolean isStatic() {
		return isStatic;
	}
	
	public boolean isFinal() {
		return isFinal;
	}
	
}
