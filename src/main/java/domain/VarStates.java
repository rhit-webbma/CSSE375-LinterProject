package domain;

import java.util.ArrayList;

import data_source.MyFieldInsnNode;
import data_source.MyVarInsnNode;

public class VarStates {
	
	public ArrayList<MyVarInsnNode> varStoring;
	public ArrayList<MyVarInsnNode> varLoading;
	
	public VarStates() {
		varStoring = new ArrayList<>();
		varLoading = new ArrayList<>();
	}
	
	public void empty() {
		varStoring.removeAll(varStoring);
		varLoading.removeAll(varLoading);
	}

}
