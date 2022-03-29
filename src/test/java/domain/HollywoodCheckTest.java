package domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;
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
import data_source.MyLocalVariableNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;


// Note: These tests were written for when the system did not use personalized class nodes
public class HollywoodCheckTest {
	HollywoodCheck checker = new HollywoodCheck();
	
	@Test
	public void testUsingField() {
		MyClassNode curClass = EasyMock.createMock(MyClassNode.class);
		ArrayList<String> superFieldNames = new ArrayList<>();
		superFieldNames.add("repeated");
		ArrayList<String> superMethodNames = new ArrayList<>();
		String superName = "SuperClass";
		MyMethodNode m1 = EasyMock.createMock(MyMethodNode.class);
		ArrayList<MyMethodNode> methods = new ArrayList<MyMethodNode>();
		methods.add(m1);
		EasyMock.expect(curClass.getCleanName()).andReturn("SubClass");
		curClass.methods = methods;
		
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<MyAbstractInsnNode>();
		MyFieldInsnNode n1a = EasyMock.createMock(MyFieldInsnNode.class);
		n1a.name = "repeated";
		MyAbstractInsnNode n1 = n1a;
		insns.add(n1);
		
		m1.instructions = insns;
		m1.name = "badMethod";
		EasyMock.expect(m1.getVarNames()).andReturn(new ArrayList<String>());
		
		EasyMock.replay(curClass, m1, n1a);
		assertEquals("	Class SubClass uses field repeated from SuperClass in method badMethod\n",
				checker.checkHollywoodViolations(curClass, superFieldNames, superMethodNames, superName));
	}
	
	@Test
	public void testUsingFieldVarInMethod() {
		MyClassNode curClass = EasyMock.createMock(MyClassNode.class);
		ArrayList<String> superFieldNames = new ArrayList<>();
		superFieldNames.add("repeated");
		ArrayList<String> superMethodNames = new ArrayList<>();
		String superName = "SuperClass";
		MyMethodNode m1 = EasyMock.createMock(MyMethodNode.class);
		ArrayList<MyMethodNode> methods = new ArrayList<MyMethodNode>();
		methods.add(m1);
		EasyMock.expect(curClass.getCleanName()).andReturn("SubClass");
		curClass.methods = methods;
		
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<MyAbstractInsnNode>();
		MyFieldInsnNode n1a = EasyMock.createMock(MyFieldInsnNode.class);
		n1a.name = "repeated";
		MyAbstractInsnNode n1 = n1a;
		insns.add(n1);
		
		MyLocalVariableNode lv1 = EasyMock.createMock(MyLocalVariableNode.class);
		ArrayList<String> locals = new ArrayList<>();
		locals.add("repeated");
		m1.instructions = insns;
		m1.name = "badMethod";
		EasyMock.expect(m1.getVarNames()).andReturn(locals);
		
		
		EasyMock.replay(curClass, m1, n1a, lv1);
		assertEquals("",
				checker.checkHollywoodViolations(curClass, superFieldNames, superMethodNames, superName));
	}
	
	@Test
	public void testUsingMethod() {
		MyClassNode curClass = EasyMock.createMock(MyClassNode.class);
		ArrayList<String> superFieldNames = new ArrayList<>();
		ArrayList<String> superMethodNames = new ArrayList<>();
		superMethodNames.add("repeated");
		String superName = "SuperClass";
		MyMethodNode m1 = EasyMock.createMock(MyMethodNode.class);
		ArrayList<MyMethodNode> methods = new ArrayList<MyMethodNode>();
		methods.add(m1);
		EasyMock.expect(curClass.getCleanName()).andReturn("SubClass");
		curClass.methods = methods;
		
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<MyAbstractInsnNode>();
		MyMethodInsnNode n1a = EasyMock.createMock(MyMethodInsnNode.class);
		n1a.name = "repeated";
		MyAbstractInsnNode n1 = n1a;
		insns.add(n1);
		
		m1.instructions = insns;
		m1.name = "badMethod";
		EasyMock.expect(m1.getVarNames()).andReturn(new ArrayList<String>());
		
		EasyMock.replay(curClass, m1, n1a);
		assertEquals("	Class SubClass calls method repeated from SuperClass in method badMethod\n",
				checker.checkHollywoodViolations(curClass, superFieldNames, superMethodNames, superName));
	}
}
