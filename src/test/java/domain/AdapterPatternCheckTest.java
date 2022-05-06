package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;

class AdapterPatternCheckTest {
	
	ArrayList<String> types;
	MyMethodNode method;
	ArrayList<MyMethodInsnNode> instructions;
	AdapterPatternCheck check;
	
	@BeforeEach
	void init() {
		types = new ArrayList<>();
		types.add("ArrayList");
		method = EasyMock.createMock(MyMethodNode.class);
		method.name = "lol";
		instructions = new ArrayList<MyMethodInsnNode>();
		check = new AdapterPatternCheck();
	}

	@Test
	void testNameOneInterface() {
		ArrayList<String> interfaceNames = new ArrayList<>();
		interfaceNames.add("interface1");
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertEquals("Interface interface1", check.nameInterfaces(interfaceNames));
	}
	
	@Test
	void testNameTwoInterfaces() {
		ArrayList<String> interfaceNames = new ArrayList<>();
		interfaceNames.add("interface1");
		interfaceNames.add("interface2");
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertEquals("Interfaces interface1, and interface2", check.nameInterfaces(interfaceNames));
	}
	
	@Test
	void testNameManyInterfaces() {
		ArrayList<String> interfaceNames = new ArrayList<>();
		interfaceNames.add("interface1");
		interfaceNames.add("interface2");
		interfaceNames.add("interface3");
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertEquals("Interfaces interface1, interface2, and interface3", check.nameInterfaces(interfaceNames));
	}
	
	@Test
	void testNameOneType() {
		ArrayList<String> classNames = new ArrayList<>();
		classNames.add("class1");
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertEquals("Class class1", check.nameTypes(classNames));
	}
	
	@Test
	void testNameTwoTypes() {
		ArrayList<String> classNames = new ArrayList<>();
		classNames.add("class1");
		classNames.add("class2");
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertEquals("Class class1, or class2", check.nameTypes(classNames));
	}
	
	@Test
	void testNameManyTypes() {
		ArrayList<String> classNames = new ArrayList<>();
		classNames.add("class1");
		classNames.add("class2");
		classNames.add("class3");
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertEquals("Class class1, class2, or class3", check.nameTypes(classNames));
	}
	
	@Test
	void testCheckMethodInit() {
		MyMethodNode method = EasyMock.createMock(MyMethodNode.class);
		method.name = "<init>";
		AdapterPatternCheck check = new AdapterPatternCheck();
		EasyMock.expect(method.isConstructor()).andReturn(true);
		EasyMock.replay(method);
		assertTrue(check.checkMethod(method, null));
		EasyMock.verify(method);
	}
	
	@Test
	void testCheckMethodEmpty() {
		MyMethodNode method = EasyMock.createMock(MyMethodNode.class);
		method.name = "lol";
		method.instructions = new LinkedList<MyAbstractInsnNode>();
		EasyMock.expect(method.isConstructor()).andReturn(true);
		AdapterPatternCheck check = new AdapterPatternCheck();
		EasyMock.replay(method);
		assertTrue(check.checkMethod(method, null));
		EasyMock.verify(method);
	}
	
	@Test
	void testCheckMethodGood() {
		MyMethodInsnNode insn = EasyMock.createMock(MyMethodInsnNode.class);
		EasyMock.expect(insn.getCleanOwner()).andReturn("ArrayList");
		instructions.add(insn);
		EasyMock.expect(method.isConstructor()).andReturn(false);
		EasyMock.expect(method.getMethodInstructions()).andReturn(instructions);
		EasyMock.replay(insn, method);
		assertTrue(check.checkMethod(method, types));
		EasyMock.verify(insn, method);
	}
	
	@Test
	void testCheckMethodGoodMultipleInstructions() {
		MyMethodInsnNode insn = EasyMock.createMock(MyMethodInsnNode.class);
		EasyMock.expect(insn.getCleanOwner()).andReturn("ArrayList");
		instructions.add(insn);
		MyMethodInsnNode insn2 = EasyMock.createMock(MyMethodInsnNode.class);
		instructions.add(insn2);
		EasyMock.expect(method.isConstructor()).andReturn(false);
		EasyMock.expect(method.getMethodInstructions()).andReturn(instructions);
		EasyMock.replay(insn, insn2, method);
		assertTrue(check.checkMethod(method, types));
		EasyMock.verify(insn, insn2, method);
	}
	
	@Test
	void testCheckMethodBad() {
		ArrayList<String> types2 = new ArrayList<>();
		types2.add("LinkedList");
		MyMethodNode method2 = EasyMock.createMock(MyMethodNode.class);
		method2.name = "lol";
		ArrayList<MyMethodInsnNode> instructions = new ArrayList<MyMethodInsnNode>();
		MyMethodInsnNode insn2 = EasyMock.createMock(MyMethodInsnNode.class);
		EasyMock.expect(insn2.getCleanOwner()).andReturn("ArrayList");
		instructions.add(insn2);
		EasyMock.expect(method2.isConstructor()).andReturn(false);
		EasyMock.expect(method2.getMethodInstructions()).andReturn(instructions);
		AdapterPatternCheck check = new AdapterPatternCheck();
		EasyMock.replay(method2);
		assertFalse(check.checkMethod(method2, types2));
		EasyMock.verify(method2);
	}
	
	@Test
	void checkAdapterEmptyFields() {
		AdapterPatternCheck check = new AdapterPatternCheck();
		MyClassNode curClass = EasyMock.createMock(MyClassNode.class);
		ArrayList<String> interfaces = new ArrayList<>();
		interfaces.add("Cat");
		ArrayList<String> fields = new ArrayList<>();
		EasyMock.expect(curClass.getCleanName()).andReturn("ThingToThingAdapter");
		EasyMock.expect(curClass.getInterfaces()).andReturn(interfaces);
		EasyMock.expect(curClass.getNonBuiltInFieldTypes()).andReturn(fields);
		EasyMock.replay(curClass);
		String expected = "	Class ThingToThingAdapter has \"adapter\" in name, but does not implement an interface and have a field of a user defined class to adapt. \n";
		assertEquals(expected, check.checkAdapter(curClass));
		EasyMock.verify(curClass);
	}
	
	@Test
	void checkAdapterEmptyInterfaces() {
		AdapterPatternCheck check = new AdapterPatternCheck();
		MyClassNode curClass = EasyMock.createMock(MyClassNode.class);
		ArrayList<String> interfaces = new ArrayList<>();
		ArrayList<String> fields = new ArrayList<>();
		EasyMock.expect(curClass.getCleanName()).andReturn("Thing");
		EasyMock.expect(curClass.getInterfaces()).andReturn(interfaces);
		EasyMock.expect(curClass.getNonBuiltInFieldTypes()).andReturn(fields);
		EasyMock.replay(curClass);
		String expected = "";
		assertEquals(expected, check.checkAdapter(curClass));
		EasyMock.verify(curClass);
	}
	
	@Test
	void testGetName() {
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertEquals("Adapter Pattern", check.getName());
	}

}
