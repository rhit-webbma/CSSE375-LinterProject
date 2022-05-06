package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.objectweb.asm.Opcodes;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyFieldInsnNode;
import data_source.MyLineNumberNode;
import data_source.MyLocalVariableNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;
import data_source.MyVarInsnNode;

class UnusedInstantiationIntegrationTest {

	@Test
	public void testGetName() {
		ClassCheck check = new UnusedInstantiationCheck();
		assertEquals("Unused Instantiation", check.getName());
	}
	
	@Test
	public void testDetermineFieldStatusLoading() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		MyFieldInsnNode fInsn = new MyFieldInsnNode("", "", Opcodes.GETFIELD);
		
		assertTrue(check.fieldStates.fieldLoading.isEmpty());
		assertTrue(check.fieldStates.fieldStoring.isEmpty());
		check.determineFieldStatus(fInsn);
		assertTrue(!check.fieldStates.fieldLoading.isEmpty());
		assertTrue(check.fieldStates.fieldLoading.contains(fInsn));
		assertTrue(check.fieldStates.fieldStoring.isEmpty());
	}
	
	@Test
	public void testDetermineFieldStatusStoring() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		MyFieldInsnNode fInsn = new MyFieldInsnNode("", "", Opcodes.PUTFIELD);
		
		assertTrue(check.fieldStates.fieldLoading.isEmpty());
		assertTrue(check.fieldStates.fieldStoring.isEmpty());
		check.determineFieldStatus(fInsn);
		assertTrue(check.fieldStates.fieldLoading.isEmpty());
		assertTrue(!check.fieldStates.fieldStoring.isEmpty());
		assertTrue(check.fieldStates.fieldStoring.contains(fInsn));
	}
	
	@Test
	public void testFindLineNumberStartOnLineNumberNode() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		
		MyLineNumberNode lNode = new MyLineNumberNode(12);
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<>(Arrays.asList(lNode));

		MyMethodNode method = new MyMethodNode("", "", insns, null);
		
		assertEquals(12, check.findLineNumber(0, method));
	}
	
	@Test
	public void testFindLineNumberStartNotOnLineNumberNode() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		
		MyLineNumberNode lNode = new MyLineNumberNode(12);
		MyMethodInsnNode mNode = new MyMethodInsnNode(null, null, 0);
		MyFieldInsnNode fNode = new MyFieldInsnNode(null, null, 0);
		MyVarInsnNode vNode = new MyVarInsnNode(0, 0);
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<>(Arrays.asList(lNode, mNode, fNode, vNode));

		MyMethodNode method = new MyMethodNode("", "", insns, null);
		
		assertEquals(12, check.findLineNumber(3, method));
	}
	
	@Test
	public void testFindUnusedVariableLocationsUnusedNamed() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		
		MyVarInsnNode lNode1 = new MyVarInsnNode(2, Opcodes.ALOAD);
		MyVarInsnNode lNode2 = new MyVarInsnNode(3, Opcodes.ALOAD);
		MyVarInsnNode lNode3 = new MyVarInsnNode(4, Opcodes.ALOAD);
		
		MyVarInsnNode sNode1 = new MyVarInsnNode(0, Opcodes.ASTORE);
		MyVarInsnNode sNode2 = new MyVarInsnNode(2, Opcodes.ASTORE);
		MyVarInsnNode sNode3 = new MyVarInsnNode(3, Opcodes.ASTORE);
		
		VarStates vStates = new VarStates();
		vStates.varLoading = new ArrayList<>(Arrays.asList(lNode1, lNode2, lNode3));
		vStates.varStoring = new ArrayList<>(Arrays.asList(sNode1, sNode2, sNode3));

		
		MyLineNumberNode lNode = new MyLineNumberNode(12);
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<>(Arrays.asList(lNode, sNode1));

		MyLocalVariableNode vLNode = new MyLocalVariableNode("counter", null);
		ArrayList<MyLocalVariableNode> vars = new ArrayList<>(Arrays.asList(vLNode));
		
		MyMethodNode method = new MyMethodNode("countThings", null, insns, vars);
		
		assertEquals("			Line 12: Unused variable named counter in method countThings\n", check.findUnusedVariables(vStates, method));
	}
	
	@Test
	public void testFindUnusedVariableLocationsUnusedUnknownName() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		
		MyVarInsnNode lNode1 = new MyVarInsnNode(2, Opcodes.ALOAD);
		MyVarInsnNode lNode2 = new MyVarInsnNode(3, Opcodes.ALOAD);
		MyVarInsnNode lNode3 = new MyVarInsnNode(4, Opcodes.ALOAD);
		
		MyVarInsnNode sNode1 = new MyVarInsnNode(0, Opcodes.ASTORE);
		MyVarInsnNode sNode2 = new MyVarInsnNode(2, Opcodes.ASTORE);
		MyVarInsnNode sNode3 = new MyVarInsnNode(3, Opcodes.ASTORE);
		
		VarStates vStates = new VarStates();
		vStates.varLoading = new ArrayList<>(Arrays.asList(lNode1, lNode2, lNode3));
		vStates.varStoring = new ArrayList<>(Arrays.asList(sNode1, sNode2, sNode3));
		
		MyLineNumberNode lNode = new MyLineNumberNode(12);
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<>(Arrays.asList(lNode, sNode1));

		ArrayList<MyLocalVariableNode> vars = new ArrayList<>();
		
		MyMethodNode method = new MyMethodNode("countThings", null, insns, vars);

		assertEquals("			Line 12: Unused variable in method countThings\n", check.findUnusedVariables(vStates, method));
	}
	
	@Test
	public void testFindVariablesMethodsLocalVars() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		
		MyVarInsnNode lNode1 = new MyVarInsnNode(2, Opcodes.ALOAD);
		MyVarInsnNode lNode2 = new MyVarInsnNode(3, Opcodes.ALOAD);
		
		MyVarInsnNode sNode1 = new MyVarInsnNode(0, Opcodes.ASTORE);
		MyVarInsnNode sNode2 = new MyVarInsnNode(2, Opcodes.ASTORE);
		MyVarInsnNode sNode3 = new MyVarInsnNode(3, Opcodes.ASTORE);
		
		MyLineNumberNode lNode = new MyLineNumberNode(12);
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<>(Arrays.asList(lNode1, lNode2, lNode, sNode1, sNode2, sNode3));

		MyLocalVariableNode vLNode = new MyLocalVariableNode("counter", null);
		ArrayList<MyLocalVariableNode> vars = new ArrayList<>(Arrays.asList(vLNode));
		
		MyMethodNode method = new MyMethodNode("countThings", null, insns, vars);
		
		assertEquals("			Line 12: Unused variable named counter in method countThings\n", check.findVariablesMethods(method));
	}
	
	@Test
	public void testFindVariablesMethodsFields() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		
		MyLineNumberNode lNode = new MyLineNumberNode(12);
		MyFieldInsnNode fInsn1 = new MyFieldInsnNode(null, null, Opcodes.GETFIELD);
		MyFieldInsnNode fInsn2 = new MyFieldInsnNode(null, null, Opcodes.PUTFIELD);
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<>(Arrays.asList(lNode, fInsn1, fInsn2));
		
		MyMethodNode method = new MyMethodNode("countThings", null, insns, null);
		
		assertEquals("", check.findVariablesMethods(method));
		assertTrue(check.fieldStates.fieldLoading.contains(fInsn1));
		assertTrue(check.fieldStates.fieldStoring.contains(fInsn2));
	}
	
	@Test
	public void testFindUnusedFieldsUnknownLine() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		
		MyFieldInsnNode fInsn1 = new MyFieldInsnNode("counter", null, Opcodes.GETFIELD);
		MyFieldInsnNode fInsn2 = new MyFieldInsnNode("download", null, Opcodes.PUTFIELD);
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<>();
		
		MyMethodNode method = new MyMethodNode("countThings", null, insns, null);
		
		ArrayList<MyMethodNode> methods = new ArrayList<>(Arrays.asList(method));
		
		MyClassNode classNode = new MyClassNode(null, null, null, null, methods, 0);
		
		check.fieldStates.fieldLoading = new ArrayList<>(Arrays.asList(fInsn1));
		check.fieldStates.fieldStoring = new ArrayList<>(Arrays.asList(fInsn2));
		
		assertEquals("			Unknown line number: Unused field named download\n", check.findUnusedFields(classNode));
	}
	
	@Test
	public void testFindUnusedFieldsKnownLine() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		
		MyLineNumberNode lNode = new MyLineNumberNode(12);
		MyFieldInsnNode fInsn1 = new MyFieldInsnNode("counter", null, Opcodes.GETFIELD);
		MyFieldInsnNode fInsn2 = new MyFieldInsnNode("download", null, Opcodes.PUTFIELD);
		MyFieldInsnNode fInsn3 = new MyFieldInsnNode("counter", null, Opcodes.PUTFIELD);
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<>(Arrays.asList(lNode, fInsn3, fInsn2, fInsn1));
		
		MyMethodNode method = new MyMethodNode("countThings", null, insns, null);
		
		ArrayList<MyMethodNode> methods = new ArrayList<>(Arrays.asList(method));
		
		MyClassNode classNode = new MyClassNode(null, null, null, null, methods, 0);
		
		check.fieldStates.fieldLoading = new ArrayList<>(Arrays.asList(fInsn1));
		check.fieldStates.fieldStoring = new ArrayList<>(Arrays.asList(fInsn2, fInsn3));
		
		assertEquals("			Line 12: Unused field named download\n", check.findUnusedFields(classNode));
	}
	
	@Test
	public void testRunCheck() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		
		MyVarInsnNode lInsn1 = new MyVarInsnNode(2, Opcodes.ALOAD);
		MyVarInsnNode lInsn2 = new MyVarInsnNode(3, Opcodes.ALOAD);
		
		MyVarInsnNode sNode1 = new MyVarInsnNode(0, Opcodes.ASTORE);
		MyVarInsnNode sNode2 = new MyVarInsnNode(2, Opcodes.ASTORE);
		MyVarInsnNode sNode3 = new MyVarInsnNode(3, Opcodes.ASTORE);
		
		MyLineNumberNode lNode1 = new MyLineNumberNode(15);
		MyLineNumberNode lNode2 = new MyLineNumberNode(12);
		
		MyFieldInsnNode fInsn1 = new MyFieldInsnNode("counter", null, Opcodes.GETFIELD);
		MyFieldInsnNode fInsn2 = new MyFieldInsnNode("download", null, Opcodes.PUTFIELD);
		
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<>(Arrays.asList(lInsn1, lInsn2, lNode1, sNode1, sNode2, sNode3, lNode2, fInsn1, fInsn2));

		MyLocalVariableNode vLNode = new MyLocalVariableNode("counter", null);
		ArrayList<MyLocalVariableNode> vars = new ArrayList<>(Arrays.asList(vLNode));
		
		MyMethodNode method = new MyMethodNode("countThings", null, insns, vars);
		ArrayList<MyMethodNode> methods = new ArrayList<>(Arrays.asList(method));
		
		MyClassNode classNode = new MyClassNode("Counting", null, null, null, methods, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>(Arrays.asList(classNode));
		
		assertEquals("Unused Instantiation Check:\n	Class: Counting\n		Unused Variables: \n" + "			Line 12: Unused field named download\n" 
				+ "			Line 15: Unused variable named counter in method countThings\n" , check.runCheck(classes));

		assertTrue(check.fieldStates.fieldLoading.contains(fInsn1));
		assertTrue(check.fieldStates.fieldStoring.contains(fInsn2));
	}
	
	@Test
	public void testRunCheckGood() {
		UnusedInstantiationCheck check = new UnusedInstantiationCheck();
		
		MyVarInsnNode lInsn1 = new MyVarInsnNode(2, Opcodes.ALOAD);
		MyVarInsnNode lInsn2 = new MyVarInsnNode(3, Opcodes.ALOAD);
		MyVarInsnNode lInsn3 = new MyVarInsnNode(0, Opcodes.ALOAD);
		
		MyVarInsnNode sNode1 = new MyVarInsnNode(0, Opcodes.ASTORE);
		MyVarInsnNode sNode2 = new MyVarInsnNode(2, Opcodes.ASTORE);
		MyVarInsnNode sNode3 = new MyVarInsnNode(3, Opcodes.ASTORE);
		
		MyLineNumberNode lNode1 = new MyLineNumberNode(15);
		MyLineNumberNode lNode2 = new MyLineNumberNode(12);
		
		MyFieldInsnNode fInsn1 = new MyFieldInsnNode("counter", null, Opcodes.GETFIELD);
		MyFieldInsnNode fInsn2 = new MyFieldInsnNode("counter", null, Opcodes.PUTFIELD);
		
		LinkedList<MyAbstractInsnNode> insns = new LinkedList<>(Arrays.asList(lInsn1, lInsn2, lInsn3, lNode1, sNode1, sNode2, sNode3, lNode2, fInsn1, fInsn2));

		MyLocalVariableNode vLNode = new MyLocalVariableNode("counter", null);
		ArrayList<MyLocalVariableNode> vars = new ArrayList<>(Arrays.asList(vLNode));
		
		MyMethodNode method = new MyMethodNode("countThings", null, insns, vars);
		ArrayList<MyMethodNode> methods = new ArrayList<>(Arrays.asList(method));
		
		MyClassNode classNode = new MyClassNode("Counting", null, null, null, methods, 0);
		ArrayList<MyClassNode> classes = new ArrayList<>(Arrays.asList(classNode));
		
		assertEquals("" , check.runCheck(classes));

		assertTrue(check.fieldStates.fieldLoading.contains(fInsn1));
		assertTrue(check.fieldStates.fieldStoring.contains(fInsn2));
	}

}
