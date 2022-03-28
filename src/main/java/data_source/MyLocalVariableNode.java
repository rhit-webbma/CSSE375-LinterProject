package data_source;

public class MyLocalVariableNode {

	public String name;
	private String desc;
	
	public MyLocalVariableNode(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	public String getFullDesc() {
		return desc;
	}
	
	public String getCleanDesc() {
		String toPrint = "";
		for (int i = 0; i < desc.length(); i++) {
			if (desc.charAt(i) == '/') {
				toPrint = "";
			} else if (desc.charAt(i) == ';') {
				
			} else {
				toPrint += desc.charAt(i);
			}
		}
		return toPrint;
	}
}
