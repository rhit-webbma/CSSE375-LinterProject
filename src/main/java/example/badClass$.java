package example;

import java.util.ArrayList;

import example.testLinters.MyFirstLinter;

public class badClass$ extends superBadClass$ {
	String string = "oh no";
	String okay = "new val";
	int NotGood = 4;
	private static final int OKAY = 3;
	int j = 0;
	String unusedString = "hello world";
	
	public badClass$(String goodString, int Ok, int p) {
		m();
	}
	
	public void BadMethodName(badClass$ badclass$) {
		int Integer = 0;
		for (int i = 0; i < 2; i++) {
			
		}
		this.noString = "newString";
		this.doNothing();
		okay = "okay";
	}
	
	public void m() {
		MyFirstLinter myfirstLinter = null;
	}
	
	public void methodWithUnusedVariables(String newName, int num) {
		String name = "name";
		int number = num;
		int newNumber = 0;
		boolean test = false;
		if (!test) {
			test = true;
		}
		newNumber = 1;
		
		//Test loop
		ArrayList<String> strings = new ArrayList<>();
		strings.add("String 1");
		strings.add("String 2");
		strings.add("String 3");
		for (int i = 0; i < strings.size(); i++) {
			int var = i;
			newNumber = var;
			String var2 = "hello";
		}
	}
	
	public int longMethod() {
		int i = 0;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		i++;
		return i;
	}
}
