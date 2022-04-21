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
	List<MyFieldNode> fields;
	LinkedList<MyAbstractInsnNode> insns;
	ArrayList<MyMethodNode> methods;
	ArrayList<MyMethodNode> methods2;
	List<MyLocalVariableNode> locals;
	MyClassNode curClass;
	MyClassNode superClass;
	
	public void initializeField() {
		MyFieldNode f1 = new MyFieldNode("repeated", null, 0);
		fields = Arrays.asList(f1);
	}
	
	public void initializeInsn(boolean isField) {
		insns = new LinkedList<MyAbstractInsnNode>();
		MyAbstractInsnNode i1 = new MyMethodInsnNode("repeated", "", 0);
		if (isField) {
			i1 = new MyFieldInsnNode("repeated", "", 0);
		}
		
		insns.add(i1);
	}
	
	public void initializeLocal() {
		MyLocalVariableNode lv1 = new MyLocalVariableNode("repeated", "");
		locals = Arrays.asList(lv1);
	}
	
	public void initializeMethod(String name, boolean withInsns, boolean withLocals, boolean firstMethod) {
		MyMethodNode m1 = new MyMethodNode(name, "", new LinkedList<MyAbstractInsnNode>(), new ArrayList<MyLocalVariableNode>());
		if (withInsns && withLocals) {
			m1 = new MyMethodNode(name, "", insns, locals);
		} else if (withInsns) {
			m1 = new MyMethodNode(name, "", insns, new ArrayList<MyLocalVariableNode>());
		}
		if (firstMethod) {
			methods = new ArrayList<MyMethodNode>();
			methods.add(m1);
		} else {
			methods2 = new ArrayList<MyMethodNode>();
			methods2.add(m1);
		}
	}
	
	@Test
	public void testUsingField() {
		this.initializeField();
		this.initializeInsn(true);
		this.initializeMethod("badMethod", true, false, true);
		curClass = new MyClassNode("SubClass", "", null, new ArrayList<MyFieldNode>(), methods, 0);
		superClass = new MyClassNode("SuperClass", "", null, fields, new ArrayList<MyMethodNode>(), 0);
		
		assertEquals("	Class SubClass uses field repeated from SuperClass in method badMethod\n",
				checker.checkHollywoodViolations(curClass, superClass));
	}
	
	@Test
	public void testUsingFieldVarInMethod() {
		this.initializeField();
		this.initializeInsn(true);
		this.initializeLocal();
		this.initializeMethod("badMethod", true, true, true);
		curClass = new MyClassNode("SubClass", "", null, new ArrayList<MyFieldNode>(), methods, 0);
		superClass = new MyClassNode("SuperClass", "", null, fields, new ArrayList<MyMethodNode>(), 0);
		
		assertEquals("", checker.checkHollywoodViolations(curClass, superClass));
	}
	
	@Test
	public void testUsingMethod() {
		this.initializeInsn(false);
		this.initializeMethod("badMethod", true, false, true);
		this.initializeMethod("repeated", false, false, false);
		curClass = new MyClassNode("SubClass", "", null, new ArrayList<MyFieldNode>(), methods, 0);
		superClass = new MyClassNode("SuperClass", "", null, new ArrayList<MyFieldNode>(), methods2, 0);
		
		assertEquals("	Class SubClass calls method repeated from SuperClass in method badMethod\n",
				checker.checkHollywoodViolations(curClass, superClass));
		
	}
}
