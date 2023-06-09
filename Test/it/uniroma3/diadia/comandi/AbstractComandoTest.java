package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

class AbstractComandoTest {
	
	private Comando comandoVai;
	private Partita partita;
	private Labirinto labirinto;

	
	@BeforeEach
	void setUp() {
		comandoVai = new FakeComando("vai", "nord");
		partita = new Partita(labirinto);
	}
	
	@Test
	void testGetParametro() {
		comandoVai.esegui(partita);
		assertEquals("Norde", comandoVai.getParametro());
		assertEquals("VaiCorretto", comandoVai.getNome());
	}
	
	@Test
	void testSetParametro() {
		comandoVai.setParametro("sud");
		assertEquals("sud", comandoVai.getParametro());
	}
	

}
