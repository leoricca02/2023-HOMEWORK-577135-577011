package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class IOSimulatorTest {
	List<String> comandiDaLeggere;
	
	@BeforeEach
	void SetUp() {
		comandiDaLeggere = new ArrayList<String>();
	}

	@Test
	void testUnSoloComando() {
		this.comandiDaLeggere.add("fine");
		assertEquals("fine", new IOSimulator(this.comandiDaLeggere).leggiRiga());
	}
	
	@Test
	void testDueComandi() {
		this.comandiDaLeggere.add("vai Nord");
		this.comandiDaLeggere.add("fine");
		IOSimulator io = new IOSimulator(this.comandiDaLeggere);
		assertEquals("vai Nord", io.leggiRiga());
		assertEquals("fine", io.leggiRiga());
	}
	@Test
	void testNessunComando() {
		assertNull(new IOSimulator(this.comandiDaLeggere).leggiRiga());
	}
	@Test
	void testTroppeLetture() {
		this.comandiDaLeggere.add("fine");
		IOSimulator io = new IOSimulator(this.comandiDaLeggere);
		assertEquals("fine", io.leggiRiga());
		io.leggiRiga();
	}
}
