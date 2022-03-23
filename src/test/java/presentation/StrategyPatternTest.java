package presentation;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import data_source.MyAbstractInsnNode;
import data_source.MyClassNode;
import data_source.MyFieldInsnNode;
import data_source.MyFieldNode;
import data_source.MyMethodInsnNode;
import data_source.MyMethodNode;
import domain.StrategyPatternCheck;

class StrategyPatternTest {

	StrategyPatternCheck checker = new StrategyPatternCheck();
	
	@Test
	void strategyPatternFailNoCall() {
		
		MyClassNode interfaceClass = EasyMock.createMock(MyClassNode.class);
		interfaceClass.name = "pattern";
		
		EasyMock.expect(interfaceClass.isInterface()).andReturn(true);
		
		MyClassNode contextClass = EasyMock.createMock(MyClassNode.class);
		contextClass.name = "context";
		
		MyFieldNode interField = EasyMock.createMock(MyFieldNode.class);
		interField.name = "interfaceImp";
		interField.desc = "pattern";
		
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(interField);
		
		contextClass.fields = fields;
		
		MyMethodNode constructor = EasyMock.createMock(MyMethodNode.class);
		constructor.name = "<init>";
		
		ArrayList<String> constructorArgs = new ArrayList<>();
		constructorArgs.add("pattern");
		constructor.argTypeNames = constructorArgs;
		
		MyFieldInsnNode interfaceField = EasyMock.createMock(MyFieldInsnNode.class);
		interfaceField.name = "interfaceImp";
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>();
		
		instructionsList.add(interfaceField);
		
		ArrayList<MyMethodNode> constructorList = new ArrayList<>();
		
		constructorList.add(constructor);
//		constructorList.add(interfaceCallMethod);
		
		constructor.instructions = instructionsList;
		
		contextClass.methods = constructorList;
		
		MyMethodNode patternMethod = EasyMock.createMock(MyMethodNode.class);
		patternMethod.name = "patternMethod";

		MyMethodNode defaultMethod = EasyMock.createMock(MyMethodNode.class);
		defaultMethod.name = "defaultMethod";
		
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		methods.add(patternMethod);
		
//		pattern1Class.methods = methods;
		interfaceClass.methods = methods;
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		
		classes.add(interfaceClass);
//		classes.add(pattern1Class);
		classes.add(contextClass);
		
		
		EasyMock.replay(
				interfaceClass,
				patternMethod, 
				defaultMethod, 
				interfaceField,
				contextClass,
				interField,
				constructor);
		
		String expected = "\nStrategy Pattern Implementations: \n";
		expected +=  "	Strategy pattern is nearly implemented in " + "context"
		+ " using interface " + "pattern" + ". To finish implementing strategy pattern, the function/functions "
		+ "called from the interface must be used\n";
		
		
		
		assertEquals(expected, checker.runCheck(classes));
		
		EasyMock.verify(interfaceClass, 
				patternMethod, 
				defaultMethod, 
				interfaceField,
				contextClass,
				interField,
				constructor);
		
	}
	
	@Test
	void strategyPatternFailNoConstructor() {
		
		MyClassNode interfaceClass = EasyMock.createMock(MyClassNode.class);
		interfaceClass.name = "pattern";
		
		MyClassNode contextClass = EasyMock.createMock(MyClassNode.class);
		contextClass.name = "context";
		
		MyFieldNode interField = EasyMock.createMock(MyFieldNode.class);
		interField.name = "interfaceImp";
		interField.desc = "pattern";
		
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(interField);
		
		contextClass.fields = fields;
		
		MyFieldInsnNode interfaceField = EasyMock.createMock(MyFieldInsnNode.class);
		interfaceField.name = "interfaceImp";
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>();
		
		instructionsList.add(interfaceField);
		
		ArrayList<MyMethodNode> constructorList = new ArrayList<>();

		contextClass.methods = constructorList;
		
		MyMethodNode patternMethod = EasyMock.createMock(MyMethodNode.class);
		patternMethod.name = "patternMethod";

		MyMethodNode defaultMethod = EasyMock.createMock(MyMethodNode.class);
		defaultMethod.name = "defaultMethod";
		
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		methods.add(patternMethod);

		interfaceClass.methods = methods;
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		
		classes.add(interfaceClass);
		classes.add(contextClass);
		
		
		EasyMock.replay(
				interfaceClass,
				patternMethod, 
				defaultMethod, 
				interfaceField,
				contextClass,
				interField
				);
		
		String expected = "";
		
		
		assertEquals(expected, checker.runCheck(classes));
		
		EasyMock.verify(interfaceClass, 
				patternMethod, 
				defaultMethod, 
				interfaceField,
				contextClass,
				interField
				);
		
	}
	
	@Test
	void strategyPatternPass1Strategy() {
		
		MyClassNode interfaceClass = EasyMock.createMock(MyClassNode.class);
		interfaceClass.name = "pattern";
		
		EasyMock.expect(interfaceClass.isInterface()).andReturn(true);
		
		MyClassNode contextClass = EasyMock.createMock(MyClassNode.class);
		contextClass.name = "context";
		
		MyFieldNode interField = EasyMock.createMock(MyFieldNode.class);
		interField.name = "interfaceImp";
		interField.desc = "pattern";
		
		ArrayList<MyMethodNode> pattern1Methods = new ArrayList<>();
		LinkedList<MyAbstractInsnNode> pattern1InstList = new LinkedList<>();
		
		MyMethodInsnNode interfaceCall = EasyMock.createMock(MyMethodInsnNode.class);
		interfaceCall.owner = "pattern";
		
		EasyMock.expect(interfaceCall.isInvokeVirtual()).andReturn(true);
		
		pattern1InstList.add(interfaceCall);
		
		MyMethodNode interfaceCallMethod = EasyMock.createMock(MyMethodNode.class);
		interfaceCallMethod.instructions = pattern1InstList;
		interfaceCallMethod.name = "interfaceCallMethod";
		
		pattern1Methods.add(interfaceCallMethod);
		
		
		
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(interField);
		
		contextClass.fields = fields;
		
		MyMethodNode constructor = EasyMock.createMock(MyMethodNode.class);
		constructor.name = "<init>";
		
		ArrayList<String> constructorArgs = new ArrayList<>();
		constructorArgs.add("pattern");
		constructor.argTypeNames = constructorArgs;
		
		MyFieldInsnNode interfaceField = EasyMock.createMock(MyFieldInsnNode.class);
		interfaceField.name = "interfaceImp";
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>();
		
		instructionsList.add(interfaceField);
		
		ArrayList<MyMethodNode> constructorList = new ArrayList<>();
		
		constructorList.add(constructor);
		constructorList.add(interfaceCallMethod);
		
		constructor.instructions = instructionsList;
		
		contextClass.methods = constructorList;
		
		MyMethodNode patternMethod = EasyMock.createMock(MyMethodNode.class);
		patternMethod.name = "patternMethod";

		MyMethodNode defaultMethod = EasyMock.createMock(MyMethodNode.class);
		defaultMethod.name = "defaultMethod";
		
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		methods.add(patternMethod);
		
//		pattern1Class.methods = methods;
		interfaceClass.methods = methods;
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		
		classes.add(interfaceClass);
//		classes.add(pattern1Class);
		classes.add(contextClass);
		
		
		EasyMock.replay(
				interfaceClass,
				interfaceCall,
				interfaceCallMethod,
				patternMethod, 
				defaultMethod, 
				interfaceField,
				contextClass,
				interField,
				constructor);
		
		String expected = "\nStrategy Pattern Implementations: \n";
		expected +=  "	Implemented in " + "context" + " class using interface " + "pattern";
		expected += ", and invoked for the first time in method ";
		expected += "interfaceCallMethod\n";
		
		
		
		assertEquals(expected, checker.runCheck(classes));
		
		EasyMock.verify(interfaceClass, 
				patternMethod, 
				defaultMethod, 
				interfaceField,
				contextClass,
				interField,
				constructor);
		
	}
	
	@Test
	void strategyPatternPass2Strategy() {
		
		MyClassNode interfaceClass = EasyMock.createMock(MyClassNode.class);
		interfaceClass.name = "pattern";
		
		MyClassNode interface2Class = EasyMock.createMock(MyClassNode.class);
		interface2Class.name = "pattern2";
		
		EasyMock.expect(interfaceClass.isInterface()).andReturn(true);
		EasyMock.expect(interface2Class.isInterface()).andReturn(true);
		
		MyClassNode contextClass = EasyMock.createMock(MyClassNode.class);
		contextClass.name = "context";
		
		MyFieldNode interField = EasyMock.createMock(MyFieldNode.class);
		interField.name = "interfaceImp";
		interField.desc = "pattern";
		
		MyFieldNode inter2Field = EasyMock.createMock(MyFieldNode.class);
		inter2Field.name = "interface2Imp";
		inter2Field.desc = "pattern2";
		
		ArrayList<MyMethodNode> patternMethods = new ArrayList<>();
		LinkedList<MyAbstractInsnNode> patternInsnList = new LinkedList<>();
		
		MyMethodInsnNode interfaceCall = EasyMock.createMock(MyMethodInsnNode.class);
		interfaceCall.owner = "pattern";
		
		MyMethodInsnNode interface2Call = EasyMock.createMock(MyMethodInsnNode.class);
		interface2Call.owner = "pattern2";
		
		EasyMock.expect(interfaceCall.isInvokeVirtual()).andReturn(true);
		EasyMock.expect(interface2Call.isInvokeVirtual()).andReturn(true);
		
		patternInsnList.add(interfaceCall);
		patternInsnList.add(interface2Call);
		
		
		MyMethodNode interfaceCallMethod = EasyMock.createMock(MyMethodNode.class);
		interfaceCallMethod.instructions = patternInsnList;
		interfaceCallMethod.name = "interfaceCallMethod";
		
		patternMethods.add(interfaceCallMethod);
		
		
		
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(interField);
		fields.add(inter2Field);
		
		contextClass.fields = fields;
		
		MyMethodNode constructor = EasyMock.createMock(MyMethodNode.class);
		constructor.name = "<init>";
		
		ArrayList<String> constructorArgs = new ArrayList<>();
		constructorArgs.add("pattern");
		constructorArgs.add("pattern2");
		constructor.argTypeNames = constructorArgs;
		
		MyFieldInsnNode interfaceField = EasyMock.createMock(MyFieldInsnNode.class);
		interfaceField.name = "interfaceImp";
		
		MyFieldInsnNode interface2Field = EasyMock.createMock(MyFieldInsnNode.class);
		interface2Field.name = "interface2Imp";
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>();
		
		instructionsList.add(interfaceField);
		instructionsList.add(interface2Field);
		
		ArrayList<MyMethodNode> constructorList = new ArrayList<>();
		
		constructorList.add(constructor);
		constructorList.add(interfaceCallMethod);
		
		constructor.instructions = instructionsList;
		
		contextClass.methods = constructorList;
		
		ArrayList<String> interfaces = new ArrayList<String>();
		interfaces.add("pattern");
		
		MyMethodNode patternMethod = EasyMock.createMock(MyMethodNode.class);
		patternMethod.name = "patternMethod";

		MyMethodNode defaultMethod = EasyMock.createMock(MyMethodNode.class);
		defaultMethod.name = "defaultMethod";
		
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		methods.add(patternMethod);
		
		interfaceClass.methods = methods;
		interface2Class.methods = methods;
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		
		classes.add(interfaceClass);
		classes.add(interface2Class);
		classes.add(contextClass);
		
		
		EasyMock.replay(
				interfaceClass,
				interface2Class,
				interfaceCall,
				interface2Call,
				interfaceCallMethod,
				patternMethod, 
				defaultMethod, 
				interfaceField,
				contextClass,
				interField,
				inter2Field,
				constructor);
		
		String expected = "\nStrategy Pattern Implementations: \n";
		expected +=  "	Implemented in " + "context" + " class using interface " + "pattern";
		expected += ", and invoked for the first time in method ";
		expected += "interfaceCallMethod\n";
		expected +=  "	Implemented in " + "context" + " class using interface " + "pattern2";
		expected += ", and invoked for the first time in method ";
		expected += "interfaceCallMethod\n";
		
		
		
		assertEquals(expected, checker.runCheck(classes));
		
		EasyMock.verify(				
				interfaceClass,
				interface2Class,
				interfaceCall,
				interface2Call,
				interfaceCallMethod,
				patternMethod, 
				defaultMethod, 
				interfaceField,
				contextClass,
				interField,
				inter2Field,
				constructor);
		
	}

}
