//package domain;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.ArrayList;
//
//import org.easymock.EasyMock;
//import org.junit.Test;
//import org.objectweb.asm.tree.AbstractInsnNode;
//import org.objectweb.asm.tree.ClassNode;
//import org.objectweb.asm.tree.FieldInsnNode;
//import org.objectweb.asm.tree.InsnList;
//import org.objectweb.asm.tree.LocalVariableNode;
//import org.objectweb.asm.tree.MethodInsnNode;
//import org.objectweb.asm.tree.MethodNode;


// Note: These tests were written for when the system did not use personalized class nodes
//public class HollywoodCheckTest {
//	HollywoodCheck checker = new HollywoodCheck();
//	
//	@Test
//	public void testUsingField() {
//		ClassNode curClass = EasyMock.createMock(ClassNode.class);
//		ArrayList<String> superFieldNames = new ArrayList<>();
//		superFieldNames.add("repeated");
//		ArrayList<String> superMethodNames = new ArrayList<>();
//		String superName = "SuperClass";
//		MethodNode m1 = EasyMock.createMock(MethodNode.class);
//		ArrayList<MethodNode> methods = new ArrayList<MethodNode>();
//		methods.add(m1);
//		curClass.name = "SubClass";
//		curClass.methods = methods;
//		
//		InsnList insns = EasyMock.createMock(InsnList.class);
//		FieldInsnNode n1a = EasyMock.createMock(FieldInsnNode.class);
//		n1a.name = "repeated";
//		AbstractInsnNode n1 = n1a;
//		AbstractInsnNode[] nArr = {n1};
//		
//		m1.localVariables = null;
//		m1.instructions = insns;
//		m1.name = "badMethod";
//		EasyMock.expect(m1.instructions.toArray()).andReturn(nArr);
//		
//		EasyMock.replay(curClass, m1, insns, n1a);
//		assertEquals("	Class SubClass uses field repeated from SuperClass in method badMethod\n",
//				checker.checkHollywoodViolations(curClass, superFieldNames, superMethodNames, superName));
//	}
//	
//	@Test
//	public void testUsingFieldVarInMethod() {
//		ClassNode curClass = EasyMock.createMock(ClassNode.class);
//		ArrayList<String> superFieldNames = new ArrayList<>();
//		superFieldNames.add("repeated");
//		ArrayList<String> superMethodNames = new ArrayList<>();
//		String superName = "SuperClass";
//		MethodNode m1 = EasyMock.createMock(MethodNode.class);
//		ArrayList<MethodNode> methods = new ArrayList<MethodNode>();
//		methods.add(m1);
//		curClass.name = "SubClass";
//		curClass.methods = methods;
//		
//		InsnList insns = EasyMock.createMock(InsnList.class);
//		FieldInsnNode n1a = EasyMock.createMock(FieldInsnNode.class);
//		n1a.name = "repeated";
//		AbstractInsnNode n1 = n1a;
//		AbstractInsnNode[] nArr = {n1};
//		
//		LocalVariableNode lv1 = EasyMock.createMock(LocalVariableNode.class);
//		ArrayList<LocalVariableNode> locals = new ArrayList<>();
//		locals.add(lv1);
//		lv1.name = "repeated";
//		m1.localVariables = locals;
//		m1.instructions = insns;
//		m1.name = "badMethod";
//		EasyMock.expect(m1.instructions.toArray()).andReturn(nArr);
//		
//		EasyMock.replay(curClass, m1, insns, n1a, lv1);
//		assertEquals("",
//				checker.checkHollywoodViolations(curClass, superFieldNames, superMethodNames, superName));
//	}
//	
//	@Test
//	public void testUsingMethod() {
//		ClassNode curClass = EasyMock.createMock(ClassNode.class);
//		ArrayList<String> superFieldNames = new ArrayList<>();
//		ArrayList<String> superMethodNames = new ArrayList<>();
//		superMethodNames.add("repeated");
//		String superName = "SuperClass";
//		MethodNode m1 = EasyMock.createMock(MethodNode.class);
//		ArrayList<MethodNode> methods = new ArrayList<MethodNode>();
//		methods.add(m1);
//		curClass.name = "SubClass";
//		curClass.methods = methods;
//		
//		InsnList insns = EasyMock.createMock(InsnList.class);
//		MethodInsnNode n1a = EasyMock.createMock(MethodInsnNode.class);
//		n1a.name = "repeated";
//		AbstractInsnNode n1 = n1a;
//		AbstractInsnNode[] nArr = {n1};
//		
//		m1.localVariables = null;
//		m1.instructions = insns;
//		m1.name = "badMethod";
//		EasyMock.expect(m1.instructions.toArray()).andReturn(nArr);
//		
//		EasyMock.replay(curClass, m1, insns, n1a);
//		assertEquals("	Class SubClass calls method repeated from SuperClass in method badMethod\n",
//				checker.checkHollywoodViolations(curClass, superFieldNames, superMethodNames, superName));
//	}
//}
