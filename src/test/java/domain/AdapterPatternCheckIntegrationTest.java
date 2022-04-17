package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.easymock.asm.Opcodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyFieldNode;
import data_source.MyLocalVariableNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;

class AdapterPatternCheckIntegrationTest {

	AdapterPatternCheck checker = new AdapterPatternCheck();

	private MyClassNode createTestableClass(boolean claimsAdapter, boolean isAdapter) {
		String name = (claimsAdapter) ? "testAdapter" : "testClass";
		ArrayList<String> interfaces = new ArrayList<>();
		interfaces.add("testInterface");
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(new MyFieldNode("testField", "testFieldClass", 0));
		ArrayList<MyMethodNode> methods = (isAdapter) ? createGoodAdapterMethods() : createBadAdapterMethods();
		return new MyClassNode(name, "", interfaces, fields, methods, Opcodes.ACC_PUBLIC);
	}

	private ArrayList<MyMethodNode> createGoodAdapterMethods() {
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		methods.add(createConstructorMethod());
		methods.add(createEmptyMethod());
		MyMethodInsnNode adapterMethod = new MyMethodInsnNode("methodInsn", "testFieldClass", 0);
		LinkedList<MyAbstractInsnNode> instructions = new LinkedList<>();
		instructions.add((MyAbstractInsnNode) adapterMethod);
		List<MyLocalVariableNode> vars = new ArrayList<>();
		methods.add(new MyMethodNode("AdapterMethod", "()V", instructions, vars));
		return methods;
	}

	private ArrayList<MyMethodNode> createBadAdapterMethods() {
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		methods.add(createConstructorMethod());
		MyMethodInsnNode otherMethod = new MyMethodInsnNode("methodInsn", "otherFieldClass", 0);
		LinkedList<MyAbstractInsnNode> instructions = new LinkedList<>();
		instructions.add((MyAbstractInsnNode) otherMethod);
		List<MyLocalVariableNode> vars = new ArrayList<>();
		methods.add(new MyMethodNode("OtherMethod", "()V", instructions, vars));
		return methods;
	}

	private MyMethodNode createEmptyMethod() {
		LinkedList<MyAbstractInsnNode> instructions = new LinkedList<>();
		List<MyLocalVariableNode> vars = new ArrayList<>();
		return new MyMethodNode("TestableMethod", "()V", instructions, vars);
	}

	private MyMethodNode createConstructorMethod() {
		LinkedList<MyAbstractInsnNode> instructions = new LinkedList<>();
		List<MyLocalVariableNode> vars = new ArrayList<>();
		return new MyMethodNode("<init>", "()V", instructions, vars);
	}

	@Test
	void testClaimsAdapterIsAdapter() {
		MyClassNode curClass = createTestableClass(true, true);


		String expected = "	Class testAdapter uses Adapter Pattern to adapt Class testFieldClass to "
				+ "Interface testInterface \n";
		
		assertEquals(expected, checker.checkAdapter(curClass));
	}

	@Test
	void testClaimsAdapterNotAdapter() {
		MyClassNode curClass = createTestableClass(true, false);

		String expected = "	Class testAdapter has \"adapter\" in name, but has methods that are not empty or "
				+ "calling methods of Class testFieldClass \n";
		
		assertEquals(expected, checker.checkAdapter(curClass));
	}
	
	@Test
	void testIsAdapter() {
		MyClassNode curClass = createTestableClass(false, true);
		
		String expected = "	Class testClass uses Adapter Pattern to adapt Class testFieldClass to "
				+ "Interface testInterface \n";
		
		assertEquals(expected, checker.checkAdapter(curClass));
	}
	
	@Test
	void testNotAdapter() {
		MyClassNode curClass = createTestableClass(false, false);
		
		String expected = "";
		
		assertEquals(expected, checker.checkAdapter(curClass));
	}
	
	@Test
	void testClaimsAdapterClassNotAdapter() {
		MyClassNode curClass = new MyClassNode("BadClassAdapter", "", new ArrayList<String>(), new ArrayList<MyFieldNode>(), null, 0);
		String expected = 	"	Class BadClassAdapter has \"adapter\" in name, but does not implement an interface and have a "
				+ "field of a user defined class to adapt. \n";
		
		assertEquals(expected, checker.checkAdapter(curClass));
	}

}
