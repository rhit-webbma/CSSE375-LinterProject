package data_source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ASMReader {

	public static ArrayList<MyClassNode> generateClassNodes(ArrayList<String> testableClasses) {
		ArrayList<ClassNode> classes = new ArrayList<>();
		for (String className : testableClasses) {
			ClassReader reader = null;
			try {
				reader = new ClassReader(className);
			} catch (IOException e) {
				e.printStackTrace();
			}
			ClassNode classNode = new ClassNode();
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);

			classes.add(classNode);
		}

		return parseASM(classes);
	}

	private static ArrayList<MyClassNode> parseASM(ArrayList<ClassNode> classes) {
		ArrayList<MyClassNode> myClasses = new ArrayList<>();
		for (ClassNode node : classes) {
			ArrayList<MyFieldNode> fields = parseFields(node);
			ArrayList<MyMethodNode> methods = parseMethods(node);
			boolean isInterface = (node.access & Opcodes.ACC_INTERFACE) != 0;
			myClasses.add(new MyClassNode(node.name, node.superName, node.interfaces, fields, 
					methods, isInterface));
		}
		return myClasses;
	}

	private static ArrayList<MyFieldNode> parseFields(ClassNode node) {
		ArrayList<MyFieldNode> fields = new ArrayList<>();
		for (FieldNode field : node.fields) {
			boolean isStatic = (field.access & Opcodes.ACC_STATIC) != 0;
			boolean isFinal = (field.access & Opcodes.ACC_FINAL) != 0;
			fields.add(new MyFieldNode(field.name, field.desc, isStatic, isFinal));
		}
		return fields;
	}

	private static ArrayList<MyMethodNode> parseMethods(ClassNode node) {
		ArrayList<MyMethodNode> methods = new ArrayList<>();
		for (MethodNode method : node.methods) {
			LinkedList<MyAbstractInsnNode> instructions = parseInstructions(method);

			ArrayList<MyLocalVariableNode> localVariables = new ArrayList<>();
			if (method.localVariables != null) {
				for (LocalVariableNode var : method.localVariables) {
					localVariables.add(new MyLocalVariableNode(var.name, var.desc));
				}
			}
			
			ArrayList<String> argTypeNames = new ArrayList<>();
			Type[] argTypes = Type.getArgumentTypes(method.desc);
			for (Type type : argTypes) {
				argTypeNames.add(type.getInternalName());
			}

			methods.add(new MyMethodNode(method.name, method.desc, instructions, localVariables, argTypeNames));
		}
		return methods;
	}

	private static LinkedList<MyAbstractInsnNode> parseInstructions(MethodNode method) {
		LinkedList<MyAbstractInsnNode> instructions = new LinkedList<>();
		for (AbstractInsnNode insn : method.instructions) {
			if (insn.getType() == AbstractInsnNode.FIELD_INSN) {
				FieldInsnNode fInsn = (FieldInsnNode) insn;
				boolean isLoading = false;
				boolean isStoring = false;

				switch (fInsn.getOpcode()) {
				case Opcodes.GETSTATIC:
				case Opcodes.GETFIELD:
					isLoading = true;
					break;
				case Opcodes.PUTSTATIC:
				case Opcodes.PUTFIELD:
					isStoring = true;
					break;
				}
				instructions.add(new MyFieldInsnNode(fInsn.name, fInsn.owner, isLoading, isStoring));
			} else if (insn.getType() == AbstractInsnNode.METHOD_INSN) {
				MethodInsnNode mInsn = (MethodInsnNode) insn;
				boolean invokeVirtual = ((mInsn.getOpcode() & Opcodes.INVOKEVIRTUAL) != 0);
				instructions.add(new MyMethodInsnNode(mInsn.name, mInsn.owner, invokeVirtual));
			} else if (insn.getType() == AbstractInsnNode.VAR_INSN) {
				VarInsnNode vInsn = (VarInsnNode) insn;
				boolean isLoading = false;
				boolean isStoring = false;

				switch (vInsn.getOpcode()) {
				case Opcodes.ALOAD:
				case Opcodes.ILOAD:
				case Opcodes.LLOAD:
				case Opcodes.FLOAD:
				case Opcodes.DLOAD:
					isLoading = true;
					break;
				case Opcodes.ASTORE:
				case Opcodes.ISTORE:
				case Opcodes.LSTORE:
				case Opcodes.FSTORE:
				case Opcodes.DSTORE:
					isStoring = true;
					break;
				}
				instructions.add(new MyVarInsnNode(vInsn.var, isLoading, isStoring));
			} else if (insn.getType() == AbstractInsnNode.LINE) {
				LineNumberNode linsn = (LineNumberNode) insn;
				instructions.add(new MyLineNumberNode(linsn.line));
			}
		}
		return instructions;
	}

}
