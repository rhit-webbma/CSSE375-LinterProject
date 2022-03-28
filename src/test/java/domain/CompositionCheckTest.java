package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import data_source.MyClassNode;

class CompositionCheckTest {

	@Test
	void testCompositionGood() {
		MyClassNode node = EasyMock.createMock(MyClassNode.class);
		EasyMock.expect(node.getFullName()).andReturn("java/util/ArrayList");
		EasyMock.expect(node.getFullSuperName()).andReturn("java/lang/Object");
		CompositionCheck check = new CompositionCheck();
		EasyMock.replay(node);
		assertEquals("", check.checkClassComposition(node));
		EasyMock.verify(node);
	}
	
	@Test
	void testCompositionBad() {
		MyClassNode node = EasyMock.createMock(MyClassNode.class);
		EasyMock.expect(node.getFullName()).andReturn("datasource/test/ClassName");
		EasyMock.expect(node.getFullSuperName()).andReturn("domain/test/SuperName");
		CompositionCheck check = new CompositionCheck();
		EasyMock.replay(node);
		assertEquals("	Class ClassName inherits from user created class SuperName. Could composition be used instead? \n", check.checkClassComposition(node));
		EasyMock.verify(node);
	}

}
