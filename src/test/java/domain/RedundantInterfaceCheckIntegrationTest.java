package domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.asm.Opcodes;
import org.junit.jupiter.api.Test;

import data_source.MyClassNode;

public class RedundantInterfaceCheckIntegrationTest {
	RedundantInterfaceCheck checker = new RedundantInterfaceCheck();

	@Test
	public void testUnimplementedInterface() {
		List<String> interfaces = new ArrayList<>();
		MyClassNode class1 = new MyClassNode("unimplemented", "", interfaces, null, null, Opcodes.ACC_INTERFACE);
		MyClassNode class2 = new MyClassNode("", "", interfaces, null, null, 0);
		MyClassNode class3 = new MyClassNode("", "", interfaces, null, null, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(class1);
		classes.add(class2);
		classes.add(class3);
		
		assertEquals("\nRedundant Interfaces: \n	0 classes use unimplemented\n", checker.runCheck(classes));
		
		
	}
	
	@Test
	public void testSingleImplementationOfInterface() {
		List<String> interfaces1 = new ArrayList<>();
		List<String> interfaces2 = new ArrayList<>();
		interfaces2.add("single");
		MyClassNode class1 = new MyClassNode("single", "", interfaces1, null, null, Opcodes.ACC_INTERFACE);
		MyClassNode class2 = new MyClassNode("", "", interfaces1, null, null, 0);
		MyClassNode class3 = new MyClassNode("", "", interfaces2, null, null, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(class1);
		classes.add(class2);
		classes.add(class3);
		
		assertEquals("\nRedundant Interfaces: \n	1 class uses single\n", checker.runCheck(classes));
	}
	
	@Test
	public void testMultipleImplementationOfInterface() {
		List<String> interfaces1 = new ArrayList<>();
		List<String> interfaces2 = new ArrayList<>();
		interfaces2.add("single");
		MyClassNode class1 = new MyClassNode("single", "", interfaces1, null, null, Opcodes.ACC_INTERFACE);
		MyClassNode class2 = new MyClassNode("", "", interfaces2, null, null, 0);
		MyClassNode class3 = new MyClassNode("", "", interfaces2, null, null, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(class1);
		classes.add(class2);
		classes.add(class3);
		
		assertEquals("", checker.runCheck(classes));
	}
	
	@Test
	public void testSingleImplementationOfInterfaceExtraInterfaces() {
		List<String> interfaces1 = new ArrayList<>();
		List<String> interfaces2 = new ArrayList<>();
		interfaces2.add("single");
		interfaces2.add("double");
		interfaces2.add("triple");
		MyClassNode class1 = new MyClassNode("single", "", interfaces1, null, null, Opcodes.ACC_INTERFACE);
		MyClassNode class2 = new MyClassNode("", "", interfaces1, null, null, 0);
		MyClassNode class3 = new MyClassNode("", "", interfaces2, null, null, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(class1);
		classes.add(class2);
		classes.add(class3);
		
		assertEquals("\nRedundant Interfaces: \n	1 class uses single\n", checker.runCheck(classes));
	}
	
	@Test
	public void testGetName() {
		assertEquals("Redundant Interfaces", checker.getName());
	}
	
}
