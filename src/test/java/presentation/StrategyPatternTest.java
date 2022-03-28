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
		
		MyMethodNode patternMethod = EasyMock.createMock(MyMethodNode.class);
		patternMethod.name = "patternMethod";
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		methods.add(patternMethod);
		
		
		MyClassNode interfaceClass = EasyMock.createMock(MyClassNode.class);
		EasyMock.expect(interfaceClass.getCleanName()).andReturn("pattern");
		interfaceClass.methods = methods;
		EasyMock.expect(interfaceClass.isInterface()).andReturn(true);
		
		MyFieldNode interField = EasyMock.createMock(MyFieldNode.class);
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		interField.name = "interfaceImp";
		EasyMock.expect(interField.getCleanDesc()).andReturn("pattern");
		EasyMock.expect(interField.getCleanDesc()).andReturn("pattern");
		fields.add(interField);
		
		MyClassNode contextClass = EasyMock.createMock(MyClassNode.class);
		EasyMock.expect(contextClass.getCleanName()).andReturn("context");
		EasyMock.expect(contextClass.getCleanName()).andReturn("context");

		contextClass.fields = fields;
		
		MyMethodNode constructor = EasyMock.createMock(MyMethodNode.class);
		ArrayList<String> constructorArgs = new ArrayList<>();
		constructorArgs.add("pattern");
		constructor.name = "<init>";
		EasyMock.expect(constructor.getCleanArgTypes()).andReturn(constructorArgs);
		
		MyFieldInsnNode interfaceField = EasyMock.createMock(MyFieldInsnNode.class);
		interfaceField.name = "interfaceImp";
		
		LinkedList<MyAbstractInsnNode> constructorInsn = new LinkedList<MyAbstractInsnNode>();
		ArrayList<MyMethodNode> constructorList = new ArrayList<>();
		constructorInsn.add(interfaceField);
		constructorList.add(constructor);
		constructor.instructions = constructorInsn;
		contextClass.methods = constructorList;
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(contextClass);
		
		
		EasyMock.replay(
				interfaceClass,
				patternMethod,  
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
				interfaceField,
				contextClass,
				interField,
				constructor);
		
	}
	
	@Test
	void strategyPatternFailNoConstructor() {
		
		MyMethodNode patternMethod = EasyMock.createMock(MyMethodNode.class);
		patternMethod.name = "patternMethod";
		
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		methods.add(patternMethod);
		
		MyClassNode interfaceClass = EasyMock.createMock(MyClassNode.class);
		interfaceClass.methods = methods;
		
		MyFieldNode interField = EasyMock.createMock(MyFieldNode.class);
		interField.name = "interfaceImp";
		
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(interField);
		
		ArrayList<MyMethodNode> constructorList = new ArrayList<>();
		
		MyClassNode contextClass = EasyMock.createMock(MyClassNode.class);
		contextClass.fields = fields;
		contextClass.methods = constructorList;
		
		MyFieldInsnNode interfaceField = EasyMock.createMock(MyFieldInsnNode.class);
		interfaceField.name = "interfaceImp";
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>();
		instructionsList.add(interfaceField);
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		classes.add(interfaceClass);
		classes.add(contextClass);
		
		EasyMock.replay(
				interfaceClass,
				patternMethod, 
				interfaceField,
				contextClass,
				interField
				);
		
		String expected = "";
		
		assertEquals(expected, checker.runCheck(classes));
		
		EasyMock.verify(interfaceClass, 
				patternMethod, 
				interfaceField,
				contextClass,
				interField
				);
		
	}
	
	@Test
	void strategyPatternPass1Strategy() {
		
		MyMethodNode patternMethod = EasyMock.createMock(MyMethodNode.class);
		patternMethod.name = "patternMethod";
		
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		methods.add(patternMethod);
		
		MyClassNode interfaceClass = EasyMock.createMock(MyClassNode.class);
		EasyMock.expect(interfaceClass.getCleanName()).andReturn("pattern");
		interfaceClass.methods = methods;
		
		EasyMock.expect(interfaceClass.isInterface()).andReturn(true);
		
		MyFieldNode interField = EasyMock.createMock(MyFieldNode.class);
		interField.name = "interfaceImp";
		EasyMock.expect(interField.getCleanDesc()).andReturn("pattern");
		EasyMock.expect(interField.getCleanDesc()).andReturn("pattern");
		
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		fields.add(interField);
		
		MyClassNode contextClass = EasyMock.createMock(MyClassNode.class);
		EasyMock.expect(contextClass.getCleanName()).andReturn("context");
		EasyMock.expect(contextClass.getCleanName()).andReturn("context");
		contextClass.fields = fields;
		
		MyMethodInsnNode interfaceCall = EasyMock.createMock(MyMethodInsnNode.class);
		EasyMock.expect(interfaceCall.getCleanOwner()).andReturn("pattern");
		EasyMock.expect(interfaceCall.isInvokeVirtual()).andReturn(true);
		LinkedList<MyAbstractInsnNode> pattern1InstList = new LinkedList<>();
		pattern1InstList.add(interfaceCall);
		
		MyMethodNode interfaceCallMethod = EasyMock.createMock(MyMethodNode.class);
		interfaceCallMethod.instructions = pattern1InstList;
		interfaceCallMethod.name = "interfaceCallMethod";
		
		ArrayList<MyMethodNode> pattern1Methods = new ArrayList<>();
		pattern1Methods.add(interfaceCallMethod);
		

		
		MyMethodNode constructor = EasyMock.createMock(MyMethodNode.class);
		constructor.name = "<init>";
		ArrayList<String> constructorArgs = new ArrayList<>();
		constructorArgs.add("pattern");
		EasyMock.expect(constructor.getCleanArgTypes()).andReturn(constructorArgs);
		
		ArrayList<MyMethodNode> constructorList = new ArrayList<>();
		constructorList.add(constructor);
		constructorList.add(interfaceCallMethod);
		
		MyFieldInsnNode interfaceField = EasyMock.createMock(MyFieldInsnNode.class);
		interfaceField.name = "interfaceImp";
		
		LinkedList<MyAbstractInsnNode> instructionsList = new LinkedList<MyAbstractInsnNode>();
		instructionsList.add(interfaceField);
		constructor.instructions = instructionsList;
		contextClass.methods = constructorList;
		
		ArrayList<MyClassNode> classes = new ArrayList<>();
		
		classes.add(interfaceClass);
		classes.add(contextClass);
		
		
		EasyMock.replay(
				interfaceClass,
				interfaceCall,
				interfaceCallMethod,
				patternMethod, 
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
				interfaceField,
				contextClass,
				interField,
				constructor);
		
	}
	
	@Test
	void strategyPatternPass2Strategy() {
		
		MyClassNode interfaceClass = EasyMock.createMock(MyClassNode.class);
		EasyMock.expect(interfaceClass.getCleanName()).andReturn("pattern");
		EasyMock.expect(interfaceClass.getCleanName()).andReturn("pattern");
		
		MyClassNode interface2Class = EasyMock.createMock(MyClassNode.class);
		EasyMock.expect(interface2Class.getCleanName()).andReturn("pattern2");
		EasyMock.expect(interface2Class.getCleanName()).andReturn("pattern2");
		
		EasyMock.expect(interfaceClass.isInterface()).andReturn(true);
		EasyMock.expect(interface2Class.isInterface()).andReturn(true);
		
		MyClassNode contextClass = EasyMock.createMock(MyClassNode.class);
		EasyMock.expect(contextClass.getCleanName()).andReturn("context");
		EasyMock.expect(contextClass.getCleanName()).andReturn("context");
		EasyMock.expect(contextClass.getCleanName()).andReturn("context");
		EasyMock.expect(contextClass.getCleanName()).andReturn("context");
		
		MyFieldNode interField = EasyMock.createMock(MyFieldNode.class);
		interField.name = "interfaceImp";
		EasyMock.expect(interField.getCleanDesc()).andReturn("pattern");
		EasyMock.expect(interField.getCleanDesc()).andReturn("pattern");
		
		MyFieldNode inter2Field = EasyMock.createMock(MyFieldNode.class);
		inter2Field.name = "interface2Imp";
		EasyMock.expect(inter2Field.getCleanDesc()).andReturn("pattern2");
		EasyMock.expect(inter2Field.getCleanDesc()).andReturn("pattern2");
		
		ArrayList<MyMethodNode> patternMethods = new ArrayList<>();
		LinkedList<MyAbstractInsnNode> patternInsnList = new LinkedList<>();
		
		MyMethodInsnNode interfaceCall = EasyMock.createMock(MyMethodInsnNode.class);
		EasyMock.expect(interfaceCall.getCleanOwner()).andReturn("pattern");
		EasyMock.expect(interfaceCall.getCleanOwner()).andReturn("pattern");
		
		MyMethodInsnNode interface2Call = EasyMock.createMock(MyMethodInsnNode.class);
		EasyMock.expect(interface2Call.getCleanOwner()).andReturn("pattern2");
		
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
		EasyMock.expect(constructor.getCleanArgTypes()).andReturn(constructorArgs);
		
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
