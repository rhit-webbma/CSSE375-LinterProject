package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
		
		//Arrange Stage
		
		//Creating Interface Class Parameters
		List<String> interfaceClassInterfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClassFields = new ArrayList<>();
		List<MyMethodNode> interfaceClassMethods = new ArrayList<>();
		
		//Creating the Interface Class
		MyClassNode interfaceClass = new MyClassNode("Pattern", "", 
				interfaceClassInterfaces, 
				interfaceClassFields, 
				interfaceClassMethods,
				Opcodes.ACC_INTERFACE
				);
		
		//Creating Context Class Parameters
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();
		
		//Creating a Field which has the Interface Classes Type
		MyFieldNode interfaceField = new MyFieldNode("strategyPattern", "Pattern", 0);
		contextClassFields.add(interfaceField);
		
		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();
		
		//Creating a False Method Call
		MyFieldInsnNode interfaceInit = new MyFieldInsnNode("strategyPattern", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interfaceInit);
		
		//Creating a Constructor with the Interface passed in through Dependency Injection
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "(LPattern;)V",
				contextConstructorInsn,
				contextConstructorlocalVariables
				);
		contextClassMethods.add(contextConstructor);
		
		LinkedList<MyAbstractInsnNode> contextMethodInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextMethodlocalVariables = new ArrayList<>();
		
		//Creating the method in Context that is supposed to call the Interfaces Method
		MyMethodNode contextMethod = new MyMethodNode("contextMethod", "()V",
				contextMethodInsn,
				contextMethodlocalVariables
				);
		
		contextClassMethods.add(contextMethod);
		
		//Creating of the context class which will hold the context call method
		MyClassNode contextClass = new MyClassNode("AlmostStrategy", "",
				contextClassInterfaces,
				contextClassFields,
				contextClassMethods,
				Opcodes.ACC_PUBLIC
				);
		
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(contextClass);
		
		String expected = "\nStrategy Pattern Implementations: \n"
				+ "	Strategy pattern is nearly implemented in AlmostStrategy"
				+ " using interface Pattern. To finish implementing strategy pattern, the function/functions "
						+ "called from the interface must be used\n";
		
		//Act Stage
		String actual = checker.runCheck(classes);
		
		
		//Assert Stage
		assertEquals(actual,
				expected);
	}

	@Test
	void testgetNameCheck()
	{
		assertEquals("Strategy Pattern", checker.getName());
	}
	
	@Test
	void failNotConstructored()
	{
		//Arrange Stage
		
		//Creating Interface Class Parameters
		List<String> interfaceClassInterfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClassFields = new ArrayList<>();
		List<MyMethodNode> interfaceClassMethods = new ArrayList<>();
		
		//Creating the Interface Class
		MyClassNode interfaceClass = new MyClassNode("Pattern", "", 
				interfaceClassInterfaces, 
				interfaceClassFields, 
				interfaceClassMethods,
				Opcodes.ACC_INTERFACE
				);
		
		//Creating Context Class Parameters
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();
		
		//Creating a Field which has the Interface Classes Type
		MyFieldNode interfaceField = new MyFieldNode("strategyPattern", "Pattern", 0);
		contextClassFields.add(interfaceField);
		
		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();
		
		//Creating a Method Call
		MyFieldInsnNode interfaceInit = new MyFieldInsnNode("strategyPattern", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interfaceInit);
		
		//Creating a Constructor with the Interface intentionally not passed in
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "()V",
				contextConstructorInsn,
				contextConstructorlocalVariables
				);
		contextClassMethods.add(contextConstructor);
		
		//Creating of the context class which will hold the context call method
		MyClassNode contextClass = new MyClassNode("AlmostStrategy", "",
				contextClassInterfaces,
				contextClassFields,
				contextClassMethods,
				Opcodes.ACC_PUBLIC
				);
		
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(contextClass);
		
		String expected = "";
		
		//Act stage
		String actual = checker.runCheck(classes);
		
		//Assert Stage
		assertEquals(actual, expected);
	}
	
	@Test
	void passSingleStrategy()
	{
		//Arrange Stage
		
		//Creating Interface Class Parameters
		List<String> interfaceClassInterfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClassFields = new ArrayList<>();
		List<MyMethodNode> interfaceClassMethods = new ArrayList<>();
		
		//Creating the Interface Class
		MyClassNode interfaceClass = new MyClassNode("Pattern", "", 
				interfaceClassInterfaces, 
				interfaceClassFields, 
				interfaceClassMethods,
				Opcodes.ACC_INTERFACE
				);
		
		//Creating Context Class Parameters
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();
		
		//Creating a Field which has the Interface Classes Type
		MyFieldNode interfaceField = new MyFieldNode("strategyPattern", "Pattern", 0);
		contextClassFields.add(interfaceField);
		
		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();
		
		//Creating a Method Call
		MyFieldInsnNode interfaceInit = new MyFieldInsnNode("strategyPattern", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interfaceInit);
		
		//Creating a Constructor with the Interface passed in through Dependency Injection
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "(LPattern;)V",
				contextConstructorInsn,
				contextConstructorlocalVariables
				);
		contextClassMethods.add(contextConstructor);
		
		LinkedList<MyAbstractInsnNode> contextMethodInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextMethodlocalVariables = new ArrayList<>();
		
		//Creating a method Instruction call to the interface to complete the strategy pattern
		MyMethodInsnNode methodInsn = new MyMethodInsnNode("methodCall", "Pattern", Opcodes.INVOKEVIRTUAL);
		contextMethodInsn.add(methodInsn);
		
		//Creating the method in Context that is supposed to call the Interfaces Method
		MyMethodNode contextMethod = new MyMethodNode("contextMethod", "()V",
				contextMethodInsn,
				contextMethodlocalVariables
				);
		
		contextClassMethods.add(contextMethod);
		
		//Creating of the context class which will hold the context call method
		MyClassNode contextClass = new MyClassNode("Strategy1", "",
				contextClassInterfaces,
				contextClassFields,
				contextClassMethods,
				Opcodes.ACC_PUBLIC
				);
		
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(contextClass);
		
		String expected = 		"\nStrategy Pattern Implementations: \n"
				+ "	Implemented in Strategy1"
				+ " class using interface Pattern"
				+ ", and invoked for the first time in method contextMethod\n";
		
		//Act Stage
		
		String actual = checker.runCheck(classes);
		
		//Assert Stage
		
		assertEquals(actual, expected);
	}

	@Test
	void passSingleStrategyWithoutInterfaceField()
	{
		//Arrange Stage

		//Creating Interface Class Parameters
		List<String> interfaceClassInterfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClassFields = new ArrayList<>();
		List<MyMethodNode> interfaceClassMethods = new ArrayList<>();

		//Creating the Interface Class
		MyClassNode interfaceClass = new MyClassNode("Pattern", "",
				interfaceClassInterfaces,
				interfaceClassFields,
				interfaceClassMethods,
				Opcodes.ACC_PUBLIC
		);

		//Creating Context Class Parameters
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();

		//Creating a Field which has the Interface Classes Type
		MyFieldNode interfaceField = new MyFieldNode("strategyPattern", "Pattern", 0);
		contextClassFields.add(interfaceField);

		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();

		//Creating a Method Call
		MyFieldInsnNode interfaceInit = new MyFieldInsnNode("strategyPattern", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interfaceInit);

		//Creating a Constructor with the Interface passed in through Dependency Injection
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "(LPattern;)V",
				contextConstructorInsn,
				contextConstructorlocalVariables
		);
		contextClassMethods.add(contextConstructor);

		LinkedList<MyAbstractInsnNode> contextMethodInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextMethodlocalVariables = new ArrayList<>();

		//Creating a method Instruction call to the interface to complete the strategy pattern
		MyMethodInsnNode methodInsn = new MyMethodInsnNode("methodCall", "Pattern", Opcodes.INVOKEVIRTUAL);
		contextMethodInsn.add(methodInsn);

		//Creating the method in Context that is supposed to call the Interfaces Method
		MyMethodNode contextMethod = new MyMethodNode("contextMethod", "()V",
				contextMethodInsn,
				contextMethodlocalVariables
		);

		contextClassMethods.add(contextMethod);

		//Creating of the context class which will hold the context call method
		MyClassNode contextClass = new MyClassNode("Strategy1", "",
				contextClassInterfaces,
				contextClassFields,
				contextClassMethods,
				Opcodes.ACC_PUBLIC
		);


		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(contextClass);

		String expected = "";

		//Act Stage

		String actual = checker.runCheck(classes);

		//Assert Stage

		assertEquals(actual, expected);
	}

	@Test
	void passSingleStrategyWithoutProperArgTypes()
	{
		//Arrange Stage

		//Creating Interface Class Parameters
		List<String> interfaceClassInterfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClassFields = new ArrayList<>();
		List<MyMethodNode> interfaceClassMethods = new ArrayList<>();

		//Creating the Interface Class
		MyClassNode interfaceClass = new MyClassNode("Pattern", "",
				interfaceClassInterfaces,
				interfaceClassFields,
				interfaceClassMethods,
				Opcodes.ACC_PUBLIC
		);

		//Creating Context Class Parameters
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();

		//Creating a Field which has the Interface Classes Type
		MyFieldNode interfaceField = new MyFieldNode("strategyPattern", "Pattern", 0);
		MyFieldNode badInterfaceField = new MyFieldNode("strategyPattern", "Putter", 0);

//		contextClassFields.add(interfaceField);
		contextClassFields.add(badInterfaceField);

		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();

		//Creating a Method Call
		MyFieldInsnNode interfaceInit = new MyFieldInsnNode("strategyPattern", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interfaceInit);

		//Creating a Constructor with the Interface passed in through Dependency Injection
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "(LPutter;)V",
				contextConstructorInsn,
				contextConstructorlocalVariables
		);
		contextClassMethods.add(contextConstructor);

		LinkedList<MyAbstractInsnNode> contextMethodInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextMethodlocalVariables = new ArrayList<>();

		//Creating a method Instruction call to the interface to complete the strategy pattern
		MyMethodInsnNode methodInsn = new MyMethodInsnNode("methodCall", "Pattern", Opcodes.INVOKEVIRTUAL);
		contextMethodInsn.add(methodInsn);

		//Creating the method in Context that is supposed to call the Interfaces Method
		MyMethodNode contextMethod = new MyMethodNode("contextMethod", "()V",
				contextMethodInsn,
				contextMethodlocalVariables
		);

		contextClassMethods.add(contextMethod);

		//Creating of the context class which will hold the context call method
		MyClassNode contextClass = new MyClassNode("Strategy1", "",
				contextClassInterfaces,
				contextClassFields,
				contextClassMethods,
				Opcodes.ACC_PUBLIC
		);


		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(contextClass);

		String expected = "";

		//Act Stage

		String actual = checker.runCheck(classes);

		//Assert Stage

		assertEquals(actual, expected);
	}

	@Test
	void passSingleStrategyWhoKnows()
	{
		//Arrange Stage

		//Creating Interface Class Parameters
		List<String> interfaceClassInterfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClassFields = new ArrayList<>();
		List<MyMethodNode> interfaceClassMethods = new ArrayList<>();

		//Creating the Interface Class
		MyClassNode interfaceClass = new MyClassNode("Pattern", "",
				interfaceClassInterfaces,
				interfaceClassFields,
				interfaceClassMethods,
				Opcodes.ACC_INTERFACE
		);

		MyClassNode interfaceClassPutter = new MyClassNode("Putter", "",
				interfaceClassInterfaces,
				interfaceClassFields,
				interfaceClassMethods,
				Opcodes.ACC_INTERFACE
		);

		//Creating Context Class Parameters
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();

		//Creating a Field which has the Interface Classes Type
		MyFieldNode interfaceField = new MyFieldNode("strategyPattern", "Pattern", 0);
		MyFieldNode badInterfaceField = new MyFieldNode("strategyPattern", "Putter", 0);

		contextClassFields.add(interfaceField);
		contextClassFields.add(badInterfaceField);



		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();

		//Creating a Method Call
		MyFieldInsnNode interfaceInit = new MyFieldInsnNode("strategyPattern", "", Opcodes.ACC_PUBLIC);
		MyFieldInsnNode interfaceInitBad = new MyFieldInsnNode("StrutPat", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interfaceInit);
		contextConstructorInsn.add(interfaceInitBad);

		//Creating a Constructor with the Interface passed in through Dependency Injection
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "(LPattern;LPutter;)V",
				contextConstructorInsn,
				contextConstructorlocalVariables
		);
		contextClassMethods.add(contextConstructor);

		LinkedList<MyAbstractInsnNode> contextMethodInsn = new LinkedList<>();
		LinkedList<MyAbstractInsnNode> contextMethodInsn1 = new LinkedList<>();
		List<MyLocalVariableNode> contextMethodlocalVariables = new ArrayList<>();
		List<MyLocalVariableNode> contextMethodLocalVariables1 = new ArrayList<>();

		//Creating a method Instruction call to the interface to complete the strategy pattern
		MyMethodInsnNode methodInsn = new MyMethodInsnNode("methodCall", "Pattern", Opcodes.INVOKEVIRTUAL);
		MyMethodInsnNode methodInsnNew = new MyMethodInsnNode("methodCall", "Putter", Opcodes.INVOKEVIRTUAL);
		contextMethodInsn.add(methodInsn);
		contextMethodInsn.add(methodInsnNew);

		//Creating the method in Context that is supposed to call the Interfaces Method
		MyMethodNode contextMethod = new MyMethodNode("contextMethod", "()V",
				contextMethodInsn,
				contextMethodlocalVariables
		);

		MyMethodNode contextMethod1 = new MyMethodNode("contextMethod1", "()V",
				contextMethodInsn1,
				contextMethodLocalVariables1
		);

		contextClassMethods.add(contextMethod);
		contextClassMethods.add(contextMethod1);

		//Creating of the context class which will hold the context call method
		MyClassNode contextClass = new MyClassNode("Strategy1", "",
				contextClassInterfaces,
				contextClassFields,
				contextClassMethods,
				Opcodes.ACC_PUBLIC
		);


		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(interfaceClassPutter);
		classes.add(contextClass);

		String expected = 		"\nStrategy Pattern Implementations: \n"
				+ "	Implemented in Strategy1"
				+ " class using interface Pattern"
				+ ", and invoked for the first time in method contextMethod\n"
				+ "	Implemented in Strategy1"
				+ " class using interface Putter"
				+ ", and invoked for the first time in method contextMethod\n";

		//Act Stage

		String actual = checker.runCheck(classes);

		//Assert Stage

		assertEquals(actual, expected);
	}

	@Test
	void passSingleStrategyWithoutVirtualInvocation()
	{
		//Arrange Stage

		//Creating Interface Class Parameters
		List<String> interfaceClassInterfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClassFields = new ArrayList<>();
		List<MyMethodNode> interfaceClassMethods = new ArrayList<>();

		//Creating the Interface Class
		MyClassNode interfaceClass = new MyClassNode("Pattern", "",
				interfaceClassInterfaces,
				interfaceClassFields,
				interfaceClassMethods,
				Opcodes.ACC_INTERFACE
		);

		MyClassNode interfaceClassPutter = new MyClassNode("Putter", "",
				interfaceClassInterfaces,
				interfaceClassFields,
				interfaceClassMethods,
				Opcodes.ACC_INTERFACE
		);

		//Creating Context Class Parameters
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();

		//Creating a Field which has the Interface Classes Type
		MyFieldNode interfaceField = new MyFieldNode("strategyPattern", "Pattern", 0);
		MyFieldNode badInterfaceField = new MyFieldNode("strategyPattern", "Putter", 0);

		contextClassFields.add(interfaceField);
		contextClassFields.add(badInterfaceField);

		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();

		//Creating a Method Call
		MyFieldInsnNode interfaceInit = new MyFieldInsnNode("strategyPattern", "", Opcodes.ACC_PUBLIC);
		MyFieldInsnNode interfaceInitBad = new MyFieldInsnNode("StrutPat", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interfaceInit);
		contextConstructorInsn.add(interfaceInitBad);

		//Creating a Constructor with the Interface passed in through Dependency Injection
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "(LPutter;LPattern;)V",
				contextConstructorInsn,
				contextConstructorlocalVariables
		);
		contextClassMethods.add(contextConstructor);

		LinkedList<MyAbstractInsnNode> contextMethodInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextMethodlocalVariables = new ArrayList<>();

		//Creating a method Instruction call to the interface to complete the strategy pattern
		MyMethodInsnNode methodInsn = new MyMethodInsnNode("methodCall", "Pattern", Opcodes.ACC_PUBLIC);
		MyMethodInsnNode methodInsnNew = new MyMethodInsnNode("methodCall", "Putter", Opcodes.ACC_PUBLIC);
		contextMethodInsn.add(methodInsn);
		contextMethodInsn.add(methodInsnNew);

		//Creating the method in Context that is supposed to call the Interfaces Method
		MyMethodNode contextMethod = new MyMethodNode("contextMethod", "()V",
				contextMethodInsn,
				contextMethodlocalVariables
		);

		contextClassMethods.add(contextMethod);

		//Creating of the context class which will hold the context call method
		MyClassNode contextClass = new MyClassNode("Strategy1", "",
				contextClassInterfaces,
				contextClassFields,
				contextClassMethods,
				Opcodes.ACC_PUBLIC
		);


		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(interfaceClassPutter);
		classes.add(contextClass);

		String expected = 		"\nStrategy Pattern Implementations: \n"
				+ "	Strategy pattern is nearly implemented in Strategy1"
				+ " using interface Pattern. To finish implementing strategy pattern, the function/functions "
				+ "called from the interface must be used\n"
				+ "	Strategy pattern is nearly implemented in Strategy1"
				+ " using interface Putter. To finish implementing strategy pattern, the function/functions "
				+ "called from the interface must be used\n";
		//Act Stage

		String actual = checker.runCheck(classes);

		//Assert Stage

		assertEquals(actual, expected);
	}
	@Test
	void passMultiStrategy()
	{
		//Arrange Stage
		
		//Creating First Interface Class Parameters
		List<String> interfaceClass1Interfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClass1Fields = new ArrayList<>();
		List<MyMethodNode> interfaceClass1Methods = new ArrayList<>();
		
		
		//Creating the First interface Class
		MyClassNode interface1Class = new MyClassNode("Pattern1", "", 
				interfaceClass1Interfaces, 
				interfaceClass1Fields, 
				interfaceClass1Methods,
				Opcodes.ACC_INTERFACE
				);
		
		//Creating Second Interface Class Parameters
		List<String> interfaceClass2Interfaces = new ArrayList<>();
		List<MyFieldNode> interfaceClass2Fields = new ArrayList<>();
		List<MyMethodNode> interfaceClass2Methods = new ArrayList<>();
		
		//Creating the First interface Class
		MyClassNode interface2Class = new MyClassNode("Pattern2", "", 
				interfaceClass2Interfaces, 
				interfaceClass2Fields, 
				interfaceClass2Methods,
				Opcodes.ACC_INTERFACE
				);
		
		//Creating context class parameters
		List<String> contextClassInterfaces = new ArrayList<>();
		List<MyFieldNode> contextClassFields = new ArrayList<>();
		List<MyMethodNode> contextClassMethods = new ArrayList<>();
		
		//Creating a Field which has Interface 1's Class Type
		MyFieldNode interface1Field = new MyFieldNode("strategyPattern1", "Pattern1", 0);
		contextClassFields.add(interface1Field);
		
		//Creating a Field which has Interface 2's Class Type
		MyFieldNode interface2Field = new MyFieldNode("strategyPattern2", "Pattern2", 0);
		contextClassFields.add(interface2Field);
		
		LinkedList<MyAbstractInsnNode> contextConstructorInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextConstructorlocalVariables = new ArrayList<>();
		
		//Creating a Method Call for Interface 1
		MyFieldInsnNode interface1Init = new MyFieldInsnNode("strategyPattern1", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interface1Init);
		
		//Creating a Method Call for Interface 2
		MyFieldInsnNode interface2Init = new MyFieldInsnNode("strategyPattern2", "", Opcodes.ACC_PUBLIC);
		contextConstructorInsn.add(interface2Init);
		
		//Creating a constructor for the context class which passes in Interface 1 and 2 through depedency injection
		MyMethodNode contextConstructor = new MyMethodNode("<init>", "(LPattern1;LPattern2;)V",
				contextConstructorInsn,
				contextConstructorlocalVariables
				);
		contextClassMethods.add(contextConstructor);
		
		LinkedList<MyAbstractInsnNode> contextMethodInsn = new LinkedList<>();
		List<MyLocalVariableNode> contextMethodlocalVariables = new ArrayList<>();
		
		//Creating the instruction method call for the first interface
		MyMethodInsnNode methodInsn1 = new MyMethodInsnNode("methodCall1", "Pattern1", Opcodes.INVOKEVIRTUAL);
		contextMethodInsn.add(methodInsn1);
		
		//Creating the instruction method call for the first interface
		MyMethodInsnNode methodInsn2 = new MyMethodInsnNode("methodCall2", "Pattern2", Opcodes.INVOKEVIRTUAL);
		contextMethodInsn.add(methodInsn2);
		
		//Creating the method in Context that is supposed to call the Interfaces Method
		MyMethodNode contextMethod = new MyMethodNode("contextMethod", "()V",
				contextMethodInsn,
				contextMethodlocalVariables
				);
		
		contextClassMethods.add(contextMethod);
		
		//Creating the context class which will call each interface method
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
		
		String expected = 		"\nStrategy Pattern Implementations: \n"
				+ "	Implemented in Strategy2"
				+ " class using interface Pattern1"
				+ ", and invoked for the first time in method contextMethod\n"
				+ "	Implemented in Strategy2"
				+ " class using interface Pattern2"
				+ ", and invoked for the first time in method contextMethod\n";
		
		//Act Stage
		
		String actual = checker.runCheck(classes);
		
		//Assert Stage
		
		assertEquals(actual, expected);
	}
}