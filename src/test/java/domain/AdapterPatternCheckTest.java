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
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;

class AdapterPatternCheckTest {

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
	void testGetClassName() {
		MyClassNode node = EasyMock.createMock(MyClassNode.class);
		node.name = "java/lang/String";
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertEquals("String", check.getClassName(node));
	}
	
	@Test
	void testGetInterfaces() {
		MyClassNode node = EasyMock.createMock(MyClassNode.class);
		List<String> interfaceNames = new ArrayList<>();
		interfaceNames.add("java/lang/Comparable");
		interfaceNames.add("src/domain/SingleClassCheck");
		node.interfaces = interfaceNames;
		AdapterPatternCheck check = new AdapterPatternCheck();
		ArrayList<String> expected = new ArrayList<>();
		expected.add("Comparable");
		expected.add("SingleClassCheck");
		assertEquals(expected, check.getInterfaces(node));
	}
	
	@Test
	void testGetFieldTypes() {
		List<MyFieldNode> fields = new ArrayList<>();
		for (int i=0; i<3; i++) {
			MyFieldNode field = EasyMock.createMock(MyFieldNode.class);
			field.desc = String.format("dataSource/lang/util/field%d", i+1);
			fields.add(field);
		}
		MyClassNode node = EasyMock.createMock(MyClassNode.class);
		node.fields = fields;
		ArrayList<String> expected = new ArrayList<>();
		expected.add("field1");
		expected.add("field2");
		expected.add("field3");
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertEquals(expected, check.getFieldTypes(node));
	}
	
	@Test
	void testGetFieldTypesNoJava() {
		List<MyFieldNode> fields = new ArrayList<>();
		for (int i=0; i<3; i++) {
			MyFieldNode field = EasyMock.createMock(MyFieldNode.class);
			field.desc = (i == 1) ? "java/lang/util/field2" : String.format("dataSource/lang/util/field%d", i+1);
			fields.add(field);
		}
		MyClassNode node = EasyMock.createMock(MyClassNode.class);
		node.fields = fields;
		ArrayList<String> expected = new ArrayList<>();
		expected.add("field1");
		expected.add("field3");
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertEquals(expected, check.getFieldTypes(node));
	}
	
	@Test
	void testCheckMethodInit() {
		MyMethodNode method = EasyMock.createMock(MyMethodNode.class);
		method.name = "<init>";
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertTrue(check.checkMethod(method, null));
	}
	
	@Test
	void testCheckMethodEmpty() {
		MyMethodNode method = EasyMock.createMock(MyMethodNode.class);
		method.name = "lol";
		method.instructions = new LinkedList<MyAbstractInsnNode>();
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertTrue(check.checkMethod(method, null));
	}
	
	@Test
	void testCheckMethodGood() {
		ArrayList<String> types = new ArrayList<>();
		types.add("ArrayList");
		MyMethodNode method = EasyMock.createMock(MyMethodNode.class);
		method.name = "lol";
		method.instructions = new LinkedList<MyAbstractInsnNode>();
		MyMethodInsnNode insn = EasyMock.createMock(MyMethodInsnNode.class);
		insn.owner = "java/util/ArrayList";
		method.instructions.add((MyAbstractInsnNode)insn);
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertTrue(check.checkMethod(method, types));
	}
	
	@Test
	void testCheckMethodBad() {
		ArrayList<String> types = new ArrayList<>();
		types.add("LinkedList");
		MyMethodNode method = EasyMock.createMock(MyMethodNode.class);
		method.name = "lol";
		method.instructions = new LinkedList<MyAbstractInsnNode>();
		MyMethodInsnNode insn = EasyMock.createMock(MyMethodInsnNode.class);
		insn.owner = "java/util/ArrayList";
		method.instructions.add((MyAbstractInsnNode)insn);
		AdapterPatternCheck check = new AdapterPatternCheck();
		assertFalse(check.checkMethod(method, types));
	}

}
