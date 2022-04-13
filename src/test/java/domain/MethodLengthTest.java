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
		ArrayList<MyClassNode> classes = new ArrayList<>();
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>(); 
		List<MyLocalVariableNode> localVars = new ArrayList<>();
	
		MyMethodNode testMethod = new MyMethodNode("TestMethod", "()V", instructionsList, localVars);
		
		List<String> interfaces = new ArrayList<>();
		List<MyFieldNode> fields = new ArrayList<>();
		List<MyMethodNode> methods = new ArrayList<>();
		
		methods.add(testMethod);
		
		MyClassNode currentClass = new MyClassNode("TestClass", "", interfaces, fields, methods, 0);
		classes.add(currentClass);
		
		//Given that this method is under length, method length should return nothing
		String expected = "";
		
		assertEquals(expected, checker.runCheck(classes));
		
	}
	
	@Test
	public void testMethodUnderLength()
	{	
		ArrayList<MyClassNode> classes = new ArrayList<>();
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>(); 
		List<MyLocalVariableNode> localVars = new ArrayList<>();
		
		
		for(int i = 0; i < 10; i++)
		{
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
		
		//Given that this method is under length, method length should return nothing
		String expected = "";
		
		assertEquals(expected, checker.runCheck(classes));
	}
	
	@Test
	public void testMethodOver35()
	{
		ArrayList<MyClassNode> classes = new ArrayList<>();
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>(); 
		List<MyLocalVariableNode> localVars = new ArrayList<>();
		
		
		for(int i = 0; i < 50; i++)
		{
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
		
		//Given that this method is under length, method length should return nothing
		String expected = "Method Length Check:\n	Class: TestClass\n" + "		Method: " + "TestMethod" + "\n";
		expected += "			Method too long: (50 lines) Shorten it to 35 lines or less. \n";
		
		assertEquals(expected, checker.runCheck(classes));
	}
	

}
