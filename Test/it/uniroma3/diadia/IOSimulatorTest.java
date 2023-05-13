package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class IOSimulatorTest {
	private IOSimulator io;
	List<String> comandiLetti;
	
	@BeforeEach
	void setUp() throws Exception {
		this.comandiLetti = new ArrayList<String>();
	}

	@Test
	void testUnComando() {
		this.comandiLetti.add("fine");
		this.io = new IOSimulator(comandiLetti);
		assertEquals("fine", this.io.leggiRiga());
	}
	
	@Test
	void testDueComandi() {
		this.comandiLetti.add("vai nord");
		this.comandiLetti.add("fine");
		this.io = new IOSimulator(comandiLetti);
	//	IOSimulator io = new IOSimulator("vai nord", "fine");
		assertEquals("vai nord", io.leggiRiga());
		assertEquals("fine", io.leggiRiga());
	}
	
	@Test
	void testNessunComando() {
		this.io = new IOSimulator(comandiLetti);
		assertNull(new IOSimulator(comandiLetti).leggiRiga());
	}
	
	/*@Test
	void testTroppeLetture() {
		this.comandiLetti.add("fine");
		this.io = new IOSimulator(comandiLetti);
		//IOSimulator io = new IOSimulator("fine");
		assertEquals("fine", io.leggiRiga());
		io.leggiRiga();
	}*/
}
