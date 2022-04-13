package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.easymock.EasyMock;
import org.easymock.asm.Type;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.Opcodes;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyFieldInsnNode;
import data_source.MyFieldNode;
import data_source.MyLocalVariableNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;
import domain.StrategyPatternCheck;

class StrategyPatternTest {

	StrategyPatternCheck checker = new StrategyPatternCheck();
	
	@Test
	void failNoCall() {
		List<String> interfaceClassInterfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClassFields = new ArrayList<>();
		List<MyMethodNode> interfaceClassMethods = new ArrayList<>();
		
		MyClassNode interfaceClass = new MyClassNode("Pattern", "", 
				interfaceClassInterfaces, 
				interfaceClassFields, 
				interfaceClassMethods,
				Opcodes.ACC_INTERFACE
				);
		
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();
		
		MyFieldNode interfaceField = new MyFieldNode("strategyPattern", "Pattern", 0);
		contextClassFields.add(interfaceField);
		
		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();
		
		MyFieldInsnNode interfaceInit = new MyFieldInsnNode("strategyPattern", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interfaceInit);
		
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "(LPattern;)V",
				contextConstructorInsn,
				contextConstructorlocalVariables
				);
		contextClassMethods.add(contextConstructor);
		
		LinkedList<MyAbstractInsnNode> contextMethodInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextMethodlocalVariables = new ArrayList<>();
		
		MyMethodNode contextMethod = new MyMethodNode("contextMethod", "()V",
				contextMethodInsn,
				contextMethodlocalVariables
				);
		
		contextClassMethods.add(contextMethod);
		
		MyClassNode contextClass = new MyClassNode("AlmostStrategy", "",
				contextClassInterfaces,
				contextClassFields,
				contextClassMethods,
				Opcodes.ACC_PUBLIC
				);
		
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(contextClass);
		
		assertEquals(checker.runCheck(classes),
				"\nStrategy Pattern Implementations: \n"
				+ "	Strategy pattern is nearly implemented in AlmostStrategy"
				+ " using interface Pattern. To finish implementing strategy pattern, the function/functions "
						+ "called from the interface must be used\n");
	}
	
	@Test
	void failNotConstructored()
	{
		List<String> interfaceClassInterfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClassFields = new ArrayList<>();
		List<MyMethodNode> interfaceClassMethods = new ArrayList<>();
		
		MyClassNode interfaceClass = new MyClassNode("Pattern", "", 
				interfaceClassInterfaces, 
				interfaceClassFields, 
				interfaceClassMethods,
				Opcodes.ACC_INTERFACE
				);
		
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();
		
		MyFieldNode interfaceField = new MyFieldNode("strategyPattern", "Pattern", 0);
		contextClassFields.add(interfaceField);
		
		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();
		
		MyFieldInsnNode interfaceInit = new MyFieldInsnNode("strategyPattern", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interfaceInit);
		
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "()V",
				contextConstructorInsn,
				contextConstructorlocalVariables
				);
		contextClassMethods.add(contextConstructor);
		
		MyClassNode contextClass = new MyClassNode("AlmostStrategy", "",
				contextClassInterfaces,
				contextClassFields,
				contextClassMethods,
				Opcodes.ACC_PUBLIC
				);
		
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(contextClass);
		
		assertEquals(checker.runCheck(classes), "");
	}
	
	@Test
	void passSingleStrategy()
	{
		List<String> interfaceClassInterfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClassFields = new ArrayList<>();
		List<MyMethodNode> interfaceClassMethods = new ArrayList<>();
		
		MyClassNode interfaceClass = new MyClassNode("Pattern", "", 
				interfaceClassInterfaces, 
				interfaceClassFields, 
				interfaceClassMethods,
				Opcodes.ACC_INTERFACE
				);
		
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();
		
		MyFieldNode interfaceField = new MyFieldNode("strategyPattern", "Pattern", 0);
		contextClassFields.add(interfaceField);
		
		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();
		
		MyFieldInsnNode interfaceInit = new MyFieldInsnNode("strategyPattern", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interfaceInit);
		
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "(LPattern;)V",
				contextConstructorInsn,
				contextConstructorlocalVariables
				);
		contextClassMethods.add(contextConstructor);
		
		LinkedList<MyAbstractInsnNode> contextMethodInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextMethodlocalVariables = new ArrayList<>();
		
		MyMethodInsnNode methodInsn = new MyMethodInsnNode("methodCall", "Pattern", Opcodes.INVOKEVIRTUAL);
		contextMethodInsn.add(methodInsn);
		
		MyMethodNode contextMethod = new MyMethodNode("contextMethod", "()V",
				contextMethodInsn,
				contextMethodlocalVariables
				);
		
		contextClassMethods.add(contextMethod);
		
		MyClassNode contextClass = new MyClassNode("Strategy1", "",
				contextClassInterfaces,
				contextClassFields,
				contextClassMethods,
				Opcodes.ACC_PUBLIC
				);
		
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(contextClass);
		
		assertEquals(checker.runCheck(classes),
		"\nStrategy Pattern Implementations: \n"
		+ "	Implemented in Strategy1"
		+ " class using interface Pattern"
		+ ", and invoked for the first time in method contextMethod\n");
	}
	
	@Test
	void passMultiStrategy()
	{
		List<String> interfaceClass1Interfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClass1Fields = new ArrayList<>();
		List<MyMethodNode> interfaceClass1Methods = new ArrayList<>();
		
		MyClassNode interface1Class = new MyClassNode("Pattern1", "", 
				interfaceClass1Interfaces, 
				interfaceClass1Fields, 
				interfaceClass1Methods,
				Opcodes.ACC_INTERFACE
				);
		
		List<String> interfaceClass2Interfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClass2Fields = new ArrayList<>();
		List<MyMethodNode> interfaceClass2Methods = new ArrayList<>();
		
		MyClassNode interface2Class = new MyClassNode("Pattern2", "", 
				interfaceClass2Interfaces, 
				interfaceClass2Fields, 
				interfaceClass2Methods,
				Opcodes.ACC_INTERFACE
				);
		
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();
		
		MyFieldNode interface1Field = new MyFieldNode("strategyPattern1", "Pattern1", 0);
		contextClassFields.add(interface1Field);
		
		MyFieldNode interface2Field = new MyFieldNode("strategyPattern2", "Pattern2", 0);
		contextClassFields.add(interface2Field);
		
		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();
		
		MyFieldInsnNode interface1Init = new MyFieldInsnNode("strategyPattern1", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interface1Init);
		
		MyFieldInsnNode interface2Init = new MyFieldInsnNode("strategyPattern2", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interface2Init);
		
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "(LPattern1;LPattern2;)V",
				contextConstructorInsn,
				contextConstructorlocalVariables
				);
		contextClassMethods.add(contextConstructor);
		
		LinkedList<MyAbstractInsnNode> contextMethodInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextMethodlocalVariables = new ArrayList<>();
		
		MyMethodInsnNode methodInsn1 = new MyMethodInsnNode("methodCall1", "Pattern1", Opcodes.INVOKEVIRTUAL);
		contextMethodInsn.add(methodInsn1);
		
		MyMethodInsnNode methodInsn2 = new MyMethodInsnNode("methodCall2", "Pattern2", Opcodes.INVOKEVIRTUAL);
		contextMethodInsn.add(methodInsn2);
		
		MyMethodNode contextMethod = new MyMethodNode("contextMethod", "()V",
				contextMethodInsn,
				contextMethodlocalVariables
				);
		
		contextClassMethods.add(contextMethod);
		
		MyClassNode contextClass = new MyClassNode("Strategy2", "",
				contextClassInterfaces,
				contextClassFields,
				contextClassMethods,
				Opcodes.ACC_PUBLIC
				);
		
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interface1Class);
		classes.add(interface2Class);
		classes.add(contextClass);
		
		assertEquals(checker.runCheck(classes),
		"\nStrategy Pattern Implementations: \n"
		+ "	Implemented in Strategy2"
		+ " class using interface Pattern1"
		+ ", and invoked for the first time in method contextMethod\n"
		+ "	Implemented in Strategy2"
		+ " class using interface Pattern2"
		+ ", and invoked for the first time in method contextMethod\n");
	}
}