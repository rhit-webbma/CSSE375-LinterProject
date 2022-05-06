package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data_source.MyVarInsnNode;

class VarStatesTest {

	@Test
	void testEmpty() {
		VarStates states = new VarStates();
		states.varLoading.add(new MyVarInsnNode(0, 0));
		states.varStoring.add(new MyVarInsnNode(0, 0));
		states.empty();
		assertTrue(states.varLoading.isEmpty());
		assertTrue(states.varStoring.isEmpty());
	}

}
