package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyFieldNode;
import data_source.MyLineNumberNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;

class FacadePatternIntegrationTest {

	@Test
	public void testGetName() {
		FacadePatternCheck check = new FacadePatternCheck();
		assertEquals("Facade Pattern", check.getName());
	}
	
	@Test
	public void testFindFacadePatternShortName() {
		FacadePatternCheck check = new FacadePatternCheck();
		
		MyMethodInsnNode mInsn1 = new MyMethodInsnNode("", "Java/PopcornMaker", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn2 = new MyMethodInsnNode("", "Java/ArrayList", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn3 = new MyMethodInsnNode("", "Java/Projector", MyAbstractInsnNode.METHOD_INSN);
		LinkedList<MyAbstractInsnNode> instructions = new LinkedList<>(Arrays.asList(mInsn1, mInsn2, mInsn3));

		MyFieldNode field1 = new MyFieldNode(null, "LJava/PopcornMaker;", 0);
		MyFieldNode field2 = new MyFieldNode(null, "L", 0);
		MyFieldNode field3 = new MyFieldNode(null, "LJava/Projector;", 0);
		ArrayList<MyFieldNode> fields = new ArrayList<>(Arrays.asList(field1, field2, field3));
		
		MyMethodNode method1 = new MyMethodNode("", "", instructions, null);
		ArrayList<MyMethodNode> methods = new ArrayList<>(Arrays.asList(method1));
		
		MyClassNode classNode1 = new MyClassNode("Java/Theater", "", null, fields, methods, 0);
		MyClassNode classNode2 = new MyClassNode("Java/PopcornMaker", "", null, new ArrayList<>(), null, 0);
		MyClassNode classNode3 = new MyClassNode("Java/Projector", "", null, new ArrayList<>(), null, 0);
	
		ArrayList<MyClassNode> classes = new ArrayList<>(Arrays.asList(classNode1, classNode2, classNode3));
		
		assertEquals("\nFacade Pattern Check:\n" + "	Java/Theater looks to be a facade for the following classes:\n" 
				+ "		Java/PopcornMaker\n" + "		Java/Projector\n", check.runCheck(classes));
	}
	
	@Test
	public void testCheckForFacadeTrue() {
		FacadePatternCheck check = new FacadePatternCheck();
		
		MyMethodInsnNode mInsn1 = new MyMethodInsnNode("", "Java/PopcornMaker", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn2 = new MyMethodInsnNode("", "Java/ArrayList", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn3 = new MyMethodInsnNode("", "Java/Projector", MyAbstractInsnNode.METHOD_INSN);
		MyLineNumberNode lInsn = new MyLineNumberNode(12);
		LinkedList<MyAbstractInsnNode> instructions = new LinkedList<>(Arrays.asList(mInsn1, mInsn2, mInsn3, lInsn));

		MyMethodNode method1 = new MyMethodNode("", "", instructions, null);
		ArrayList<MyMethodNode> methods = new ArrayList<>(Arrays.asList(method1));
		
		MyClassNode classNode = new MyClassNode("Java/Theater", "", null, null, methods, 0);
		
		ArrayList<String> fieldUserClassNames = new ArrayList<>(Arrays.asList("Java/PopcornMaker", "Java/Projector"));
		
		assertEquals("	Java/Theater looks to be a facade for the following classes:\n" 
				+ "		Java/PopcornMaker\n" + "		Java/Projector\n", check.checkForFacade(classNode, fieldUserClassNames));
	}
	
	@Test
	public void testCheckForFacadeMissingClasses() {
		FacadePatternCheck check = new FacadePatternCheck();
		
		MyMethodInsnNode mInsn1 = new MyMethodInsnNode("", "Java/PopcornMaker", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn2 = new MyMethodInsnNode("", "Java/ArrayList", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn3 = new MyMethodInsnNode("", "Java/Projector", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn4 = new MyMethodInsnNode("", "Java/Sound", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn5 = new MyMethodInsnNode("", "Java/Computer", MyAbstractInsnNode.INVOKE_VIRTUAL);
		MyMethodInsnNode mInsn6 = new MyMethodInsnNode("", "Java/Recliner", MyAbstractInsnNode.INVOKE_VIRTUAL);
		LinkedList<MyAbstractInsnNode> instructions = new LinkedList<>(Arrays.asList(mInsn1, mInsn2, mInsn3, mInsn4));

		MyMethodNode method1 = new MyMethodNode("", "", instructions, null);
		ArrayList<MyMethodNode> methods = new ArrayList<>(Arrays.asList(method1));
		
		MyClassNode classNode = new MyClassNode("Java/Theater", "", null, null, methods, 0);
		
		ArrayList<String> fieldUserClassNames = new ArrayList<>(Arrays.asList("Java/PopcornMaker", "Java/Projector", "Java/Lights", "Java/Computer"));
		
		assertEquals("	Java/Theater might be an attempt at facade pattern. It is missing calls to methods in these classes:\n" 
				+ "		Java/Lights\n", check.checkForFacade(classNode, fieldUserClassNames));
	}
	
	@Test
	public void testRunCheckWithFacade() {
		FacadePatternCheck check = new FacadePatternCheck();
		
		MyMethodInsnNode mInsn1 = new MyMethodInsnNode("", "Java/PopcornMaker", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn2 = new MyMethodInsnNode("", "Java/ArrayList", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn3 = new MyMethodInsnNode("", "Java/Projector", MyAbstractInsnNode.METHOD_INSN);
		LinkedList<MyAbstractInsnNode> instructions = new LinkedList<>(Arrays.asList(mInsn1, mInsn2, mInsn3));

		MyFieldNode field1 = new MyFieldNode(null, "LJava/PopcornMaker;", 0);
		MyFieldNode field2 = new MyFieldNode(null, "LJava/ArrayList;", 0);
		MyFieldNode field3 = new MyFieldNode(null, "LJava/Projector;", 0);
		ArrayList<MyFieldNode> fields = new ArrayList<>(Arrays.asList(field1, field2, field3));
		
		MyMethodNode method1 = new MyMethodNode("", "", instructions, null);
		ArrayList<MyMethodNode> methods = new ArrayList<>(Arrays.asList(method1));
		
		MyClassNode classNode1 = new MyClassNode("Java/Theater", "", null, fields, methods, 0);
		MyClassNode classNode2 = new MyClassNode("Java/PopcornMaker", "", null, new ArrayList<>(), null, 0);
		MyClassNode classNode3 = new MyClassNode("Java/Projector", "", null, new ArrayList<>(), null, 0);
	
		ArrayList<MyClassNode> classes = new ArrayList<>(Arrays.asList(classNode1, classNode2, classNode3));
		
		assertEquals("\nFacade Pattern Check:\n" + "	Java/Theater looks to be a facade for the following classes:\n" 
				+ "		Java/PopcornMaker\n" + "		Java/Projector\n", check.runCheck(classes));
	}
	
	@Test
	public void testRunCheckWithFailedFacade() {
		FacadePatternCheck check = new FacadePatternCheck();
		
		MyMethodInsnNode mInsn1 = new MyMethodInsnNode("", "Java/PopcornMaker", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn2 = new MyMethodInsnNode("", "Java/ArrayList", MyAbstractInsnNode.METHOD_INSN);
		MyMethodInsnNode mInsn3 = new MyMethodInsnNode("", "Java/Projector", MyAbstractInsnNode.METHOD_INSN);
		LinkedList<MyAbstractInsnNode> instructions = new LinkedList<>(Arrays.asList(mInsn1, mInsn2, mInsn3));

		MyFieldNode field1 = new MyFieldNode(null, "LJava/PopcornMaker;", 0);
		MyFieldNode field2 = new MyFieldNode(null, "LJava/Lights;", 0);
		MyFieldNode field3 = new MyFieldNode(null, "LJava/Projector;", 0);
		ArrayList<MyFieldNode> fields = new ArrayList<>(Arrays.asList(field1, field2, field3));
		
		MyMethodNode method1 = new MyMethodNode("", "", instructions, null);
		ArrayList<MyMethodNode> methods = new ArrayList<>(Arrays.asList(method1));
		
		MyClassNode classNode1 = new MyClassNode("Java/TheaterFacade", "", null, fields, methods, 0);
		MyClassNode classNode2 = new MyClassNode("Java/PopcornMaker", "", null, new ArrayList<>(), null, 0);
		MyClassNode classNode3 = new MyClassNode("Java/Projector", "", null, new ArrayList<>(), null, 0);
		MyClassNode classNode4 = new MyClassNode("Java/Lights", "", null, new ArrayList<>(), null, 0);
		
		ArrayList<MyClassNode> classes = new ArrayList<>(Arrays.asList(classNode1, classNode2, classNode3, classNode4));
		
		assertEquals("\nFacade Pattern Check:\n" + "	Java/TheaterFacade might be an attempt at facade pattern. It is missing calls to methods in these classes:\n" 
				+ "		Java/Lights\n", check.runCheck(classes));
	}
	
	@Test
	public void testRunCheckWithClassNameFacade() {
		FacadePatternCheck check = new FacadePatternCheck();
		
		MyClassNode classNode1 = new MyClassNode("Java/TheaterFacade", "", null, new ArrayList<>(), null, 0);
		MyClassNode classNode2 = new MyClassNode("Java/PopcornMaker", "", null, new ArrayList<>(), null, 0);
		MyClassNode classNode3 = new MyClassNode("Java/Projector", "", null, new ArrayList<>(), null, 0);
		MyClassNode classNode4 = new MyClassNode("Java/Lights", "", null, new ArrayList<>(), null, 0);
		
		ArrayList<MyClassNode> classes = new ArrayList<>(Arrays.asList(classNode1, classNode2, classNode3, classNode4));
		
		assertEquals("\nFacade Pattern Check:\n" + "	Java/TheaterFacade contains the word 'facade' but is not a facade pattern\n"
				, check.runCheck(classes));
	}
	
	@Test
	public void testRunCheckWithNoFacade() {
		FacadePatternCheck check = new FacadePatternCheck();
		
		MyClassNode classNode1 = new MyClassNode("Java/Theater", "", null, new ArrayList<>(), null, 0);
		MyClassNode classNode2 = new MyClassNode("Java/PopcornMaker", "", null, new ArrayList<>(), null, 0);
		MyClassNode classNode3 = new MyClassNode("Java/Projector", "", null, new ArrayList<>(), null, 0);
		MyClassNode classNode4 = new MyClassNode("Java/Lights", "", null, new ArrayList<>(), null, 0);
		
		ArrayList<MyClassNode> classes = new ArrayList<>(Arrays.asList(classNode1, classNode2, classNode3, classNode4));
		
		assertEquals("", check.runCheck(classes));
	}

}
