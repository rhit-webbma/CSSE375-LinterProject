package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.asm.Opcodes;
import org.junit.jupiter.api.BeforeEach;
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
	ArrayList<MyClassNode> classes;
	
	public MyMethodNode createTestableMethod(int numberOfLines)
	{
		LinkedList<MyAbstractInsnNode> instructionsList = this.populateMethodInstructions(numberOfLines); 
		List<MyLocalVariableNode> localVars = new ArrayList<>();
		
		return new MyMethodNode("TestableMethod", "()V", instructionsList, localVars);
	}
	
	public MyClassNode createMethodLengthClass(MyMethodNode methodPassIn)
	{
		List<String> interfaces = new ArrayList<>();
		List<MyFieldNode> fields = new ArrayList<>();
		List<MyMethodNode> methods = new ArrayList<>();
		
		methods.add(methodPassIn);
		
		return new MyClassNode("TestableClass", "", interfaces, fields, methods, Opcodes.ACC_PUBLIC);
	}
	
	public LinkedList<MyAbstractInsnNode> populateMethodInstructions(int numberOfLines)
	{
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>();
		
		for(int i = 0; i < numberOfLines; i++)
		{
			MyLineNumberNode insn = new MyLineNumberNode(i);
			MyAbstractInsnNode n1 = insn;
			instructionsList.add(n1);	
		}
		
		return instructionsList;
	}
	
	@BeforeEach
	void init()
	{
		classes = new ArrayList<>();
	}
	
	@Test
	public void testMethodEmpty()
	{	
		
		MyMethodNode testMethod = this.createTestableMethod(0);
		MyClassNode currentClass = this.createMethodLengthClass(testMethod);
		
		classes.add(currentClass);
		
		//Given that this method is under length, method length should return nothing
		String expected = "";
		
		assertEquals(expected, checker.runCheck(classes));
		
	}
	
	@Test
	public void testMethodUnderLength()
	{	
		MyMethodNode testMethod = this.createTestableMethod(10);
		MyClassNode currentClass = this.createMethodLengthClass(testMethod);
		classes.add(currentClass);
		
		//Given that this method is under length, method length should return nothing
		String expected = "";
		
		assertEquals(expected, checker.runCheck(classes));
	}
	
	@Test
	public void testMethodOver35()
	{			
		MyMethodNode testMethod = this.createTestableMethod(50);
		MyClassNode currentClass = this.createMethodLengthClass(testMethod);
		classes.add(currentClass);
		
		//Given that this method is under length, method length should return nothing
		String expected = "Method Length Check:\n	Class: TestableClass\n" + "		Method: " + "TestableMethod" + "\n";
		expected += "			Method too long: (50 lines) Shorten it to 35 lines or less. \n";
		
		assertEquals(expected, checker.runCheck(classes));
	}
	

}
