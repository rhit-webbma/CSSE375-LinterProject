package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyFieldNode;
import data_source.MyLineNumberNode;
import data_source.MyLocalVariableNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;
import domain.MethodLengthCheck;

class MethodLengthTest {
	
	MethodLengthCheck checker = new MethodLengthCheck();
	
	@Test
	public void testMethodEmpty()
	{
//		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>(); 
		List<MyLocalVariableNode> localVars = new ArrayList<>();
		
		
//		for(int i = 0; i < 10; i++)
//		{
////			MyLineNumberNode insn = EasyMock.createMock(MyLineNumberNode.class);
////			insn.line = i;
//			MyLineNumberNode insn = new MyLineNumberNode(i);
//			MyAbstractInsnNode n1 = insn;
//			instructionsList.add(n1);	
//		}
		
		
		MyMethodNode testMethod = new MyMethodNode("TestMethod", "()V", instructionsList, localVars);
		
		List<String> interfaces = new ArrayList<>();
		List<MyFieldNode> fields = new ArrayList<>();
		List<MyMethodNode> methods = new ArrayList<>();
		
		methods.add(testMethod);
		
		MyClassNode currentClass = new MyClassNode("TestClass", "", interfaces, fields, methods, 0);
		classes.add(currentClass);
		
		
		//Mock 
//		EasyMock.expect(currentClass.getCleanName()).andReturn("TestClass");
//		EasyMock.expect(currentClass.getCleanName()).andReturn("TestClass");
//		MyClassNode currentClass = EasyMock.createMock(MyClassNode.class);
		
		
//		MyMethodNode testMethod = EasyMock.createMock(MyMethodNode.class);
//		testMethod.name = "TestMethod";
		

		
//		testMethod.instructions = instructionsList;
		
//		ArrayList<MyMethodNode> methods = new ArrayList<MyMethodNode>();
		
//		currentClass.methods = methods;
		
		//Given that this method is under length, method length should return nothing
		String expected = "";
		
		assertEquals(expected, checker.runCheck(classes));
		
//		
//		EasyMock.expect(testMethod.getLength()).andReturn(0);
//		
//		EasyMock.replay(currentClass, testMethod);
//		
//		
//		
//		assertEquals(expected, checker.runCheck(classes));
//		
//		EasyMock.verify(currentClass, testMethod);
		
	}
	
	@Test
	public void testMethodUnderLength()
	{
//		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>(); 
		List<MyLocalVariableNode> localVars = new ArrayList<>();
		
		
		for(int i = 0; i < 10; i++)
		{
//			MyLineNumberNode insn = EasyMock.createMock(MyLineNumberNode.class);
//			insn.line = i;
			MyLineNumberNode insn = new MyLineNumberNode(i);
			MyAbstractInsnNode n1 = insn;
			instructionsList.add(n1);	
		}
		
		
		MyMethodNode testMethod = new MyMethodNode("TestMethod", "()V", instructionsList, localVars);
		
		List<String> interfaces = new ArrayList<>();
		List<MyFieldNode> fields = new ArrayList<>();
		List<MyMethodNode> methods = new ArrayList<>();
		
		methods.add(testMethod);
		
		MyClassNode currentClass = new MyClassNode("TestClass", "", interfaces, fields, methods, 0);
		classes.add(currentClass);
		
		
		//Mock 
//		EasyMock.expect(currentClass.getCleanName()).andReturn("TestClass");
//		EasyMock.expect(currentClass.getCleanName()).andReturn("TestClass");
//		MyClassNode currentClass = EasyMock.createMock(MyClassNode.class);
		
		
//		MyMethodNode testMethod = EasyMock.createMock(MyMethodNode.class);
//		testMethod.name = "TestMethod";
		

		
//		testMethod.instructions = instructionsList;
		
//		ArrayList<MyMethodNode> methods = new ArrayList<MyMethodNode>();
		
//		currentClass.methods = methods;
		
		//Given that this method is under length, method length should return nothing
		String expected = "";
		
		assertEquals(expected, checker.runCheck(classes));
		
//		
//		EasyMock.expect(testMethod.getLength()).andReturn(0);
//		
//		EasyMock.replay(currentClass, testMethod);
//		
//		
//		
//		assertEquals(expected, checker.runCheck(classes));
//		
//		EasyMock.verify(currentClass, testMethod);
	}
	
//	@Test
//	public void testMethodUnderLength()
//	{
//		MyClassNode currentClass = EasyMock.createMock(MyClassNode.class);
//		ArrayList<MyClassNode> classes = new ArrayList<>();
//		classes.add(currentClass);
//		
//		EasyMock.expect(currentClass.getCleanName()).andReturn("TestClass");
//		EasyMock.expect(currentClass.getCleanName()).andReturn("TestClass");
//		
//		MyMethodNode testMethod = EasyMock.createMock(MyMethodNode.class);
//		
//		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>(); 
//		
//		testMethod.name = "TestMethod";
//		
//		testMethod.instructions = instructionsList;
//		
//		ArrayList<MyMethodNode> methods = new ArrayList<MyMethodNode>();
//		
//		methods.add(testMethod);
//		
//		currentClass.methods = methods;
//		
//		EasyMock.expect(testMethod.getLength()).andReturn(13);
//		
//		EasyMock.replay(currentClass, testMethod);
//		
//		//Given that this method is under length, method length should return nothing
//		String expected = "";
//		
//		assertEquals(expected, checker.runCheck(classes));
//		
//		EasyMock.verify(currentClass, testMethod);
//		
//	}
	
	@Test
	public void testMethodOver35()
	{
//		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>(); 
		List<MyLocalVariableNode> localVars = new ArrayList<>();
		
		
		for(int i = 0; i < 50; i++)
		{
//			MyLineNumberNode insn = EasyMock.createMock(MyLineNumberNode.class);
//			insn.line = i;
			MyLineNumberNode insn = new MyLineNumberNode(i);
			MyAbstractInsnNode n1 = insn;
			instructionsList.add(n1);	
		}
		
		
		MyMethodNode testMethod = new MyMethodNode("TestMethod", "()V", instructionsList, localVars);
		
		List<String> interfaces = new ArrayList<>();
		List<MyFieldNode> fields = new ArrayList<>();
		List<MyMethodNode> methods = new ArrayList<>();
		
		methods.add(testMethod);
		
		MyClassNode currentClass = new MyClassNode("TestClass", "", interfaces, fields, methods, 0);
		classes.add(currentClass);
		
		
		//Mock 
//		EasyMock.expect(currentClass.getCleanName()).andReturn("TestClass");
//		EasyMock.expect(currentClass.getCleanName()).andReturn("TestClass");
//		MyClassNode currentClass = EasyMock.createMock(MyClassNode.class);
		
		
//		MyMethodNode testMethod = EasyMock.createMock(MyMethodNode.class);
//		testMethod.name = "TestMethod";
		

		
//		testMethod.instructions = instructionsList;
		
//		ArrayList<MyMethodNode> methods = new ArrayList<MyMethodNode>();
		
//		currentClass.methods = methods;
		
		//Given that this method is under length, method length should return nothing
		String expected = "Method Length Check:\n	Class: TestClass\n" + "		Method: " + "TestMethod" + "\n";
		expected += "			Method too long: (50 lines) Shorten it to 35 lines or less. \n";
		
		assertEquals(expected, checker.runCheck(classes));
		
//		
//		EasyMock.expect(testMethod.getLength()).andReturn(0);
//		
//		EasyMock.replay(currentClass, testMethod);
//		
//		
//		
//		assertEquals(expected, checker.runCheck(classes));
//		
//		EasyMock.verify(currentClass, testMethod);
	}
	
//	@Test
//	public void testMethodOver35()
//	{
//		MyClassNode currentClass = EasyMock.createMock(MyClassNode.class);
//		ArrayList<MyClassNode> classes = new ArrayList<>();
//		classes.add(currentClass);
//		
//		EasyMock.expect(currentClass.getCleanName()).andReturn("TestClass");
//		EasyMock.expect(currentClass.getCleanName()).andReturn("TestClass");
//		
//		MyMethodNode testMethod = EasyMock.createMock(MyMethodNode.class);
//		
//		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>(); 
//		
//		testMethod.name = "TestMethod";
//		
//		
//		testMethod.instructions = instructionsList;
//		
//		ArrayList<MyMethodNode> methods = new ArrayList<MyMethodNode>();
//		
//		methods.add(testMethod);
//		
//		currentClass.methods = methods;
//
//		EasyMock.expect(testMethod.getLength()).andReturn(40);
//		
//		EasyMock.replay(currentClass, testMethod);
//		
//		String expected = "Method Length Check:\n	Class: TestClass\n" + "		Method: " + "TestMethod" + "\n";
//		expected += "			Method too long: (40 lines) Shorten it to 35 lines or less. \n";
//		
//		assertEquals(expected, checker.runCheck(classes));;
//		
//	}

}
