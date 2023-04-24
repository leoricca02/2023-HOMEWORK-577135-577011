package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOSimulatorTest {
	private IOSimulator io;
	
	@BeforeEach
	void setUp() throws Exception {
		this.io = new IOSimulator();
	}

	@Test
	void testUnComando() {
		assertEquals("fine", new IOSimulator("fine").leggiRiga());
	}
	
	@Test
	void testDueComandi() {
		this.io=new IOSimulator();
		IOSimulator io = new IOSimulator("vai nord", "fine");
		assertEquals("vai nord", io.leggiRiga());
		assertEquals("fine", io.leggiRiga());
	}
	
	@Test
	void testNessunComando() {
		assertNull(new IOSimulator().leggiRiga());
	}
	
	@Test
	void testTroppeLetture() {
		IOSimulator io = new IOSimulator("fine");
		assertEquals("fine", io.leggiRiga());
		io.leggiRiga();
	}
}
