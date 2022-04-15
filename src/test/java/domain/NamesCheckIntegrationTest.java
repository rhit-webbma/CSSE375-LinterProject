package domain;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.asm.Opcodes;
import org.junit.jupiter.api.Test;
import data_source.MyFieldNode;
import data_source.MyLocalVariableNode;
import data_source.MyMethodNode;

public class NamesCheckIntegrationTest {
	NamesCheck checker = new NamesCheck();
	
	@Test
	public void testShortClassName() {
		assertEquals("				Class Name is too short (1 character) \n", checker.checkClass("A"));
	}
	
	@Test
	public void testNonCapitalClass() {
		assertEquals("				Class Name does not start with a capital letter \n", checker.checkClass("incorrect"));
	}
	
	@Test
	public void testClassNameSymbol() {
		assertEquals("				Class Name contains the non-alphanumeric character: ^\n", checker.checkClass("Hello^"));
	}
	
	@Test
	public void validClassName() {
		assertEquals("", checker.checkClass("GoodClass"));
	}
	
	@Test
	public void testFieldUppercaseInvalid() {
		MyFieldNode node = new MyFieldNode("Badfield", "I", Opcodes.ACC_STATIC);
		assertEquals("				Field Badfield has an uppercase first letter, and is not static and final \n", checker.checkField(node));
	}
	
	@Test
	public void testFieldUppercaseValid() {
		MyFieldNode node = new MyFieldNode("GOODFIELD", "I", (Opcodes.ACC_STATIC + Opcodes.ACC_FINAL));
		assertEquals("", checker.checkField(node));
	}
	
	@Test
	public void testFieldTypeName() {
		MyFieldNode node = new MyFieldNode("badfield", "Badfield", 0);
		assertEquals("				Field badfield has the same name as its type \n", checker.checkField(node));
	}
	
	@Test
	public void testFieldNameLength() {
		MyFieldNode node = new MyFieldNode("j", "I", 0);
		assertEquals("				Field j has too short of a name (1 character) \n", checker.checkField(node));
	}
	
	@Test
	public void testValidField() {
		MyFieldNode node = new MyFieldNode("goodField", "I", 0);
		
		assertEquals("", checker.checkField(node));
	}
	
	@Test
	public void testMethodLocalVarUppercase() {
		MyLocalVariableNode lv1 = new MyLocalVariableNode("Bad", "I");
		List<MyLocalVariableNode> list = Arrays.asList(lv1);
		MyMethodNode node = new MyMethodNode("goodMethod", "", null, list);

		assertEquals("				Variable Bad has an uppercase first letter in method goodMethod\n", checker.checkMethod(node));
	}
	
	@Test
	public void testMethodLocalVarTypeName() {
		MyLocalVariableNode lv1 = new MyLocalVariableNode("bad", "Bad");
		List<MyLocalVariableNode> list = Arrays.asList(lv1);
		MyMethodNode node = new MyMethodNode("goodMethod", "", null, list);
		
		assertEquals("				Variable bad has the same name as its type in method goodMethod\n", checker.checkMethod(node));
	}
	
	@Test
	public void testMethodUppercase() {
		MyLocalVariableNode lv1 = new MyLocalVariableNode("good", "I");
		List<MyLocalVariableNode> list = Arrays.asList(lv1);
		MyMethodNode node = new MyMethodNode("BadMethod", "", null, list);
		
		assertEquals("				Method BadMethod has an uppercase first letter \n", checker.checkMethod(node));
	}
	
	@Test
	public void testMethodNameLength() {
		MyLocalVariableNode lv1 = new MyLocalVariableNode("good", "I");
		List<MyLocalVariableNode> list = Arrays.asList(lv1);
		MyMethodNode node = new MyMethodNode("b", "", null, list);
		
		assertEquals("				Method b has too short of a name (1 character) \n", checker.checkMethod(node));
	}
	
	@Test
	public void testMethodValid() {
		MyLocalVariableNode lv1 = new MyLocalVariableNode("good", "I");
		List<MyLocalVariableNode> list = Arrays.asList(lv1);
		MyMethodNode node = new MyMethodNode("goodMethod", "", null, list);

		assertEquals("", checker.checkMethod(node));
	}
}
