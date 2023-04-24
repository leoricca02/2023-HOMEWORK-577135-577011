package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabbrica;
	
	@BeforeEach
	 void setUp() {
		this.fabbrica = new FabbricaDiComandiFisarmonica();
	}

	@Test
	final void testCostruisciComandoAiuto() {
		assertEquals("aiuto", fabbrica.costruisciComando("aiuto").getNome());
	}
	@Test
	final void testCostruisciComandoFine() {
		assertEquals("fine", fabbrica.costruisciComando("fine").getNome());
	}
	@Test
	final void testCostruisciComandoGuarda() {
		assertEquals("guarda", fabbrica.costruisciComando("guarda").getNome());
	}
	@Test
	final void testCostruisciComandoNonValido() {
		assertEquals("nonValido", fabbrica.costruisciComando("nonValido").getNome());
	}
	@Test
	final void testCostruisciComandoPosa() {
		assertEquals("posa", fabbrica.costruisciComando("posa").getNome());
	}
	@Test
	final void testCostruisciComandoPrendi() {
		assertEquals("prendi", fabbrica.costruisciComando("prendi").getNome());
	}
	@Test
	final void testCostruisciComandVai() {
		assertEquals("vai", fabbrica.costruisciComando("vai").getNome());
	}
}
