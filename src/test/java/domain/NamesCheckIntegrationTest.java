package domain;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.asm.Opcodes;
import org.junit.jupiter.api.Test;

import data_source.MyClassNode;
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
	public void testFieldUppercaseInvalidOnlyStatic() {
		MyFieldNode node = new MyFieldNode("Badfield", "I", (Opcodes.ACC_STATIC));
		assertEquals("				Field Badfield has an uppercase first letter, and is not static and final \n", checker.checkField(node));
	}
	
	@Test
	public void testFieldUppercaseInvalidOnlyFinal() {
		MyFieldNode node = new MyFieldNode("Badfield", "I", (Opcodes.ACC_FINAL));
		assertEquals("				Field Badfield has an uppercase first letter, and is not static and final \n", checker.checkField(node));
	}
	
	@Test
	public void testFieldUppercaseInvalidNoStaticOrFinal() {
		MyFieldNode node = new MyFieldNode("Badfield", "I", 0);
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
	
	@Test
	public void testMethodValidNoLocals() {
		List<MyLocalVariableNode> list = new ArrayList<>();
		MyMethodNode node = new MyMethodNode("goodMethod", "", null, list);

		assertEquals("", checker.checkMethod(node));
	}
	
	@Test
	public void testRunCheckNoOutput() {
		NamesCheck check = EasyMock.partialMockBuilder(NamesCheck.class).addMockedMethod("namesCheck").createMock();
		MyClassNode curClass = new MyClassNode("okClass", "", null, null, null, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(curClass);
		
		EasyMock.expect(check.namesCheck(curClass)).andReturn("");
		
		EasyMock.replay(check);
		assertEquals("", check.runCheck(classes));
		EasyMock.verify(check);
	}
	
	@Test
	public void testRunCheckFull() {
		MyFieldNode fnode = new MyFieldNode("badfield", "Badfield", 0);
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(fnode);
		
		MyLocalVariableNode lvnode = new MyLocalVariableNode("Bad", "I");
		List<MyLocalVariableNode> lvlist = Arrays.asList(lvnode);
		
		MyMethodNode mnode = new MyMethodNode("b", "", null, lvlist);
		List<MyMethodNode> mlist = Arrays.asList(mnode);
		
		MyClassNode curClass = new MyClassNode("badClass", "", null, fields, mlist, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(curClass);
		
		assertEquals("Names Check:\n" + 
				"	Class: badClass\n" + 
				"		Name Style Violations: \n" + 
				"			Class Name checks: \n" + 
				"				Class Name does not start with a capital letter \n" + 
				"			Field Name checks: \n" + 
				"				Field badfield has the same name as its type \n" + 
				"			Method & Method Variable Name checks: \n" + 
				"				Variable Bad has an uppercase first letter in method b\n" + 
				"				Method b has too short of a name (1 character) \n", checker.runCheck(classes));
	}
	
	@Test
	public void testNameCheckFull() {
		MyFieldNode fnode = new MyFieldNode("badfield", "Badfield", 0);
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(fnode);
		
		MyLocalVariableNode lvnode = new MyLocalVariableNode("Bad", "I");
		List<MyLocalVariableNode> lvlist = Arrays.asList(lvnode);
		
		MyMethodNode mnode = new MyMethodNode("b", "", null, lvlist);
		List<MyMethodNode> mlist = Arrays.asList(mnode);
		
		MyClassNode curClass = new MyClassNode("badClass", "", null, fields, mlist, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(curClass);
		
		assertEquals("		Name Style Violations: \n" + 
				"			Class Name checks: \n" + 
				"				Class Name does not start with a capital letter \n" + 
				"			Field Name checks: \n" + 
				"				Field badfield has the same name as its type \n" + 
				"			Method & Method Variable Name checks: \n" + 
				"				Variable Bad has an uppercase first letter in method b\n" + 
				"				Method b has too short of a name (1 character) \n", checker.namesCheck(curClass));
	}
	
	@Test
	public void testNameCheckNoOutput() {
		MyFieldNode fnode = new MyFieldNode("ok", "GoodField", 0);
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(fnode);
		
		MyLocalVariableNode lvnode = new MyLocalVariableNode("goodName", "I");
		List<MyLocalVariableNode> lvlist = Arrays.asList(lvnode);
		
		MyMethodNode mnode = new MyMethodNode("methodName", "", null, lvlist);
		List<MyMethodNode> mlist = Arrays.asList(mnode);
		
		MyClassNode curClass = new MyClassNode("GoodClass", "", null, fields, mlist, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(curClass);
		
		assertEquals("", checker.namesCheck(curClass));
	}
	
	@Test
	public void testGetName() {
		assertEquals("Name Style", checker.getName());
	}
}
