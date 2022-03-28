package data_source;

public class MyFieldNode {

	public String name;
	private String desc;
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
