package data_source;

public class MyLocalVariableNode {

	public String name;
	public String desc;
	
	public MyLocalVariableNode(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	public String getTypeName() {
		String[] nameSplit = desc.split("/");
		return nameSplit[nameSplit.length-1];
	}
}
