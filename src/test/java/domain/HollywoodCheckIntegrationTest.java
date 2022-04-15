package domain;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyFieldInsnNode;
import data_source.MyFieldNode;
import data_source.MyLocalVariableNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;


// Note: These tests were written for when the system did not use personalized class nodes
public class HollywoodCheckIntegrationTest {
	HollywoodCheck checker = new HollywoodCheck();
	
	@Test
	public void testUsingField() {
		MyFieldNode f1 = new MyFieldNode("repeated", null, 0);
		List<MyFieldNode> fields = Arrays.asList(f1);
		
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<MyAbstractInsnNode>();
		MyAbstractInsnNode i1 = new MyFieldInsnNode("repeated", "", 0);
		insns.add(i1);
		
		MyMethodNode m1 = new MyMethodNode("badMethod", "", insns, new ArrayList<MyLocalVariableNode>());
		ArrayList<MyMethodNode> methods = new ArrayList<MyMethodNode>();
		methods.add(m1);
		
		MyClassNode curClass = new MyClassNode("SubClass", "", null, new ArrayList<MyFieldNode>(), methods, 0);
		MyClassNode superClass = new MyClassNode("SuperClass", "", null, fields, new ArrayList<MyMethodNode>(), 0);
		
		assertEquals("	Class SubClass uses field repeated from SuperClass in method badMethod\n",
				checker.checkHollywoodViolations(curClass, superClass));
	}
	
	@Test
	public void testUsingFieldVarInMethod() {
		MyFieldNode f1 = new MyFieldNode("repeated", null, 0);
		List<MyFieldNode> fields = Arrays.asList(f1);
		
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<MyAbstractInsnNode>();
		MyAbstractInsnNode i1 = new MyFieldInsnNode("repeated", "", 0);
		insns.add(i1);
		
		MyLocalVariableNode lv1 = new MyLocalVariableNode("repeated", "");
		List<MyLocalVariableNode> locals = Arrays.asList(lv1);
		
		MyMethodNode m1 = new MyMethodNode("badMethod", "", insns, locals);
		ArrayList<MyMethodNode> methods = new ArrayList<MyMethodNode>();
		methods.add(m1);
		
		MyClassNode curClass = new MyClassNode("SubClass", "", null, new ArrayList<MyFieldNode>(), methods, 0);
		MyClassNode superClass = new MyClassNode("SuperClass", "", null, fields, new ArrayList<MyMethodNode>(), 0);
		
		assertEquals("", checker.checkHollywoodViolations(curClass, superClass));
	}
	
	@Test
	public void testUsingMethod() {
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<MyAbstractInsnNode>();
		MyAbstractInsnNode i1 = new MyMethodInsnNode("repeated", "", 0);
		insns.add(i1);
		
		MyMethodNode m1 = new MyMethodNode("badMethod", "", insns, new ArrayList<MyLocalVariableNode>());
		ArrayList<MyMethodNode> methods1 = new ArrayList<MyMethodNode>();
		methods1.add(m1);
		
		MyMethodNode m2 = new MyMethodNode("repeated", "", new LinkedList<MyAbstractInsnNode>(), new ArrayList<MyLocalVariableNode>());
		ArrayList<MyMethodNode> methods2 = new ArrayList<MyMethodNode>();
		methods2.add(m2);
		
		MyClassNode curClass = new MyClassNode("SubClass", "", null, new ArrayList<MyFieldNode>(), methods1, 0);
		MyClassNode superClass = new MyClassNode("SuperClass", "", null, new ArrayList<MyFieldNode>(), methods2, 0);
		
		assertEquals("	Class SubClass calls method repeated from SuperClass in method badMethod\n",
				checker.checkHollywoodViolations(curClass, superClass));
		
	}
}
