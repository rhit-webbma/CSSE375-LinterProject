package domain;

import java.util.ArrayList;

import data_source.MyClassNode;

public interface MultiClassCheck {

	public String runCheck(ArrayList<MyClassNode> classes);
	public String getName();
	
}
