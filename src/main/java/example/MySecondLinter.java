package example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class MySecondLinter {
	
	String[] toAnalyze = new String[1];
	
	public static void main(String[] args) throws IOException {
		for (String className : args) {
			ClassReader reader = new ClassReader(className);
			ClassNode classNode = new ClassNode();
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);
			
			checkAdapter(classNode);
			
			for (MethodNode method : classNode.methods) {

				System.out.println(method.name);
				printParamNames(method);
				System.out.println("  Method Length: " + getMethodLength(method));
				System.out.println();
			}
		}
	}
	
	
	public static void printParamNames(MethodNode method) {
		int numParams = Type.getArgumentTypes(method.desc).length;
		for (int i=0; i<numParams; i++) {
			System.out.println("  " +method.localVariables.get(i).name);
		}
	}
	
	public static int getMethodLength(MethodNode method) {
		InsnList instructions = method.instructions;
		ArrayList<Integer> lines = new ArrayList<Integer>();
		int methodLength = 0;
		for (AbstractInsnNode insn : instructions) {
			if (insn instanceof LineNumberNode) {
				LineNumberNode ln = (LineNumberNode) insn;
				int line = ln.line;
				if (!lines.contains(line)) {
					lines.add(line);
					methodLength++;
				}
			}
		}
		System.out.println(String.format("Method %s has %d instructions", method.name, getNumMethodInstructions(method.instructions)));
		return methodLength;
	}
	
	private static int getNumMethodInstructions(InsnList insns) {
		int count = 0;
		for (AbstractInsnNode insn : insns) {
			if (insn instanceof MethodInsnNode) {
				count++;
				MethodInsnNode mi = (MethodInsnNode) insn;
				System.out.println(mi.owner);
			}
		}
		return count;
	}
		
	private static void checkAdapter(ClassNode classNode) {
		System.out.println(classNode.name.toLowerCase().contains("adapter"));
		ArrayList<String> interfaceNames = getInterfaces(classNode);
		ArrayList<String> fieldTypes = getFieldTypes(classNode);
		System.out.println("Interfaces:");
		for (String intf : interfaceNames) {
			System.out.println("	" + intf);
		}
		System.out.println("Field types:");
		for (String field : fieldTypes) {
			System.out.println("	" + field);
		}
	}
	
	private static ArrayList<String> getInterfaces(ClassNode classNode) {
		ArrayList<String> out = new ArrayList<String>();
		for (String intf : classNode.interfaces) {
			String[] name = intf.split("/");
			out.add(name[name.length-1]);
		}
		return out;
	}
	
	private static ArrayList<String> getFieldTypes(ClassNode classNode) {
		ArrayList<String> out = new ArrayList<String>();
		for (FieldNode field : classNode.fields) {
			String[] name = field.desc.split("/|;");
			out.add(name[name.length-1]);
		}
		return out;
	}
	
	

}
