//package domain;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.easymock.EasyMock;
//import org.easymock.asm.Opcodes;
//import org.junit.Test;
//import org.objectweb.asm.tree.FieldNode;
//import org.objectweb.asm.tree.LocalVariableNode;
//import org.objectweb.asm.tree.MethodNode;
//

// Note: These tests were written for when the system did not use personalized class nodes
//public class NamesCheckTest {
//	NamesCheck checker = new NamesCheck();
//	
//	@Test
//	public void testShortClassName() {
//		assertEquals("			Class Name is too short (1 character) \n", checker.checkClass("A"));
//	}
//	
//	@Test
//	public void testNonCapitalClass() {
//		assertEquals("			Class Name does not start with a capital letter \n", checker.checkClass("incorrect"));
//	}
//	
//	@Test
//	public void testClassNameSymbol() {
//		assertEquals("			Class Name contains the non-alphanumeric character: ^\n", checker.checkClass("Hello^"));
//	}
//	
//	@Test
//	public void validClassName() {
//		assertEquals("", checker.checkClass("GoodClass"));
//	}
//	
//	@Test
//	public void testFieldUppercaseInvalid() {
//		FieldNode node = EasyMock.createMock(FieldNode.class);
//		node.name = "Badfield";
//		node.access = 1;
//		node.desc = "I";
//		
//		EasyMock.replay(node);
//		assertEquals("			Field Badfield has an uppercase first letter, and is not static and final \n", checker.checkField(node));
//		EasyMock.verify(node);
//	}
//	
//	@Test
//	public void testFieldUppercaseValid() {
//		FieldNode node = EasyMock.createMock(FieldNode.class);
//		node.name = "GOODFIELD";
//		node.access = Opcodes.ACC_STATIC + Opcodes.ACC_FINAL;
//		node.desc = "I";
//		
//		EasyMock.replay(node);
//		assertEquals("", checker.checkField(node));
//		EasyMock.verify(node);
//	}
//	
//	@Test
//	public void testFieldTypeName() {
//		FieldNode node = EasyMock.createMock(FieldNode.class);
//		node.name = "badfield";
//		node.access = 1;
//		node.desc = "Ljava/lang/Badfield;";
//		
//		EasyMock.replay(node);
//		assertEquals("			Field badfield has the same name as its type \n", checker.checkField(node));
//		EasyMock.verify(node);
//	}
//	
//	@Test
//	public void testFieldNameLength() {
//		FieldNode node = EasyMock.createMock(FieldNode.class);
//		node.name = "j";
//		node.access = 1;
//		node.desc = "I";
//		
//		EasyMock.replay(node);
//		assertEquals("			Field j has too short of a name (1 character) \n", checker.checkField(node));
//		EasyMock.verify(node);
//	}
//	
//	@Test
//	public void testValidField() {
//		FieldNode node = EasyMock.createMock(FieldNode.class);
//		node.name = "goodField";
//		node.access = 1;
//		node.desc = "I";
//		
//		EasyMock.replay(node);
//		assertEquals("", checker.checkField(node));
//		EasyMock.verify(node);
//	}
//	
//	@Test
//	public void testMethodLocalVarUppercase() {
//		LocalVariableNode lv1 = EasyMock.createMock(LocalVariableNode.class);
//		List<LocalVariableNode> list = Arrays.asList(lv1);
//		MethodNode node = EasyMock.createMock(MethodNode.class);
//		node.localVariables = list;
//		node.name = "goodMethod";
//		lv1.name = "Bad";
//		lv1.desc = "I";
//
//		EasyMock.replay(node);
//		assertEquals("			Variable Bad has an uppercase first letter in method goodMethod\n", checker.checkMethod(node));
//		EasyMock.verify(node);
//	}
//	
//	@Test
//	public void testMethodLocalVarTypeName() {
//		LocalVariableNode lv1 = EasyMock.createMock(LocalVariableNode.class);
//		List<LocalVariableNode> list = Arrays.asList(lv1);
//		MethodNode node = EasyMock.createMock(MethodNode.class);
//		node.localVariables = list;
//		node.name = "goodMethod";
//		lv1.name = "bad";
//		lv1.desc = "Ljava/lang/Bad";
//
//		EasyMock.replay(node);
//		assertEquals("			Variable bad has the same name as its type in method goodMethod\n", checker.checkMethod(node));
//		EasyMock.verify(node);
//	}
//	
//	@Test
//	public void testMethodUppercase() {
//		LocalVariableNode lv1 = EasyMock.createMock(LocalVariableNode.class);
//		List<LocalVariableNode> list = Arrays.asList(lv1);
//		MethodNode node = EasyMock.createMock(MethodNode.class);
//		node.localVariables = list;
//		node.name = "BadMethod";
//		lv1.name = "good";
//		lv1.desc = "I";
//
//		EasyMock.replay(node);
//		assertEquals("			Method BadMethod has an uppercase first letter \n", checker.checkMethod(node));
//		EasyMock.verify(node);
//	}
//	
//	@Test
//	public void testMethodNameLength() {
//		LocalVariableNode lv1 = EasyMock.createMock(LocalVariableNode.class);
//		List<LocalVariableNode> list = Arrays.asList(lv1);
//		MethodNode node = EasyMock.createMock(MethodNode.class);
//		node.localVariables = list;
//		node.name = "b";
//		lv1.name = "good";
//		lv1.desc = "I";
//
//		EasyMock.replay(node);
//		assertEquals("			Method b has too short of a name (1 character) \n", checker.checkMethod(node));
//		EasyMock.verify(node);
//	}
//	
//	@Test
//	public void testMethodValid() {
//		LocalVariableNode lv1 = EasyMock.createMock(LocalVariableNode.class);
//		List<LocalVariableNode> list = Arrays.asList(lv1);
//		MethodNode node = EasyMock.createMock(MethodNode.class);
//		node.localVariables = list;
//		node.name = "goodMethod";
//		lv1.name = "good";
//		lv1.desc = "I";
//
//		EasyMock.replay(node);
//		assertEquals("", checker.checkMethod(node));
//		EasyMock.verify(node);
//	}
//}
