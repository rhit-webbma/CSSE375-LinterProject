package data_source;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.asm.Opcodes;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

class ASMParserTest {

	@Test
	void testParseTestClass() {		
		FieldNode mf1 = EasyMock.createMock(FieldNode.class);
		mf1.name = "field1";
		mf1.desc = "I";
		mf1.access = 0;
		FieldNode mf2 = EasyMock.createMock(FieldNode.class);
		mf2.name = "field2";
		mf2.desc = "Ljava/lang/String;";
		mf2.access = 0;
		ArrayList<FieldNode> mfields = new ArrayList<>();
		mfields.add(mf1);
		mfields.add(mf2);
		
		LineNumberNode mn1 = EasyMock.createMock(LineNumberNode.class);
		mn1.line = 0;
		EasyMock.expect(mn1.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn1.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn1.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn1.getType()).andReturn(AbstractInsnNode.LINE);
		VarInsnNode mn2 = EasyMock.createMock(VarInsnNode.class);
		EasyMock.expect(mn2.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		EasyMock.expect(mn2.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		EasyMock.expect(mn2.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		mn2.var = 0;
		EasyMock.expect(mn2.getOpcode()).andReturn(0);
		MethodInsnNode mn3 = EasyMock.createMock(MethodInsnNode.class);
		EasyMock.expect(mn3.getType()).andReturn(AbstractInsnNode.METHOD_INSN);
		EasyMock.expect(mn3.getType()).andReturn(AbstractInsnNode.METHOD_INSN);
		mn3.name = "";
		mn3.owner = "";
		EasyMock.expect(mn3.getOpcode()).andReturn(0);
		LineNumberNode mn4 = EasyMock.createMock(LineNumberNode.class);
		EasyMock.expect(mn4.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn4.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn4.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn4.getType()).andReturn(AbstractInsnNode.LINE);
		mn4.line = 0;
		VarInsnNode mn5 = EasyMock.createMock(VarInsnNode.class);
		EasyMock.expect(mn5.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		EasyMock.expect(mn5.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		EasyMock.expect(mn5.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		mn5.var = 0;
		EasyMock.expect(mn5.getOpcode()).andReturn(0);
		FieldInsnNode mn6 = EasyMock.createMock(FieldInsnNode.class);
		EasyMock.expect(mn6.getType()).andReturn(AbstractInsnNode.FIELD_INSN);
		mn6.name = "";
		mn6.owner = "";
		EasyMock.expect(mn6.getOpcode()).andReturn(0);
		LineNumberNode mn7 = EasyMock.createMock(LineNumberNode.class);
		EasyMock.expect(mn7.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn7.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn7.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn7.getType()).andReturn(AbstractInsnNode.LINE);
		mn7.line = 0;
		VarInsnNode mn8 = EasyMock.createMock(VarInsnNode.class);
		EasyMock.expect(mn8.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		EasyMock.expect(mn8.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		EasyMock.expect(mn8.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		mn8.var = 0;
		EasyMock.expect(mn8.getOpcode()).andReturn(0);
		FieldInsnNode mn9 = EasyMock.createMock(FieldInsnNode.class);
		EasyMock.expect(mn9.getType()).andReturn(AbstractInsnNode.FIELD_INSN);
		mn9.name = "";
		mn9.owner = "";
		EasyMock.expect(mn9.getOpcode()).andReturn(0);
		LineNumberNode mn10 = EasyMock.createMock(LineNumberNode.class);
		EasyMock.expect(mn10.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn10.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn10.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn10.getType()).andReturn(AbstractInsnNode.LINE);
		mn10.line = 0;
		InsnList m1InsnsMock = new InsnList();
		m1InsnsMock.add(mn1);
		m1InsnsMock.add(mn2);
		m1InsnsMock.add(mn3);
		m1InsnsMock.add(mn4);
		m1InsnsMock.add(mn5);
		m1InsnsMock.add(mn6);
		m1InsnsMock.add(mn7);
		m1InsnsMock.add(mn8);
		m1InsnsMock.add(mn9);
		m1InsnsMock.add(mn10);
		
		LineNumberNode mn11 = EasyMock.createMock(LineNumberNode.class);
		EasyMock.expect(mn11.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn11.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn11.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn11.getType()).andReturn(AbstractInsnNode.LINE);
		mn11.line = 0;
		VarInsnNode mn12 = EasyMock.createMock(VarInsnNode.class);
		EasyMock.expect(mn12.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		EasyMock.expect(mn12.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		EasyMock.expect(mn12.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		mn12.var = 0;
		EasyMock.expect(mn12.getOpcode()).andReturn(0);
		LineNumberNode mn13 = EasyMock.createMock(LineNumberNode.class);
		EasyMock.expect(mn13.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn13.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn13.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn13.getType()).andReturn(AbstractInsnNode.LINE);
		mn13.line = 0;
		LineNumberNode mn14 = EasyMock.createMock(LineNumberNode.class);
		EasyMock.expect(mn14.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn14.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn14.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn14.getType()).andReturn(AbstractInsnNode.LINE);
		mn14.line = 0;
		LineNumberNode mn15 = EasyMock.createMock(LineNumberNode.class);
		EasyMock.expect(mn15.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn15.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn15.getType()).andReturn(AbstractInsnNode.LINE);
		EasyMock.expect(mn15.getType()).andReturn(AbstractInsnNode.LINE);
		mn15.line = 0;
		VarInsnNode mn16 = EasyMock.createMock(VarInsnNode.class);
		EasyMock.expect(mn16.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		EasyMock.expect(mn16.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		EasyMock.expect(mn16.getType()).andReturn(AbstractInsnNode.VAR_INSN);
		mn16.var = 0;
		EasyMock.expect(mn16.getOpcode()).andReturn(0);
		InsnList m2InsnsMock = new InsnList();
		m2InsnsMock.add(mn11);
		m2InsnsMock.add(mn12);
		m2InsnsMock.add(mn13);
		m2InsnsMock.add(mn14);
		m2InsnsMock.add(mn15);
		m2InsnsMock.add(mn16);
		
		LocalVariableNode ml1 = EasyMock.createMock(LocalVariableNode.class);
		ml1.name = "this";
		ml1.desc = "Ldata_source/TestClass;";
		LocalVariableNode ml2 = EasyMock.createMock(LocalVariableNode.class);
		ml2.name = "i";
		ml2.desc = "I";
		List<LocalVariableNode> m1LVMock = new ArrayList<>();
		m1LVMock.add(ml1);
		List<LocalVariableNode> m2LVMock = new ArrayList<>();
		m2LVMock.add(ml1);
		m2LVMock.add(ml2);
		
		MethodNode m1Mock = EasyMock.createMock(MethodNode.class);
		m1Mock.name = "<init>";
		m1Mock.desc = "()V";
		m1Mock.instructions = m1InsnsMock;
		m1Mock.localVariables = m1LVMock;
		
		MethodNode m2Mock = EasyMock.createMock(MethodNode.class);
		m2Mock.name = "method";
		m2Mock.desc = "()I";
		m2Mock.instructions = m2InsnsMock;
		m2Mock.localVariables = m2LVMock;
		
		ArrayList<MethodNode> methodsMocks = new ArrayList<>();
		methodsMocks.add(m1Mock);
		methodsMocks.add(m2Mock);
	
		ClassNode classNode = EasyMock.createMock(ClassNode.class);
		ArrayList<ClassNode> nodes = new ArrayList<ClassNode>();
		nodes.add(classNode);
		classNode.name = "data_source/TestClass";
		classNode.superName = "java/lang/Object";
		classNode.interfaces = null;
		classNode.fields = mfields;
		classNode.methods = methodsMocks;
		classNode.access = 0;

		// For expectations
		MyAbstractInsnNode n1 = new MyLineNumberNode(0);
		MyAbstractInsnNode n2 = new MyVarInsnNode(0, 0);
		MyAbstractInsnNode n3 = new MyMethodInsnNode(null, null, 0);
		MyAbstractInsnNode n4 = new MyLineNumberNode(0);
		MyAbstractInsnNode n5 = new MyVarInsnNode(0, 0);
		MyAbstractInsnNode n6 = new MyFieldInsnNode(null, null, 0);
		MyAbstractInsnNode n7 = new MyLineNumberNode(0);
		MyAbstractInsnNode n8 = new MyVarInsnNode(0, 0);
		MyAbstractInsnNode n9 = new MyFieldInsnNode(null, null, 0);
		MyAbstractInsnNode n10 = new MyLineNumberNode(0);
		LinkedList<MyAbstractInsnNode> m1Insns = new LinkedList<>();
		m1Insns.add(n1);
		m1Insns.add(n2);
		m1Insns.add(n3);
		m1Insns.add(n4);
		m1Insns.add(n5);
		m1Insns.add(n6);
		m1Insns.add(n7);
		m1Insns.add(n8);
		m1Insns.add(n9);
		m1Insns.add(n10);
		
		MyAbstractInsnNode n11 = new MyLineNumberNode(0);
		MyAbstractInsnNode n12 = new MyVarInsnNode(0, 0);
		MyAbstractInsnNode n13 = new MyLineNumberNode(0);
		MyAbstractInsnNode n14 = new MyLineNumberNode(0);
		MyAbstractInsnNode n15 = new MyLineNumberNode(0);
		MyAbstractInsnNode n16 = new MyVarInsnNode(0, 0);
		LinkedList<MyAbstractInsnNode> m2Insns = new LinkedList<>();
		m2Insns.add(n11);
		m2Insns.add(n12);
		m2Insns.add(n13);
		m2Insns.add(n14);
		m2Insns.add(n15);
		m2Insns.add(n16);
		
		MyLocalVariableNode l1 = new MyLocalVariableNode("this", "Ldata_source/TestClass;");
		MyLocalVariableNode l2 = new MyLocalVariableNode("i", "I");
		List<MyLocalVariableNode> m1LV = new ArrayList<>();
		m1LV.add(l1);
		List<MyLocalVariableNode> m2LV = new ArrayList<>();
		m2LV.add(l1);
		m2LV.add(l2);

		MyFieldNode f1 = new MyFieldNode("field1", "I", 0);
		MyFieldNode f2 = new MyFieldNode("field2", "Ljava/lang/String;", 0);
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(f1);
		fields.add(f2);
		
		MyMethodNode m1 = new MyMethodNode("<init>", "()V", m1Insns, m1LV);
		MyMethodNode m2 = new MyMethodNode("method", "()I", m2Insns, m2LV);
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		methods.add(m1);
		methods.add(m2);
		
		MyClassNode myClass = new MyClassNode("data_source/TestClass", "java/lang/Object", null, fields, methods, 0);
	
		// Actual
		EasyMock.replay(classNode, mf1, mf2, mn1, mn2, mn3, mn4, mn5, mn6, mn7, mn8, mn9, mn10, mn11, mn12, mn13, mn14, mn15, mn16, ml1, ml2, m1Mock, m2Mock);
		ArrayList<MyClassNode> myNodes = ASMParser.parseASM(nodes);
		MyClassNode parsedClass = myNodes.get(0);
		
		assertEquals(myClass.getFullName(), parsedClass.getFullName());
		assertEquals(myClass.getFullSuperName(), parsedClass.getFullSuperName());
		
		assertEquals(f1.name, myClass.fields.get(0).name);
		assertEquals(f1.getFullDesc(), myClass.fields.get(0).getFullDesc());
		assertEquals(f2.name, myClass.fields.get(1).name);
		assertEquals(f2.getFullDesc(), myClass.fields.get(1).getFullDesc());
		
		MyMethodNode curMethod = parsedClass.methods.get(0);
		assertEquals(m1.name, curMethod.name);
		assertEquals(m1.getFullDesc(), curMethod.getFullDesc());
		for (int i = 0; i < m1Insns.size(); i++) {
			assertEquals(m1Insns.get(i).getType(), curMethod.instructions.get(i).getType());
		}
		assertEquals(l1.name, curMethod.localVariables.get(0).name);
		assertEquals(l1.getFullDesc(), curMethod.localVariables.get(0).getFullDesc());
		
		curMethod = parsedClass.methods.get(1);
		assertEquals(m2.name, parsedClass.methods.get(1).name);
		assertEquals(m2.getFullDesc(), parsedClass.methods.get(1).getFullDesc());
		for (int i = 0; i < m2Insns.size(); i++) {
			assertEquals(m2Insns.get(i).getType(), curMethod.instructions.get(i).getType());
		}
		assertEquals(l1.name, curMethod.localVariables.get(0).name);
		assertEquals(l1.getFullDesc(), curMethod.localVariables.get(0).getFullDesc());
		assertEquals(l2.name, curMethod.localVariables.get(1).name);
		assertEquals(l2.getFullDesc(), curMethod.localVariables.get(1).getFullDesc());
		
		EasyMock.verify(classNode, mf1, mf2, mn1, mn2, mn3, mn4, mn5, mn6, mn7, mn8, mn9, mn10, mn11, mn12, mn13, mn14, mn15, mn16, ml1, ml2, m1Mock, m2Mock);
	}
	
	@Test
	void testParseTestClassIntegration() {
		ClassReader reader = null;
		try {
			reader = new ClassReader("data_source.TestClass");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClassNode classNode = new ClassNode();
		reader.accept(classNode, ClassReader.EXPAND_FRAMES);
		
		ArrayList<ClassNode> nodes = new ArrayList<ClassNode>();
		nodes.add(classNode);
		
		// For expectations
		MyAbstractInsnNode n1 = new MyLineNumberNode(0);
		MyAbstractInsnNode n2 = new MyVarInsnNode(0, 0);
		MyAbstractInsnNode n3 = new MyMethodInsnNode(null, null, 0);
		MyAbstractInsnNode n4 = new MyLineNumberNode(0);
		MyAbstractInsnNode n5 = new MyVarInsnNode(0, 0);
		MyAbstractInsnNode n6 = new MyFieldInsnNode(null, null, 0);
		MyAbstractInsnNode n7 = new MyLineNumberNode(0);
		MyAbstractInsnNode n8 = new MyVarInsnNode(0, 0);
		MyAbstractInsnNode n9 = new MyFieldInsnNode(null, null, 0);
		MyAbstractInsnNode n10 = new MyLineNumberNode(0);
		LinkedList<MyAbstractInsnNode> m1Insns = new LinkedList<>();
		m1Insns.add(n1);
		m1Insns.add(n2);
		m1Insns.add(n3);
		m1Insns.add(n4);
		m1Insns.add(n5);
		m1Insns.add(n6);
		m1Insns.add(n7);
		m1Insns.add(n8);
		m1Insns.add(n9);
		m1Insns.add(n10);
		
		MyAbstractInsnNode n11 = new MyLineNumberNode(0);
		MyAbstractInsnNode n12 = new MyVarInsnNode(0, 0);
		MyAbstractInsnNode n13 = new MyLineNumberNode(0);
		MyAbstractInsnNode n14 = new MyLineNumberNode(0);
		MyAbstractInsnNode n15 = new MyLineNumberNode(0);
		MyAbstractInsnNode n16 = new MyVarInsnNode(0, 0);
		LinkedList<MyAbstractInsnNode> m2Insns = new LinkedList<>();
		m2Insns.add(n11);
		m2Insns.add(n12);
		m2Insns.add(n13);
		m2Insns.add(n14);
		m2Insns.add(n15);
		m2Insns.add(n16);
		
		MyLocalVariableNode l1 = new MyLocalVariableNode("this", "Ldata_source/TestClass;");
		MyLocalVariableNode l2 = new MyLocalVariableNode("i", "I");
		List<MyLocalVariableNode> m1LV = new ArrayList<>();
		m1LV.add(l1);
		List<MyLocalVariableNode> m2LV = new ArrayList<>();
		m2LV.add(l1);
		m2LV.add(l2);

		MyFieldNode f1 = new MyFieldNode("field1", "I", 0);
		MyFieldNode f2 = new MyFieldNode("field2", "Ljava/lang/String;", 0);
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(f1);
		fields.add(f2);
		
		MyMethodNode m1 = new MyMethodNode("<init>", "()V", m1Insns, m1LV);
		MyMethodNode m2 = new MyMethodNode("method", "()I", m2Insns, m2LV);
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		methods.add(m1);
		methods.add(m2);
		
		MyClassNode myClass = new MyClassNode("data_source/TestClass", "java/lang/Object", null, fields, methods, 0);
		
		// Actual
		ArrayList<MyClassNode> myNodes = ASMParser.parseASM(nodes);
		MyClassNode parsedClass = myNodes.get(0);
		
		assertEquals(myClass.getFullName(), parsedClass.getFullName());
		assertEquals(myClass.getFullSuperName(), parsedClass.getFullSuperName());
		
		assertEquals(f1.name, myClass.fields.get(0).name);
		assertEquals(f1.getFullDesc(), myClass.fields.get(0).getFullDesc());
		assertEquals(f2.name, myClass.fields.get(1).name);
		assertEquals(f2.getFullDesc(), myClass.fields.get(1).getFullDesc());
		
		MyMethodNode curMethod = parsedClass.methods.get(0);
		assertEquals(m1.name, curMethod.name);
		assertEquals(m1.getFullDesc(), curMethod.getFullDesc());
		for (int i = 0; i < m1Insns.size(); i++) {
			assertEquals(m1Insns.get(i).getType(), curMethod.instructions.get(i).getType());
		}
		assertEquals(l1.name, curMethod.localVariables.get(0).name);
		assertEquals(l1.getFullDesc(), curMethod.localVariables.get(0).getFullDesc());
		
		curMethod = parsedClass.methods.get(1);
		assertEquals(m2.name, parsedClass.methods.get(1).name);
		assertEquals(m2.getFullDesc(), parsedClass.methods.get(1).getFullDesc());
		for (int i = 0; i < m2Insns.size(); i++) {
			assertEquals(m2Insns.get(i).getType(), curMethod.instructions.get(i).getType());
		}
		assertEquals(l1.name, curMethod.localVariables.get(0).name);
		assertEquals(l1.getFullDesc(), curMethod.localVariables.get(0).getFullDesc());
		assertEquals(l2.name, curMethod.localVariables.get(1).name);
		assertEquals(l2.getFullDesc(), curMethod.localVariables.get(1).getFullDesc());
		
	}

}
